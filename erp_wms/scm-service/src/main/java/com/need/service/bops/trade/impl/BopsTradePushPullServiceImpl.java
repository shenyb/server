package com.need.service.bops.trade.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.api.trade.TradeBaseDAO;
import com.need.dao.bops.trade.BopsTradePushPullDAO;
import com.need.dao.bops.wms.ESynEdiMessageDAO;
import com.need.domain.common.enums.PushReturnStatusEnum;
import com.need.domain.common.enums.TrackingCodeEnum;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.api.trade.TradeBasePO;
import com.need.domain.po.bops.trade.BopsTradePushPull;
import com.need.domain.po.bops.wms.ESynEdiMessage;
import com.need.domain.vo.trade.TradeVO;
import com.need.domain.vo.wms.TheOrderItemVo;
import com.need.domain.vo.wms.TheOrderVo;
import com.need.framework.utils.PropertiesUtil;
import com.need.kafka.services.producer.NeedProducer;
import com.need.service.bops.trade.BopsTradePushPullService;
import com.need.service.constant.Constants;
import com.need.utils.ExcelUtil;
import com.need.utils.StringUtil;

@Service
@Transactional
public class BopsTradePushPullServiceImpl implements BopsTradePushPullService {

	@Autowired
	private ESynEdiMessageDAO eSynEdiMessageDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private BopsTradePushPullDAO bopsTradePushPullDAO;
	@Autowired
	private TradeBaseDAO tradeBaseDAO;
	@Override
	public List<BopsTradePushPull> queryByBatchNo(String batchNo) {
		List<BopsTradePushPull> list = bopsTradePushPullDAO.getbyBatchNo(batchNo);
		for (BopsTradePushPull tradePushPull : list) {
			List<TradeVO> tradeList = tradeBaseDAO.getByUserTradeNo(tradePushPull.getTradeNo());
			tradePushPull.setPushReturnStatus(PushReturnStatusEnum.getDesc(tradePushPull.getPushReturnStatus()));
			tradePushPull.setRetrieveStatus(TrackingCodeEnum.getDesc(tradePushPull.getRetrieveStatus()));
			if (tradeList != null && tradeList.size() > 0) {
				tradePushPull.setPayChannel(tradeList.get(0).getPayChannel());
				tradePushPull.setAlipayRetrieveStatus(
						PushReturnStatusEnum.getDesc(tradeList.get(0).getAlipayRetrieveStatus()));
				tradePushPull.setWechatRetrieveStatus(
						PushReturnStatusEnum.getDesc(tradeList.get(0).getWechatRetrieveStatus()));
			}
		}
		return list;

	}

	@Override
	@Transactional("bops_txManager")
	public void createDeliverTrade() {
		// 要拿取发货信息的订单,使用清关状态标示是保税
		List<BopsTradePushPull> tradeList = bopsTradePushPullDAO.queryListToDeliver();
		if (tradeList == null) {
			return;
		}
		for (BopsTradePushPull bopsTradePushPull : tradeList) {
			TheOrderVo theOrderVo = new TheOrderVo(); //wms 需要的
			//根据推送的tradeNo 查到TradeBase 对象
			List<TradeBasePO> list = tradeBaseDAO.getByUserTradeNoFromWms(bopsTradePushPull.getTradeNo());
			TradeBasePO tradeBasePO = null;
			if(list!=null && list.size()>0){
				tradeBasePO = list.get(0);
			}
			if (tradeBasePO == null) {
				continue;
			}
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(tradeBasePO.getGoodsId());
			theOrderVo.setId(tradeBasePO.getUserTradeNo());
			theOrderVo.setExpWorkCode(bopsTradePushPull.getLogisticsNo()); //物流单号
			theOrderVo.setCarryId(bopsTradePushPull.getLogisticsNo()); //物流单号
			theOrderVo.setBillNo(tradeBasePO.getUserOrderNo()); //业务单号
			theOrderVo.setPayType(2L);             //付款类型
			theOrderVo.setWarehouseId(2L);         //库房号
			theOrderVo.setOrderType(1);            //订单类型
			theOrderVo.setConsignee(tradeBasePO.getReceiver()); //收件人
			theOrderVo.setInvoicePay(Long.parseLong(tradeBasePO.getPayPrice()+""));  //发票金额
			theOrderVo.setQty(1L);      //数量
			theOrderVo.setChannelCategory("-1");   //渠道类别
			theOrderVo.setExpId(2L); //物流商编码
			theOrderVo.setZipCode("");             //邮编
			theOrderVo.setRemark("");              //备注
			theOrderVo.setPhone(tradeBasePO.getTelephone()); //手机
			theOrderVo.setBusinessType("201");   //业务类型
			theOrderVo.setCreatedAt(tradeBasePO.getCreateTime()); //创建时间
			theOrderVo.setPointPay(0L);   //积分支付
			theOrderVo.setWrapPay(0L);     //包装费
			theOrderVo.setArrivePay(0L);  //  到货支付
			theOrderVo.setDeliverPointPay(0L); //现实积分支付
			theOrderVo.setOnlinePay(Long.parseLong(tradeBasePO.getPayPrice()+"")); //在线支付
			theOrderVo.setShowPriceInReceipt(1); //是否在发货单上显示价格
			theOrderVo.setSendTime(bopsTradePushPull.getUpdateTime());   //分配物流商 待发货时间
			theOrderVo.setIsPos("0");   //	是否pos刷卡
			theOrderVo.setIsBestlogtistic(0); //是否是百世物流的订单
			theOrderVo.setRprovince(StringUtil.getAddressCity(tradeBasePO.getLogisticsValue())); //省
			theOrderVo.setRarea(StringUtil.getAddressZone(tradeBasePO.getLogisticsValue())); //地区
			theOrderVo.setRcity(StringUtil.getAddressCity(tradeBasePO.getLogisticsValue())); //城市
			theOrderVo.setReceiveAddr(tradeBasePO.getAddress());
			//商品明细
			List<TheOrderItemVo> items = new ArrayList<TheOrderItemVo>();
			TheOrderItemVo theOrderItemVo= new TheOrderItemVo();
			if(goods!=null){
				theOrderItemVo.setSkuId(Long.parseLong(goods.getGoodsCode()));
				theOrderItemVo.setName(goods.getGoodsName());
			}
			theOrderItemVo.setQty(Long.parseLong(tradeBasePO.getBuyCount()+""));
			theOrderItemVo.setCreatedAt(tradeBasePO.getCreateTime());
			theOrderItemVo.setId(Long.parseLong(tradeBasePO.getUserOrderNo()));
			theOrderItemVo.setPrice(Long.parseLong(tradeBasePO.getBuyPrice()+""));
			items.add(theOrderItemVo);
			theOrderVo.setItemVos(items);
			theOrderVo.setTotalPay(Long.parseLong((tradeBasePO.getTotalPrice()+tradeBasePO.getTransportFee())+""));  //合计支付
			theOrderVo.setRealPay(Long.parseLong((tradeBasePO.getPayPrice()+tradeBasePO.getTransportFee())+""));   //现实支付
			theOrderVo.setHadPay(Long.parseLong(tradeBasePO.getPayPrice()+""));    //用户支付金额
			theOrderVo.setDeliverPay(Long.parseLong(tradeBasePO.getTransportFee()+""));
			 //将对象转成json
			String body = JSONObject.toJSONString(theOrderVo);
			//创建发送对象
			ESynEdiMessage eSynEdiMessage = new ESynEdiMessage(theOrderVo.getBillNo(), "201", body, theOrderVo.getWarehouseId());
			eSynEdiMessageDAO.insert(eSynEdiMessage);
			NeedProducer needProducer = NeedProducer.getInstance();
			String result =  JSONObject.toJSONString(eSynEdiMessage);
			String topic = PropertiesUtil.getProperty("/properties/erpToWmsTopic.properties", "201");
			boolean flag = needProducer.sendSync(topic, eSynEdiMessage.getId()+"", result);
			//更新发送表的状态2为发送成功
			if(flag){
				eSynEdiMessage.setStatus(2L);
				eSynEdiMessageDAO.updateByPrimaryKey(eSynEdiMessage);
				bopsTradePushPull.setDeliverPushStatus(Constants.TRUE);
			}else{
				eSynEdiMessage.setStatus(9L);
				eSynEdiMessageDAO.updateByPrimaryKey(eSynEdiMessage);
				bopsTradePushPull.setDeliverPushStatus(Constants.FALSE);
			}
			 bopsTradePushPullDAO.updateByTradeNo(bopsTradePushPull);
		}
	}

	/**
	 * 
	 * @author liuhongyang 2015年12月17日 上午10:28:54
	 * @Method: exportBatch 
	 * @Description: 批次详情列表导出
	 * @param list
	 * @return 
	 * @see com.need.service.bops.trade.BopsTradePushPullService#exportBatch(java.util.List)
	 */
	@Override
	public HSSFWorkbook exportBatch(List<BopsTradePushPull> list) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = ExcelUtil.createSheet(workbook, "批次详情列表数据");
		// 创建表头
		Font font = ExcelUtil.createFont(workbook, (short) 5, HSSFColor.BLACK.index, (short) 10);
		CellStyle cellStylec = ExcelUtil.createBorderCellStyle(workbook, HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				(short) 30, font);
		CellStyle cellStylecNum = ExcelUtil.createBorderCellStyle(workbook, HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				(short) 30, font);
		HSSFRow row = ExcelUtil.createRow(sheet, 0, 300);
		row.getSheet().setColumnWidth(0, 8000);
		row.getSheet().setColumnWidth(15, 8000);
		row.getSheet().setColumnWidth(17, 8000);
		row.getSheet().setColumnWidth(2, 12000);
		HSSFCell cell = ExcelUtil.createCell(row, 0, cellStylec);
		cell.setCellValue("批次号");
		cell = ExcelUtil.createCell(row, 1, cellStylec);
		cell.setCellValue("订单号");
		cell = ExcelUtil.createCell(row, 2, cellStylec);
		cell.setCellValue("运单号");
		cell = ExcelUtil.createCell(row, 3, cellStylec);
		cell.setCellValue("支付渠道");
		cell = ExcelUtil.createCell(row, 4, cellStylec);
		cell.setCellValue("E贸易推送状态");
		cell = ExcelUtil.createCell(row, 5, cellStylec);
		cell.setCellValue("支付单推送状态");
		cell = ExcelUtil.createCell(row, 6, cellStylec);
		cell.setCellValue("清关状态");
		// 插入实体数据
		BopsTradePushPull bopsTradePushPull =new BopsTradePushPull();
		if(list.size()>0){
			for (int i = 1; i < list.size()+1; i++) {
				bopsTradePushPull = list.get(i-1);
				row = ExcelUtil.createRow(sheet, i, 500);
				cell = ExcelUtil.createCell(row, 0, cellStylec);
				cell.setCellValue(bopsTradePushPull.getBatchNo());
				cell = ExcelUtil.createCell(row, 1, cellStylec);
				cell.setCellValue(bopsTradePushPull.getTradeNo());
				cell = ExcelUtil.createCell(row, 2, cellStylec);
				if(StringUtils.hasText(bopsTradePushPull.getLogisticsNo())){
					cell.setCellValue(bopsTradePushPull.getLogisticsNo());
				}else{
					cell.setCellValue("");
				}
				cell = ExcelUtil.createCell(row, 3, cellStylec);
				if(StringUtils.hasText(bopsTradePushPull.getPayChannel())){
					if("WECHAT".equals(bopsTradePushPull.getPayChannel())){
						cell.setCellValue("微信");
					}else if("ALIPAY".equals(bopsTradePushPull.getPayChannel())){
						cell.setCellValue("支付宝");
					}else{
						cell.setCellValue("");
					}
				}else{
					cell.setCellValue("");
				}
				cell = ExcelUtil.createCell(row, 4, cellStylecNum);
				if(StringUtils.hasText(bopsTradePushPull.getPushReturnStatus())){
					cell.setCellValue(bopsTradePushPull.getPushReturnStatus());
				}else{
					cell.setCellValue("");
				}
				cell = ExcelUtil.createCell(row, 5, cellStylec);
				if(StringUtils.hasText(bopsTradePushPull.getAlipayRetrieveStatus())){
					cell.setCellValue(bopsTradePushPull.getAlipayRetrieveStatus());
				}else{
					cell.setCellValue("");
				}
				cell = ExcelUtil.createCell(row, 6, cellStylecNum);
				if(StringUtils.hasText(bopsTradePushPull.getRetrieveStatus())){
					cell.setCellValue(bopsTradePushPull.getRetrieveStatus());
				}else{
					cell.setCellValue("");
				}
			}
		}
		return workbook;
	}
}

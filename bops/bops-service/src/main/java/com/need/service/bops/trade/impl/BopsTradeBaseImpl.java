package com.need.service.bops.trade.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.need.dao.api.trade.TradeBaseDAO;
import com.need.dao.api.trade.TradeRetrieveStatusRecordDAO;
import com.need.dao.bops.goods.BopsGoodsCategoriesDAO;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.trade.BopsTradeLogisticsDAO;
import com.need.dao.bops.trade.BopsTradePushPullDAO;
import com.need.dao.bops.trade.BopsTradeRefundDAO;
import com.need.domain.common.enums.PayChannelEnum;
import com.need.domain.common.enums.ServiceProviderEnum;
import com.need.domain.common.enums.TrackingCodeEnum;
import com.need.domain.common.enums.WarehouseTypeEnum;
import com.need.domain.po.api.trade.TradeBasePO;
import com.need.domain.po.api.trade.TradeRetrieveStatusRecord;
import com.need.domain.po.bops.goods.BopsGoodsCategoriesPO;
import com.need.domain.po.bops.trade.BopsTradeLogistics;
import com.need.domain.po.bops.trade.BopsTradeRefund;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.BopsGoodsVO;
import com.need.domain.vo.trade.BatchSendTradeParamVO;
import com.need.domain.vo.trade.CallcenterPageVO;
import com.need.domain.vo.trade.OrderExportParamVO;
import com.need.domain.vo.trade.OrderExportResultVO;
import com.need.domain.vo.trade.OrderExportVO;
import com.need.domain.vo.trade.TradeBaseParam;
import com.need.domain.vo.trade.TradeBaseVO;
import com.need.domain.vo.trade.TradeExportItemVO;
import com.need.domain.vo.trade.TradeExportVO;
import com.need.domain.vo.trade.TradeOrderParamsVO;
import com.need.domain.vo.trade.TradeRetrieveStatusRecordResultVO;
import com.need.domain.vo.trade.TradeSendVO;
import com.need.service.api.trade.PortalTradeService;
import com.need.service.bops.trade.BopsTradeBaseService;
import com.need.trade.enums.TradeStatus;
import com.need.utils.ExcelUtil;
import com.need.utils.StringUtil;

@Service
public class BopsTradeBaseImpl implements BopsTradeBaseService {
	private static final Logger logger = Logger.getLogger(BopsTradeBaseImpl.class);

	@Autowired
	private TradeBaseDAO portalTradeBaseDAO;
	@Autowired
	private BopsTradeLogisticsDAO bopsTradeLogisticsDAO;
	@Autowired
	private BopsTradeRefundDAO bopsTradeRefundDAO;
	@Autowired
	private PortalTradeService portalTradeService;
	@Autowired
	private BopsTradePushPullDAO bopsTradePushPullDAO;
	@Autowired
	private TradeRetrieveStatusRecordDAO tradeRetrieveStatusRecordDAO;
	@Autowired
	BopsGoodsCategoriesDAO bopsGoodsCategoriesDAO;	
//	@Autowired
//	TradeLogisticsInfoDAO  tradeLogisticsInfoDAO;
	@Autowired
	BopsGoodsDAO bopsGoodsDAO;
	@Override
	public List<TradeBaseVO> queryPageByParam(TradeBaseParam param) {
		// String mobileParam = param.getMobile();
		param.setTotal(portalTradeBaseDAO.getCountByPage(param));
		List<TradeBaseVO> tradeList = portalTradeBaseDAO.queryPageByParam(param);
		// modify by liuhongyang20181028 根据交易列表获取流转信息
		// List<TradeRetrieveStatusRecord> dataList =new
		// ArrayList<TradeRetrieveStatusRecord>();
		if (tradeList.size() > 0) {
			for (int i = 0; i < tradeList.size(); i++) {
				TradeBaseVO tradeBaseVO = tradeList.get(i);
				String userTradeNo = tradeBaseVO.getUserTradeNo();
				List<TradeRetrieveStatusRecordResultVO> tradeStatusList = tradeRetrieveStatusRecordDAO
						.selectByUserTradeNo(userTradeNo);
				TradeRetrieveStatusRecordResultVO tradeRetrieveStatusRecord = new TradeRetrieveStatusRecordResultVO();
				if (tradeStatusList != null && tradeStatusList.size() > 0) {
					tradeRetrieveStatusRecord = tradeStatusList.get(tradeStatusList.size() - 1);
					tradeBaseVO.setTrackingStatus(TrackingCodeEnum.getDesc(tradeRetrieveStatusRecord.getTrackingCode()));
					System.out.println("tradeBaseVO: " + tradeBaseVO);
				}
			}
		}
		return tradeList;
	}

	@Override
	public List<TradeBaseVO> queryPageByParamForCallCenter(TradeBaseParam param) {
		/** TODO Auto-generated method stub */
		param.setTotal(portalTradeBaseDAO.getCountByPage(param));
		List<TradeBaseVO> tradeList = portalTradeBaseDAO.queryPageByMobileParam(param);
		// modify by liuhongyang20181028 根据交易列表获取流转信息
		if (tradeList.size() > 0) {
			for (int i = 0; i < tradeList.size(); i++) {
				TradeBaseVO tradeBaseVO = tradeList.get(i);
				String userTradeNo = tradeBaseVO.getUserTradeNo();
				List<TradeRetrieveStatusRecordResultVO> tradeStatusList = tradeRetrieveStatusRecordDAO
						.selectByUserTradeNo(userTradeNo);
				TradeRetrieveStatusRecordResultVO tradeRetrieveStatusRecord = new TradeRetrieveStatusRecordResultVO();
				if (tradeStatusList != null && tradeStatusList.size() > 0) {
					tradeRetrieveStatusRecord = tradeStatusList.get(tradeStatusList.size() - 1);
					tradeBaseVO.setTrackingStatus(TrackingCodeEnum.getDesc(tradeRetrieveStatusRecord.getTrackingCode()));
					System.out.println("tradeBaseVO: " + tradeBaseVO);
				}
			}
		}
		return tradeList;
	}

	/**
	 * 
	 * @author peiboli 2015年11月6日 下午5:58:37
	 * @Method: queryPageByParamForCallCenter
	 * @Description: TODO弹屏list
	 * @param param
	 * @return
	 * @see com.need.service.bops.trade.BopsTradeBaseService#queryPageByParamForCallCenter(com.need.domain.vo.trade.TradeBaseParam)
	 */
	@Override
	public List<TradeBaseVO> queryPageCallCenterByMobile(CallcenterPageVO param) {
		/** TODO Auto-generated method stub */
		param.setTotal(portalTradeBaseDAO.getCountCallcenter(param));
		List<TradeBaseVO> tradeList = portalTradeBaseDAO.queryPageCallcenter(param);
		if (tradeList.size() > 0) {
			for (int i = 0; i < tradeList.size(); i++) {
				TradeBaseVO tradeBaseVO = tradeList.get(i);
				String userTradeNo = tradeBaseVO.getUserTradeNo();
				List<TradeRetrieveStatusRecordResultVO> tradeStatusList = tradeRetrieveStatusRecordDAO
						.selectByUserTradeNo(userTradeNo);
				TradeRetrieveStatusRecordResultVO tradeRetrieveStatusRecord = new TradeRetrieveStatusRecordResultVO();
				if (tradeStatusList != null && tradeStatusList.size() > 0) {
					tradeRetrieveStatusRecord = tradeStatusList.get(tradeStatusList.size() - 1);
					tradeBaseVO.setTrackingStatus(TrackingCodeEnum.getDesc(tradeRetrieveStatusRecord.getTrackingCode()));
					System.out.println("tradeBaseVO: " + tradeBaseVO);
				}
			}
		}
		return tradeList;
	}

	@Override
	@Transactional
	public Message send(BopsTradeLogistics po) {
		String tradeNo = po.getTradeNo();
		/**
		 * 1. 先变更前台的交易状态，根据结果决定是否插入发货。
		 */
		logger.info("into BopsTradeBaseImpl send: BopsTradeLogistics : " + po);
		
		Message message = portalTradeService.sendTrade(tradeNo);
		if (message.getCode() != Message.SUCCESS) {
			return message;
		}
		/**
		 * 2如果前台库成功,则插入发货表
		 */
		int result = bopsTradeLogisticsDAO.insert(po);
		/**
		 * 发送表同步成功以后，把物流信息，同步到trade_logistics_info
		 * 2015-12-07
		 * @author zhangmengbin
		 */
		/*TradeLogisticsInfo tradeLogisticsInfo = JSONObject.parseObject(po.getLogisticsInfo(), TradeLogisticsInfo.class);
		tradeLogisticsInfo.setTradeNo(po.getTradeNo());
		tradeLogisticsInfoDAO.insert(tradeLogisticsInfo);*/
		if (result <= 0) {
			return Message.error(7004);
		}
		return message;
	}

	/**
	 * 
	 * @author shenyb 2015年8月18日 下午6:08:53
	 * @Method: refund
	 * @Description:
	 * @param tradeNo
	 * @param refundAmmount
	 * @param memo
	 * @return
	 * @see com.need.bops.service.trade.BopsTradeBaseService#refund(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public Message refund(String tradeNo, int refundAmount, String memo, BopsUser user) {

		/**
		 * 1先执行前台的交易逻辑，根据结果决定是否处理后台的退款逻辑。
		 */
		Message message = portalTradeService.refund(tradeNo, refundAmount, memo);
		if (message.getCode() != Message.SUCCESS) {
			logger.error(String.format("refund error:tradeNo:%s,refundAmount:%s,memo:%s", tradeNo, refundAmount, memo));
			return message;
		}
		/**
		 * 2前台库修改成功后，插入后台退款表
		 */
		int updateCount = 0;
		List<TradeBasePO> tradeList = portalTradeBaseDAO.getByTradeNo(tradeNo);
		BopsTradeRefund tradeRefundPO = null;
		if (tradeList != null && tradeList.size() > 0) {
			String payChannel = tradeList.get(0).getPayChannel();
			tradeRefundPO = new BopsTradeRefund();
			tradeRefundPO.setTradeNo(tradeNo);
			tradeRefundPO.setOrderNo(tradeNo);
			tradeRefundPO.setRefundAmount(refundAmount);
			tradeRefundPO.setAuditorId(user.getUserId());
			tradeRefundPO.setPayChannel(payChannel == null ? "" : payChannel);
			tradeRefundPO.setPublisherId(user.getUserId());
			tradeRefundPO.setTradeStatus(TradeStatus.REFUND_SUCCESS.code);
			tradeRefundPO.setMemo(memo == null ? "" : memo);
			updateCount += bopsTradeRefundDAO.insert(tradeRefundPO);
		}

		return updateCount > 0 ? Message.success() : Message.error(7011);
	}

	/**
	 * 
	 * @author LXD 2015年8月25日 上午11:05:55
	 * @Method: exportTrade
	 * @Description: TODO 交易订单导出
	 * @param param
	 * @return
	 * @see com.need.bops.service.trade.BopsTradeBaseService#exportTrade(com.need.bops.web.controller.vo.trade.TradeBaseParam)
	 */
	@Override
	public HSSFWorkbook exportTrade(TradeBaseParam param) {
		if (StringUtils.hasText(param.getTradeTimeStart().trim())) {
			param.setTradeTimeStart(param.getTradeTimeStart().trim());
		}
		if (StringUtils.hasText(param.getTradeTimeEnd().trim())) {
			param.setTradeTimeEnd(param.getTradeTimeEnd().trim());
		}
		param.setTotal(portalTradeBaseDAO.queryCountTradeExportByPage(param));
		//List<TradeSendVO> bopsTradeList = portalTradeBaseDAO.queryByPage(param);
		//HSSFWorkbook workbook = getTradebook(bopsTradeList);
		List<TradeExportVO> bopsTradeList = portalTradeBaseDAO.queryTradeExportByPage(param);
		HSSFWorkbook workbook = getTradeExportWorkBook(bopsTradeList);
		return workbook;
	}

	/**
	 * @author xiehao 2015年9月16日 上午10:16:38
	 * @Method: exportOrder
	 * @Description: TODO 明细导出
	 * @param orderParamsVO
	 * @return
	 * @see com.need.bops.service.trade.BopsTradeBaseService#exportOrder(com.need.bops.web.controller.vo.trade.TradeOrderParamsVO)
	 */
	@Override
	public HSSFWorkbook exportOrder(TradeOrderParamsVO orderParamsVO) {
		if (StringUtils.hasText(orderParamsVO.getTradeTimeStart().trim())) {
			orderParamsVO.setTradeTimeStart(orderParamsVO.getTradeTimeStart().trim() + " 00:00:00");
		}
		if (StringUtils.hasText(orderParamsVO.getTradeTimeEnd().trim())) {
			orderParamsVO.setTradeTimeEnd(orderParamsVO.getTradeTimeEnd().trim() + " 23:59:59");
		}
		orderParamsVO.setTotal(portalTradeBaseDAO.queryCountTradeExportItemByPage(orderParamsVO));
		//List<OrderExportVO> orderList = portalTradeBaseDAO.queryOrderByPage(orderParamsVO);
		List<TradeExportItemVO> orderItemList = portalTradeBaseDAO.queryTradeExportItemByPage(orderParamsVO);
		HSSFWorkbook workbook = getOrderItemBook(orderItemList);
		return workbook;
	}

	/**
	 * @author xiehao 2015年9月16日 上午10:16:38
	 * @Method: exportOrder
	 * @Description: TODO 明细北京仓订单
	 * @param orderParamsVO
	 * @return
	 * @see com.need.bops.service.trade.BopsTradeBaseService#exportOrder(com.need.bops.web.controller.vo.trade.TradeOrderParamsVO)
	 */
	@Override
	public HSSFWorkbook exportOrder_v1_1(TradeOrderParamsVO orderParamsVO) {
		if (StringUtils.hasText(orderParamsVO.getTradeTimeStart().trim())) {
			orderParamsVO.setTradeTimeStart(orderParamsVO.getTradeTimeStart().trim() + "");
		}
		if (StringUtils.hasText(orderParamsVO.getTradeTimeEnd().trim())) {
			orderParamsVO.setTradeTimeEnd(orderParamsVO.getTradeTimeEnd().trim() + "");
		}
		orderParamsVO.setTotal(portalTradeBaseDAO.queryOrderCountByPage_v_1_1(orderParamsVO));
		List<OrderExportVO> orderList = portalTradeBaseDAO.queryOrderByPage_v_1_1(orderParamsVO);
		HSSFWorkbook workbook = getOrderbook(orderList);
		return workbook;
	}

	@Override
	public HSSFWorkbook exportTradeOrder_v1_1(OrderExportParamVO orderParamsVO) {
		// TODO Auto-generated method stub
		if ("ZHONG".equals(orderParamsVO.getRetrieveStatus())) {
			orderParamsVO.setRetrieveStatus("'" + TrackingCodeEnum.INTERIM.code + "', '"
					+ TrackingCodeEnum.DECLARATION.code + "','" + TrackingCodeEnum.INSPECTION_DETENTION.code + "','"
					+ TrackingCodeEnum.INSPECTION_PASS.code + "'");
		} else if ("FANGXING".equals(orderParamsVO.getRetrieveStatus())) {
			orderParamsVO
					.setRetrieveStatus("'" + TrackingCodeEnum.OK.code + "','" + TrackingCodeEnum.END.code + "'");
		} else {
			orderParamsVO.setRetrieveStatus("'" + TrackingCodeEnum.EXAM_PASS.code + "'");
		}
		if (StringUtils.hasText(orderParamsVO.getTradeTimeStart().trim())) {
			orderParamsVO.setTradeTimeStart(orderParamsVO.getTradeTimeStart().trim() + "");

		}
		if (StringUtils.hasText(orderParamsVO.getTradeTimeEnd().trim())) {
			orderParamsVO.setTradeTimeEnd(orderParamsVO.getTradeTimeEnd().trim() + "");
		}
		orderParamsVO.setTotal(portalTradeBaseDAO.countTradeExport(orderParamsVO));
		List<OrderExportResultVO> orderExportList = portalTradeBaseDAO.queryTradeExport(orderParamsVO);
		List<String> orderNoList = bopsTradePushPullDAO
				.queryOrderNosByRetrieveStatus(orderParamsVO.getRetrieveStatus());
		orderExportList.retainAll(orderNoList);
		for (OrderExportResultVO orderExport : orderExportList) {
			String logisticsValue[] = orderExport.getLogisticsValue().split("-");
			orderExport.setCountry("中国");
			orderExport.setProvince(logisticsValue[0]);
			orderExport.setCity(logisticsValue[1]);
			orderExport.setArea(logisticsValue[2]);
			orderExport.setGoodsInfo("【1】" + orderExport.getGoodsName() + "\n" + "(商品条码: "
					+ orderExport.getGoodsBarcode() + ")\n" + "(数量:" + orderExport.getBuyCount() + ")");

			String logisticsNo = bopsTradePushPullDAO.getlogisticsNo(orderExport.getUserOrderNo());
			orderExport.setLogisticsNo(logisticsNo);
		}

		return exportZhengzhouExcel(orderExportList, orderParamsVO);
	}

	private HSSFWorkbook exportZhengzhouExcel(List<OrderExportResultVO> data, OrderExportParamVO orderParamsVO) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		HSSFSheet sheet = ExcelUtil.createSheet(workbook, "订单数据");
		// 创建表头
		Font font = ExcelUtil.createFont(workbook, (short) 5, HSSFColor.BLACK.index, (short) 10);
		CellStyle cellStylec = ExcelUtil.createBorderCellStyle(workbook, HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				(short) 30, font);
		HSSFRow row = ExcelUtil.createRow(sheet, 0, 500);
		row.getSheet().setColumnWidth(0, 8000);
		row.getSheet().setColumnWidth(1, 5000);
		row.getSheet().setColumnWidth(2, 3000);
		row.getSheet().setColumnWidth(3, 5000);
		row.getSheet().setColumnWidth(4, 5000);
		row.getSheet().setColumnWidth(12, 8000);
		row.getSheet().setColumnWidth(21, 7000);
		row.getSheet().setColumnWidth(22, 10000);
		row.getSheet().setColumnWidth(29, 7000);
		row.getSheet().setColumnWidth(30, 5000);
		HSSFCell cell = ExcelUtil.createCell(row, 0, cellStylec);
		cell.setCellValue("订单号");
		cell = ExcelUtil.createCell(row, 1, cellStylec);
		cell.setCellValue("买家昵称");
		cell = ExcelUtil.createCell(row, 2, cellStylec);
		cell.setCellValue("买家邮箱");
		cell = ExcelUtil.createCell(row, 3, cellStylec);
		cell.setCellValue("拍单时间");
		cell = ExcelUtil.createCell(row, 4, cellStylec);
		cell.setCellValue("付款时间");
		cell = ExcelUtil.createCell(row, 5, cellStylec);
		cell.setCellValue("产品总金额");
		cell = ExcelUtil.createCell(row, 6, cellStylec);
		cell.setCellValue("订单金额");
		cell = ExcelUtil.createCell(row, 7, cellStylec);
		cell.setCellValue("优惠金额");
		cell = ExcelUtil.createCell(row, 8, cellStylec);
		cell.setCellValue("运费");
		cell = ExcelUtil.createCell(row, 9, cellStylec);
		cell.setCellValue("货到付款服务费");
		cell = ExcelUtil.createCell(row, 10, cellStylec);
		cell.setCellValue("订单备注");
		cell = ExcelUtil.createCell(row, 11, cellStylec);
		cell.setCellValue("买家留言");
		cell = ExcelUtil.createCell(row, 12, cellStylec);
		cell.setCellValue("收货地址");
		cell = ExcelUtil.createCell(row, 13, cellStylec);
		cell.setCellValue("收货人名称");
		cell = ExcelUtil.createCell(row, 14, cellStylec);
		cell.setCellValue("收货国家");
		cell = ExcelUtil.createCell(row, 15, cellStylec);
		cell.setCellValue("州/省");
		cell = ExcelUtil.createCell(row, 16, cellStylec);
		cell.setCellValue("城市");
		cell = ExcelUtil.createCell(row, 17, cellStylec);
		cell.setCellValue("区");
		cell = ExcelUtil.createCell(row, 18, cellStylec);
		cell.setCellValue("邮编");
		cell = ExcelUtil.createCell(row, 19, cellStylec);
		cell.setCellValue("联系电话");
		cell = ExcelUtil.createCell(row, 20, cellStylec);
		cell.setCellValue("手机");
		cell = ExcelUtil.createCell(row, 21, cellStylec);
		cell.setCellValue("买家选择物流");
		cell = ExcelUtil.createCell(row, 22, cellStylec);
		cell.setCellValue("产品信息");
		cell = ExcelUtil.createCell(row, 23, cellStylec);
		cell.setCellValue("最晚发货时间");
		cell = ExcelUtil.createCell(row, 24, cellStylec);
		cell.setCellValue("海外订单");
		cell = ExcelUtil.createCell(row, 25, cellStylec);
		cell.setCellValue("是否货到付款");
		cell = ExcelUtil.createCell(row, 26, cellStylec);
		cell.setCellValue("是否已发货");
		cell = ExcelUtil.createCell(row, 27, cellStylec);
		cell.setCellValue("发货快递单号");
		cell = ExcelUtil.createCell(row, 28, cellStylec);
		cell.setCellValue("货主Id");
		cell = ExcelUtil.createCell(row, 29, cellStylec);
		cell.setCellValue("货主名称");
		cell = ExcelUtil.createCell(row, 30, cellStylec);
		cell.setCellValue("是否匹配促销模板");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// 插入实体数据
		OrderExportResultVO po = null;
		int size = 0;
		if (orderParamsVO.getPageSize().intValue() == 100) {
			if (data.size() > 100) {
				size = 100;
			} else {
				size = data.size() + 1;
			}
		} else {
			size = data.size() + 1;
		}
		for (int i = 1; i < size; i++) {
			po = data.get(i - 1);
			row = ExcelUtil.createRow(sheet, i, 900);
			cell = ExcelUtil.createCell(row, 0, cellStylec);
			cell.setCellValue(po.getUserTradeNo());
			cell = ExcelUtil.createCell(row, 1, cellStylec);
			cell.setCellValue(po.getNickName());
			cell = ExcelUtil.createCell(row, 2, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 3, cellStylec);
			cell.setCellValue(sdf.format(po.getCreateTime()));// 拍单时间
			cell = ExcelUtil.createCell(row, 4, cellStylec);
			cell.setCellValue(sdf.format(po.getPayTime()));// 付款时间
			cell = ExcelUtil.createCell(row, 5, cellStylec);
			cell.setCellValue(po.getPayPrice() + po.getYouhuiquanValue());// 产品总金额
			cell = ExcelUtil.createCell(row, 6, cellStylec);
			cell.setCellValue(po.getPayPrice());// 支付金额金额
			cell = ExcelUtil.createCell(row, 7, cellStylec);
			cell.setCellValue(po.getYouhuiquanValue());// 优惠金额
			cell = ExcelUtil.createCell(row, 8, cellStylec);
			cell.setCellValue(0D);// 运费
			cell = ExcelUtil.createCell(row, 9, cellStylec);
			cell.setCellValue(0.0D);// 货到付款服务费
			cell = ExcelUtil.createCell(row, 10, cellStylec);
			cell.setCellValue(" ");// 订单备注
			cell = ExcelUtil.createCell(row, 11, cellStylec);
			cell.setCellValue(" ");// 买家留言
			cell = ExcelUtil.createCell(row, 12, cellStylec);
			cell.setCellValue(po.getAddress());
			cell = ExcelUtil.createCell(row, 13, cellStylec);
			cell.setCellValue(po.getReceiver());
			cell = ExcelUtil.createCell(row, 14, cellStylec);
			cell.setCellValue(po.getCountry());
			cell = ExcelUtil.createCell(row, 15, cellStylec);
			cell.setCellValue(po.getProvince());
			cell = ExcelUtil.createCell(row, 16, cellStylec);
			cell.setCellValue(po.getCity());
			cell = ExcelUtil.createCell(row, 17, cellStylec);
			cell.setCellValue(po.getArea());
			cell = ExcelUtil.createCell(row, 18, cellStylec);
			cell.setCellValue(" ");// 邮编
			cell = ExcelUtil.createCell(row, 19, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 20, cellStylec);
			cell.setCellValue(po.getTelephone());
			cell = ExcelUtil.createCell(row, 21, cellStylec);
			cell.setCellValue("河南省圆通速递有限公司");// 买家选择物流
			cell = ExcelUtil.createCell(row, 22, cellStylec);
			cell.getCellStyle().setWrapText(true);// 设置自动换行
			cell.setCellValue(po.getGoodsInfo());// 产品信息
			cell = ExcelUtil.createCell(row, 23, cellStylec);
			cell.setCellValue(" ");// 最晚发货时间
			cell = ExcelUtil.createCell(row, 24, cellStylec);
			cell.setCellValue(0L);// 海外订单,1表示为海外订单，0或其它为非海外订单
			cell = ExcelUtil.createCell(row, 25, cellStylec);
			cell.setCellValue("否");// 是否货到付款
			cell = ExcelUtil.createCell(row, 26, cellStylec);
			cell.setCellValue(0L);// 是否已发货,1表示为已发货，0或其它为未发货
			cell = ExcelUtil.createCell(row, 27, cellStylec);
			cell.setCellValue(po.getLogisticsNo());
			cell = ExcelUtil.createCell(row, 28, cellStylec);
			cell.setCellValue("10000");// 货主Id
			cell = ExcelUtil.createCell(row, 29, cellStylec);
			cell.setCellValue("稻田（北京）科技有限公司");// 货主名称
			cell = ExcelUtil.createCell(row, 30, cellStylec);
			cell.setCellValue(0L);// 是否匹配促销模板，1表示为匹配促销模板，0或其它为不匹配促销模板
		}

		return workbook;
	}
	public HSSFWorkbook getTradeExportWorkBook(List<TradeExportVO> data){
		HSSFWorkbook workbook = new HSSFWorkbook();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HSSFSheet sheet = ExcelUtil.createSheet(workbook, "1-sheet");
		// 创建表头
		Font font = ExcelUtil.createFont(workbook, (short) 5, HSSFColor.BLACK.index, (short) 10);
		CellStyle cellStylec = ExcelUtil.createBorderCellStyle(workbook, HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				(short) 30, font);
		HSSFRow row = ExcelUtil.createRow(sheet, 0, 500);
		row.getSheet().setColumnWidth(0, 7000);
		row.getSheet().setColumnWidth(8, 8000);
		row.getSheet().setColumnWidth(9, 8000);
		row.getSheet().setColumnWidth(16, 8000);
		row.getSheet().setColumnWidth(17, 12000);
		HSSFCell cell = ExcelUtil.createCell(row, 0, cellStylec);
		cell.setCellValue("订单号");
		cell = ExcelUtil.createCell(row, 1, cellStylec);
		cell.setCellValue("支付渠道");
		cell = ExcelUtil.createCell(row, 2, cellStylec);
		cell.setCellValue("应支付总价");
		cell = ExcelUtil.createCell(row, 3, cellStylec);
		cell.setCellValue("实际支付总价");
		cell = ExcelUtil.createCell(row, 4, cellStylec);
		cell.setCellValue("商品总价");
		cell = ExcelUtil.createCell(row, 5, cellStylec);
		cell.setCellValue("优惠金额");
		cell = ExcelUtil.createCell(row, 6, cellStylec);
		cell.setCellValue("运费");
		cell = ExcelUtil.createCell(row, 7, cellStylec);
		cell.setCellValue("交易状态");
		cell = ExcelUtil.createCell(row, 8, cellStylec);
		cell.setCellValue("交易时间");
		cell = ExcelUtil.createCell(row, 9, cellStylec);
		cell.setCellValue("支付时间");
		cell = ExcelUtil.createCell(row, 10, cellStylec);
		cell.setCellValue("订单仓库");
		cell = ExcelUtil.createCell(row, 11, cellStylec);
		cell.setCellValue("注册用户ID");
		cell = ExcelUtil.createCell(row, 12, cellStylec);
		cell.setCellValue("买家名");
		cell = ExcelUtil.createCell(row, 13, cellStylec);
		cell.setCellValue("买家手机号");
		cell = ExcelUtil.createCell(row, 14, cellStylec);
		cell.setCellValue("收货人姓名");
		cell = ExcelUtil.createCell(row, 15, cellStylec);
		cell.setCellValue("收件人手机号");
		cell = ExcelUtil.createCell(row, 16, cellStylec);
		cell.setCellValue("收货区域");
		cell = ExcelUtil.createCell(row, 17, cellStylec);
		cell.setCellValue("收货地址");
		cell = ExcelUtil.createCell(row, 18, cellStylec);
		cell.setCellValue("快递公司");
		cell = ExcelUtil.createCell(row, 19, cellStylec);
		cell.setCellValue("快递单号");
		cell = ExcelUtil.createCell(row, 20, cellStylec);
		cell.setCellValue("流转状态");
//		List<BopsGoodsCategoriesPO> categoryList = bopsGoodsCategoriesDAO.queryAll();
		List<BopsTradeLogistics> list = bopsTradeLogisticsDAO.queryAllBopsTradeLogistics();
		List<TradeRetrieveStatusRecord> records=tradeRetrieveStatusRecordDAO.getAll();
		// 插入实体数据
		TradeExportVO po = null;
		for (int i = 1; i < data.size() + 1; i++) {
			po = data.get(i - 1);
			po=getBopsTradeLogistics(list,po);
			po=getTrackingStatus(records,po);
			row = ExcelUtil.createRow(sheet, i, 500);
			cell = ExcelUtil.createCell(row, 0, cellStylec);
			cell.setCellValue(po.getUserTradeNo());
			cell = ExcelUtil.createCell(row, 1, cellStylec);
			cell.setCellValue(po.getPayChannel()==null?"":PayChannelEnum.getMessage(po.getPayChannel()));
			cell = ExcelUtil.createCell(row, 2, cellStylec);
			cell.setCellValue(po.getTotalPrice().doubleValue()/100);
			cell = ExcelUtil.createCell(row, 3, cellStylec);
			cell.setCellValue(po.getPayPrice().doubleValue()/100);
			cell = ExcelUtil.createCell(row, 4, cellStylec);
			cell.setCellValue(po.getGoodsPrice().doubleValue()/100);
			cell = ExcelUtil.createCell(row,5, cellStylec);
			cell.setCellValue(po.getDiscountAmount().doubleValue()/100);
			cell = ExcelUtil.createCell(row, 6, cellStylec);
			cell.setCellValue(po.getTransportFee().doubleValue()/100);
			
			cell = ExcelUtil.createCell(row, 7, cellStylec);
			cell.setCellValue(TradeStatus.getDesc(po.getTradeStatus()));
			cell = ExcelUtil.createCell(row, 8, cellStylec);
			cell.setCellValue(po.getCreateTime()==null?"":sf.format(po.getCreateTime()));
			cell = ExcelUtil.createCell(row, 9, cellStylec);
			cell.setCellValue(po.getPayTime()==null?"":sf.format(po.getPayTime()));
			cell = ExcelUtil.createCell(row, 10, cellStylec);
			cell.setCellValue(WarehouseTypeEnum.getDesc(po.getWarehouseType()));
			cell = ExcelUtil.createCell(row, 11, cellStylec);
			cell.setCellValue(po.getBuyerId());
			cell = ExcelUtil.createCell(row, 12, cellStylec);
			cell.setCellValue(po.getNickName());
			cell = ExcelUtil.createCell(row, 13, cellStylec);
			cell.setCellValue(po.getMobile());
			cell = ExcelUtil.createCell(row, 14, cellStylec);
			cell.setCellValue(po.getReceiver());
			cell = ExcelUtil.createCell(row, 15, cellStylec);
			cell.setCellValue(po.getTelephone());
			cell = ExcelUtil.createCell(row, 16, cellStylec);
			cell.setCellValue(po.getLogisticsValue()==null?"":po.getLogisticsValue());
			cell = ExcelUtil.createCell(row, 17, cellStylec);
			cell.setCellValue(po.getAddress());
			cell = ExcelUtil.createCell(row, 18, cellStylec);
			cell.setCellValue(po.getLogisticsNums()==null?"":po.getLogisticsNums());
			cell = ExcelUtil.createCell(row, 19, cellStylec);
			cell.setCellValue(po.getLogisticsServiceId()==null?"":po.getLogisticsServiceId());
			cell = ExcelUtil.createCell(row, 20, cellStylec);
			cell.setCellValue(TrackingCodeEnum.getDesc(po.getTrackingStatus()==null?"":po.getTrackingStatus()));
			
		}
		return workbook;
	}
	public TradeExportVO getTrackingStatus(List<TradeRetrieveStatusRecord> list,TradeExportVO tradeExportVO){
		if(list!=null && list.size()>0){
			for(TradeRetrieveStatusRecord record :list){
				if(record.getTradeNo().equals(tradeExportVO.getUserTradeNo())){
					tradeExportVO.setTrackingStatus(record.getTrackingCode());
					break;
				}
			}
		}
		return tradeExportVO;
		
	}
	/**
	 * 获取交易的物流信息
	 * @param list
	 * @param tradeExportVO
	 * @return
	 */
	public TradeExportVO getBopsTradeLogistics(List<BopsTradeLogistics> list,TradeExportVO tradeExportVO){
		if(list!=null && list.size()>0){
			for(BopsTradeLogistics bopsTradeLogistics :list){
				if(!StringUtil.isBlank(bopsTradeLogistics.getLogisticsInfo().toString())){
					if(bopsTradeLogistics.getTradeNo().equals(tradeExportVO.getTradeNo())){
						String info =bopsTradeLogistics.getLogisticsInfo();
						if(info.contains("[")){
							info = bopsTradeLogistics.getLogisticsInfo().replace("[","").replace("]","");
						}
						JSONObject tradeLogistics = JSONObject.parseObject(info);
						tradeExportVO.setLogisticsServiceId(tradeLogistics.getString("logisticsNums"));
						tradeExportVO.setLogisticsNums(ServiceProviderEnum.getDesc(tradeLogistics.getString("logisticsServiceId")));
						break;
					}
				}
			}
		}
		return tradeExportVO;
	}
	public HSSFWorkbook getTradebook(List<TradeSendVO> data) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		HSSFSheet sheet = ExcelUtil.createSheet(workbook, "1-sheet");
		// 创建表头
		Font font = ExcelUtil.createFont(workbook, (short) 5, HSSFColor.BLACK.index, (short) 10);
		CellStyle cellStylec = ExcelUtil.createBorderCellStyle(workbook, HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				(short) 30, font);
		HSSFRow row = ExcelUtil.createRow(sheet, 0, 500);
		row.getSheet().setColumnWidth(0, 7000);
		row.getSheet().setColumnWidth(2, 7000);
		row.getSheet().setColumnWidth(3, 8000);
		HSSFCell cell = ExcelUtil.createCell(row, 0, cellStylec);
		cell.setCellValue("订单号");
		cell = ExcelUtil.createCell(row, 1, cellStylec);
		cell.setCellValue("商品编码");
		cell = ExcelUtil.createCell(row, 2, cellStylec);
		cell.setCellValue("商品名称");
		cell = ExcelUtil.createCell(row, 3, cellStylec);
		cell.setCellValue("国际条形码");
		cell = ExcelUtil.createCell(row, 4, cellStylec);
		cell.setCellValue("商品数量");
		cell = ExcelUtil.createCell(row, 5, cellStylec);
		cell.setCellValue("交易状态");
		cell = ExcelUtil.createCell(row, 6, cellStylec);
		cell.setCellValue("买家名");
		cell = ExcelUtil.createCell(row, 7, cellStylec);
		cell.setCellValue("买家手机");
		cell = ExcelUtil.createCell(row, 8, cellStylec);
		cell.setCellValue("是否需要发票");
		cell = ExcelUtil.createCell(row, 9, cellStylec);
		cell.setCellValue("订单生成时间");
		cell = ExcelUtil.createCell(row, 10, cellStylec);
		cell.setCellValue("订单发货时间");
		cell = ExcelUtil.createCell(row, 11, cellStylec);
		cell.setCellValue("订单金额");
		cell = ExcelUtil.createCell(row, 12, cellStylec);
		cell.setCellValue("实际交易金额");
		cell = ExcelUtil.createCell(row, 13, cellStylec);
		cell.setCellValue("商品一级分类");
		cell = ExcelUtil.createCell(row, 14, cellStylec);
		cell.setCellValue("商品二级分类");
		cell = ExcelUtil.createCell(row, 15, cellStylec);
		cell.setCellValue("商品三级分类");
		cell = ExcelUtil.createCell(row, 16, cellStylec);
		cell.setCellValue("订单状态");
		cell = ExcelUtil.createCell(row, 17, cellStylec);
		cell.setCellValue("订单仓库");
		
		List<BopsGoodsCategoriesPO> categoryList = bopsGoodsCategoriesDAO.queryAll();
		
		// 插入实体数据
		TradeSendVO po = null;
		for (int i = 1; i < data.size() + 1; i++) {
			po = data.get(i - 1);
			row = ExcelUtil.createRow(sheet, i, 500);
			cell = ExcelUtil.createCell(row, 0, cellStylec);
			cell.setCellValue(po.getUserTradeNo());
			cell = ExcelUtil.createCell(row, 1, cellStylec);
			cell.setCellValue(po.getGoodsCode());
			cell = ExcelUtil.createCell(row, 2, cellStylec);
			cell.setCellValue(po.getGoodsName());
			cell = ExcelUtil.createCell(row, 3, cellStylec);
			cell.setCellValue(po.getGoodsBarcode() != null ? po.getGoodsBarcode() : "暂无");
			cell = ExcelUtil.createCell(row, 4, cellStylec);
			cell.setCellValue(po.getBuyCount());
			cell = ExcelUtil.createCell(row, 5, cellStylec);
			cell.setCellValue(TradeStatus.getDesc(po.getTradeStatus()));
			cell = ExcelUtil.createCell(row, 6, cellStylec);
			cell.setCellValue(po.getNickName());
			cell = ExcelUtil.createCell(row, 7, cellStylec);
			cell.setCellValue(po.getMobile());
			cell = ExcelUtil.createCell(row, 8, cellStylec);
			cell.setCellValue(po.getIsInvoice() != null ? po.getIsInvoice() : "暂无");
			cell = ExcelUtil.createCell(row, 9, cellStylec);
			cell.setCellValue(po.getTradeTime() == null ? " " : sf.format(po.getTradeTime()));
			cell = ExcelUtil.createCell(row, 10, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 11, cellStylec);
			cell.setCellValue(po.getTotalPrice());
			cell = ExcelUtil.createCell(row, 12, cellStylec);
			cell.setCellValue(po.getPayPrice());
//			BopsGoodsCategoriesPO categoryThree = bopsGoodsCategoriesDAO.selectByPrimaryKey(Integer.parseInt(po.getGoodsCategories()));
//			BopsGoodsCategoriesPO categoryTwo = bopsGoodsCategoriesDAO.selectByPrimaryKey(categoryThree.getParentId());
//			BopsGoodsCategoriesPO categoryOne = bopsGoodsCategoriesDAO.selectByPrimaryKey(categoryTwo.getParentId());
//			cell = ExcelUtil.createCell(row, 13, cellStylec);
//			cell.setCellValue(categoryOne.getCategoryName());
//			cell = ExcelUtil.createCell(row, 14, cellStylec);
//			cell.setCellValue(categoryTwo.getCategoryName());
//			cell = ExcelUtil.createCell(row, 15, cellStylec);
//			cell.setCellValue(categoryThree.getCategoryName());
			List<String> categoryNameList = null;
			if(null != po.getGoodsCategories()){
				categoryNameList = getCategoryNameList(categoryList, Integer.parseInt(po.getGoodsCategories()));
			}
			if(null != categoryNameList && categoryNameList.size() == 3){
				cell = ExcelUtil.createCell(row, 13, cellStylec);
				cell.setCellValue(categoryNameList.get(2));
				cell = ExcelUtil.createCell(row, 14, cellStylec);
				cell.setCellValue(categoryNameList.get(1));
				cell = ExcelUtil.createCell(row, 15, cellStylec);
				cell.setCellValue(categoryNameList.get(0));
			}
			else{
				cell = ExcelUtil.createCell(row, 13, cellStylec);
				cell.setCellValue(" ");
				cell = ExcelUtil.createCell(row, 14, cellStylec);
				cell.setCellValue(" ");
				cell = ExcelUtil.createCell(row, 15, cellStylec);
				cell.setCellValue(" ");
			}
			
			
			cell = ExcelUtil.createCell(row, 16, cellStylec);
			cell.setCellValue(TradeStatus.getDesc(po.getTradeStatus()));
			cell = ExcelUtil.createCell(row, 17, cellStylec);
			cell.setCellValue(WarehouseTypeEnum.getDesc(po.getWarehouse()));

		}
		return workbook;
	}
	/**
	 * 导出订单明细的execl
	 * @param orderItemList
	 * @return
	 * @author zhangmengbin
	 */
	public HSSFWorkbook getOrderItemBook(List<TradeExportItemVO> orderItemList) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		HSSFSheet sheet = ExcelUtil.createSheet(workbook, "订单明细数据");
		// 创建表头
		Font font = ExcelUtil.createFont(workbook, (short) 5, HSSFColor.BLACK.index, (short) 10);
		CellStyle cellStylec = ExcelUtil.createBorderCellStyle(workbook, HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				(short) 30, font);
		HSSFRow row = ExcelUtil.createRow(sheet, 0, 300);
		row.getSheet().setColumnWidth(0, 8000);
		row.getSheet().setColumnWidth(15, 8000);
		row.getSheet().setColumnWidth(17, 8000);
		row.getSheet().setColumnWidth(2, 12000);
		HSSFCell cell = ExcelUtil.createCell(row, 0, cellStylec);
		cell.setCellValue("订单号");
		cell = ExcelUtil.createCell(row, 1, cellStylec);
		cell.setCellValue("商品编号");
		cell = ExcelUtil.createCell(row, 2, cellStylec);
		cell.setCellValue("商品名称");
		cell = ExcelUtil.createCell(row, 3, cellStylec);
		cell.setCellValue("国际条形码");
		cell = ExcelUtil.createCell(row, 4, cellStylec);
		cell.setCellValue("Need价");
		cell = ExcelUtil.createCell(row, 5, cellStylec);
		cell.setCellValue("销售数量");
		cell = ExcelUtil.createCell(row, 6, cellStylec);
		cell.setCellValue("销售金额");
		cell = ExcelUtil.createCell(row, 7, cellStylec);
		cell.setCellValue("商品采购价");
		cell = ExcelUtil.createCell(row, 8, cellStylec);
		cell.setCellValue("毛利率");	
		cell = ExcelUtil.createCell(row, 9, cellStylec);
		cell.setCellValue("一级分类");
		cell = ExcelUtil.createCell(row, 10, cellStylec);
		cell.setCellValue("二级分类");
		cell = ExcelUtil.createCell(row, 11, cellStylec);
		cell.setCellValue("三级分类");
		cell = ExcelUtil.createCell(row, 12, cellStylec);
		cell.setCellValue("采购经理");
		cell = ExcelUtil.createCell(row, 13, cellStylec);
		cell.setCellValue("采购主管");
		cell = ExcelUtil.createCell(row, 14, cellStylec);
		cell.setCellValue("交易状态");
		cell = ExcelUtil.createCell(row, 15, cellStylec);
		cell.setCellValue("交易时间");
		cell = ExcelUtil.createCell(row, 16, cellStylec);
		cell.setCellValue("库房属性");
		cell = ExcelUtil.createCell(row, 17, cellStylec);
		cell.setCellValue("支付时间");
		cell = ExcelUtil.createCell(row, 18, cellStylec);
		cell.setCellValue("优惠（分SKU）");
		cell = ExcelUtil.createCell(row, 19, cellStylec);
		cell.setCellValue("运费（分SKU）");
		cell = ExcelUtil.createCell(row, 20, cellStylec);
		cell.setCellValue("实际支付金额");
		cell = ExcelUtil.createCell(row, 21, cellStylec);
		cell.setCellValue("是否开票");
		cell = ExcelUtil.createCell(row, 22, cellStylec);
		cell.setCellValue("订单备注");
		
		List<BopsGoodsCategoriesPO> categoryList = bopsGoodsCategoriesDAO.queryAll();
		List<BopsGoodsVO> goodsList =  bopsGoodsDAO.queryAllgoods();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// 插入实体数据
		TradeExportItemVO po = null;
		for (int i = 1; i < orderItemList.size() + 1; i++) {
			po = orderItemList.get(i - 1);
			po=getBopsGoodsInfo(goodsList,po);
			row = ExcelUtil.createRow(sheet, i, 500);
			cell = ExcelUtil.createCell(row, 0, cellStylec);
			cell.setCellValue(po.getUserTradeNo());
			cell = ExcelUtil.createCell(row, 1, cellStylec);
			cell.setCellValue(po.getGoodsCode());
			cell = ExcelUtil.createCell(row, 2, cellStylec);
			cell.setCellValue(po.getGoodsName());
			cell = ExcelUtil.createCell(row, 3, cellStylec);
			cell.setCellValue(po.getGoodsBarcode());
			cell = ExcelUtil.createCell(row, 4, cellStylec);
			cell.setCellValue(po.getBuyPrice().doubleValue());
			cell = ExcelUtil.createCell(row, 5, cellStylec);
			cell.setCellValue(po.getBuyCount());
			cell = ExcelUtil.createCell(row, 6, cellStylec);
			cell.setCellValue(po.getSalesAmount().doubleValue());
			cell = ExcelUtil.createCell(row, 7, cellStylec);
			cell.setCellValue(po.getPurchasePrice().doubleValue());
			cell = ExcelUtil.createCell(row, 8, cellStylec);
			cell.setCellValue(po.getGrossMargin()==null?0:po.getGrossMargin().doubleValue()); //毛利率
			List<String> categoryNameList = null;
			if(null != po.getGoodsCategories()){
				categoryNameList = getCategoryNameList(categoryList, Integer.parseInt(po.getGoodsCategories()));
			}
			if(null != categoryNameList && categoryNameList.size() == 3){
				cell = ExcelUtil.createCell(row, 9, cellStylec);
				cell.setCellValue(categoryNameList.get(2));
				cell = ExcelUtil.createCell(row, 10, cellStylec);
				cell.setCellValue(categoryNameList.get(1));
				cell = ExcelUtil.createCell(row, 11, cellStylec);
				cell.setCellValue(categoryNameList.get(0));
			}
			else{
				cell = ExcelUtil.createCell(row, 9, cellStylec);
				cell.setCellValue(" ");
				cell = ExcelUtil.createCell(row, 10, cellStylec);
				cell.setCellValue(" ");
				cell = ExcelUtil.createCell(row, 11, cellStylec);
				cell.setCellValue(" ");
			}
			cell = ExcelUtil.createCell(row, 12, cellStylec);
			cell.setCellValue(po.getPurchasingManager()==null?"":po.getPurchasingManager());
			cell = ExcelUtil.createCell(row, 13, cellStylec);
			cell.setCellValue(po.getPurchasingSupervisor()==null?"":po.getPurchasingSupervisor());
			cell = ExcelUtil.createCell(row, 14, cellStylec);
			cell.setCellValue(TradeStatus.getDesc(po.getTradeStatus()));
			cell = ExcelUtil.createCell(row, 15, cellStylec);
			cell.setCellValue(po.getCreateTime()==null?"":sdf.format(po.getCreateTime()));
			cell = ExcelUtil.createCell(row, 16, cellStylec);
			cell.setCellValue(WarehouseTypeEnum.getDesc(po.getWarehouseType()));
			cell = ExcelUtil.createCell(row, 17, cellStylec);
			cell.setCellValue(po.getPayTime()==null?"":sdf.format(po.getPayTime()));
			cell = ExcelUtil.createCell(row, 18, cellStylec);
			cell.setCellValue(po.getDiscount()==null?0:po.getDiscount().doubleValue()); //优惠
			cell = ExcelUtil.createCell(row, 19, cellStylec);
			cell.setCellValue(po.getFreight()==null?0:po.getFreight().doubleValue()); //运费
			cell = ExcelUtil.createCell(row, 20, cellStylec);
			cell.setCellValue(po.getRealpayPrice()==null ?0:po.getRealpayPrice().doubleValue());// 实付总金额
			cell = ExcelUtil.createCell(row, 21, cellStylec);
			cell.setCellValue("");
			cell = ExcelUtil.createCell(row, 22, cellStylec);
			cell.setCellValue(" ");// 商品标题
		}
		return workbook;
	}
	public HSSFWorkbook getOrderbook(List<OrderExportVO> orderList) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		HSSFSheet sheet = ExcelUtil.createSheet(workbook, "订单明细数据");
		// 创建表头
		Font font = ExcelUtil.createFont(workbook, (short) 5, HSSFColor.BLACK.index, (short) 10);
		CellStyle cellStylec = ExcelUtil.createBorderCellStyle(workbook, HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				(short) 30, font);
		HSSFRow row = ExcelUtil.createRow(sheet, 0, 300);
		row.getSheet().setColumnWidth(0, 8000);
		row.getSheet().setColumnWidth(1, 8000);
		HSSFCell cell = ExcelUtil.createCell(row, 0, cellStylec);
		cell.setCellValue("原始单号");
		cell = ExcelUtil.createCell(row, 1, cellStylec);
		cell.setCellValue("店铺");
		cell = ExcelUtil.createCell(row, 2, cellStylec);
		cell.setCellValue("寄件人姓名");
		cell = ExcelUtil.createCell(row, 3, cellStylec);
		cell.setCellValue("寄件人电话");
		cell = ExcelUtil.createCell(row, 4, cellStylec);
		cell.setCellValue("寄件人手机");
		cell = ExcelUtil.createCell(row, 5, cellStylec);
		cell.setCellValue("寄件人省");
		cell = ExcelUtil.createCell(row, 6, cellStylec);
		cell.setCellValue("寄件人市");
		cell = ExcelUtil.createCell(row, 7, cellStylec);
		cell.setCellValue("寄件人区/县");
		cell = ExcelUtil.createCell(row, 8, cellStylec);
		cell.setCellValue("寄件人详细地址");
		cell = ExcelUtil.createCell(row, 9, cellStylec);
		cell.setCellValue("寄件人邮政编码");
		cell = ExcelUtil.createCell(row, 10, cellStylec);
		cell.setCellValue("客户ID");
		cell = ExcelUtil.createCell(row, 11, cellStylec);
		cell.setCellValue("收件人姓名*");
		cell = ExcelUtil.createCell(row, 12, cellStylec);
		cell.setCellValue("收件人手机");
		cell = ExcelUtil.createCell(row, 13, cellStylec);
		cell.setCellValue("收件人电话");
		cell = ExcelUtil.createCell(row, 14, cellStylec);
		cell.setCellValue("收件人省");
		cell = ExcelUtil.createCell(row, 15, cellStylec);
		cell.setCellValue("收件人市");
		cell = ExcelUtil.createCell(row, 16, cellStylec);
		cell.setCellValue("收件人区/县");
		cell = ExcelUtil.createCell(row, 17, cellStylec);
		cell.setCellValue("收件人详细地址*");
		cell = ExcelUtil.createCell(row, 18, cellStylec);
		cell.setCellValue("收件人邮政编码");
		cell = ExcelUtil.createCell(row, 19, cellStylec);
		cell.setCellValue("订单总金额");
		cell = ExcelUtil.createCell(row, 20, cellStylec);
		cell.setCellValue("实付总金额");
		cell = ExcelUtil.createCell(row, 21, cellStylec);
		cell.setCellValue("商品编码*");
		cell = ExcelUtil.createCell(row, 22, cellStylec);
		cell.setCellValue("商品标题");
		cell = ExcelUtil.createCell(row, 23, cellStylec);
		cell.setCellValue("商品规格");
		cell = ExcelUtil.createCell(row, 24, cellStylec);
		cell.setCellValue("商品价格");
		cell = ExcelUtil.createCell(row, 25, cellStylec);
		cell.setCellValue("商品数量");
		cell = ExcelUtil.createCell(row, 26, cellStylec);
		cell.setCellValue("商品重量");
		cell = ExcelUtil.createCell(row, 27, cellStylec);
		cell.setCellValue("留言");
		cell = ExcelUtil.createCell(row, 28, cellStylec);
		cell.setCellValue("备注");
		cell = ExcelUtil.createCell(row, 29, cellStylec);
		cell.setCellValue("下发仓库");
		cell = ExcelUtil.createCell(row, 30, cellStylec);
		cell.setCellValue("商品一级分类");
		cell = ExcelUtil.createCell(row, 31, cellStylec);
		cell.setCellValue("商品二级分类");
		cell = ExcelUtil.createCell(row, 32, cellStylec);
		cell.setCellValue("商品三级分类");
		cell = ExcelUtil.createCell(row, 33, cellStylec);
		cell.setCellValue("订单状态");
		cell = ExcelUtil.createCell(row, 34, cellStylec);
		cell.setCellValue("订单仓库");
		
		List<BopsGoodsCategoriesPO> categoryList = bopsGoodsCategoriesDAO.queryAll();
		
		// 插入实体数据
		OrderExportVO po = null;
		for (int i = 1; i < orderList.size() + 1; i++) {
			po = orderList.get(i - 1);
			row = ExcelUtil.createRow(sheet, i, 500);
			cell = ExcelUtil.createCell(row, 0, cellStylec);
			cell.setCellValue(po.getUserTradeNo());
			cell = ExcelUtil.createCell(row, 1, cellStylec);
			cell.setCellValue("DTKJ");
			cell = ExcelUtil.createCell(row, 2, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 3, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 4, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 5, cellStylec);
			cell.setCellValue(" ");// 寄件人省
			cell = ExcelUtil.createCell(row, 6, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 7, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 8, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 9, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 10, cellStylec);
			cell.setCellValue(" ");// 客户ID
			cell = ExcelUtil.createCell(row, 11, cellStylec);
			cell.setCellValue(po.getReceiver());
			cell = ExcelUtil.createCell(row, 12, cellStylec);
			cell.setCellValue(po.getMobile());
			String[] area = new String[3];
			if (po.getLogisticsValue() != null) {
				area = po.getLogisticsValue().split("-");
			} else {
				Arrays.fill(area, " ");
			}
			cell = ExcelUtil.createCell(row, 13, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 14, cellStylec);
			cell.setCellValue(area[0]);// 收件人省
			cell = ExcelUtil.createCell(row, 15, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 16, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 17, cellStylec);
			cell.setCellValue(po.getLogisticsValue() + " " + po.getAddress());// 收件人详细地址*
			cell = ExcelUtil.createCell(row, 18, cellStylec);
			cell.setCellValue(" ");// 收件人邮政编码
			cell = ExcelUtil.createCell(row, 19, cellStylec);
			cell.setCellValue(" ");// 订单总金额
			cell = ExcelUtil.createCell(row, 20, cellStylec);
			cell.setCellValue(po.getPayPrice());// 实付总金额
			cell = ExcelUtil.createCell(row, 21, cellStylec);
			cell.setCellValue(po.getGoodsCode());
			cell = ExcelUtil.createCell(row, 22, cellStylec);
			cell.setCellValue(" ");// 商品标题
			cell = ExcelUtil.createCell(row, 23, cellStylec);
			cell.setCellValue(" ");// 商品规格
			cell = ExcelUtil.createCell(row, 24, cellStylec);
			cell.setCellValue(" ");// 商品价格
			cell = ExcelUtil.createCell(row, 25, cellStylec);
			cell.setCellValue(po.getBuyCount());// 商品数量
			cell = ExcelUtil.createCell(row, 26, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 27, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 28, cellStylec);
			cell.setCellValue(" ");
			cell = ExcelUtil.createCell(row, 29, cellStylec);
			cell.setCellValue(" ");
//			BopsGoodsCategoriesPO categoryThree = bopsGoodsCategoriesDAO.selectByPrimaryKey(Integer.parseInt(po.getGoodsCategories()));
//			BopsGoodsCategoriesPO categoryTwo = bopsGoodsCategoriesDAO.selectByPrimaryKey(categoryThree.getParentId());
//			BopsGoodsCategoriesPO categoryOne = bopsGoodsCategoriesDAO.selectByPrimaryKey(categoryTwo.getParentId());
//			cell = ExcelUtil.createCell(row, 30, cellStylec);
//			cell.setCellValue(categoryOne.getCategoryName());
//			cell = ExcelUtil.createCell(row, 31, cellStylec);
//			cell.setCellValue(categoryTwo.getCategoryName());
//			cell = ExcelUtil.createCell(row, 32, cellStylec);
//			cell.setCellValue(categoryThree.getCategoryName());
			List<String> categoryNameList = null;
			if(null != po.getGoodsCategories()){
				categoryNameList = getCategoryNameList(categoryList, Integer.parseInt(po.getGoodsCategories()));
			}
			if(null != categoryNameList && categoryNameList.size() == 3){
				cell = ExcelUtil.createCell(row, 30, cellStylec);
				cell.setCellValue(categoryNameList.get(2));
				cell = ExcelUtil.createCell(row, 31, cellStylec);
				cell.setCellValue(categoryNameList.get(1));
				cell = ExcelUtil.createCell(row, 32, cellStylec);
				cell.setCellValue(categoryNameList.get(0));
			}
			else{
				cell = ExcelUtil.createCell(row, 30, cellStylec);
				cell.setCellValue(" ");
				cell = ExcelUtil.createCell(row, 31, cellStylec);
				cell.setCellValue(" ");
				cell = ExcelUtil.createCell(row, 32, cellStylec);
				cell.setCellValue(" ");
			}
			
			cell = ExcelUtil.createCell(row, 33, cellStylec);
			cell.setCellValue(TradeStatus.getDesc(po.getTradeStatus()));
			cell = ExcelUtil.createCell(row, 34, cellStylec);
			cell.setCellValue(WarehouseTypeEnum.getDesc(po.getWarehouse()));
		}
		return workbook;
	}
	
	private List<String> getCategoryNameList(List<BopsGoodsCategoriesPO> categoryList, int categoryId){
		List<String> categoryNameList = new ArrayList<String>();
		out:
		for(BopsGoodsCategoriesPO bopsGoodsCategoriesPOThree : categoryList){
			if(categoryId == bopsGoodsCategoriesPOThree.getCategoryId()){
				categoryNameList.add(bopsGoodsCategoriesPOThree.getCategoryName());
				for(BopsGoodsCategoriesPO bopsGoodsCategoriesPOTwo : categoryList){
					if(bopsGoodsCategoriesPOThree.getParentId() == bopsGoodsCategoriesPOTwo.getCategoryId()){
						categoryNameList.add(bopsGoodsCategoriesPOTwo.getCategoryName());
						for(BopsGoodsCategoriesPO bopsGoodsCategoriesPOOne : categoryList){
							if(bopsGoodsCategoriesPOTwo.getParentId() == bopsGoodsCategoriesPOOne.getCategoryId()){
								categoryNameList.add(bopsGoodsCategoriesPOOne.getCategoryName());
								break out;
							}
						}
					}
				}
			}
		}
		
		
		return categoryNameList;
	}
	/**
	 * 获取bops后台库商品的采购价，采购经理，采购主管
	 * @param goodsList
	 * @param theOrderItemVo
	 * @return
	 */
	public TradeExportItemVO getBopsGoodsInfo(List<BopsGoodsVO> goodsList,TradeExportItemVO tradeExportItemVO){
		if(goodsList!=null && goodsList.size()>0 && tradeExportItemVO!=null){
			for(BopsGoodsVO bopsGoods :goodsList){
				if(bopsGoods.getGoodsId().equals(tradeExportItemVO.getGoodsId())){
					tradeExportItemVO.setPurchasePrice(bopsGoods.getPurPrice());
					tradeExportItemVO.setPurchasingManager(bopsGoods.getPurchaseManager()==null?"":bopsGoods.getPurchaseManager());
					tradeExportItemVO.setPurchasingSupervisor(bopsGoods.getPurchaseLeader()==null?"":bopsGoods.getPurchaseLeader());
					break;
				}
			}
		}
		return tradeExportItemVO;
	}
	@Override
	public List<BatchSendTradeParamVO> excelToList(InputStream in, Boolean is07Or10) {
		// TODO Auto-generated method stub
		List<BatchSendTradeParamVO> batchSendTradeList = new ArrayList<BatchSendTradeParamVO>();
		try {
			// 07 2010版本
			if (is07Or10) {
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
				return read07Excel(xssfWorkbook, batchSendTradeList);
			} else {
				POIFSFileSystem fs = new POIFSFileSystem(in);
				return read03Excel(fs, batchSendTradeList);
			}

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return batchSendTradeList;
	}

	private List<BatchSendTradeParamVO> read03Excel(POIFSFileSystem fs,
			List<BatchSendTradeParamVO> batchSendTradeList) {
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HSSFCell cell = null;
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet st = wb.getSheetAt(sheetIndex);
			// 第一行为标题，不取
			for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
				BatchSendTradeParamVO batchSendTrade = new BatchSendTradeParamVO();
				HSSFRow row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
					cell = row.getCell(columnIndex);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					if (cell != null && columnIndex == 0) {
						batchSendTrade.setOrderNo(StringTrim(cell.getStringCellValue()));
					} else if (cell != null && columnIndex == 1) {
						batchSendTrade.setServiceProvider(StringTrim(cell.getStringCellValue()));
					} else {
						batchSendTrade.setLogisticsNo(StringTrim(cell.getStringCellValue()));
					}
				}
				batchSendTradeList.add(batchSendTrade);

			}
		}
		return batchSendTradeList;
	}

	private List<BatchSendTradeParamVO> read07Excel(XSSFWorkbook xssfWorkbook, List<BatchSendTradeParamVO> list) {
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					BatchSendTradeParamVO vo = new BatchSendTradeParamVO();
					if (null != xssfRow.getCell(0)) {
						xssfRow.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
						vo.setOrderNo(xssfRow.getCell(0).getStringCellValue());
					}
					if (null != xssfRow.getCell(1)) {
						xssfRow.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
						vo.setServiceProvider(xssfRow.getCell(1).getStringCellValue());
					}
					if (null != xssfRow.getCell(2)) {
						xssfRow.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);
						vo.setLogisticsNo(xssfRow.getCell(2).getStringCellValue());
					}
					list.add(vo);
				}
			}
		}
		return list;
	}

	private String StringTrim(String value) {
		if (StringUtils.hasText(value)) {
			return value.trim();
		} else {
			return "";
		}
	}

	@Override
	public XSSFWorkbook exportTradeBatchTemplate() {
		// TODO Auto-generated method stub
		XSSFWorkbook workBook = null;
		workBook = new XSSFWorkbook();// 创建工作薄
		XSSFSheet sheet = workBook.createSheet();
		workBook.setSheetName(0, "批量发货模板");
		XSSFFont font = workBook.createFont();
		font.setColor(XSSFFont.COLOR_NORMAL);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		XSSFCellStyle cellStyle = workBook.createCellStyle();// 创建格式
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		XSSFRow titleRow = sheet.createRow(0);// 第一行标题
		XSSFCell cell = titleRow.createCell(0, 0);
		cell.setCellStyle(cellStyle);
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("订单号");
		XSSFCell cell2 = titleRow.createCell(1, 0);
		cell2.setCellStyle(cellStyle);
		cell2.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell2.setCellValue("快递公司ID");
		XSSFCell cell3 = titleRow.createCell(2, 0);
		cell3.setCellStyle(cellStyle);
		cell3.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell3.setCellValue("快递单号");

		return workBook;
	}

}

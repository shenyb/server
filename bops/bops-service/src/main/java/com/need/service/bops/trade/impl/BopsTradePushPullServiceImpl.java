package com.need.service.bops.trade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.api.trade.TradeBaseDAO;
import com.need.dao.bops.trade.BopsTradePushPullDAO;
import com.need.dao.bops.wms.ESynEdiMessageDAO;
import com.need.domain.common.enums.PayChannelEnum;
import com.need.domain.common.enums.PushReturnStatusEnum;
import com.need.domain.common.enums.TrackingCodeEnum;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.api.trade.TradeBasePO;
import com.need.domain.po.bops.trade.BopsTradePushPull;
import com.need.domain.po.bops.wms.ESynEdiMessage;
import com.need.domain.vo.trade.TradeBaseParam;
import com.need.domain.vo.trade.TradeVO;
import com.need.domain.vo.wms.TheOrderItemVo;
import com.need.domain.vo.wms.TheOrderVo;
import com.need.framework.utils.PropertiesUtil;
import com.need.kafka.services.producer.NeedProducer;
import com.need.service.bops.trade.BopsTradePushPullService;
import com.need.service.constant.Constants;
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
	public void createDeliverTradeTest() {
		// logger.info("createDeliverTrade start.............");
		// 要拿取发货信息的订单,使用清关状态标示是保税还是笨鸟
		List<BopsTradePushPull> tradeList = bopsTradePushPullDAO.queryListToDeliver();
		if (tradeList == null) {
			// logger.info("tradeList null..........");
			return;
		}
			for (BopsTradePushPull bopsTradePushPull : tradeList) {
				TheOrderVo theOrderVo = new TheOrderVo(); //wms 需要的
				//根据推送的tradeNo 查到TradeBase 对象
				List<TradeBasePO> list = tradeBaseDAO.getByTradeNo(bopsTradePushPull.getTradeNo());
				TradeBasePO tradeBasePO = list != null ? list.get(0) : null;
				if (tradeBasePO == null) {
					//logger.error("trade no not exists:" + bopsTradePushPull.getTradeNo());
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
				//theOrderVo.setReceiveAddr(tradeBasePO.getLogisticsValue()); //收货地址
				theOrderVo.setZipCode("");             //邮编
				theOrderVo.setRemark("");              //备注
				theOrderVo.setPhone(tradeBasePO.getTelephone()); //手机
				theOrderVo.setBusinessType("201");   //业务类型
				theOrderVo.setCreatedAt(tradeBasePO.getCreateTime()); //创建时间
				theOrderVo.setPointPay(0L);   //积分支付
				theOrderVo.setDeliverPay(Long.parseLong(tradeBasePO.getTransportFee() + ""));   //顾客运费
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
				theOrderVo.setTotalPay(Long.parseLong((tradeBasePO.getPayPrice()+tradeBasePO.getTransportFee())+""));  //合计支付
				theOrderVo.setRealPay(Long.parseLong(tradeBasePO.getPayPrice()+""));   //现实支付
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
}

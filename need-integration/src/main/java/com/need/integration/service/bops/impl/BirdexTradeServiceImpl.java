package com.need.integration.service.bops.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.need.integration.common.birdex.BirdexConstant;
import com.need.integration.common.birdex.bean.orderResult.OrderResultVO;
import com.need.integration.common.birdex.bean.tracking.TrackingOrderVO;
import com.need.integration.common.enums.LogisticsCompanyEnum;
import com.need.integration.common.enums.TrackingCodeEnum;
import com.need.integration.dao.jdbc.api.goods.GoodsMainDAO;
import com.need.integration.dao.jdbc.api.goods.po.GoodsMainPO;
import com.need.integration.dao.jdbc.api.trade.TradeAddressDAO;
import com.need.integration.dao.jdbc.api.trade.TradeBaseDAO;
import com.need.integration.dao.jdbc.api.trade.TradePushPullDAO;
import com.need.integration.dao.jdbc.api.trade.TradeRetrieveStatusRecordDAO;
import com.need.integration.dao.jdbc.api.trade.po.TradeAddressPO;
import com.need.integration.dao.jdbc.api.trade.po.TradeBasePO;
import com.need.integration.dao.jdbc.api.trade.po.TradeRetrieveStatusRecord;
import com.need.integration.dao.jdbc.api.user.UserBaseDAO;
import com.need.integration.dao.jdbc.api.user.po.UserBase;
import com.need.integration.dao.jdbc.bops.store.BopsInventoryDAO;
import com.need.integration.dao.jdbc.bops.store.BopsInventoryFreezeLogDAO;
import com.need.integration.dao.jdbc.bops.trade.BopsTradeLogisticsDAO;
import com.need.integration.dao.jdbc.bops.trade.BopsTradePushPullDAO;
import com.need.integration.dao.jdbc.bops.trade.po.BopsTradeLogisticsPO;
import com.need.integration.pub.Message;
import com.need.integration.service.api.trade.PortalTradeService;
import com.need.integration.service.bops.BirdexTradeService;
import com.need.integration.service.vo.LogisticsInfoJsonVO;
import com.need.utils.DateUtil;
import java.util.List;

@Service
public class BirdexTradeServiceImpl implements BirdexTradeService {

	private static Logger logger = Logger.getLogger(BirdexTradeServiceImpl.class);

	@Autowired
	private TradeBaseDAO tradeBaseDAO;
	@Autowired
	private BopsTradePushPullDAO bopsTradePushPullDAO;
	@Autowired
	private TradeRetrieveStatusRecordDAO tradeRetrieveStatusRecordDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private PortalTradeService portalTradeService;
	@Autowired
	private UserBaseDAO userBaseDAO;
	@Autowired
	private TradeAddressDAO tradeAddressDAO;
	@Autowired
	private BopsTradeLogisticsDAO bopsTradeLogisticsDAO;
	@Autowired
	private TradePushPullDAO apiTradePushPullDAO;
	@Autowired
	private BopsInventoryDAO bopsInventoryDAO;
	@Autowired
	private BopsInventoryFreezeLogDAO bopsInventoryFreezeLogDAO;
	
	@Override
	@Transactional
	public int birdexTradeResultUpdate(String userTradeNo, OrderResultVO orderResult) {
		// TODO Auto-generated method stub
		/**
		 * 更新订单表和推送表的推送状态
		 */
		String birdexString = JSONObject.toJSONString(orderResult);
		int resutl = tradeBaseDAO.updatePushStatus(userTradeNo);
		bopsTradePushPullDAO.updatePushStatus(userTradeNo, birdexString);
		apiTradePushPullDAO.updatePushStatus(userTradeNo, birdexString);

//		/**
//		 * 当笨鸟返回成功之后，往流转表插入一条记录，告诉用户订单已受理
//		 */
//		TradeRetrieveStatusRecord tradeRetrieveStatusRecord = new TradeRetrieveStatusRecord();
//		tradeRetrieveStatusRecord.setCreateTime(Calendar.getInstance().getTime());
//		/**
//		 * 根据和申延彬协商，我们把userTradeNo保存到tradeNo字段中，方便前端查询
//		 */
////		tradeRetrieveStatusRecord.setUserTradeNo(userTradeNo);
//		tradeRetrieveStatusRecord.setTradeNo(userTradeNo);
//		tradeRetrieveStatusRecord.setTrackingCode(TrackingCodeEnum.RECEIVED.code);
//		tradeRetrieveStatusRecord.setTrackingDesc(TrackingCodeEnum.RECEIVED.userDesc);
//		tradeRetrieveStatusRecord.setBirdexResult(birdexString);
//		insertBirdexRecord(tradeRetrieveStatusRecord);

		return resutl;
	}

	private int insertBirdexRecord(TradeRetrieveStatusRecord tradeRetrieveStatusRecord){
		TradeRetrieveStatusRecord historyRecord = getRecord(tradeRetrieveStatusRecord.getTradeNo(), 
				tradeRetrieveStatusRecord.getTrackingCode());
		if(null == historyRecord){
			logger.info("tradeRetrieveStatusRecord: " + tradeRetrieveStatusRecord);
			return tradeRetrieveStatusRecordDAO.insertBiredexRecord(tradeRetrieveStatusRecord);
		}
		return 0;
	}
	
	@Override
	@Transactional
	public int saveTrackingOrderInfo(TrackingOrderVO trackingOrder) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TradeRetrieveStatusRecord tradeRetrieveStatusRecord = new TradeRetrieveStatusRecord();
		try {
			String result = JSONObject.toJSONString(trackingOrder);
			/**
			 * TODO UTC需要加8小时才是北京时间
			 */
			tradeRetrieveStatusRecord.setCreateTime(DateUtil.hoursOfPlus(sdf.parse(trackingOrder.getOccurTime()), 8));
			tradeRetrieveStatusRecord.setTradeNo(trackingOrder.getLogisticsId());
//			tradeRetrieveStatusRecord.setUserTradeNo(trackingOrder.getLogisticsId());
			String trackingCode = trackingOrder.getTrackingCode();
			String trackingUserDesc = "";
			String trackingDesc = trackingOrder.getTrackingDesc();
			tradeRetrieveStatusRecord.setTrackingCode(trackingCode);
			tradeRetrieveStatusRecord.setBirdexResult(result);
//			TradeRetrieveStatusRecord historyTradeRetrieveStatusRecord = getRecord(trackingOrder.getLogisticsId(), trackingOrder.getTrackingCode());
			switch (trackingCode) {
			case "PULLING":
				trackingUserDesc = TrackingCodeEnum.PULLING.userDesc;
				tradeRetrieveStatusRecord.setTrackingDesc(trackingUserDesc);
				insertBirdexRecord(tradeRetrieveStatusRecord);
				break;
			case "COMBINE":
				trackingUserDesc = TrackingCodeEnum.COMBINE.userDesc;
				tradeRetrieveStatusRecord.setTrackingDesc(trackingUserDesc);
				insertBirdexRecord(tradeRetrieveStatusRecord);
				break;
			case "TURNIN":
				trackingUserDesc = TrackingCodeEnum.TURNIN.userDesc;
				tradeRetrieveStatusRecord.setTrackingDesc(trackingUserDesc);
				insertBirdexRecord(tradeRetrieveStatusRecord);
				break;
			case "CHECKOUT":
				trackingUserDesc = "您的订单已完成出库，等待提货 " + trackingDesc;
				tradeRetrieveStatusRecord.setTrackingDesc(trackingUserDesc);
				insertBirdexRecord(tradeRetrieveStatusRecord);
				break;
			case "AIRLINE":
				trackingUserDesc = TrackingCodeEnum.AIRLINE.userDesc;
				tradeRetrieveStatusRecord.setTrackingDesc(trackingUserDesc);
				insertBirdexRecord(tradeRetrieveStatusRecord);
				break;
			case "CUSTOMS"://此状态下不需要检查是否重复插入，因为这个状态笨鸟会传多个记录，此状态下一定有多条记录
				trackingUserDesc = trackingDesc;
				tradeRetrieveStatusRecord.setTrackingDesc(trackingUserDesc);
				logger.info("tradeRetrieveStatusRecord: " + tradeRetrieveStatusRecord);
				tradeRetrieveStatusRecordDAO.insertBiredexRecord(tradeRetrieveStatusRecord);
				break;
			case "DOMESTICEXCHANGE":
				trackingUserDesc = "您的订单已经交给国内快递公司 " + trackingDesc;
				tradeRetrieveStatusRecord.setTrackingDesc(trackingUserDesc);
				insertBirdexRecord(tradeRetrieveStatusRecord);
				break;
			case "DISPATCH"://此状态下不需要检查是否重复插入，因为这个状态笨鸟会传多个记录，此状态下一定有多条记录
				trackingUserDesc = trackingDesc;
				tradeRetrieveStatusRecord.setTrackingDesc(trackingUserDesc);
				logger.info("tradeRetrieveStatusRecord: " + tradeRetrieveStatusRecord);
				tradeRetrieveStatusRecordDAO.insertBiredexRecord(tradeRetrieveStatusRecord);
				break;
			case "SIGN":
				trackingUserDesc = trackingDesc;
				tradeRetrieveStatusRecord.setTrackingDesc(trackingUserDesc);
				insertBirdexRecord(tradeRetrieveStatusRecord);
				break;
			case "OTHER":
				trackingUserDesc = trackingDesc;
				tradeRetrieveStatusRecord.setTrackingDesc(trackingUserDesc);
				insertBirdexRecord(tradeRetrieveStatusRecord);
				break;
			default:
				trackingUserDesc = "";
				break;
			}

			/**
			 * 去做发货操作
			 */
			if (BirdexConstant.TRACKINGCODE_CHECKOUT.equals(trackingOrder.getTrackingCode())) {
				String logisticsNo = trackingOrder.getTrackingDesc().split(":")[1];
				logger.info("logisticsNo: " + logisticsNo);
				/**
				 * 发货
				 */
				send(trackingOrder.getLogisticsId(), LogisticsCompanyEnum.BENNIAOWULIU.code, logisticsNo);
				/**
				 * 更新 推送表中的运单号
				 */
				updateLogisticsNo(trackingOrder.getLogisticsId(), logisticsNo);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @author xiehao 2015年10月28日 上午10:42:20
	 * @Method: send
	 * @Description: TODO 发货
	 * @param orderNo
	 * @param transporterId
	 * @param outIds
	 * @see com.need.integration.service.BirdexTradeService#send(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void send(String orderNo, String transporterId, String outIds) {
        List<TradeBasePO> tradeList = tradeBaseDAO.getByUserTradeNo(orderNo);
		if (tradeList == null || tradeList.isEmpty()) {
			logger.error(String.format("trade is null:%s", JSON.toJSON(orderNo)));
			return;
		}
		TradeBasePO tradePO = tradeList.get(0);
		Message message = portalTradeService.sendTrade(tradePO.getTradeNo());
		if (Message.SUCCESS == message.getCode()) {
			// 后台发货表插入一条发货记录
			saveSendRecord(transporterId, outIds, tradePO);
		}
		
		/**
		 * 扣减库存
		 */
		reduceStore(orderNo);
	}

	private void saveSendRecord(String transporterId, String logisticsNo, TradeBasePO trade) {
		BopsTradeLogisticsPO sendPO = new BopsTradeLogisticsPO();
		// 设置商品属性
		String goodsId = trade.getGoodsId();
		GoodsMainPO goods = goodsMainDAO.getByGoodsId(goodsId);
		if (goods == null) {
			logger.error(String.format("orderNO :%s ,goodsId :%s not exist", trade.getOrderNo(), trade.getGoodsId()));
			return;
		}

		// 设置收获人以及地址信息
		UserBase user = userBaseDAO.getByUserId(trade.getBuyerId());
		if (user == null) {
			logger.error(String.format("orderNO :%s ,userId :%s not exist", trade.getOrderNo(), trade.getBuyerId()));
			return;
		}
		TradeAddressPO address = tradeAddressDAO.getByAddressId(trade.getAddressId());
		sendPO.setCreateTime(Calendar.getInstance().getTime());
		sendPO.setUserId(trade.getBuyerId());
		sendPO.setTradeNo(trade.getTradeNo());
		sendPO.setGoodsSnNo(goods.getGoodsBarcode());
		sendPO.setAddressId(trade.getAddressId());
		sendPO.setAddress(trade.getAddress());
		sendPO.setLogisticsId(address.getLogisticsId());
		LogisticsInfoJsonVO logisticsInfo = new LogisticsInfoJsonVO(transporterId, logisticsNo);
		sendPO.setLogisticsInfo(JSON.toJSONString(logisticsInfo));
		bopsTradeLogisticsDAO.insert(sendPO);
	}
	
	
	@Override
	@Transactional("bops_txManager")
	public Message reduceStore(String userTradeNo){
		Message message = Message.success();
		int resultCount = 0;
		List<TradeBasePO> tradeList = tradeBaseDAO.getByUserTradeNo(userTradeNo);
		if(tradeList != null && tradeList.size() > 0){
			for(TradeBasePO trade: tradeList){
				logger.info("reduce bops store : trade: " + trade);
				String  goodsId = trade.getGoodsId();
				resultCount = resultCount + bopsInventoryDAO.updateStore(goodsId, trade.getBuyCount());
				long bopsInventoryId = bopsInventoryDAO.selectByGoodsId(goodsId).getId();
				bopsInventoryFreezeLogDAO.saveStoreLog(bopsInventoryId, trade.getBuyCount()*(-1), trade.getOrderNo(), "订单出库扣减库存流水记录");
			}
		}
		
		return resultCount > 0 ? message : Message.error(1);
	}

	@Override
	@Transactional
	public int birdexResultUpdate(String userTradeNo, String result) {
		// TODO Auto-generated method stub
		bopsTradePushPullDAO.updateBirdexResult(userTradeNo, result);
		apiTradePushPullDAO.updateBirdexResult(userTradeNo, result);

		return 0;
	}

	@Override
	@Transactional
	public int identityNotPass(String userTradeNo, String result) {
		// TODO Auto-generated method stub
		TradeRetrieveStatusRecord tradeRetrieveStatusRecord = new TradeRetrieveStatusRecord();
		tradeRetrieveStatusRecord.setTradeNo(userTradeNo);
		tradeRetrieveStatusRecord.setCreateTime(Calendar.getInstance().getTime());
//		tradeRetrieveStatusRecord.setUserTradeNo(userTradeNo);
		tradeRetrieveStatusRecord.setTrackingCode(TrackingCodeEnum.IDENTITYNOTPASS.code);
		tradeRetrieveStatusRecord.setTrackingDesc(TrackingCodeEnum.IDENTITYNOTPASS.userDesc);
		tradeRetrieveStatusRecord.setBirdexResult(result);
		insertBirdexRecord(tradeRetrieveStatusRecord);

		return 0;
	}

	@Override
	public int updateLogisticsNo(String userTradeNo, String logisticsNo) {
		// TODO Auto-generated method stub
		bopsTradePushPullDAO.updateLogisticsNo(userTradeNo, logisticsNo);
		apiTradePushPullDAO.updateLogisticsNo(userTradeNo, logisticsNo);
		return 0;
	}

	public TradeRetrieveStatusRecord getRecord(String tradeNo, String trackingCode) {
		return tradeRetrieveStatusRecordDAO.getByTradeNoAndTrackingCode(tradeNo, trackingCode);
	}
}

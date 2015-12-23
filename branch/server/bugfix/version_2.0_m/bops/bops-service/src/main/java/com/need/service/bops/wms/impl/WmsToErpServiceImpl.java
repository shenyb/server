package com.need.service.bops.wms.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.api.trade.TradeAddressDAO;
import com.need.dao.api.trade.TradeBaseDAO;
import com.need.dao.api.trade.TradeRetrieveStatusRecordDAO;
import com.need.dao.api.user.UserBaseDAO;
import com.need.dao.bops.trade.BopsExchangeDAO;
import com.need.dao.bops.trade.BopsExchangeItemDAO;
import com.need.dao.bops.trade.BopsTradeLogisticsDAO;
import com.need.dao.bops.trade.BopsTradePushPullDAO;
import com.need.dao.bops.trade.WmsOrderToErpDAO;
import com.need.dao.bops.wms.ESynEdiReceiveMessageDAO;
import com.need.domain.common.enums.DeliverStatusEnum;
import com.need.domain.common.enums.TrackingCodeEnum;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.api.trade.TradeAddressPO;
import com.need.domain.po.api.trade.TradeBasePO;
import com.need.domain.po.api.trade.TradeRetrieveStatusRecord;
import com.need.domain.po.api.user.UserBase;
import com.need.domain.po.bops.trade.BopsExchange;
import com.need.domain.po.bops.trade.BopsExchangeItem;
import com.need.domain.po.bops.trade.BopsTradeLogistics;
import com.need.domain.po.bops.trade.BopsTradePushPull;
import com.need.domain.po.bops.trade.WmsOrderToErp;
import com.need.domain.po.bops.wms.ESynEdiReceiveMessage;
import com.need.domain.pub.Message;
import com.need.domain.vo.trade.LogisticsInfoJsonVO;
import com.need.domain.vo.trade.TradeVO;
import com.need.domain.vo.wms.BarterSaleExchangeItemOutVo;
import com.need.domain.vo.wms.BarterSaleExchangeOutVo;
import com.need.service.api.trade.PortalTradeService;
import com.need.service.bops.goods.BopsGoodsStoreService;
import com.need.service.bops.wms.WmsToErpService;
import com.need.trade.enums.TradeStatus;
import com.need.utils.StringUtil;
@Service
@Transactional
public class WmsToErpServiceImpl implements WmsToErpService{
	@Autowired
	WmsOrderToErpDAO wmsOrderToErpDAO;
	@Autowired
	BopsGoodsStoreService bopsGoodsStoreService;
	@Autowired
	TradeBaseDAO tradeBaseDAO;
	@Autowired
	BopsTradePushPullDAO bopsTradePushPullDAO;
	@Autowired
	PortalTradeService portalTradeService;
	@Autowired
	GoodsMainDAO goodsMainDAO;
	@Autowired
	UserBaseDAO userBaseDAO;
	@Autowired
	TradeAddressDAO tradeAddressDAO;
	@Autowired
	BopsTradeLogisticsDAO bopsTradeLogisticsDAO;
	@Autowired
	TradeRetrieveStatusRecordDAO tradeRetrieveStatusRecordDAO;
	@Autowired
	ESynEdiReceiveMessageDAO eSynEdiReceiveMessageDAO;
	@Autowired
	BopsExchangeDAO bopsExchangeDAO;
	@Autowired
	BopsExchangeItemDAO bopsExchangeItemDAO;
	/** 
	 * 吧wms订单到erp 的数据存到wms_order_erp
	 * @author zhangmengbin
	 */
	//@Override
	public void createWmsOrderToErp(ESynEdiReceiveMessage message) {
		try {
			if(message!=null){
				WmsOrderToErp wmsOrderToErp =JSONObject.parseObject(message.getBody(), WmsOrderToErp.class);
				if(wmsOrderToErp!=null){
						wmsOrderToErpDAO.insert(wmsOrderToErp);
						getDeliverTradeListToPullStatusTest(wmsOrderToErp.getId());
						message.setHandleDate(new Date());
						message.setStatus(2L);
						eSynEdiReceiveMessageDAO.updateByPrimaryKey(message);
				}
			}
		} catch (Exception e) {
			message.setHandleDate(new Date());
			message.setStatus(9L);
			eSynEdiReceiveMessageDAO.updateByPrimaryKey(message);
			e.printStackTrace();
		}
		
	}
	/**
	 * 查询海美的发货状态，如果完成，则我们的系统就自动发货 @author shenyb 2015年10月27日 下午2:02:13 @Method:
	 * getTradeDeliverStatus @Description:  @throws
	 */
	// @Scheduled(cron = "${tradeDeliverTime}")
	public void getDeliverTradeListToPullStatusTest(long userOrderNo) {
		//PageHelper.startPage(PAGE_NUM, PAGE_SIZE);
		// 要拿取发货信息的订单
		BopsTradePushPull bopsTradePushPull= bopsTradePushPullDAO.selectByPrimaryKey(userOrderNo+"");
		
			try {
					String outIds = bopsTradePushPull.getLogisticsNo();// 运单号
					//if (Long.valueOf(DeliverStatusEnum.OK.code) == status) {
						// 自动发货,推送给E贸易的orderNo为我们交易表的userTradeNo
						/*logger.info(String.format("send:ordrNo:%s,transporterId:%s,outIds:%s", orderNo, transporterId,
								outIds));*/
						send(bopsTradePushPull.getOrderNo(), "2", outIds);
						bopsTradePushPull.setOrderStatus(TradeStatus.WAIT_RECEIVE.code);
						bopsTradePushPull.setTradeStatus(TradeStatus.WAIT_RECEIVE.code);
						bopsTradePushPull.setDeliverStatus(DeliverStatusEnum.OK.code);
						// 发货后插入清关记录表一条数据
						if (!StringUtil.isBlank(bopsTradePushPull.getLogisticsNo())) {
							TradeRetrieveStatusRecord record = new TradeRetrieveStatusRecord();
							record.setOrderNo(bopsTradePushPull.getOrderNo());
							record.setTradeNo(bopsTradePushPull.getTradeNo());
							// record.setRetrieveStatus(RetrieveStatusEnum.DELIVER_DONE.code);
							record.setTrackingCode(TrackingCodeEnum.DELIVER_DONE.code);
							record.setTrackingDesc(TrackingCodeEnum.DELIVER_DONE.userDesc + ",单号:" + outIds);
							recordRetrieveStatus(record);
						}
					//}
					String status = "";
					String pull = bopsTradePushPull.getDeliverPullStatus();
					if (StringUtil.isBlank(pull)) {
						bopsTradePushPull.setDeliverPullStatus(status + "");
					} else if (!pull.contains(status + "")) {
						bopsTradePushPull.setDeliverPullStatus(status + ",");
					}
					bopsTradePushPullDAO.updateByOrderNo(bopsTradePushPull);
				//}

			} catch (Exception e) {
				//logger.error("IscsException..." + e.getMessage());
			}
	}
	/**
	 * 
	 * @author shenyb 2015年9月16日 下午4:09:46 @Method: send @Description: 发货
	 *         1设置trade status为待收货 2库存锁定数减少 3插入发货表一条记录 @throws
	 */
	@Transactional
	public void send(String orderNo, String transporterId, String outIds) {
		TradeBasePO tradePO = (TradeBasePO) tradeBaseDAO.getByUserOrderNoWms(orderNo);
		if (tradePO == null) {
			//logger.error(String.format("trade is null:%s", JSON.toJSON(tradePO)));
			return;
		}
		Message message = portalTradeService.sendTrade(tradePO.getTradeNo());
		if (Message.SUCCESS == message.getCode()) {
			// 后台发货表插入一条发货记录
			saveSendRecord(transporterId, outIds, tradePO);
		}
	}
	private void saveSendRecord(String transporterId, String logisticsNo, TradeBasePO trade) {
		BopsTradeLogistics sendPO = new BopsTradeLogistics();
		// 设置商品属性
		String goodsId = trade.getGoodsId();
		GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(goodsId);
		if (goods == null) {
			//logger.error(String.format("orderNO :%s ,goodsId :%s not exist", trade.getOrderNo(), trade.getGoodsId()));
			return;
		}

		// 设置收获人以及地址信息
		UserBase user = userBaseDAO.selectByPrimaryKey(trade.getBuyerId());
		if (user == null) {
			//logger.error(String.format("orderNO :%s ,userId :%s not exist", trade.getOrderNo(), trade.getBuyerId()));
			return;
		}
		TradeAddressPO address = tradeAddressDAO.selectByPrimaryKey(trade.getAddressId());
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
	/**
	 * 
	 * @author shenyb 2015年10月31日 下午4:10:27 @Method:
	 *         recordRetrieveStatus @Description:  @param record @throws
	 */
	private void recordRetrieveStatus(TradeRetrieveStatusRecord record) {
		List<TradeRetrieveStatusRecord> retrieveRecord = tradeRetrieveStatusRecordDAO
				.getByRetrieveStatusAndTradeNo(record.getTrackingCode(), record.getTradeNo());
		if (retrieveRecord == null || retrieveRecord.size() == 0) {
			//logger.info("before insert record:userTradeNo" + record.getTradeNo() + ":" + JSON.toJSONString(record));
			tradeRetrieveStatusRecordDAO.insert(record);
		}
	}
	/**
	 * 把wms拒收的订单同步到wms
	 * @param message
	 * @author zhangmengbin
	 * 
	 */
	@Override
	public void createRejectionOrder(ESynEdiReceiveMessage message) {
		try {
			if(message!=null){
				BarterSaleExchangeOutVo barterSaleExchangeOutVo = JSONObject.parseObject(message.getBody(), BarterSaleExchangeOutVo.class);
				if("107".equals(barterSaleExchangeOutVo.getBusinessType())){
					int rejectQuantity = 0;
					/**
					 * 交易的
					 * buyCount 这个交易的数量
					 */
					List<TradeVO> list = tradeBaseDAO.getByUserTradeNoWms(barterSaleExchangeOutVo.getOrderId()+"");
					BopsExchange bopsExchange = new BopsExchange();
					bopsExchange.setBillNo(barterSaleExchangeOutVo.getBillNo());
					bopsExchange.setBopsOrderId(barterSaleExchangeOutVo.getOrderId());
					bopsExchange.setBopsWarehouseId(bopsExchange.getBopsWarehouseId());
					bopsExchange.setReason(barterSaleExchangeOutVo.getRejectReason());
					bopsExchange.setCreateAt(new Date());
					bopsExchange.setCreateBy("sys");
					if(list!=null && list.size()>0){
						//int 
						TradeVO tradeVO =list.get(0);
						bopsExchange.setAddress(tradeVO.getAddress());
						bopsExchange.setUserName(tradeVO.getReceiver()); //顾客的姓名
						bopsExchange.setPhone(tradeVO.getTelephone());
						bopsExchange.setStatus("0");
						bopsExchange.setTotalPay(tradeVO.getTradePrice()); //交易的应支付金额
						List<BarterSaleExchangeItemOutVo> item = barterSaleExchangeOutVo.getItem();
						
						if(item!=null && item.size()>0){
							List<BopsExchangeItem> bopsExchangeItems = new ArrayList<BopsExchangeItem>();
							for(BarterSaleExchangeItemOutVo barterSaleExchangeItemOutVo :item){
								GoodsMainPO goodsMainPO = goodsMainDAO.selectByPrimaryKey(barterSaleExchangeItemOutVo.getSkuId()+"");
								TradeBasePO tradeBasePO = tradeBaseDAO.getByUserOrderNoWms(barterSaleExchangeItemOutVo.getOrderItemId()+"");
								BopsExchangeItem bopsExchangeItem = new BopsExchangeItem();
								BeanUtils.copyProperties(bopsExchangeItem, barterSaleExchangeItemOutVo);
								bopsExchangeItem.setBopsExchangeId(bopsExchange.getBopsOrderId());
								bopsExchangeItem.setGoodsCode(Long.parseLong(goodsMainPO.getGoodsCode()));
								bopsExchangeItem.setGoodsName(goodsMainPO.getGoodsName());
								bopsExchangeItem.setQuantity(tradeBasePO.getBuyCount());
								bopsExchangeItem.setPrice(Long.parseLong(tradeBasePO.getBuyPrice()+""));
								bopsExchangeItem.setActualQuantity(barterSaleExchangeItemOutVo.getNormalQuantity()+barterSaleExchangeItemOutVo.getGoodsBreakQuantity()+barterSaleExchangeItemOutVo.getDamagePackQuantity()+barterSaleExchangeItemOutVo.getDamageWarehouseQuantity());
								bopsExchangeItem.setCreateAt(new Date());
								bopsExchangeItem.setCreateBy("sys");
								bopsExchangeItems.add(bopsExchangeItem);
								rejectQuantity+=bopsExchangeItem.getActualQuantity();
							}
							//是否全部拒收
							if(rejectQuantity==tradeVO.getBuyCount().intValue()){
								bopsExchange.setAllReject(1);
							}else{
								bopsExchange.setAllReject(0);
							}
							bopsExchangeDAO.insert(bopsExchange);
							if(bopsExchangeItems!=null && bopsExchangeItems.size()>0){
								for(BopsExchangeItem bopsExchangeItem : bopsExchangeItems){
									bopsExchangeItemDAO.insert(bopsExchangeItem);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

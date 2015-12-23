package com.need.service.bops.wms.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.trade.BopsExchangeDAO;
import com.need.dao.bops.trade.BopsExchangeItemDAO;
import com.need.dao.bops.trade.BopsTradeLogisticsDAO;
import com.need.dao.bops.trade.BopsTradePushPullDAO;
import com.need.dao.bops.trade.WmsOrderToErpDAO;
import com.need.dao.bops.wms.ESynEdiReceiveMessageDAO;
import com.need.domain.common.enums.DeliverStatusEnum;
import com.need.domain.common.enums.ServiceProviderEnum;
import com.need.domain.common.enums.TrackingCodeEnum;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.api.trade.TradeAddressPO;
import com.need.domain.po.api.trade.TradeBasePO;
import com.need.domain.po.api.trade.TradeRetrieveStatusRecord;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.trade.BopsExchange;
import com.need.domain.po.bops.trade.BopsExchangeItem;
import com.need.domain.po.bops.trade.BopsTradeLogistics;
import com.need.domain.po.bops.trade.BopsTradePushPull;
import com.need.domain.po.bops.trade.WmsOrderToErp;
import com.need.domain.po.bops.wms.ESynEdiReceiveMessage;
import com.need.domain.vo.trade.LogisticsInfoJsonVO;
import com.need.domain.vo.trade.TradeVO;
import com.need.domain.vo.wms.BarterSaleExchangeItemOutVo;
import com.need.domain.vo.wms.BarterSaleExchangeOutVo;
import com.need.domain.vo.wms.StoreChangeVO;
import com.need.service.api.trade.PortalTradeService;
import com.need.service.bops.goods.BopsGoodsStoreService;
import com.need.service.bops.trade.BopsTradeBaseService;
import com.need.service.bops.wms.WmsToErpService;
import com.need.service.constant.Constants;
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
	@Autowired
	BopsGoodsDAO bopsGoodsDAO;
	@Autowired
	private BopsTradeBaseService bopsTradeBaseService;
	/** 
	 * 吧wms订单到erp 的数据存到wms_order_erp
	 * @author zhangmengbin
	 */
	//@Override
	@Transactional("bops_txManager")
	public void createWmsOrderToErp(ESynEdiReceiveMessage message) {
		try {
			if(message!=null){
				WmsOrderToErp wmsOrderToErp =JSONObject.parseObject(message.getBody(), WmsOrderToErp.class);
				if(wmsOrderToErp!=null){
						wmsOrderToErpDAO.insert(wmsOrderToErp);
						getDeliverTradeListToPullStatus(wmsOrderToErp.getId());
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
	 *订单回传的，修改订单的状态为待收货，减商品的冻结量，插入一条发货的流水
	 * @param userOrderNo
	 */
	public void getDeliverTradeListToPullStatus(long userOrderNo) {
		//PageHelper.startPage(PAGE_NUM, PAGE_SIZE);
		// 要拿取发货信息的订单
		BopsTradePushPull bopsTradePushPull= bopsTradePushPullDAO.getByTradeNo(userOrderNo+"");
			try {
					String outIds = bopsTradePushPull.getLogisticsNo();// 运单号
					TradeBasePO tradePO = (TradeBasePO) tradeBaseDAO.getByUserOrderNoWms(userOrderNo+"");
					BopsTradeLogistics po = new BopsTradeLogistics();
					TradeAddressPO address = tradeAddressDAO.selectByPrimaryKey(tradePO.getAddressId());
					GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(tradePO.getGoodsId());
					LogisticsInfoJsonVO logisticsInfoVO = new LogisticsInfoJsonVO();
					logisticsInfoVO.setLogisticsServiceId("2");
					logisticsInfoVO.setLogisticsNums(outIds);
					po.setAddress(address.getAddress());
					po.setAddressId(address.getAddressId());
					po.setCreateTime(Calendar.getInstance().getTime());
					po.setGoodsSnNo(goods.getGoodsBarcode());
					po.setLogisticsInfo(JSON.toJSONString(logisticsInfoVO));
					po.setLogisticsId(address.getLogisticsId());
					po.setUserId(tradePO.getBuyerId());
					po.setTradeNo(tradePO.getTradeNo());
					//发货，减冻结量，插入发货流水
					bopsTradeBaseService.send(po);
					bopsTradePushPull.setOrderStatus(TradeStatus.WAIT_RECEIVE.code);
					bopsTradePushPull.setTradeStatus(TradeStatus.WAIT_RECEIVE.code);
					bopsTradePushPull.setDeliverStatus(DeliverStatusEnum.OK.code);
					// 发货后插入清关记录表一条数据
					if (!StringUtil.isBlank(bopsTradePushPull.getLogisticsNo())) {
						TradeRetrieveStatusRecord record = new TradeRetrieveStatusRecord();
						record.setOrderNo(bopsTradePushPull.getOrderNo());
						record.setTradeNo(bopsTradePushPull.getTradeNo());
						record.setTrackingCode(TrackingCodeEnum.DELIVER_DONE.code);
						record.setTrackingDesc(TrackingCodeEnum.DELIVER_DONE.userDesc+"("+ServiceProviderEnum.getDesc("2")+"),单号:" + outIds);
						recordRetrieveStatus(record);
					}
					String status = "";
					String pull = bopsTradePushPull.getDeliverPullStatus();
					if (StringUtil.isBlank(pull)) {
						bopsTradePushPull.setDeliverPullStatus(status + "");
					} else if (!pull.contains(status + "")) {
						bopsTradePushPull.setDeliverPullStatus(status + ",");
					}
					bopsTradePushPullDAO.updateByOrderNo(bopsTradePushPull);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	/**
	 * 
	 * @author shenyb 2015年9月16日 下午4:09:46 @Method: send @Description: 发货
	 *         1设置trade status为待收货 2库存锁定数减少 3插入发货表一条记录 @throws
	 */
	@Transactional
	/*public void send(String orderNo, String transporterId, String outIds) {
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
	}*/
	/**
	 * 
	 * @author shenyb 2015年10月31日 下午4:10:27 @Method:
	 *         recordRetrieveStatus @Description:  @param record @throws
	 */
	private void recordRetrieveStatus(TradeRetrieveStatusRecord record) {
		List<TradeRetrieveStatusRecord> retrieveRecord = tradeRetrieveStatusRecordDAO
				.getByRetrieveStatusAndTradeNo(record.getTrackingCode(), record.getTradeNo());
		if (retrieveRecord == null || retrieveRecord.size() == 0) {
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
	@Transactional("bops_txManager")
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
						bopsExchange.setBopsWarehouseId(2);
						List<BarterSaleExchangeItemOutVo> item = barterSaleExchangeOutVo.getItem();
						
						if(item!=null && item.size()>0){
							List<BopsExchangeItem> bopsExchangeItems = new ArrayList<BopsExchangeItem>();
							for(BarterSaleExchangeItemOutVo barterSaleExchangeItemOutVo :item){
								BopsGoods bopsGoods = bopsGoodsDAO.getByGoodsCode(barterSaleExchangeItemOutVo.getSkuId()+"");
								
								TradeBasePO tradeBasePO = tradeBaseDAO.getByUserOrderNoWms(barterSaleExchangeItemOutVo.getOrderItemId()+"");
								BopsExchangeItem bopsExchangeItem = new BopsExchangeItem();
								bopsExchangeItem.setDamagewarehouseQuantity(barterSaleExchangeItemOutVo.getDamageWarehouseQuantity());
								bopsExchangeItem.setDamagepackQuantity(barterSaleExchangeItemOutVo.getDamagePackQuantity());
								bopsExchangeItem.setGoodslossQuantity(barterSaleExchangeItemOutVo.getGoodsLossQuantity());
								bopsExchangeItem.setGoodsbreakQuantity(barterSaleExchangeItemOutVo.getGoodsBreakQuantity());
								bopsExchangeItem.setNormalQuantity(barterSaleExchangeItemOutVo.getNormalQuantity());
								bopsExchangeItem.setBopsExchangeId(bopsExchange.getBopsOrderId());
								bopsExchangeItem.setGoodsCode(Long.parseLong(bopsGoods.getGoodsCode()));
								bopsExchangeItem.setGoodsName(bopsGoods.getGoodsName());
								bopsExchangeItem.setQuantity(tradeBasePO.getBuyCount());
								bopsExchangeItem.setPrice(Long.parseLong(tradeBasePO.getBuyPrice()+""));
								bopsExchangeItem.setActualQuantity(barterSaleExchangeItemOutVo.getNormalQuantity()+barterSaleExchangeItemOutVo.getGoodsBreakQuantity()+barterSaleExchangeItemOutVo.getDamagePackQuantity()+barterSaleExchangeItemOutVo.getDamageWarehouseQuantity());
								bopsExchangeItem.setCreateAt(new Date());
								bopsExchangeItem.setCreateBy("sys");
								bopsExchangeItem.setBopsOrderItemId(barterSaleExchangeItemOutVo.getOrderItemId());
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
									BopsGoods bopsGoods = bopsGoodsDAO.selectByGoodsCode(bopsExchangeItem.getGoodsCode().toString());
									bopsExchangeItemDAO.insert(bopsExchangeItem);
									StoreChangeVO storeChangeVO=new StoreChangeVO();
									storeChangeVO.setNowStoreCount(bopsExchangeItem.getNormalQuantity().intValue());
									storeChangeVO.setDefectiveStore(bopsExchangeItem.getGoodsbreakQuantity().intValue()+bopsExchangeItem.getDamagepackQuantity().intValue()+bopsExchangeItem.getDamagewarehouseQuantity().intValue());
									storeChangeVO.setStoreType("REJECTION");
									storeChangeVO.setStoreNo(bopsExchangeItem.getBopsOrderItemId()+"");
									storeChangeVO.setWarehouseId(2);
									storeChangeVO.setAuthorId(Constants.WMS_USER_ID);
									if(bopsGoods!=null){
										storeChangeVO.setGoodsId(bopsGoods.getGoodsId());
									}
									bopsGoodsStoreService.saveStoreChange(storeChangeVO);
								}
							}
						}
						message.setStatus(2L);
						message.setHandleDate(new Date());
						eSynEdiReceiveMessageDAO.updateByPrimaryKey(message);
					}
				}
			}
		} catch (Exception e) {
			message.setStatus(9L);
			message.setHandleDate(new Date());
			eSynEdiReceiveMessageDAO.updateByPrimaryKey(message);
			e.printStackTrace();
		}
	}
}

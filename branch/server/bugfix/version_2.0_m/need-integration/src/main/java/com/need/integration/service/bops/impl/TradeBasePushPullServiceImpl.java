package com.need.integration.service.bops.impl;
//package com.need.integration.service.impl;
//
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.dom4j.Document;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.alibaba.fastjson.JSON;
//import com.need.integration.common.enums.PayChannelEnum;
//import com.need.integration.common.enums.RetrieveStatusEnum;
//import com.need.integration.dao.jdbc.bops.trade.BopsTradePushPullDAO;
//import com.need.integration.dao.jdbc.bops.trade.po.BopsTradePushPullPO;
//import com.need.integration.dao.jdbc.api.goods.GoodsMainDAO;
//import com.need.integration.dao.jdbc.api.goods.po.GoodsMainPO;
//import com.need.integration.dao.jdbc.api.trade.TradeAddressDAO;
//import com.need.integration.dao.jdbc.api.trade.TradeBaseDAO;
//import com.need.integration.dao.jdbc.api.trade.TradePayDAO;
//import com.need.integration.dao.jdbc.api.trade.po.TradeAddressPO;
//import com.need.integration.dao.jdbc.api.trade.po.TradeBasePO;
//import com.need.integration.dao.jdbc.api.trade.po.TradePayPO;
//import com.need.integration.dao.jdbc.api.user.UserBaseDAO;
//import com.need.integration.dao.jdbc.api.user.po.UserBase;
//import com.need.integration.pub.ResultData;
//import com.need.integration.service.TradeBasePushPullService;
//import com.need.integration.service.vo.TradePushParamVO;
//import com.need.integration.util.OrderClientUtil;
//import com.need.integration.util.StringUtil;
//import com.need.trade.enums.TradeStatus;
//
//@Service
//public class TradeBasePushPullServiceImpl implements TradeBasePushPullService {
//	private Logger logger = Logger.getLogger(TradeBasePushPullServiceImpl.class);
//	@Autowired
//	private TradeBaseDAO tradeBaseDAO;
//	@Autowired
//	private BopsTradePushPullDAO bopsTradePushPullDAO;
//	@Autowired
//	private UserBaseDAO userBaseDAO;
//	@Autowired
//	private GoodsMainDAO goodsMainDAO;
//	@Autowired
//	private TradeAddressDAO tradeAddressDAO;
//	@Autowired
//	private TradePayDAO tradePayDAO;
//
//	//@Scheduled(cron = "${tradePushTime}")
//	public void pushTradeBase() {
//		List<TradeBasePO> tradeList = tradeBaseDAO.queryGlobalByTradeStatus(TradeStatus.WAIT_PLATFORM_SEND.code);
//		for (TradeBasePO trade : tradeList) {
//			// 1如果已经推送成功则不在推送
//			String pushStatus = trade.getPushStatus();
//			if (Boolean.TRUE.toString().toUpperCase().equals(pushStatus)) {
//				continue;
//			}
//			// 2设置要推送的参数信息
//			TradePushParamVO param = new TradePushParamVO();
//			param.setOrderNo(trade.getOrderNo());
//			// 设置商品属性
//			String goodsId = trade.getGoodsId();
//			GoodsMainPO goods = goodsMainDAO.getByGoodsId(goodsId);
//			if (goods == null) {
//				logger.error(
//						String.format("orderNO :%s ,goodsId :%s not exist", trade.getOrderNo(), trade.getGoodsId()));
//				continue;
//			}
//			param.setGoodsNo(goods.getGoodsBarcode());
//			param.setShelfGoodsName(goods.getGoodsName());
//			//商品计量单位和原产国
//			param.setUnitCiq(goods.getGuojianCount());
//			param.setUnitCus(goods.haiguanCount);
//			param.setOriginCountryCiq(goods.getGuojianCountryCode());
//			param.setOriginCountryCus(goods.getHaiguanCountryCode());
//			
//			param.setQuantity(trade.getBuyCount() + "");
//			param.setGoodsValue(trade.getBuyCount() * goods.getDiscountPrice() + "");
//			param.setPriceTotal(trade.getBuyCount() * goods.getDiscountPrice() + "");
//
//			// 设置收获人以及地址信息
//			UserBase user = userBaseDAO.getByUserId(trade.getBuyerId());
//			if (user == null) {
//				logger.error(
//						String.format("orderNO :%s ,userId :%s not exist", trade.getOrderNo(), trade.getBuyerId()));
//				continue;
//			}
//			TradeAddressPO address = tradeAddressDAO.getByAddressId(trade.getAddressId());
//			if (address == null) {
//				logger.error(String.format("orderNO :%s ,addressId :%s not exist", trade.getOrderNo(),
//						trade.getAddressId()));
//				continue;
//			}
//			String logisticsValue = address.getLogisticsValue();
//			param.setConsignee(address.getReceiver());
//			param.setConsigneeTelephone(address.getTelephone());
//			param.setConsigneeAddress(logisticsValue + address.getAddress());
//			param.setConsigneeProvince(address.getLogisticsValue());
//			param.setConsigneeProvince(StringUtil.getAddressProvince(logisticsValue));
//			param.setConsigneeCity(StringUtil.getAddressCity(logisticsValue));
//			param.setConsigneeZone(StringUtil.getAddressZone(logisticsValue));
//			param.setIdNumber(address.getCertificationCard());
//			// 支付流水号
//			TradePayPO tradePay = tradePayDAO.getByTradeNo(trade.getTradeNo());
//			if (tradePay == null) {
//				logger.error(String.format("orderNO :%s , paymentNo not exist", trade.getOrderNo()));
//				continue;
//			}
//			param.setPaymentNo(tradePay.getOutPayNo());
//			String payChannel = trade.getPayChannel();
//			if (PayChannelEnum.ALIPAY.code.equals(payChannel)) {
//				param.setPaymentName("支付宝（中国）网络技术有限公司");
//				param.setPaymentCode("P0001");
//			} else {
//				param.setPaymentName("财付通支付科技有限公司");
//				param.setPaymentCode("P0015");
//			}
//
//			logger.debug("params:" + JSON.toJSONString(param));
//			// 3 调用e贸易接口
//			ResultData result = OrderClientUtil.pushTrade(param);
//			BopsTradePushPullPO pull = createTradePushPull(trade, param, tradePay.getOutPayNo());
//			if (ResultData.SUCCESS == result.getCode()) {
//				/**
//				 * 1前台交易表改为已推送状态 2插入推送记录表，状态为成功
//				 */
//				tradeBaseDAO.updateByOrderNo(trade.getOrderNo(), Boolean.TRUE.toString().toUpperCase());
//				pull.setPushReturnStatus(Boolean.TRUE.toString().toUpperCase());
//				logger.info(String.format("success:trade:%s", JSON.toJSON(trade)));
//
//			} else {
//				// 如果失败则memo存参数加失败消息
//				pull.setMemoPush(JSON.toJSONString(param) + "##" + JSON.toJSONString(result.getMsg()));
//				logger.error(String.format("err reason:%s", result.getMsg()));
//				pull.setPushReturnStatus(Boolean.FALSE.toString().toUpperCase());
//			}
//			// 插入或更新推送记录表
//			BopsTradePushPullPO bopsPull = bopsTradePushPullDAO.getByTradeNo(trade.getTradeNo());
//			if (bopsPull == null) {
//				bopsTradePushPullDAO.insert(pull);
//			} else {
//				bopsTradePushPullDAO.updateByOrderNo(pull);
//			}
//		}
//	}
//
//	/**
//	 * 
//	 * @author shenyb 2015年9月12日 上午11:20:23
//	 * @Method: pullTradeBase
//	 * @Description: 拉取运单号
//	 * @param orderNo
//	 * @see com.need.integration.service.TradeBasePushPullService#pullTradeBase(java.lang.String)
//	 */
//	//@Scheduled(cron = "${tradePullTime}")
//	public void pullTradeBase() {
//		// 已经推送成功，并且没有拿到运单号的订单
//		List<BopsTradePushPullPO> bopsPullList = bopsTradePushPullDAO.queryListToPull();
//		for (BopsTradePushPullPO pull : bopsPullList) {
//			String orderNo = pull.getOrderNo();
//			ResultData result = OrderClientUtil.queryLogisticsNoByOrderNo(orderNo);
//			// 如果拿到运单号就存入到推送记录表
//			if (ResultData.SUCCESS == result.getCode()) {
//				String logisticsNo = (String) result.getData();
//				pull.setLogisticsNo(logisticsNo);
//			} else {
//				// 记录失败信息
//				pull.setMemoPush(result.getMsg());
//			}
//			bopsTradePushPullDAO.updateByOrderNo(pull);
//		}
//	}
//
//	//@Scheduled(cron = "${tradeRetrieveTime}")
//	public void retrieveInfo() {
//		// 拿到运单号的进行获取清关状态
//		List<BopsTradePushPullPO> bopsPullList = bopsTradePushPullDAO.queryListToRetrieve();
//		for (BopsTradePushPullPO pull : bopsPullList) {
//			String orderNo = pull.getOrderNo();
//			String waybillNo = pull.getLogisticsNo();
//			ResultData result = OrderClientUtil.getRetrieveResult(orderNo, waybillNo);
//			// 将清关状态存入数据库
//			if (ResultData.SUCCESS == result.getCode()) {
//				String status = (String) result.getData();
//				logger.info("status:" + status);
//				for (RetrieveStatusEnum disStatusEnum : RetrieveStatusEnum.values()) {
//					if (disStatusEnum.desc.equals(status)) {
//						pull.setRetrieveStatus(disStatusEnum.code);
//						break;
//					}
//				}
//			} else {
//				// 记录失败信息
//				pull.setMemoRetrieve(result.getMsg());
//			}
//			bopsTradePushPullDAO.updateByOrderNo(pull);
//		}
//	}
//	// @Scheduled(cron = "${tradeDeliverTime}")
//	public void tradeDeliverQuery() {
//		// 要拿取发货信息的订单
//		List<BopsTradePushPullPO> tradeList = bopsTradePushPullDAO.queryListToDelievr();
//		for (BopsTradePushPullPO bopsTradePushPull : tradeList) {
//			ResultData result = OrderClientUtil.tradeDeliverQuery(bopsTradePushPull.getOrderNo());
//			// 将发货状态以及返回的xml存入trade表，更新交易主表，后台推送记录表
//			//成功时存入交易表后后台记录表，失败时只存入后台记录表
//			TradeBasePO tradePortal = tradeBaseDAO.getByOrderNo(bopsTradePushPull.getOrderNo());
//			if (ResultData.SUCCESS == result.getCode()) {
//				tradePortal.setDeliverStatus(result.getData().toString());
//				tradePortal.setMemo_deliver(result.getMsg());
//				//TODO 如果全部发货完成，1设置trade status为待收货 2库存锁定数减少 3 插入发货表一条记录
//				//tradePortal.setTradeStatus(TradeStatus.WAIT_RECEIVE);
//				//tradePortal.setOrderStatus(TradeStatus.WAIT_RECEIVE);
//				tradeBaseDAO.updateByPO(tradePortal);
//			}
//			bopsTradePushPull.setDeliverStatus(result.getData().toString());
//			bopsTradePushPull.setMemoDeliver(result.getMsg());
//			bopsTradePushPullDAO.updateByOrderNo(bopsTradePushPull);
//
//		}
//	}
//	/**
//	 * 
//	 * @author shenyb 2015年9月16日 下午4:09:46
//	 * @Method: send 
//	 * @Description: 发货
//	 * 1设置trade status为待收货 
//	 * 2库存锁定数减少
//	 * 3插入发货表一条记录
//	 * @throws
//	 */
//	@Transactional
//	private void send(String orderNo){
//		TradeBasePO tradePortal = tradeBaseDAO.getByOrderNo(orderNo);
//		tradePortal.setTradeStatus(TradeStatus.WAIT_RECEIVE);
//		tradePortal.setOrderStatus(TradeStatus.WAIT_RECEIVE);
//		int count = tradeBaseDAO.updateByPO(tradePortal);
//		if(count>0){
//			
//		}
//		String goodsId = tradePortal.getGoodsId();
//		GoodsMainPO goods = goodsMainDAO.getByGoodsId(goodsId);
//		if(goods==null){
//			
//		}
//		goods.setLockCount(goods.getLockCount()-tradePortal.getBuyCount());
//		int updateCount = goodsMainDAO.updateStoreCount(goods);
//		
//	}
//	private BopsTradePushPullPO createTradePushPull(TradeBasePO trade, TradePushParamVO param, String paymentNo) {
//		BopsTradePushPullPO pull = new BopsTradePushPullPO();
//		pull.setOrderNo(trade.getOrderNo());
//		pull.setTradeNo(trade.getTradeNo());
//		pull.setOrderStatus(trade.getTradeStatus().code);
//		pull.setOutTradeNo(paymentNo);
//		pull.setTradeStatus(trade.getTradeStatus().code);
//		pull.setMemoPush(JSON.toJSONString(param));
//		return pull;
//	}
//
//	public static void main(String[] args) throws Exception {
//		StringBuffer xmlSb = new StringBuffer();
//		xmlSb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
//		xmlSb.append("<root>\n");
//		xmlSb.append("	<pub>\n");
//		xmlSb.append("			<orderNo>" + 2 + "</orderNo>\n");
//		xmlSb.append("			<id>" + 123 + "</id>\n");
//		xmlSb.append("	</pub>\n");
//		xmlSb.append("	<orders>\n");
//		xmlSb.append("	<order>\n");
//		xmlSb.append("			<orderNo>" + 3 + "</orderNo>\n");
//		xmlSb.append("	</order>\n");
//		xmlSb.append("	</orders>\n");
//		xmlSb.append("</root>\n");
//		Document doc = DocumentHelper.parseText(xmlSb.toString()); // 将字符串转为XML
//		Element rootElt = doc.getRootElement(); // 获取根节点
//		System.out.println("根节点：" + rootElt.asXML()); // 拿到根节点的名称
//		// for ( Iterator i = rootElt.elementIterator("pub"); i.hasNext();) {
//		// Element foo = (Element) i.next();
//		// System.out.println(foo.elementText("orderNo"));
//		// }
//		String val = rootElt.element("orders").element("order").element("orderNo").getText();
//		System.out.println("val:" + val);
//		Iterator<?> iter = rootElt.elementIterator("pub");
//		while (iter.hasNext()) {
//			Element ele = (Element) iter.next();
//			String orderNo = ele.elementTextTrim("orderNo"); // 拿到head节点下的子节点title值
//			String id = ele.elementTextTrim("id"); // 拿到head节点下的子节点title值
//
//			System.out.println("orderNo:" + orderNo + ",id:" + id);
//
//		}
//	}
//
//}

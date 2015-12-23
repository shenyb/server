package com.need.integration.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.need.biz.utils.CurrencyUtil;
import com.need.integration.common.birdex.Birdex;
import com.need.integration.common.birdex.BirdexConstant;
import com.need.integration.common.birdex.Utils;
import com.need.integration.common.birdex.bean.Item;
import com.need.integration.common.birdex.bean.LogisticsOrder;
import com.need.integration.common.birdex.bean.Order;
import com.need.integration.common.birdex.bean.PaymentItem;
import com.need.integration.common.birdex.bean.ReceiverDetail;
import com.need.integration.common.birdex.bean.TradeOrder;
import com.need.integration.common.enums.PayChannelEnum;
import com.need.integration.common.enums.WarehouseTypeEnum;
import com.need.integration.dao.jdbc.api.coupon.CouponUserDAO;
import com.need.integration.dao.jdbc.api.coupon.po.CouponUserPO;
import com.need.integration.dao.jdbc.api.goods.GoodsMainDAO;
import com.need.integration.dao.jdbc.api.goods.po.GoodsMainPO;
import com.need.integration.dao.jdbc.api.trade.TradeBaseDAO;
import com.need.integration.dao.jdbc.api.trade.TradePayDAO;
import com.need.integration.dao.jdbc.api.trade.TradePushPullDAO;
import com.need.integration.dao.jdbc.api.trade.po.TradeBasePO;
import com.need.integration.dao.jdbc.api.trade.po.TradePayPO;
import com.need.integration.dao.jdbc.api.trade.po.TradePushPullPO;
import com.need.integration.dao.jdbc.bops.goods.BopsGoodsCategoriesDAO;
import com.need.integration.dao.jdbc.bops.goods.po.BopsGoodsCategoriesPO;
import com.need.integration.dao.jdbc.bops.trade.BopsTradePushPullDAO;
import com.need.integration.dao.jdbc.bops.trade.po.BopsTradePushPullPO;
import com.need.integration.pub.ConstantsProConfig;

@Service
public class BirdexPushPullTask {

	private static Logger LOGGER = LoggerFactory.getLogger(BirdexPushPullTask.class);

	@Autowired
	private TradeBaseDAO tradeBaseDAO;

	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private TradePayDAO tradePayDAO;
//	@Autowired
//	private TradeAddressDAO tradeAddressDAO;
	@Autowired
	private BopsGoodsCategoriesDAO bopsGoodsCategoriesDAO;
	@Autowired
	private BopsTradePushPullDAO bopsTradePushPullDAO;
	@Autowired
	private CouponUserDAO couponUserDAO;
	@Autowired
	private TradePushPullDAO apiTradePushPullDAO;
	@Autowired
	private ConstantsProConfig constantsProConfig;

	private final Map<String, List<TradeBasePO>> tradeMap = new HashMap<String, List<TradeBasePO>>();

	//@Scheduled(cron = "${birdexTradePushTime}")
	public void pushBirdexTrade(){
		tradeMap.clear();
		PageHelper.startPage(1, 200);
		List<TradeBasePO> tradeList = tradeBaseDAO.queryGlobalTradeToPush_HONGKONG();
		if (tradeList == null || tradeList.isEmpty()) {
			return;
		}
		for (TradeBasePO tradeBasePO : tradeList) {
			String pushStatus = tradeBasePO.getPushStatus();
			if (Boolean.TRUE.toString().toUpperCase().equals(pushStatus)) {
				continue;
			}
			// 只处理支付宝，跳过微信
			/**
			 * 放开微信
			 */
//			if (PayChannelEnum.WECHAT.code.equals(tradeBasePO.getPayChannel())) {
//				continue;
//			}
			String userTradeNo = tradeBasePO.getUserTradeNo();
			List<TradeBasePO> list = tradeMap.get(userTradeNo);
			if (list == null) {
				list = new ArrayList<TradeBasePO>();
			}
			list.add(tradeBasePO);
			tradeMap.put(userTradeNo, list);
		}
		Order order = new Order();
		/**
		 * appKey
		 */
		order.setAppKey(constantsProConfig.getBirdexAppKey());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		order.setEventTime(df.format(new Date()));
		order.setOccurTime(df.format(new Date()));
		// 2.1 订单集合
		List<TradeOrder> tradeOrders = new ArrayList<TradeOrder>();
		List<BopsTradePushPullPO> tradePushPullPOs = new ArrayList<BopsTradePushPullPO>();
		for (Map.Entry<String, List<TradeBasePO>> entry : tradeMap.entrySet()) {
			List<TradeBasePO> list = entry.getValue();
			TradeBasePO trade = list.get(0);
			/**
			 * 运费需要单独计算
			 */
			int transportFee = 0;
			for(TradeBasePO tradeBase: list){
				transportFee = transportFee + tradeBase.getTransportFee();
			}
			BopsTradePushPullPO tradePushPullPO = new BopsTradePushPullPO();
			tradePushPullPO.setOrderNo(trade.getUserOrderNo());
			tradePushPullPO.setTradeNo(trade.getUserTradeNo());
			tradePushPullPO.setOrderStatus(trade.getTradeStatus());
			tradePushPullPO.setOutTradeNo(trade.getTradeNo());
			tradePushPullPO.setTradeStatus(trade.getTradeStatus());
			tradePushPullPOs.add(tradePushPullPO);
			// tradePushPullPO.setMemoPush(JSON.toJSONString(param));
			// tradePushPullPO.setBatchNo(param.getBatchNo());
			// 设置要推送的参数信息 基本信息
			TradeOrder tradeOrder = new TradeOrder();
			tradeOrder.setTradeOrderCode(trade.getUserTradeNo());
			tradeOrder.setPaymentName(BirdexConstant.ORDER_PAYMENT_NAME);
			TradePayPO tp = tradePayDAO.getByTradeNo(trade.getTradeNo());
			String paymentNo = tp.getOutPayNo();
			tradeOrder.setPaymentCode(paymentNo);
			tradeOrder.setPaymentTime(df.format(tp.getPayTime()));
//			tradeOrder.setTradeOrderValue(new BigDecimal(trade.getPayPrice()/100));
			tradeOrder.setTradeOrderValue(new BigDecimal(CurrencyUtil.fromFenToYuan(String.valueOf(trade.getPayPrice()))));
			tradeOrder.setTradeOrderValueUnit(BirdexConstant.PRICE_UNIT);
			//金额明细列表  使用在面单打印上
			List<PaymentItem> paymentItems = new ArrayList<PaymentItem>();
			PaymentItem paymentItem1 = new PaymentItem();
			paymentItem1.setPaymentmName("运费");
			paymentItem1.setPaymentmPrice(new BigDecimal(transportFee));
			paymentItem1.setPaymentmPriceUnit(BirdexConstant.PRICE_UNIT);
			paymentItems.add(paymentItem1);
			PaymentItem paymentItem2 = new PaymentItem();
			paymentItem2.setPaymentmName("优惠金额");
			CouponUserPO couponUser = null;
			couponUser = couponUserDAO.getByTradeNo(trade.getTradeNo());
			paymentItem2.setPaymentmPrice(new BigDecimal(null == couponUser ? "0" : CurrencyUtil.fromFenToYuan(String.valueOf(couponUser.getValue()))));
			paymentItem2.setPaymentmPriceUnit(BirdexConstant.PRICE_UNIT);
			paymentItems.add(paymentItem2);
			tradeOrder.setPaymentItems(paymentItems);
			// 设置物流bean
			LogisticsOrder logisticsOrder = new LogisticsOrder();
			logisticsOrder.setLogisticsId(trade.getUserTradeNo());
			logisticsOrder.setWarehouseCode(BirdexConstant.WAREHOUSE_CODE);
			logisticsOrder.setRouteId(BirdexConstant.ROUTEID);
			tradeOrder.setLogisticsOrder(logisticsOrder);
			// 设置商品列表
			List<Item> items = new ArrayList<Item>();
			for (TradeBasePO tradeBasePO : list) {
				GoodsMainPO goodsMainPO = goodsMainDAO.getByGoodsId(tradeBasePO.getGoodsId());
				Item ii = new Item();
				ii.setItemName(goodsMainPO.getGoodsName());
				ii.setItemNo(goodsMainPO.getGoodsCode());
				// TODO 商品分类格式为:'1-美妆&个护'
				String categoryName = "1-美妆&个护";
				BopsGoodsCategoriesPO goodsCategory = bopsGoodsCategoriesDAO.selectByPrimaryKey(goodsMainPO.getGoodsCategoryId());
				if (null != goodsCategory && !StringUtils.isBlank(goodsCategory.getCategoryName())) {
					categoryName = goodsCategory.getCategoryId() + "-" + goodsCategory.getCategoryName();
				}
				ii.setItemCategoryName(categoryName);
				ii.setItemUnitPrice(new BigDecimal(goodsMainPO.getDiscountPrice()/100));
				ii.setItemUnitPriceUnit(BirdexConstant.PRICE_UNIT);
				ii.setItemQuantity(tradeBasePO.getBuyCount());
				ii.setItemImage(constantsProConfig.getPic_domain() + goodsMainPO.getScenePicKey());
				ii.setItemUrl(constantsProConfig.getPic_domain() + goodsMainPO.getScenePicKey());
				/**
				 * 包裹号，笨鸟这边要求写死“SKUPicking”
				 */
				ii.setTrackingNo(BirdexConstant.TRACKINGNO);
				items.add(ii);
			}
			tradeOrder.setItems(items);
			// 收货人
			ReceiverDetail rd = new ReceiverDetail();
			rd.setName(trade.getReceiver());
			rd.setMobile(trade.getTelephone());
			rd.setIdentityNumber(trade.getCertificationCard());
			// 身份证正反面图片
			/**
			 * 目前身份证的正反面还没有保存交易快照（后期需要申延彬配合去修改），需要从地址表中获取
			 */
			rd.setIdentityImages1(constantsProConfig.getPic_domain() + trade.getCertificationPositiveKey());
			rd.setIdentityImages2(constantsProConfig.getPic_domain() + trade.getCertificationNegativeKey());
//			String addressId = trade.getAddressId();
//			TradeAddressPO tradeAddress = tradeAddressDAO.getByAddressId(addressId);
//			rd.setIdentityImages1(constantsProConfig.getPic_domain() + tradeAddress.getCertificationPositiveKey());
//			rd.setIdentityImages2(constantsProConfig.getPic_domain() + tradeAddress.getCertificationNegativeKey());
			rd.setIdentityNumberGa(0);

			
			String addr = trade.getLogisticsValue();
			String[] adds = addr.split("-");
			rd.setCountry("中国");
			rd.setProvince(adds[0]);
			rd.setCity(adds[1]);
			rd.setDistrict(adds[2]);
			rd.setAddressDetail(trade.getAddress());
			tradeOrder.setReceiverDetail(rd);
			tradeOrders.add(tradeOrder);
		}
		order.setTradeOrders(tradeOrders);
		String param = JSONObject.toJSONString(order);
		/**
		 * secretKey
		 */
		String header="";
		try {
			header = Utils.getSignature(param, constantsProConfig.getBirdexSecretKey());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			String result = Birdex.create(constantsProConfig.getBirdex_order_create(), order, header);
			LOGGER.info("birdexResult: " + result);
			JSONObject resultObject = JSONObject.parseObject(result);
			if (resultObject.getString("code").equals("0")) {
				insertTradePush(tradePushPullPOs, Boolean.FALSE.toString().toUpperCase(), result);
			} else {
				LOGGER.error("birdex call error which param : \r\n" + param + "\r\n and result : \r\n" + result);
				// insertTradePush(tradePushPullPOs,
				// Boolean.TRUE.toString().toUpperCase());
			}
		} catch (Exception e) {
			LOGGER.error("birdex connect error", e);
			// insertTradePush(tradePushPullPOs,
			// Boolean.TRUE.toString().toUpperCase());
		}
	}

	private void insertTradePush(List<BopsTradePushPullPO> tradePushPullPOs, String status, String resultJSONObject) {
		for (BopsTradePushPullPO pull : tradePushPullPOs) {
			// 插入或更新推送记录表
			pull.setPushReturnStatus(status);
			pull.setMemoPush(resultJSONObject);
			pull.setWarehouseType(WarehouseTypeEnum.OVERSEA_WAREHOUSE.code);
			BopsTradePushPullPO bopsPull = bopsTradePushPullDAO.getByTradeNo(pull.getTradeNo());
			TradePushPullPO apiPull = new TradePushPullPO();
			BeanUtils.copyProperties(pull, apiPull);
			if (bopsPull == null) {
				bopsTradePushPullDAO.insert(pull);
				apiTradePushPullDAO.insert(apiPull);
			} else {
				bopsTradePushPullDAO.updateByOrderNo(pull);
				apiTradePushPullDAO.updateByOrderNo(apiPull);
			}
		}
	}
}

package com.need.common.core.service.trade.impl;

import com.alibaba.fastjson.JSON;
import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.trade.TradeAddressDAO;
import com.need.common.core.dao.jdbc.trade.TradeBaseDAO;
import com.need.common.core.pub.ServiceException;
import com.need.common.core.service.coupon.CouponService;
import com.need.common.core.service.distribution.UserCommissionAccountService;
import com.need.common.core.service.system.SystemSettingService;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.enums.OrderTypeEnum;
import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.trade.TradeAddressPO;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.CheckParamVO;
import com.need.common.domain.vo.trade.CreateTradeBaseParamVO;
import com.need.common.domain.vo.trade.CreateTradeParamVO;
import com.need.common.domain.vo.trade.TradeCartVO;
import com.need.trade.enums.TradeStatus;
import com.need.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年12月3日 下午2:31:58
 * @ClassName TaxWarehouseConfirmBuy
 * @Description 保税仓下单单独处理
 * @version V2.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年12月3日
 * @modify by reason:{方法名}:{原因}
 */
@Component
public class TaxWarehouseConfirmBuy extends ConfirmBuyAbstract implements InitializingBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaxWarehouseConfirmBuy.class);

	@Autowired
	private CouponService couponService;
	@Autowired
	private TradeAddressDAO tradeAddressDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private TradeBaseService tradeBaseService;
	@Autowired
	private TradeBaseDAO tradeBaseDAO;
	@Autowired
	private UserCommissionAccountService userCommissionAccountService;
	@Autowired
	private SystemSettingService systemSettingService;

	@Override
	protected Message generateTrade(CreateTradeBaseParamVO param) {
		// 商品价格
		int totalSum = 0;
		// 促销价格
		int salesSum = 0;
		// 运费
		int transportFee = 0;
		// 优惠券金额
		int value = 0;
		CreateTradeParamVO vo = null;
		if (param instanceof CreateTradeParamVO) {
			vo = (CreateTradeParamVO) param;
		} else {
			return Message.error(BizReturnCode.SYSTEM_ERR);
		}
		String addressId = vo.getAddressId();
		String userId = vo.getUserId();
		TradeCartVO[] records = vo.getRecords();
		String couponNo = vo.getCouponNo();
		String certificationChannel = vo.getCertificationChannel();
		String useBalance = vo.getUseBalance();

		List<TradeBasePO> resultList = new ArrayList<TradeBasePO>();
		if (StringUtil.isBlank(addressId)) {
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		if (records == null || records.length == 0) {
			return Message.error(BizReturnCode.TRADE_CART_NULL);
		}
		Message message = Message.success();
		Message checkGoodsStoreMessage = checkGoodsStore(records);
		if (checkGoodsStoreMessage.getCode() != Message.SUCCESS) {
			return checkGoodsStoreMessage;
		}
		TradeAddressPO address = tradeAddressDAO.selectByPrimaryKey(addressId);
		if (address == null) {
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		if (StringUtil.isBlank(address.getCertificationNegativeKey())
				|| StringUtil.isBlank(address.getCertificationPositiveKey())) {
			return Message.error(BizReturnCode.TRADE_ADDRESS_PHOTO_NOT_EXISTS);
		}
		if (StringUtil.isBlank(address.getCertificationCard())) {
			return Message.error(BizReturnCode.CERTIFICATIONCARD_NOT_NULL);
		}
		Message result = tradeBaseService.certificationCardVerify(address.getReceiver(), address.getCertificationCard(),
				address.getUserId(), certificationChannel);
		switch (result.getCode()) {
		case BizReturnCode.TRADE_CERTIFICATION_FAIL:
			return Message.error(BizReturnCode.TRADE_CERTIFICATION_FAIL);
		case BizReturnCode.TRADE_CERTIFICATION_TIMEOUT:
			message = Message.error(BizReturnCode.TRADE_CERTIFICATION_TIMEOUT);
			break;
		default:
			break;
		}

		for (TradeCartVO tradeCartVO : records) {
			String goodsId = tradeCartVO.getGoodsId();
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKeyForTrade(goodsId);
			if (goods == null) {// 此时商品有可能库存不充足或是下架
				return Message.error(BizReturnCode.GOODS_NOT_EXIST);
			}
			int discountPrice = goods.getDiscountPrice();

			int buyCount = tradeCartVO.getGoodsCount();
			for (int j = 0; j < buyCount; j++) {
				TradeBasePO trade = new TradeBasePO();
				setDealNo(userId, trade, goodsId, goods, vo.getShareUserId());
				//modify by liyongran 分销增加订单类型
				if(!StringUtils.isEmpty(vo.getOrderType())){
					setBaseParam(userId, goodsId,vo.getOrderType() ,discountPrice, trade);
				}else{
					setBaseParam(userId, goodsId,discountPrice, trade);
				}				
				setAddressParam(address, trade);
				salesSum += discountPrice;
				totalSum += discountPrice;
				resultList.add(trade);
			}
		}
		// 获取运费
		Message transportFeeMessage = getTransportFee(resultList, userId, WarehouseTypeEnum.TAX_WAREHOUSE, totalSum);
		if (transportFeeMessage.getCode() != Message.SUCCESS) {
			return transportFeeMessage;
		} else {
			transportFee = (int) transportFeeMessage.getData().get("transportFee");
		}
		// 使用优惠券
		if (!StringUtil.isBlank(couponNo)) {
			Message discountFeeMessage = splitDiscountFee(resultList, couponNo, userId, salesSum);
			if (discountFeeMessage.getCode() != Message.SUCCESS) {
				return discountFeeMessage;
			} else {
				value = (int) discountFeeMessage.getData().get("value");
			}
		}
		// 使用余额,是(促销价格－优惠券＋运费价格)为标准
		int useCommissionLimit = salesSum - value + transportFee;
		if (Constants.TRUE.equals(useBalance)) {
			Message useCommissionMessage = useCommission(resultList, userId, totalSum, useCommissionLimit);
			if (useCommissionMessage.getCode() != Message.SUCCESS) {
				return useCommissionMessage;
			}
		}
		if (transportFee > 0) {
			Message splitTransportFeeMessage = splitTransportFee(resultList, userId, totalSum, salesSum, transportFee);
			if (splitTransportFeeMessage.getCode() != Message.SUCCESS) {
				return splitTransportFeeMessage;
			}
		}
		message.addData("checkParamVO", null);
		message.addData("resultList", resultList);
		return message;
	}

	private void setDealNo(String userId, TradeBasePO record, String goodsId, GoodsMainPO goods, String shareUserId) {
		String splitTradeNo = BizCodeUtil.generateTradeNo(goods.getGoodsName(), userId);
		String splitUserTradeNo = BizCodeUtil.generateUserOrderNo(userId, goodsId);
		record.setTradeNo(splitTradeNo);
		record.setOrderNo(splitTradeNo);
		record.setUserTradeNo(splitUserTradeNo);
		record.setUserOrderNo(splitUserTradeNo);
		record.setDistributionShareId(shareUserId);
	}

	@Override
	protected Message checkTradeData(List<TradeBasePO> resultList, CheckParamVO vo) {
		Message message = Message.success();
		int totalPrice = 0;
		int paySum = 0;
		int transportFee = 0;
		int discountFee = 0;
		int commission = 0;
		for (TradeBasePO tradePO : resultList) {
			totalPrice += tradePO.getTotalPrice();
			paySum += tradePO.getPayPrice();
			transportFee += tradePO.getTransportFee();
			discountFee += tradePO.getDiscountAmount();
			commission += tradePO.getCommission();
		}
		int computePaySum = totalPrice + transportFee - discountFee - commission;
		if (paySum != computePaySum) {
			LOGGER.error(String.format("checkTradeData err,paySum:%s,computePaySum:%s,resultList:%s", paySum,
					computePaySum, JSON.toJSONString(resultList)));
			return Message.error(BizReturnCode.TRADE_ORDER_PRICE_ERROR);
		}
		return message;
	}

	@Override
	protected Message insertByBatch(List<TradeBasePO> resultList) throws ServiceException {
		Message message = Message.success();
		List<String> resultTradeNos = new ArrayList<String>();
		int updateCount = tradeBaseDAO.insertByBatch(resultList);
		if (updateCount != resultList.size()) {
			ServiceException e = new ServiceException(BizReturnCode.SYSTEM_ERR, "insert trade count error");
			throw e;
		}
		for (TradeBasePO trade : resultList) {
			resultTradeNos.add(trade.getTradeNo());
		}
		message.addData("tradeNoList", resultTradeNos);
		return message;
	}

	private void setBaseParam(String userId, String goodsId, int discountPrice, TradeBasePO trade) {
		trade.setIsCheap(Constants.FALSE);
		trade.setOrderType(OrderTypeEnum.COMMON.code);
		trade.setOrderStatus(TradeStatus.WAIT_PAY);
		trade.setTradeStatus(TradeStatus.WAIT_PAY);
		trade.setBuyerId(userId);
		trade.setWarehouseType(WarehouseTypeEnum.TAX_WAREHOUSE.code);
		trade.setGoodsId(goodsId);
		trade.setBuyCount(Constants.ONE);
		trade.setBuyPrice(discountPrice);
		trade.setTotalPrice(discountPrice);
		trade.setPayPrice(discountPrice);
	}
	
	private void setBaseParam(String userId, String goodsId, String orderType,int discountPrice, TradeBasePO trade) {
		trade.setIsCheap(Constants.FALSE);
		trade.setOrderType(orderType);
		trade.setOrderStatus(TradeStatus.WAIT_PAY);
		trade.setTradeStatus(TradeStatus.WAIT_PAY);
		trade.setBuyerId(userId);
		trade.setWarehouseType(WarehouseTypeEnum.TAX_WAREHOUSE.code);
		trade.setGoodsId(goodsId);
		trade.setBuyCount(Constants.ONE);
		trade.setBuyPrice(discountPrice);
		trade.setTotalPrice(discountPrice);
		trade.setPayPrice(discountPrice);
	}

	protected Message splitTransportFee(List<TradeBasePO> trades, String userId, int totalSum, int salesSum,
			int transportFee) {
		Message success = Message.success();
		int usedSumTransportFee = 0;
		for (int i = 0; i < trades.size(); i++) {
			TradeBasePO trade = trades.get(i);
			int transportFeeTmp = trade.getPayPrice() * transportFee / totalSum;
			if (i == (trades.size() - 1)) {
				transportFeeTmp = transportFee - usedSumTransportFee;
				trade.setTransportFee(transportFeeTmp);
				trade.setPayPrice(trade.getPayPrice() + transportFeeTmp);
				break;
			}
			usedSumTransportFee += transportFeeTmp;
			trade.setTransportFee(transportFeeTmp);
			trade.setPayPrice(trade.getPayPrice() + transportFeeTmp);
		}
		return success;
	}

	/**
	 * 
	 * @author shenyb 2015年12月7日 下午5:45:01
	 * @Method: splitDiscountFee
	 * @Description:
	 * @param trades交易集合
	 * @param couponNo优惠券编号
	 * @param userId用户id
	 * @param salesSum促销价格
	 * @return
	 * @see com.need.common.core.service.trade.impl.ConfirmBuyAbstract#splitDiscountFee(java.util.List,
	 *      java.lang.String, java.lang.String, int)
	 */
	@Override
	protected Message splitDiscountFee(List<TradeBasePO> trades, String couponNo, String userId, int salesSum) {
		Message success = Message.success();
		if (StringUtil.isBlank(couponNo)) {
			return success;
		}
		Collection<String> goodsIds = new HashSet<String>();
		for (TradeBasePO trade : trades) {
			goodsIds.add(trade.getGoodsId());
		}
		Message userCouponMessage = couponService.useCouponPrepay(couponNo, null, salesSum, userId, goodsIds);
		if (userCouponMessage.getCode() != Message.SUCCESS) {
			return userCouponMessage;
		}
		int discountFee = (int) userCouponMessage.getData().get("value");
		if (discountFee <= 0 || salesSum < discountFee) {
			return Message.error(BizReturnCode.TRADE_ORDER_PRICE_ERROR);
		}
		int usedSumDiscountFee = 0;
		for (int i = 0; i < trades.size(); i++) {
			TradeBasePO trade = trades.get(i);
			if (i == (trades.size() - 1)) {
				int lastDecrease = discountFee - usedSumDiscountFee;
				trade.setPayPrice(trade.getPayPrice() - lastDecrease);
				trade.setDiscountAmount(lastDecrease);
			} else {
				int discountFeeTmp = trade.getPayPrice() * discountFee / salesSum;
				usedSumDiscountFee += discountFeeTmp;
				trade.setPayPrice(trade.getPayPrice() - discountFeeTmp);
				trade.setDiscountAmount(discountFeeTmp);
			}
		}
		success.addData("value", discountFee);
		return success;
	}

	/**
	 * 
	 * @author shenyb 2015年12月7日 下午5:47:23
	 * @Method: useCommission
	 * @Description: TODO
	 * @param trades
	 * @param userId
	 * @param totalSum
	 *            商品总价
	 * @param paySum
	 *            促销价格－优惠券＋运费价格
	 * @return
	 * @see com.need.common.core.service.trade.impl.ConfirmBuyAbstract#useCommission(java.util.List,
	 *      java.lang.String, int, int)
	 */
	@Override
	protected Message useCommission(List<TradeBasePO> trades, String userId, int totalSum, int paySum) {
		Message message = Message.success();
		int commisson = userCommissionAccountService.getAvailableBalance(userId, paySum);
		if (commisson > 0) {
			int usedSumcommisson = 0;
			for (int i = 0; i < trades.size(); i++) {
				TradeBasePO trade = trades.get(i);
				if (i == (trades.size() - 1)) {
					int lastDecrease = commisson - usedSumcommisson;
					trade.setPayPrice(trade.getPayPrice() - lastDecrease);
					trade.setCommission(lastDecrease);
				} else {
					int commissonTmp = trade.getPayPrice() * commisson / paySum;
					usedSumcommisson += commissonTmp;
					trade.setPayPrice(trade.getPayPrice() - commissonTmp);
					trade.setCommission(commissonTmp);
				}
			}
		}
		return message;

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.couponService = this.couponService;
		super.userCommissionAccountService = this.userCommissionAccountService;
		super.tradeBaseService = this.tradeBaseService;
		super.systemSettingService = this.systemSettingService;

	}
}

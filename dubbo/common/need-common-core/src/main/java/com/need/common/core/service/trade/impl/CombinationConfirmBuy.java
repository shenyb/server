package com.need.common.core.service.trade.impl;

import com.alibaba.fastjson.JSON;
import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.trade.TradeAddressDAO;
import com.need.common.core.dao.jdbc.trade.TradeBaseDAO;
import com.need.common.core.service.coupon.CouponService;
import com.need.common.core.service.distribution.UserCommissionAccountService;
import com.need.common.core.service.goodsGroup.GoodsGroupService;
import com.need.common.core.service.system.SystemSettingService;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.enums.OrderTypeEnum;
import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.trade.TradeAddressPO;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.*;
import com.need.trade.enums.TradeStatus;
import com.need.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author shenyb 2015年11月18日 下午3:52:46
 * @ClassName CombinationConfirmBuy
 * @Description 组合购买下单
 * @version V2.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年11月18日
 * @modify by reason:{方法名}:{原因}
 */
@Component
public class CombinationConfirmBuy extends ConfirmBuyAbstract implements InitializingBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(CombinationConfirmBuy.class);

	@Autowired
	private TradeBaseDAO tradeBaseDAO;
	@Autowired
	private TradeAddressDAO tradeAddressDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private CouponService couponService;
	@Autowired
	private GoodsGroupService goodsGroupService;
	@Autowired
	private UserCommissionAccountService userCommissionAccountService;
	@Autowired
	private TradeBaseService tradeBaseService;
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
		CreateCombinationTradeParamVO vo;
		if (param instanceof CreateCombinationTradeParamVO) {
			vo = (CreateCombinationTradeParamVO) param;
		} else {
			return Message.error(BizReturnCode.SYSTEM_ERR);
		}
		String addressId = vo.getAddressId();
		String userId = vo.getUserId();
		String couponNo = vo.getCouponNo();
		String warehouseType = vo.getWarehouseType();
		String batchNo = vo.getBatchNo();
		String useBalance = vo.getUseBalance();
		TradeCartVO[] records = vo.getRecords();
		String certificationChannel = vo.getCertificationChannel();
		if (StringUtil.isBlank(addressId)) {
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		if (records == null || records.length == 0) {
			return Message.error(BizReturnCode.TRADE_CART_NULL);
		}
		List<String> goodsIds = new ArrayList<String>();
		int goodsNumber = 0;
		for (TradeCartVO record : records) {
			goodsIds.add(record.getGoodsId());
			goodsNumber += record.getGoodsCount();
		}
		Message getTotalPriceMessage;
		try {
			getTotalPriceMessage = goodsGroupService.getGroupPrice(batchNo, goodsNumber, goodsIds);
		} catch (Exception e) {
			LOGGER.error(String.format("goodsGroupService.getGroupPrice,batchNo:%s,goodsNo:%s,goodsId:%s,获取价格异常：%s",
					batchNo, goodsNumber, goodsIds, e.getMessage()));
			return Message.error(BizReturnCode.TRADE_ORDER_COMBINATION_PRICE_ERROR);
		}
		if (getTotalPriceMessage.getCode() != Message.SUCCESS) {
			return getTotalPriceMessage;
		} else {
			salesSum = (int) getTotalPriceMessage.getData().get("totalPrice");
		}
		if (salesSum <= 0) {
			return Message.error(BizReturnCode.TRADE_ORDER_PRICE_ERROR);
		}
		Message message = Message.success();
		CheckParamVO checkParamVO = new CheckParamVO();
		checkParamVO.setPaySum(salesSum);
		// 库存检查
		List<TradeGoodsStoreVO> goodsList = new ArrayList<TradeGoodsStoreVO>();
		for (TradeCartVO tradeCartVO : records) {
			TradeGoodsStoreVO goodsStoreVO = new TradeGoodsStoreVO();
			goodsStoreVO.setBuyCount(tradeCartVO.getGoodsCount());
			goodsStoreVO.setGoodsId(tradeCartVO.getGoodsId());
			goodsList.add(goodsStoreVO);
		}
		Message checkStoreMessage = tradeBaseService.checkGoodsStoreByList
				(goodsList);
		if (checkStoreMessage.getCode() != Message.SUCCESS) {
			return checkStoreMessage;
		}
		List<TradeBasePO> resultList = new ArrayList<TradeBasePO>();
		TradeAddressPO address = tradeAddressDAO.selectByPrimaryKey(addressId);
		if (address == null) {
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		if (StringUtil.isBlank(address.getCertificationNegativeKey())
				|| StringUtil.isBlank(address.getCertificationPositiveKey())) {
			return Message.error(BizReturnCode.TRADE_ADDRESS_PHOTO_NOT_EXISTS);
		}
		if (StringUtil.isBlank(address.getCertificationCard())) {
			return Message.error(BizReturnCode.TRADE_ORDER_IDCARD_NOT_EXISTS);
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
		TradeBasePO record = new TradeBasePO();
		record.setIsCheap(Constants.FALSE);
		String firstGoodsId = records[0].getGoodsId();
		// 生成交易号
		String tradeNo = BizCodeUtil.generateTradeNo(firstGoodsId, userId);
		record.setTradeNo(tradeNo);
		// 生成用户交易号
		String userTradeNo = BizCodeUtil.generateUserOrderNo(userId, firstGoodsId);
		record.setUserTradeNo(userTradeNo);
		record.setOrderStatus(TradeStatus.WAIT_PAY);// 状态
		record.setTradeStatus(TradeStatus.WAIT_PAY);
		record.setBuyerId(userId);
		record.setWarehouseType(warehouseType);
		record.setOrderType(OrderTypeEnum.COMBINATION.code);
		record.setBatchNo(batchNo);

		for (TradeCartVO tradeCartVO : records) {
			TradeBasePO trade = new TradeBasePO();
			String goodsId = tradeCartVO.getGoodsId();
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKeyForTrade(goodsId);
			if (goods == null) {
				return Message.error(BizReturnCode.GOODS_NOT_EXIST);
			}
			int buyCount = tradeCartVO.getGoodsCount();
			setOrderNo(userId, record, goods);
			record.setGoodsId(tradeCartVO.getGoodsId());
			record.setBuyCount(tradeCartVO.getGoodsCount());
			int discountPrice = goods.getDiscountPrice();
			record.setBuyPrice(discountPrice);
			record.setTotalPrice(buyCount * discountPrice);
			setAddressParam(address, record);
			totalSum += buyCount * discountPrice;
			BeanUtils.copyProperties(record, trade);
			resultList.add(trade);
		}
		Message transportFeeMessage = getTransportFee(resultList, userId, WarehouseTypeEnum.valueOf(warehouseType),
				totalSum);
		if (transportFeeMessage.getCode() != Message.SUCCESS) {
			return transportFeeMessage;
		} else {
			transportFee = (int) transportFeeMessage.getData().get("transportFee");
		}
		updatePayPriceBySalesSum(resultList, salesSum);

		// 使用优惠券
		if (!StringUtil.isBlank(couponNo)) {
			Message discountFeeMessage = splitDiscountFee(resultList, couponNo, userId, salesSum);
			if (discountFeeMessage.getCode() != Message.SUCCESS) {
				return discountFeeMessage;
			} else {
				value = (int) discountFeeMessage.getData().get("value");
			}
		}
		if (Constants.TRUE.equals(useBalance)) {
			// 使用余额,是促销价格－优惠券＋运费价格为标准
			int useCommissionLimit = salesSum - value + transportFee;
			Message useCommissionMessage = useCommission(resultList, userId, totalSum, useCommissionLimit);
			if (useCommissionMessage.getCode() != Message.SUCCESS) {
				return useCommissionMessage;
			}
		}
		if (transportFee > 0) {
			updatePayPriceByTransportFee(transportFee, resultList);
		}
		message.addData("checkParamVO", checkParamVO);
		message.addData("resultList", resultList);
		return message;
	}

	@Override
	protected Message checkTradeData(List<TradeBasePO> resultList, CheckParamVO checkParamVO) {
		Message message = Message.success();
		int combinationSum = checkParamVO.getPaySum();
		TradeBasePO trade = resultList.get(0);
		int paySum = trade.getPayPrice();
		int transportFee = 0;
		int discountFee = 0;
		int commission = 0;
		for (TradeBasePO tradePO : resultList) {
			transportFee += tradePO.getTransportFee();
			discountFee += tradePO.getDiscountAmount();
			commission += tradePO.getCommission();
		}
		int computePaySum = combinationSum + transportFee - discountFee - commission;
		if (paySum != computePaySum) {
			LOGGER.error(String.format("checkTradeData err,paySum:%s,computePaySum:%s,resultList:%s", paySum,
					computePaySum, JSON.toJSONString(resultList)));
			return Message.error(BizReturnCode.TRADE_ORDER_PRICE_ERROR);
		}
		return message;
	}

	@Override
	protected Message insertByBatch(List<TradeBasePO> resultList) {
		Message message = Message.success();
		int updateCount = tradeBaseDAO.insertByBatch(resultList);
		if (updateCount != resultList.size()) {
			return Message.error(BizReturnCode.SYSTEM_ERR);
		}
		message.addData("tradeNo", resultList.get(0).getTradeNo());
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

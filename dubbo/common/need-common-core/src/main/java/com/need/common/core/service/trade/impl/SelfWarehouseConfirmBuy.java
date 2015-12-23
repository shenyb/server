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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年12月3日 下午2:31:39
 * @ClassName SelfWarehouseConfirmBuy
 * @Description 自营仓单独下单处理
 * @version V2.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年12月3日
 * @modify by reason:{方法名}:{原因}
 */
@Component
public class SelfWarehouseConfirmBuy extends ConfirmBuyAbstract implements InitializingBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(SelfWarehouseConfirmBuy.class);

	@Autowired
	private CouponService couponService;
	@Autowired
	private TradeAddressDAO tradeAddressDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private TradeBaseDAO tradeBaseDAO;
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
		String useBalance = vo.getUseBalance();
		Message message = Message.success();
		Message checkGoodsStoreMessage = checkGoodsStore(records);
		if (checkGoodsStoreMessage.getCode() != Message.SUCCESS) {
			return checkGoodsStoreMessage;
		}
		List<TradeBasePO> resultList = new ArrayList<TradeBasePO>();
		if (StringUtil.isBlank(addressId)) {
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		if (records == null || records.length == 0) {
			return Message.error(BizReturnCode.TRADE_CART_NULL);
		}
		TradeAddressPO address = tradeAddressDAO.selectByPrimaryKey(addressId);
		if (address == null) {
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		TradeBasePO record = new TradeBasePO();
		record.setIsCheap(Constants.FALSE);
		//modify by liyongran 分销下单增加订单类型
		if(!StringUtils.isEmpty(vo.getOrderType())){
			record.setOrderType(vo.getOrderType());
		}else{
			record.setOrderType(OrderTypeEnum.COMMON.code);
		}
		
		String firstGoodsId = records[0].getGoodsId();
		// 生成交易号
		String tradeNo = BizCodeUtil.generateTradeNo(firstGoodsId, userId);
		record.setTradeNo(tradeNo);
		// 用户交易号
		String userTradeNo = BizCodeUtil.generateUserOrderNo(userId, firstGoodsId);
		record.setUserTradeNo(userTradeNo);
		record.setOrderStatus(TradeStatus.WAIT_PAY);// 状态
		record.setTradeStatus(TradeStatus.WAIT_PAY);
		record.setBuyerId(userId);
		record.setWarehouseType(WarehouseTypeEnum.SELF_WAREHOUSE.code);
		record.setDistributionShareId(vo.getShareUserId());
		for (TradeCartVO tradeCartVO : records) {
			TradeBasePO trade = new TradeBasePO();
			String goodsId = tradeCartVO.getGoodsId();
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKeyForTrade(goodsId);
			if (goods == null) {// 此时商品有可能库存不充足或是下架
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
			salesSum += buyCount * discountPrice;
			totalSum += buyCount * discountPrice;
			BeanUtils.copyProperties(record, trade);
			resultList.add(trade);
		}
		Message transportFeeMessage = getTransportFee(resultList, userId, WarehouseTypeEnum.SELF_WAREHOUSE, totalSum);
		if (transportFeeMessage.getCode() != Message.SUCCESS) {
			return transportFeeMessage;
		} else {
			transportFee = (int) transportFeeMessage.getData().get("transportFee");
		}
		updatePayPriceBySalesSum(resultList, salesSum);
		if (!StringUtils.isEmpty(couponNo)) {
			// 优惠券
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
		message.addData("checkParamVO", null);
		message.addData("resultList", resultList);
		return message;
	}

	@Override
	protected Message checkTradeData(List<TradeBasePO> resultList, CheckParamVO vo) {
		Message message = Message.success();
		TradeBasePO trade = resultList.get(0);
		int totalPrice = 0;
		int paySum = 0;
		int transportFee = 0;
		int discountFee = 0;
		int commission = 0;
		paySum += trade.getPayPrice();
		for (TradeBasePO tradePO : resultList) {
			totalPrice += tradePO.getTotalPrice();
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
	protected Message insertByBatch(List<TradeBasePO> resultList) {
		Message message = Message.success();
		List<String> tradeNoList = new ArrayList<String>();
		int updateCount = tradeBaseDAO.insertByBatch(resultList);
		if (updateCount != resultList.size()) {
			return Message.error(BizReturnCode.SYSTEM_ERR);
		}
		tradeNoList.add(resultList.get(0).getTradeNo());
		message.addData("tradeNoList", tradeNoList);
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

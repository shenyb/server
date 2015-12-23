package com.need.common.core.service.trade.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.goods.GoodsItemsDAO;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.trade.TradeAddressDAO;
import com.need.common.core.dao.jdbc.trade.TradeBaseDAO;
import com.need.common.core.dao.jdbc.trade.TradeCartDAO;
import com.need.common.core.dao.jdbc.trade.TradeItemsGoodsRecordDAO;
import com.need.common.core.pub.ConstantsProConfig;
import com.need.common.core.service.coupon.CouponService;
import com.need.common.core.service.system.SystemSettingService;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.core.utils.WXPay;
import com.need.common.domain.enums.GoodsTypeEnums;
import com.need.common.domain.enums.PayChannelEnum;
import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.po.api.goods.GoodsItemsPO;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.trade.TradeAddressPO;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.po.api.trade.TradeItemsGoodsRecordPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.distribution.ConsumeGoodsVO;
import com.need.common.domain.vo.trade.*;
import com.need.http.HttpClientProxy;
import com.need.trade.enums.TradeStatus;
import com.need.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TradeBaseServiceImpl implements TradeBaseService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TradeBaseServiceImpl.class);

	@Autowired
	private TradeBaseDAO tradeBaseDAO;
	@Autowired
	private TradeCartDAO tradeCartDAO;
	@Autowired
	private TradeAddressDAO tradeAddressDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private CouponService couponService;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	@Autowired
	private GoodsItemsDAO goodsItemsDAO;
	@Autowired
	private TradeItemsGoodsRecordDAO tradeItemsGoodsRecordDAO;
    @Autowired
    private SystemSettingService systemSettingService;

	@Transactional
	@Override
	public Message addTradeBaseService(TradeCartVO[] records, String userId, String tradeNo) {
		/** TODO Auto-generated method stub */
		if (records == null) {
			return null;
		}
		TradeBasePO record = new TradeBasePO();
		record.setTradeNo(tradeNo);
		record.setOrderStatus(TradeStatus.WAIT_PAY);// 状态
		record.setTradeStatus(TradeStatus.WAIT_PAY);
		record.setBuyerId(userId);
		String firstGoodsId = records[0].getGoodsId();
		String userTradeNo = BizCodeUtil.generateUserOrderNo(userId, firstGoodsId);// 暂时先用第一个商品的
																					// 20150922
		record.setUserTradeNo(userTradeNo);// Addy liyongran 20150922 新增用户交易编号
		String warehouseTypeUnique = "";
		for (int i = 0; i < records.length; i++) {
			// add by shenyb20150826 判断库存是否充足
			String goodsId = records[i].getGoodsId();
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(goodsId);
			// Addy liyongran 20151003
			if (goods == null) {// 此时商品有可能库存不充足或是下架
				return Message.error(BizReturnCode.GOODS_NOT_ENOUGH);
			}

			// add by shenyb 20151104
			String warehouseType = goods.getWarehouseType();
			if (WarehouseTypeEnum.OVERSEA_WAREHOUSE.code.equals(warehouseType)) {
				return Message.error(BizReturnCode.TRADE_NEED_VERSION_FOR_OVERSEA_WAREHOUSE_LOW);
			}
			if (i == 0) {
				warehouseTypeUnique = warehouseType;
			}
			if (i > 0) {
				if (!warehouseTypeUnique.equals(warehouseType)) {
					return Message.error(BizReturnCode.TRADE_BUY_MANY_WAREHOUSE);
				}
			}
			// Addy liyongran 20151003
			int storeCount = goods.getStoreCount();
			int buyCount = records[i].getGoodsCount();
			if (storeCount < buyCount) {
				return Message.error(BizReturnCode.GOODS_STORE_NOT_ENOUGH);
			}

			String orderNo = BizCodeUtil.generateTradeNo(goodsId, userId);// 生成交易号

			String userOrderNo = BizCodeUtil.generateUserOrderNo(userId, goodsId);// Addy
																					// liyongran
																					// 20150922
																					// 新增用户交易子编号
			record.setUserOrderNo(userOrderNo);// Addy liyongran 20150922
												// 新增用户交易子编号

			record.setOrderNo(orderNo);
			record.setGoodsId(records[i].getGoodsId());
			record.setBuyCount(records[i].getGoodsCount());
			int discountPrice = goods.getDiscountPrice();// 实时查询价格
			record.setBuyPrice(discountPrice);
			record.setTotalPrice(buyCount * discountPrice);
			// edit by 20151104 shenyb 为了避免数据有问题，和1.3数据保持一致
			record.setIsCheap(Constants.FALSE);
			record.setWarehouseType(goods.getWarehouseType());

			tradeBaseDAO.insert(record);
			tradeCartDAO.deleteByCartId(records[i].getCartId());
		}

		return Message.success();
	}

	@Override
	public Message addTradeBaseService_V1_2(Message message, TradeCartVO[] records, String addressId, String userId,
			String tradeNo, String couponNo, String certificationChannel) {
		String warehouseTypeUnique = "";// 保证一次只能购买一个仓商品
		if (records == null || StringUtil.isBlank(addressId)) {
			return null;
		}
		TradeAddressPO address = tradeAddressDAO.selectByPrimaryKey(addressId);
		if (address == null) {
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		TradeBasePO record = new TradeBasePO();
		record.setTradeNo(tradeNo);
		record.setOrderStatus(TradeStatus.WAIT_PAY);// 状态
		record.setTradeStatus(TradeStatus.WAIT_PAY);
		record.setBuyerId(userId);
		String firstGoodsId = records[0].getGoodsId();
		String userTradeNo = BizCodeUtil.generateUserOrderNo(userId, firstGoodsId);
		record.setUserTradeNo(userTradeNo);// Addy liyongran 20150922 新增用户交易编号
		int sum = 0;
		Boolean isGoodsGlobal = Boolean.FALSE;
		Boolean avoidRepeatVerify = Boolean.TRUE;
		for (int i = 0; i < records.length; i++) {
			// add by shenyb20150826 判断库存是否充足
			String goodsId = records[i].getGoodsId();
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(goodsId);
			// Addy liyongran 20151003
			if (goods == null) {// 此时商品有可能库存不充足或是下架
				return Message.error(BizReturnCode.GOODS_NOT_ENOUGH);
			}
			// add by shenyb 20151104
			String warehouseType = goods.getWarehouseType();
			if (WarehouseTypeEnum.OVERSEA_WAREHOUSE.code.equals(warehouseType)) {
				return Message.error(BizReturnCode.TRADE_NEED_VERSION_FOR_OVERSEA_WAREHOUSE_LOW);
			}
			if (i == 0) {
				warehouseTypeUnique = warehouseType;
			}
			if (i > 0) {
				if (!warehouseTypeUnique.equals(warehouseType)) {
					return Message.error(BizReturnCode.TRADE_BUY_MANY_WAREHOUSE);
				}
			}
			// Addy liyongran 20151003
			int storeCount = goods.getStoreCount();
			int buyCount = records[i].getGoodsCount();
			if (storeCount < buyCount) {
				return Message.error(BizReturnCode.GOODS_STORE_NOT_ENOUGH);
			}
			if (Boolean.FALSE.toString().toUpperCase().equals(isGoodsGlobal.toString().toUpperCase())
					&& Boolean.TRUE.toString().toUpperCase().equals(goods.getIsGlobal())) {
				isGoodsGlobal = Boolean.TRUE;
			}
			if (avoidRepeatVerify && isGoodsGlobal && address != null
					&& !StringUtil.isBlank(address.getCertificationCard())) {
				Message result = certificationCardVerify(address.getReceiver(), address.getCertificationCard(),
						address.getUserId(), certificationChannel);
				if (Message.SUCCESS != result.getCode()) {
					message = Message.error(BizReturnCode.TRADE_CERTIFICATION_FAIL);
				}
				avoidRepeatVerify = Boolean.FALSE;
			}
			String orderNo = BizCodeUtil.generateTradeNo(goodsId, userId);// 生成交易号

			String userOrderNo = BizCodeUtil.generateUserOrderNo(userId, goodsId);
			record.setUserOrderNo(userOrderNo);

			record.setOrderNo(orderNo);
			record.setGoodsId(records[i].getGoodsId());
			record.setBuyCount(records[i].getGoodsCount());
			int discountPrice = goods.getDiscountPrice();// 实时查询价格
			record.setBuyPrice(discountPrice);
			record.setTotalPrice(buyCount * discountPrice);
			setAddressParam(address, record);
			sum += buyCount * discountPrice;

			// edit by shenyb 为了避免数据有问题，和1.3数据保持一致
			record.setIsCheap(Constants.FALSE);
			record.setWarehouseType(goods.getWarehouseType());

			tradeBaseDAO.insert(record);
			tradeCartDAO.deleteByCartId(records[i].getCartId());
		}
		// 如果有优惠券则减去优惠券面值
		if (!StringUtil.isBlank(couponNo)) {
			Message userCouponMessage = couponService.useCouponPrepay(couponNo, tradeNo, sum, userId, null);
			if (userCouponMessage.getCode() != Message.SUCCESS) {
				return userCouponMessage;
			}
			int decrease = (int) userCouponMessage.getData().get("value");
			sum = sum - decrease;
		}
		// 写入支付价格
		tradeBaseDAO.updatePayPriceByTradeNo(userTradeNo, sum);
		message.addData("tradeNo", tradeNo);
		return message;
	}

	@Override
	public List<TradeBaseResultVO> selectTradeBaseByTradeNo(String tradeNo) {
		List<TradeBasePO> tradeList = tradeBaseDAO.getByTradeNo(tradeNo);
		if (tradeList == null) {
			return null;
		}
		List<TradeBaseResultVO> list = new ArrayList<TradeBaseResultVO>();
		for (TradeBasePO tradeBasePO : tradeList) {
			TradeBaseResultVO tradeBaseResultVO = new TradeBaseResultVO();
			BeanUtils.copyProperties(tradeBasePO, tradeBaseResultVO);
			list.add(tradeBaseResultVO);
		}
		return list;
	}

	@Override
	public List<TradeBaseResult> selectTradeBaseByOrderNo(String tradeNo, String userId) {
		List<TradeBaseResult> baseList = tradeBaseDAO.getTradeBaseByTradeNo(tradeNo, userId);
		if (baseList == null || baseList.size() == 0) {
			return null;
		}
		return baseList;
	}

	@Override
	public int countTrade(String userId) {
		return tradeBaseDAO.countTrade(userId);
	}

	@Override
	public Message getTradeTotalPrice(String tradeNo) {
		/** TODO Auto-generated method stub */
		Message message = Message.success();
		if (tradeNo == null) {
			return Message.error(BizReturnCode.TRADE_NOT_EXIST);
		}

		Integer totalTradePirce = 0;// 交易总价
		List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
		/** 集合为空不应返回success，应该error */
		if (trades == null || trades.isEmpty()) {
			LoggerFactory.getLogger(this.getClass())
					.error("-+-+-+-+-+-+-+-+-+trade is empty which tradeNo is" + tradeNo);
			return Message.error(BizReturnCode.TRADE_NOT_EXIST);
		}
		for (TradeBasePO tradeBasePO : trades) {
			int goodsPrice = goodsMainDAO.getByGoodsIdAndBuyCount(tradeBasePO.getGoodsId(), tradeBasePO.getBuyCount());
			if (goodsPrice == 0) {
				LoggerFactory.getLogger(this.getClass())
						.error("-+-+-+-+-+-+-+-+-+goods is 0 price which tradeNo is" + tradeNo);
				return Message.error(BizReturnCode.GOODS_STORE_NOT_ENOUGH);
			}

			totalTradePirce += goodsPrice;// 累计 订单总价
			message.addData("totalPrice", totalTradePirce);
		}

		return message;
	}

	@Override
	@Transactional
	public int updateGoodsPriceByTradeNo(String tradeNo) {
		int result = 0;
		List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
		for (TradeBasePO tradeBasePO : trades) {
			int goodsPrice = goodsMainDAO.getDiscountPriceByGoodsId(tradeBasePO.getGoodsId());
			int orderTotal = goodsPrice * tradeBasePO.getBuyCount();// 订单总价
			result += tradeBaseDAO.updateBuyPriceAndTotalPriceByTradeNo(tradeBasePO.getOrderNo(), goodsPrice,
					orderTotal);
		}

		return result;
	}

	@Override
	@Transactional
	public Message createTrade(Message success, String userId, String goodsId, int buyCount) {
		GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(goodsId);
		if (goods == null) {
			return Message.error(BizReturnCode.TRADE_GOODS_NOT_EXIST);
		}
		int storeCount = goods.getStoreCount();
		String goodsName = goods.getGoodsName();
		int goodsPrice = goods.getDiscountPrice();
		// 库存不足
		if (storeCount < buyCount) {
			return Message.error(BizReturnCode.GOODS_STORE_NOT_ENOUGH);
		}
		String tradeNo = BizCodeUtil.generateTradeNo(goodsName, userId);
		String userTradeNo = BizCodeUtil.generateUserOrderNo(userId, goodsId);// Addy
																				// liyongran
																				// 20150922
																				// 新增用户交易编号

		TradeBasePO po = new TradeBasePO();
		po.setIsCheap(Constants.FALSE);
		po.setBuyCount(buyCount);
		po.setBuyerId(userId);
		po.setBuyPrice(goodsPrice);
		po.setGoodsId(goodsId);
		po.setOrderStatus(TradeStatus.WAIT_PAY);
		po.setTradeNo(tradeNo);
		po.setOrderNo(tradeNo);

		po.setUserTradeNo(userTradeNo);// Addy liyongran 20150922 新增用户交易编号
		po.setUserOrderNo(userTradeNo);// Addy liyongran 20150922 新增用户交易编号

		po.setTotalPrice(goodsPrice * buyCount);
		po.setTradeStatus(TradeStatus.WAIT_PAY);

		// edit by shenyb 20151103为了避免数据有问题，和1.3数据保持一致
		po.setIsCheap(Constants.FALSE);
		String warehouseType = goods.getWarehouseType();
		po.setWarehouseType(warehouseType);
		// edit 20151104 1.3版本以下无法购买海外仓商品，但是客户端写死了，无法提示正确的错误信息
		if (WarehouseTypeEnum.OVERSEA_WAREHOUSE.code.equals(warehouseType)) {
			return Message.error(BizReturnCode.TRADE_NEED_VERSION_FOR_OVERSEA_WAREHOUSE_LOW);
		}

		int result = tradeBaseDAO.insert(po);
		if (result > 0) {
			success.addData("tradeNo", tradeNo);
		}
		return success;
	}

	/**
	 * 团便宜下单 @author shenyb 2015年10月26日 下午2:39:51 @Method:
	 * createCheapTrade @Description: TODO @param success @param userId @param
	 * goodsId @param buyCount @return @throws
	 */
	@Override
	@Transactional
	public Message createCheapTrade(String userId, String goodsId, int cheapPrice, String addressId) {
		Message success = Message.success();
		GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(goodsId);
		if (goods == null) {
			return Message.error(BizReturnCode.TRADE_GOODS_NOT_EXIST);
		}
		String warehouseType = goods.getWarehouseType();
		int storeCount = goods.getStoreCount();
		String goodsName = goods.getGoodsName();

		// 库存不足
		if (storeCount < Constants.ONE) {
			return Message.error(BizReturnCode.GOODS_STORE_NOT_ENOUGH);
		}
		TradeAddressPO address = tradeAddressDAO.selectByPrimaryKey(addressId);
		if (address == null) {
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		if (WarehouseTypeEnum.OVERSEA_WAREHOUSE.code.equals(warehouseType)) {
			if (StringUtil.isBlank(address.getCertificationNegativeKey())
					|| StringUtil.isBlank(address.getCertificationPositiveKey())) {
				return Message.error(BizReturnCode.TRADE_ADDRESS_PHOTO_NOT_EXISTS);
			}
		}
		if (WarehouseTypeEnum.OVERSEA_WAREHOUSE.code.equals(warehouseType)
				|| WarehouseTypeEnum.TAX_WAREHOUSE.code.equals(warehouseType)) {
			if (!StringUtil.isBlank(address.getCertificationCard())) {
				Message result = certificationCardVerify(address.getReceiver(), address.getCertificationCard(),
						address.getUserId(), "");
				if (Message.SUCCESS != result.getCode()) {
					success = Message.error(BizReturnCode.TRADE_CHEAP_CERTIFICATION_FAIL);
				}
			} else {
				return Message.error(BizReturnCode.TRADE_ORDER_IDCARD_NOT_EXISTS);
			}
		}

		String tradeNo = BizCodeUtil.generateTradeNo(goodsName, userId);
		String userTradeNo = BizCodeUtil.generateUserOrderNo(userId, goodsId);
		TradeBasePO po = new TradeBasePO();

		po.setWarehouseType(warehouseType);

		po.setIsCheap(Constants.TRUE);
		po.setBuyCount(Constants.ONE);
		po.setBuyerId(userId);
		po.setBuyPrice(cheapPrice);
		po.setGoodsId(goodsId);
		po.setOrderStatus(TradeStatus.WAIT_PAY);
		po.setTradeNo(tradeNo);
		po.setOrderNo(tradeNo);

		po.setUserTradeNo(userTradeNo);
		po.setUserOrderNo(userTradeNo);
		po.setTotalPrice(cheapPrice);
		po.setTradeStatus(TradeStatus.WAIT_PAY);

		po.setAddressId(address.getAddressId());
		po.setAddress(address.getAddress());
		po.setCertificationCard(address.getCertificationCard().toUpperCase());
		po.setLogisticsValue(address.getLogisticsValue());
		po.setReceiver(address.getReceiver());
		po.setTelephone(address.getTelephone());
		po.setCertificationNegativeKey(address.getCertificationNegativeKey());
		po.setCertificationPositiveKey(address.getCertificationPositiveKey());
		// 是否需要运费
        int transportFee = systemSettingService.getTransportFee(WarehouseTypeEnum.valueOf(warehouseType), cheapPrice);
        po.setTransportFee(transportFee);
        cheapPrice += transportFee;
		po.setPayPrice(cheapPrice);
		int result = tradeBaseDAO.insert(po);
		if (result > 0) {
			success.addData("tradeNo", tradeNo);
		}
		return success;
	}

	@Override
	public List<TradeGoodVO> getTradeGoodsList(String userId) {
		List<TradeGoodVO> goodsMainPO = tradeBaseDAO.selectTradeGoods(userId, TradeStatus.TRADE_SUCCESS.code);
		return goodsMainPO;
	}

	@Override
	public Message payMark(String tradeNo, String addressId, PayChannelEnum payChannel) {
		return null;
	}

	@Override
	@Transactional
	public int updateAddressAndChannelByTradeNo(String tradeNo, String addressId, String payChannel) {
		/** TODO Auto-generated method stub */
		return tradeBaseDAO.updateAddressAndChannelByTradeNo(tradeNo, addressId, payChannel);
	}

	@Override
	@Transactional
	public Message updateAddressAndChannelByTradeNo_V1_1(String tradeNo, String addressId, String payChannel) {
		/** TODO Auto-generated method stub */
		TradeAddressPO addressPO = tradeAddressDAO.selectByPrimaryKey(addressId);
		// add by shenyb 添加身份认证
		Boolean isGlobal = Boolean.FALSE;
		List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
		if (trades != null && trades.size() > 0) {
			TradeBasePO trade = trades.get(0);
			if (trade != null) {
				GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(trade.getGoodsId());
				if (goods != null && Boolean.TRUE.toString().toUpperCase().equals(goods.getIsGlobal())) {
					isGlobal = Boolean.TRUE;
				}
			}
		}
		if (isGlobal && addressPO != null && !StringUtil.isBlank(addressPO.getCertificationCard())) {
			// 由于第三方身份验证有问题 临时去掉身份验证 modify by liyongran 20151017
			Message result = Message.success();// certificationCardVerify(addressPO.getReceiver(),
												// addressPO.getCertificationCard(),addressPO.getUserId(),
												// null);

			if (Message.SUCCESS != result.getCode()) {
				return Message.error(BizReturnCode.TRADE_CERTIFICATION_FAIL);
			}
		}
		TradeAdrressAndPayChannelParamVO paramVO = new TradeAdrressAndPayChannelParamVO();
		if (addressPO != null) {
			paramVO.setTradeNo(tradeNo);
			paramVO.setAddress(addressPO.getAddress());
			paramVO.setCertificationCard(addressPO.getCertificationCard());
			paramVO.setLogisticsValue(addressPO.getLogisticsValue());
			paramVO.setPayChannel(payChannel);
			paramVO.setReceiver(addressPO.getReceiver());
			paramVO.setTelephone(addressPO.getTelephone());
			paramVO.setAddressId(addressId);
		} else {
			return Message.success();
		}
		int retCode = tradeBaseDAO.updateAddressAndChannelByTradeNo_V1_1(paramVO);
		return Message.success();
	}

	@Override
	@Transactional
	public void updatePriceByTradeNo(String tradeNo, int sum) {
		List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
		if (trades == null || trades.size() == 0) {
			return;
		}
		for (TradeBasePO tradeBasePO : trades) {
			int goodsPrice = goodsMainDAO.getDiscountPriceByGoodsId(tradeBasePO.getGoodsId());
			int totalPrice = goodsPrice * tradeBasePO.getBuyCount();
			tradeBaseDAO.updateBuyPriceAndTotalPriceAndPayPriceByTradeNo(tradeBasePO.getOrderNo(), goodsPrice,
					totalPrice, sum);
		}
	}

	@Override
	public Message certificationCardVerify(String username, String idCard, String userId, String certificationChannel) {
		Message message = Message.success();
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("idCard", idCard.toUpperCase());
		params.put("userId", userId);
		params.put("certificationChannel", certificationChannel);
		try {
			String result = HttpClientProxy.sendPostRequest(constantsProConfig.getCertificationUrl(), params, "utf-8");
			// {"code":200,"data":{"result":0},"msg":"操作成功!"}
			message = JSON.parseObject(result, new TypeReference<Message>() {
			});// 转换对象
		} catch (Exception e) {
			LOGGER.error(String.format("HttpClientProxy.sendPostRequest 身份验证公共接口异常:%s", e.getMessage()));
			// throw new RuntimeException("HttpClientProxy.sendPostRequest
			// 身份验证公共接口异常", e);
			return Message.error(BizReturnCode.TRADE_CERTIFICATION_TIMEOUT);
		}
		if (message.getCode() != BizReturnCode.SUCCESS) {
			return Message.error(BizReturnCode.TRADE_CERTIFICATION_FAIL);
		}
		int resultCode = (int) message.getData().get("result");
		if (resultCode == 0) {
			message.setMsg("身份认证成功");
			return message;
		}
		return Message.error(BizReturnCode.TRADE_CERTIFICATION_FAIL);

	}

	@Override
	@Transactional
	public Message addTradeBaseService_V1_3(Message message, TradeCartVO[] records, String addressId, String userId,
			String couponNo, String certificationChannel, String warehouseType) {
		// 如果不拆单只返回一个订单号，如果拆单，返回多个
		if (StringUtil.isBlank(addressId)) {
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		if (StringUtil.isBlank(warehouseType)) {
			return Message.error(BizReturnCode.TRADE_WAREHOUSE_TYPE_NULL);
		}
		if(records==null||records.length==0){
			return Message.error(BizReturnCode.TRADE_CART_NULL);
		}
		Boolean isGoodsGlobal = WarehouseTypeEnum.TAX_WAREHOUSE.code.equals(warehouseType);
		if (isGoodsGlobal) {
			int buyCount = 0;
			for (TradeCartVO tradeCartVO : records) {
				buyCount += tradeCartVO.getGoodsCount();
				if (buyCount > Constants.TAX_WAREHOUSE_BUY_ONCE_MAX) {
					return Message.error(BizReturnCode.TRADE_TAX_WAREHOUSE_BUY_LIMIT);
				}
			}
		}
		TradeAddressPO address = tradeAddressDAO.selectByPrimaryKey(addressId);
		if (address == null) {
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		if (WarehouseTypeEnum.OVERSEA_WAREHOUSE.code.equals(warehouseType)) {
			if (StringUtil.isBlank(address.getCertificationNegativeKey())
					|| StringUtil.isBlank(address.getCertificationPositiveKey())) {
				return Message.error(BizReturnCode.TRADE_ADDRESS_PHOTO_NOT_EXISTS);
			}
		}
		if (WarehouseTypeEnum.OVERSEA_WAREHOUSE.code.equals(warehouseType)
				|| WarehouseTypeEnum.TAX_WAREHOUSE.code.equals(warehouseType)) {
			if (StringUtil.isBlank(address.getCertificationCard())) {
				return Message.error(BizReturnCode.TRADE_ORDER_IDCARD_NOT_EXISTS);
			}
		}
		List<String> resultList = new ArrayList<String>();
		List<TradeBasePO> resultTradeList = new ArrayList<TradeBasePO>();

		TradeBasePO record = new TradeBasePO();
		record.setIsCheap(Constants.FALSE);
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
		record.setWarehouseType(warehouseType);
		int sum = 0;
		Boolean isOverseaWarehouse = WarehouseTypeEnum.OVERSEA_WAREHOUSE.code.equals(warehouseType);
		Boolean avoidRepeatVerify = Boolean.TRUE;
		for (TradeCartVO tradeCartVO : records) {
			String goodsId = tradeCartVO.getGoodsId();
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKeyForTrade(goodsId);
			if (goods == null) {// 此时商品有可能库存不充足或是下架
				return Message.error(BizReturnCode.GOODS_NOT_EXIST);
			}
			int storeCount = goods.getStoreCount();
			int buyCount = tradeCartVO.getGoodsCount();
			if (storeCount < buyCount) {
				return Message.error(BizReturnCode.GOODS_STORE_NOT_ENOUGH);
			}

			// 使用warehouseType进行数据检查,保证一次只能购买一个仓商品
			if (!warehouseType.equals(goods.getWarehouseType())) {
				return Message.error(BizReturnCode.TRADE_BUY_MANY_WAREHOUSE);
			}
			if (avoidRepeatVerify && (isGoodsGlobal || isOverseaWarehouse)
					&& !StringUtil.isBlank(address.getCertificationCard())) {
				Message result = certificationCardVerify(address.getReceiver(), address.getCertificationCard(),
						address.getUserId(), certificationChannel);
				if (Message.SUCCESS != result.getCode()) {
					message = Message.error(BizReturnCode.TRADE_CERTIFICATION_FAIL);
				}
				avoidRepeatVerify = Boolean.FALSE;
			}
			// 如果满足拆单条件则进行拆单
			if (isGoodsGlobal) {
				for (int j = 0; j < buyCount; j++) {
					setDealNo(userId, record, goodsId, goods);
					record.setGoodsId(tradeCartVO.getGoodsId());
					record.setBuyCount(1);
					int discountPrice = goods.getDiscountPrice();
					record.setBuyPrice(discountPrice);
					record.setTotalPrice(discountPrice);
					setAddressParam(address, record);
					// 支付价格
					record.setPayPrice(discountPrice);
					sum += discountPrice;
					addTradeBasePO(resultTradeList, record);
					// 要返回的订单号
					resultList.add(record.getTradeNo());
				}
			} else {
				setOrderNo(userId, record, goods);
				record.setGoodsId(tradeCartVO.getGoodsId());
				record.setBuyCount(tradeCartVO.getGoodsCount());
				int discountPrice = goods.getDiscountPrice();
				record.setBuyPrice(discountPrice);
				record.setTotalPrice(buyCount * discountPrice);
				setAddressParam(address, record);
				sum += buyCount * discountPrice;
				record.setPayPrice(buyCount * discountPrice);
				addTradeBasePO(resultTradeList, record);
			}
		}
		WarehouseTypeEnum warehouseTypeEnum = WarehouseTypeEnum.valueOf(warehouseType);
		int paySum = splitTransportFee(resultTradeList, sum, warehouseTypeEnum, userId);
		if (!StringUtil.isBlank(couponNo)) {
			message = splitDiscountFee(resultTradeList, sum, paySum, couponNo, warehouseTypeEnum, userId);
			if (Message.SUCCESS != message.getCode()) {
				return message;
			}
		}
		if (!isGoodsGlobal) {
			resultList.add(tradeNo);
		}
		int insertCount = insertByBatch(resultTradeList);
		
		LOGGER.info(String.format("resultTradeList:trade:%s", JSON.toJSONString(resultTradeList)));
		if (insertCount != resultTradeList.size()) {
			LOGGER.error(
					String.format("addTradeBaseService_V1_3 insert error.:%s", JSON.toJSONString(resultTradeList)));
			return message;
		}
		for (TradeCartVO tradeCartVO : records) {
			tradeCartDAO.deleteByCartId(tradeCartVO.getCartId());
		}
		message.addData("tradeNoList", resultList);
		return message;

	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public void saveTradeItemsGoodsRecord(TradeCartVO[] originalTradeCartVOArray, Message message, String warehouseType){
		if(WarehouseTypeEnum.TAX_WAREHOUSE.code.equals(warehouseType)){
			return ;
		}
		String tradeNo = "";
		List<String> tradeNoList;
		tradeNoList = (List<String>)message.getData().get("tradeNoList");
		if(null != tradeNoList && tradeNoList.size() > 0){
			tradeNo = tradeNoList.get(0);
			if(StringUtils.isBlank(tradeNo)){
				return ;
			}
		}
		else{
			return ;
		}
		List<TradeItemsGoodsRecordPO> tradeItemsGoodsRecordList = new ArrayList<TradeItemsGoodsRecordPO>();
		for(TradeCartVO originalTradeVO :originalTradeCartVOArray){
			if(null == originalTradeVO)
				return ;
			String goodsId = originalTradeVO.getGoodsId();
			GoodsMainPO goodsMain = goodsMainDAO.selectByPrimaryKey(goodsId);
			if(GoodsTypeEnums.MORE.code.equals(goodsMain.getGoodsType())){
				List<GoodsItemsPO> goodsItemsList = goodsItemsDAO.queryByGoodsGroupId(goodsId);
				if(null != goodsItemsList && goodsItemsList.size() > 0){
					for(GoodsItemsPO goodsItems: goodsItemsList){
						TradeItemsGoodsRecordPO tradeItemsGoodsRecord = new TradeItemsGoodsRecordPO();
						tradeItemsGoodsRecord.setGoodsCount(goodsItems.getGoodsCount());
						tradeItemsGoodsRecord.setGoodsGroup(originalTradeVO.getGoodsCount());
						tradeItemsGoodsRecord.setGoodsGroupId(originalTradeVO.getGoodsId());
						tradeItemsGoodsRecord.setGoodsId(goodsItems.getGoodsId());
						tradeItemsGoodsRecord.setTradeNo(tradeNo);
						tradeItemsGoodsRecordList.add(tradeItemsGoodsRecord);
					}
				}
			}
		}
		if(null != tradeItemsGoodsRecordList && tradeItemsGoodsRecordList.size() > 0){
			tradeItemsGoodsRecordDAO.saveBatchRecord(tradeItemsGoodsRecordList);
		}
	}
	
	private Message splitDiscountFee(List<TradeBasePO> trades, int sum, int paySum, String couponNo,
			WarehouseTypeEnum warehosueType, String userId) {
		Message success = Message.success();
		if (StringUtil.isBlank(couponNo)) {
			return success;
		}
		Collection<String> goodsIds = new HashSet<String>();
		for (TradeBasePO trade : trades) {
			goodsIds.add(trade.getGoodsId());
		}
		String useCouponTradeNo = null;
		if (WarehouseTypeEnum.TAX_WAREHOUSE != warehosueType || trades.size() == 1) {
			useCouponTradeNo = trades.get(0).getTradeNo();
		}
		Message userCouponMessage = couponService.useCouponPrepay(couponNo, useCouponTradeNo, sum, userId, goodsIds);
		if (userCouponMessage.getCode() != Message.SUCCESS) {
			return userCouponMessage;
		}
		int discountFee = (int) userCouponMessage.getData().get("value");
		if (discountFee <= 0 || sum <= discountFee) {
			return success;
		}
		if (WarehouseTypeEnum.TAX_WAREHOUSE == warehosueType) {
			int usedSumDiscountFee = 0;
			for (int i = 0; i < trades.size(); i++) {
				TradeBasePO trade = trades.get(i);
				if (i == (trades.size() - 1)) {
					int lastDecrease = discountFee - usedSumDiscountFee;
					if (trade.getPayPrice() < lastDecrease) {
						trades.get(0).setPayPrice(trades.get(0).getPayPrice() - lastDecrease);
					} else {
						trade.setPayPrice(trade.getPayPrice() - lastDecrease);
					}
					break;
				}
				int discountFeeTmp = trade.getPayPrice() * discountFee / sum;
				usedSumDiscountFee += discountFeeTmp;
				trade.setPayPrice(trade.getPayPrice() - discountFeeTmp);
			}
		} else {
			int payPrice = paySum - discountFee;
			for (TradeBasePO po : trades) {
				po.setPayPrice(payPrice);
			}
		}
		return success;
	}

	public int splitTransportFee(List<TradeBasePO> trades, int sum, WarehouseTypeEnum warehosueType, String userId) {
		int totalSum = sum;
		int transportFee = systemSettingService.getTransportFee(warehosueType, sum);
		Collections.sort(trades, new Comparator<TradeBasePO>() {
			@Override
			public int compare(TradeBasePO tradeO, TradeBasePO tradeT) {
				return tradeT.getTotalPrice() - tradeO.getTotalPrice();
			}
		});
		if (WarehouseTypeEnum.TAX_WAREHOUSE == warehosueType) {
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
		} else {
			for (int i = 0; i < trades.size(); i++) {
				TradeBasePO trade = trades.get(i);
				if (i == 0) {
					trade.setTransportFee(transportFee);
					totalSum += transportFee;
					trade.setPayPrice(totalSum);
				} else {
					trade.setPayPrice(totalSum);
				}
			}
		}
		return totalSum;

	}



	private void addTradeBasePO(List<TradeBasePO> resultTradeList, TradeBasePO record) {
		TradeBasePO trade = new TradeBasePO();
		BeanUtils.copyProperties(record, trade);
		resultTradeList.add(trade);
	}

	private void setDealNo(String userId, TradeBasePO record, String goodsId, GoodsMainPO goods) {
		String splitTradeNo = BizCodeUtil.generateTradeNo(goods.getGoodsName(), userId);
		String splitUserTradeNo = BizCodeUtil.generateUserOrderNo(userId, goodsId);
		record.setTradeNo(splitTradeNo);
		record.setOrderNo(splitTradeNo);
		record.setUserTradeNo(splitUserTradeNo);
		record.setUserOrderNo(splitUserTradeNo);
	}

	public int insertByBatch(List<TradeBasePO> trades) {
		return tradeBaseDAO.insertByBatch(trades);

	}

	private void setOrderNo(String userId, TradeBasePO record, GoodsMainPO goods) {
		String orderNo = BizCodeUtil.generateTradeNo(goods.getGoodsId(), userId);
		record.setOrderNo(orderNo);
		String userOrderNo = BizCodeUtil.generateUserOrderNo(userId, goods.getGoodsId());
		record.setUserOrderNo(userOrderNo);
	}

	private void setAddressParam(TradeAddressPO address, TradeBasePO record) {
		record.setAddressId(address.getAddressId());
		record.setAddress(address.getAddress());
		String idCard = address.getCertificationCard();
		record.setCertificationCard(idCard == null ? "" : idCard.toUpperCase());
		record.setCertificationNegativeKey(address.getCertificationNegativeKey());
		record.setCertificationPositiveKey(address.getCertificationPositiveKey());
		record.setLogisticsValue(address.getLogisticsValue());
		record.setReceiver(address.getReceiver());
		record.setTelephone(address.getTelephone());
	}

	@Override
	public Message getConsumeByUserId(String userId, int pageNum, int pageSize) {
		Message message = Message.success();
		PageHelper.startPage(pageNum, pageSize);// 用分页工具对结果进行分页
		Page<TradeBaseResult> page = (Page<TradeBaseResult>) tradeBaseDAO.queryConsumeByUserId(userId);
		List<ConsumeGoodsVO> resultList = new ArrayList<ConsumeGoodsVO>();
		for (TradeBaseResult tradeBaseResult : page.getResult()) {
			ConsumeGoodsVO consumeGoodsVO = new ConsumeGoodsVO();
			BeanUtils.copyProperties(tradeBaseResult, consumeGoodsVO);
			consumeGoodsVO.setTradeTime(tradeBaseResult.getTradeTime().getTime());
			consumeGoodsVO.setUserTradeNo(tradeBaseResult.getUserOrderNo());
			resultList.add(consumeGoodsVO);
		}
		message.addData("goodsList", resultList);
		message.addData("totalCount", page.getTotal());

		return message;
	}

	@Override
	public Message checkGoodsStore(String tradeNo) {
		Message message = Message.success();
		List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
		if (trades == null || trades.size() == 0) {
			return Message.error(BizReturnCode.TRADE_NOT_EXIST);
		}
		for (TradeBasePO tradeBasePO : trades) {
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKeyForTrade(tradeBasePO.getGoodsId());
			if (goods == null) {// 此时商品有可能库存不充足或是下架
				return Message.error(BizReturnCode.GOODS_NOT_EXIST);
			}
			int storeCount = goods.getStoreCount();
			int buyCount = tradeBasePO.getBuyCount();
			if (storeCount < buyCount) {
				return Message.error(BizReturnCode.GOODS_STORE_NOT_ENOUGH);
			}
		}
		return message;
	}

	@Override
	public Message checkGoodsStoreByList(List<TradeGoodsStoreVO> list) {
		Message message = Message.success();
		StringBuffer sb = new StringBuffer();
		for (TradeGoodsStoreVO goodsItem : list) {
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(goodsItem.getGoodsId());
			if (goods == null) {// 此时商品有可能库存不充足或是下架
				continue;
			}
			int storeCount = goods.getStoreCount();
			int buyCount = goodsItem.getBuyCount();
			if (storeCount < buyCount) {
				sb.append(goods.getGoodsName() + ",");
			}
		}
		if (sb.length() > 0) {
			Message.error(BizReturnCode.GOODS_NOT_ENOUGH, sb.toString().substring(0, sb.toString().length() - 1)
					+ Message.error(BizReturnCode.GOODS_NOT_ENOUGH).getMsg());
		}
		return message;
	}

    @Override
    public int getRealTradeCountByUserId(String userId) {
        return tradeBaseDAO.countRealTrade(userId);
    }

	@Override
	@Transactional
	public void testTransaction() {
		List<TradeBasePO> list = tradeBaseDAO.getByTradeNo("20151109165207da587f629b2");
		TradeBasePO trade1 = list.get(0);
		trade1.setOrderStatus(TradeStatus.WAIT_PAY);
		tradeBaseDAO.updateByOrderNo(trade1);
		int m = 1/0;
		TradeBasePO trade2= list.get(1);
		trade2.setOrderStatus(TradeStatus.WAIT_PLATFORM_SEND);
		tradeBaseDAO.updateByOrderNo(trade2);
	}

	@Override
	public Message wecharPaymentUnifiedOrder(String openId,String tradeNo,String ip) {
		/** TODO Auto-generated method stub*/
		Message success= Message.success();	
		Map<String,Object> params=new HashMap<String,Object>();
		//查询所有订单信息
		List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
		TradeStatus status = trades.get(0).getTradeStatus();
		if (status != TradeStatus.WAIT_PAY) {
			return Message.error(BizReturnCode.TRADE_STATUS_NOT_WAIT_PAY);
		}
		int sum = trades.get(0).getPayPrice();
		params.put("total_fee", sum);
		StringBuffer subject = new StringBuffer();
		for (TradeBasePO tradeBasePO : trades) {
			subject.append(goodsMainDAO.selectByPrimaryKey(tradeBasePO.getGoodsId()).getGoodsName() + ",");
		}
		String title = subject.length() > 40 ? subject.substring(0, 40) : subject.toString();
		WXPay pay=new WXPay();
		Map<Object,Object>  resultMap=pay.payByJSAPI(title, sum, trades.get(0).getUserTradeNo(), ip,openId,"JSAPI");
		String prepay_id =resultMap.get("prepay_id").toString();
		success.addData("prepay_id", prepay_id);
		return success;
	}
	
	
	public static void main(String[] args) {
		Map<String,Object> params=new HashMap<String,Object>();
		WXPay pay=new WXPay();
		//String result =pay.payByJSAPI("tess", 120, "2015082118308497e98ce1a21", "182.92.182.28","111");
		
		
		
		
	}
	
	
}

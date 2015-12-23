package com.need.common.core.service.trade.impl;

import com.alibaba.fastjson.JSON;
import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.cheap.CheapBaseDAO;
import com.need.common.core.dao.jdbc.cheap.CheapOpenDAO;
import com.need.common.core.dao.jdbc.cheap.CheapOpenUserDAO;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.trade.TradeAddressDAO;
import com.need.common.core.dao.jdbc.trade.TradeBaseDAO;
import com.need.common.core.dao.jdbc.user.UserBaseDAO;
import com.need.common.core.service.system.SystemSettingService;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.enums.CheapStatusEnum;
import com.need.common.domain.enums.OrderTypeEnum;
import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.po.api.cheap.CheapBasePO;
import com.need.common.domain.po.api.cheap.CheapOpenPO;
import com.need.common.domain.po.api.cheap.CheapOpenUserPO;
import com.need.common.domain.po.api.cheap.CheapOpenUserPOKey;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.trade.TradeAddressPO;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.*;
import com.need.trade.enums.TradeStatus;
import com.need.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author shenyb 2015年11月18日 下午3:52:46
 * @ClassName CheapConfirmBuy
 * @Description 团便宜下单接口
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年11月18日
 * @modify by reason:{方法名}:{原因}
 */
@Component
public class CheapConfirmBuy extends ConfirmBuyAbstract implements InitializingBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(CheapConfirmBuy.class);

	@Autowired
	private TradeBaseDAO tradeBaseDAO;
	@Autowired
	private TradeBaseService tradeBaseService;
	@Autowired
	private TradeAddressDAO tradeAddressDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private CheapOpenDAO cheapOpenDAO;
	@Autowired
	private UserBaseDAO userBaseDAO;
	@Autowired
	private CheapOpenUserDAO cheapOpenUserDAO;
	@Autowired
	private CheapBaseDAO cheapBaseDao;
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
		CreateCheapTradeParamVO vo = null;
		if (param instanceof CreateCheapTradeParamVO) {
			vo = (CreateCheapTradeParamVO) param;
		} else {
			return Message.error(BizReturnCode.SYSTEM_ERR);
		}
		int cheapOpenId = vo.getCheapOpenId();
		String userId = vo.getUserId();
		String addressId = vo.getAddressId();
		String useBalance = vo.getUseBalance();
		// 根据团便宜id获取商品id和价格
		CheapBasePO cheapBasePO = null;
		Message cheapBasePOMessage = getCheapBasePO(cheapOpenId, userId);
		if (cheapBasePOMessage.getCode() != Message.SUCCESS) {
			return cheapBasePOMessage;
		} else {
			cheapBasePO = (CheapBasePO) cheapBasePOMessage.getData().get("cheapBasePO");
		}
		String goodsId = cheapBasePO.getGoodsId();
		salesSum = cheapBasePO.getCheapPrice();
		Message success = Message.success();
		CheckCheapParamVO checkCheapParamVO = new CheckCheapParamVO();
		checkCheapParamVO.setCheapPrice(salesSum);
		GoodsMainPO goods = goodsMainDAO.selectByPrimaryKeyForTrade(goodsId);
		if (goods == null) {
			return Message.error(BizReturnCode.TRADE_GOODS_NOT_EXIST);
		}
		String warehouseType = goods.getWarehouseType();
		String goodsName = goods.getGoodsName();
		totalSum = goods.getDiscountPrice();
		// 库存检查
		List<TradeGoodsStoreVO> goodsList = new ArrayList<TradeGoodsStoreVO>();
		TradeGoodsStoreVO goodsStoreVO = new TradeGoodsStoreVO();
		goodsStoreVO.setBuyCount(Constants.ONE);
		goodsStoreVO.setGoodsId(goods.getGoodsId());
        goodsList.add(goodsStoreVO);
		Message checkStoreMessge = tradeBaseService.checkGoodsStoreByList(goodsList);
		if (checkStoreMessge.getCode() != Message.SUCCESS) {
			return checkStoreMessge;
		}
		TradeAddressPO address = tradeAddressDAO.selectByPrimaryKey(addressId);
		if (address == null) {
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
        if (StringUtil.isBlank(address.getCertificationNegativeKey())
                || StringUtil.isBlank(address.getCertificationPositiveKey())) {
            return Message.error(BizReturnCode.TRADE_ADDRESS_PHOTO_NOT_EXISTS);
        }
        if (!StringUtil.isBlank(address.getCertificationCard())) {
            Message result = tradeBaseService.certificationCardVerify(address.getReceiver(),
                    address.getCertificationCard(), address.getUserId(), "");
            if (Message.SUCCESS != result.getCode()) {
                success = Message.error(BizReturnCode.TRADE_CHEAP_CERTIFICATION_FAIL);
            }
        } else {
            return Message.error(BizReturnCode.TRADE_ORDER_IDCARD_NOT_EXISTS);
        }

		String tradeNo = BizCodeUtil.generateTradeNo(goodsName, userId);
		String userTradeNo = BizCodeUtil.generateUserOrderNo(userId, goodsId);
		TradeBasePO po = new TradeBasePO();

		setTradeBaseParam(userId, goodsId, salesSum, warehouseType, tradeNo, userTradeNo, po);

		setAddressParam(address, po);
		Message transportFeeMessage = getTransportFee(null, null, WarehouseTypeEnum.valueOf(warehouseType), totalSum);
		if (transportFeeMessage.getCode() != Message.SUCCESS) {
			return transportFeeMessage;
		} else {
			transportFee = (int) transportFeeMessage.getData().get("transportFee");
		}
		po.setPayPrice(salesSum);
		List<TradeBasePO> resultList = new ArrayList<TradeBasePO>();
		resultList.add(po);
		if (Constants.TRUE.equals(useBalance)) {
			int useCommissionLimit = salesSum+transportFee;
			Message useCommissionMessage = useCommission(resultList, userId, totalSum, useCommissionLimit);
			if (useCommissionMessage.getCode() != Message.SUCCESS) {
				return useCommissionMessage;
			}
		}
		if (transportFee > 0) {
			updatePayPriceByTransportFee(transportFee, resultList);
		}
		success.addData("checkParamVO", checkCheapParamVO);
		success.addData("resultList", resultList);
		return success;
	}

	/**
	 * @author shenyb 2015年12月4日 下午3:41:44 @Method: getCheapBasePO @Description:
	 *         TODO @param cheapOpenId @param userId @return @throws
	 */

	protected Message getCheapBasePO(int cheapOpenId, String userId) {
		Message message = Message.success();
		CheapOpenPO cheapOpenPO = cheapOpenDAO.selectByPrimaryKey(cheapOpenId);
		if (cheapOpenPO == null) {
			return Message.error(BizReturnCode.CHEAP_NOT_OPEN);
		}
		if (CheapStatusEnum.CLOSED.status.equals(cheapOpenPO.getCheapStatus())) {
			return Message.error(BizReturnCode.CHEAP_CLOSED);
		}
		if (CheapStatusEnum.OPEN.status.equals(cheapOpenPO.getCheapStatus())) {
			return Message.error(BizReturnCode.CHEAP_NOT_COMPLETE);
		}
		if (cheapOpenPO.getCompleteTime() == null || System.currentTimeMillis()
				- cheapOpenPO.getCompleteTime().getTime() > Constants.CHEAP_TRADE_EXPIRE) {
			return Message.error(BizReturnCode.CHEAP_TRADE_EXPIRED);
		}
		UserBasePO user = userBaseDAO.selectByPrimaryKey(userId);
		if (user == null) {
			return Message.error(BizReturnCode.USER_NOT_EXIST);
		}
		List<CheapOpenUserPO> tradedList = cheapOpenUserDAO.queryByParams(user.getMobile(), cheapOpenPO.getCheapNo(),
				Boolean.TRUE.toString().toUpperCase());
		if (!tradedList.isEmpty()) {
			return Message.error(BizReturnCode.CHEAP_TRADED);
		}
		CheapOpenUserPOKey cheapOpenUserPOKey = new CheapOpenUserPOKey(cheapOpenId, user.getMobile());
		CheapOpenUserPO cheapOpenUserPO = cheapOpenUserDAO.selectByPrimaryKey(cheapOpenUserPOKey);
		if (cheapOpenUserPO == null) {
			return Message.error(BizReturnCode.CHEAP_NOT_JOIN);
		}
		if (Boolean.TRUE.toString().equals(cheapOpenUserPO.getTraded())) {
			return Message.error(BizReturnCode.CHEAP_TRADED);
		}
		CheapBasePO cheapBasePO = cheapBaseDao.selectById(cheapOpenPO.getCheapNo());
		message.addData("cheapBasePO", cheapBasePO);
		return message;
	}

	@Override
	protected Message checkTradeData(List<TradeBasePO> resultList, CheckParamVO checkVO) {
		Message message = Message.success();
		CheckCheapParamVO vo = null;
		if (checkVO instanceof CheckCheapParamVO) {
			vo = (CheckCheapParamVO) checkVO;
		}
		int cheapPrice = vo.getCheapPrice();
		TradeBasePO trade = resultList.get(0);
		int paySum = trade.getPayPrice();
		int transportFee = trade.getTransportFee();
		int discountFee = trade.getDiscountAmount();
		int commission = trade.getCommission();
		int computePaySum = cheapPrice + transportFee - discountFee - commission;
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
		int result = tradeBaseDAO.insertByBatch(resultList);
		if (result > 0) {
			message.addData("tradeNo", resultList.get(0).getTradeNo());
		} else {
			LOGGER.error(String.format("insert err:tradeBase:%s", JSON.toJSONString(resultList)));
		}
		return message;
	}

	private void setTradeBaseParam(String userId, String goodsId, int cheapPrice, String warehouseType, String tradeNo,
			String userTradeNo, TradeBasePO po) {
		po.setWarehouseType(warehouseType);

		po.setIsCheap(Constants.TRUE);
        po.setOrderType(OrderTypeEnum.CHEAP.code);
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
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.couponService = this.couponService;
		super.userCommissionAccountService = this.userCommissionAccountService;
		super.tradeBaseService = this.tradeBaseService;
        super.systemSettingService = this.systemSettingService;

	}

}

package com.need.common.core.service.trade.impl;

import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.pub.ServiceException;
import com.need.common.core.service.coupon.CouponService;
import com.need.common.core.service.distribution.UserCommissionAccountService;
import com.need.common.core.service.system.SystemSettingService;
import com.need.common.core.service.trade.IConfirmBuy;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.enums.UserCommissionAccountOperateTypeEnum;
import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.trade.TradeAddressPO;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.CheckParamVO;
import com.need.common.domain.vo.trade.CreateTradeBaseParamVO;
import com.need.common.domain.vo.trade.TradeCartVO;
import com.need.common.domain.vo.trade.TradeGoodsStoreVO;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author shenyb 2015年12月3日 下午2:34:12
 * @version V2.0
 * @ClassName ConfirmBuyAbstract
 * @Description 下单抽象类
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年12月3日
 * @modify by reason:{方法名}:{原因}
 */
public abstract class ConfirmBuyAbstract implements IConfirmBuy {
    protected CouponService couponService;
    protected UserCommissionAccountService userCommissionAccountService;
    protected TradeBaseService tradeBaseService;
    protected SystemSettingService systemSettingService;

    /**
     * @param param
     * @return
     * @author shenyb 2015年12月1日 下午3:26:58
     * @Method: createTrade
     * @Description: // 1生成订单，2检查金额 3 插入数据库
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Message createTrade(CreateTradeBaseParamVO param) throws ServiceException {
        List<TradeBasePO> resultList;
        CheckParamVO checkParamVO;
        // 1生成订单
        Message result = generateTrade(param);
        if (result.getCode() != Message.SUCCESS && result.getCode() != BizReturnCode.TRADE_CERTIFICATION_TIMEOUT) {
            return result;
        } else {
            resultList = (List<TradeBasePO>) result.getData().get("resultList");
            checkParamVO = (CheckParamVO) result.getData().get("checkParamVO");
        }
        // 2检查金额
        Message checkMessage = checkTradeData(resultList, checkParamVO);
        if (checkMessage.getCode() != Message.SUCCESS) {
            return checkMessage;
        }
        //2.1如果使用了余额,则扣除余额
        Message useCommissionAccountMessage = useCommissionAccount(resultList);
        if (useCommissionAccountMessage.getCode() != Message.SUCCESS) {
            return useCommissionAccountMessage;
        }
        // 3 插入数据库,并返回结果
        return insertByBatch(resultList);
    }

    protected abstract Message generateTrade(CreateTradeBaseParamVO param);

    protected abstract Message checkTradeData(List<TradeBasePO> resultList, CheckParamVO checkParamVO);

    /**
     * 使用佣金
     * @param resultList
     * @return
     */
    protected Message useCommissionAccount(List<TradeBasePO> resultList) {
        int useCommission = 0;
        for (TradeBasePO trade : resultList) {
            useCommission += trade.getCommission();
        }
        if (useCommission > 0) {
            Message useMessage = userCommissionAccountService.decreaseAccount(resultList.get(0).getBuyerId(), useCommission, UserCommissionAccountOperateTypeEnum.TRADE_CONFIRM,resultList
                    .get(0).getTradeNo());
            return useCommission != Message.SUCCESS ? useMessage : Message.success();
        }
        return Message.success();
    }

    protected abstract Message insertByBatch(List<TradeBasePO> resultList) throws ServiceException;

    protected Message getTransportFee(List<TradeBasePO> trades, String userId, WarehouseTypeEnum warehouseType,
                                      int totalSum) {
        Message message = Message.success();
        int transportFee = systemSettingService.getTransportFee(warehouseType, totalSum);
        message.addData("transportFee", transportFee);
        return message;
    }

    /**
     * @author shenyb 2015年12月7日 下午6:03:30 @Method:
     * splitDiscountFee @Description: TODO @param trades @param
     * couponNo @param userId @param salesSum 促销价格 @return @throws
     */
    protected Message splitDiscountFee(List<TradeBasePO> trades, String couponNo, String userId, int salesSum) {
        Message success = Message.success();
        // if (StringUtil.isBlank(couponNo)) {
        // for (TradeBasePO po : trades) {
        // po.setPayPrice(salesSum);
        // }
        // return success;
        // }
        Collection<String> goodsIds = new HashSet<String>();
        for (TradeBasePO trade : trades) {
            goodsIds.add(trade.getGoodsId());
        }
        String useCouponTradeNo = trades.get(0).getTradeNo();
        Message userCouponMessage = couponService.useCouponPrepay(couponNo, useCouponTradeNo, salesSum, userId,
                goodsIds);
        if (userCouponMessage.getCode() != Message.SUCCESS) {
            return userCouponMessage;
        }
        int discountFee = (int) userCouponMessage.getData().get("value");
        if (discountFee <= 0 || salesSum < discountFee) {
            return Message.error(BizReturnCode.TRADE_ORDER_PRICE_ERROR);
        }
        int payPrice = salesSum - discountFee;
        if (discountFee > 0) {
            for (int i = 0; i < trades.size(); i++) {
                if (i == 0) {
                    trades.get(i).setDiscountAmount(discountFee);
                }
                trades.get(i).setPayPrice(payPrice);
            }
        }
        success.addData("value", discountFee);
        return success;
    }

    /**
     * @param trades
     * @param userId
     * @param totalPrice
     * @param useCommissionLimit
     * @return @throws
     * @author shenyb 2015年12月7日 下午6:12:28 @Method: useCommission @Description:
     */
    protected Message useCommission(List<TradeBasePO> trades, String userId, int totalPrice, int useCommissionLimit) {
        Message message = Message.success();
        int commisson = userCommissionAccountService.getAvailableBalance(userId, useCommissionLimit);
        if (commisson > 0) {
            for (int i = 0; i < trades.size(); i++) {
                if (i == 0) {
                    trades.get(i).setCommission(commisson);
                }
                trades.get(i).setPayPrice(trades.get(i).getPayPrice() - commisson);
            }
        }
        return message;
    }

    protected void addTradeBasePO(List<TradeBasePO> resultTradeList, TradeBasePO record) {
        TradeBasePO trade = new TradeBasePO();
        BeanUtils.copyProperties(record, trade);
        resultTradeList.add(trade);
    }

    protected void setDealNo(String userId, TradeBasePO record, String goodsId, GoodsMainPO goods) {
        String splitTradeNo = BizCodeUtil.generateTradeNo(goods.getGoodsName(), userId);
        String splitUserTradeNo = BizCodeUtil.generateUserOrderNo(userId, goodsId);
        record.setTradeNo(splitTradeNo);
        record.setOrderNo(splitTradeNo);
        record.setUserTradeNo(splitUserTradeNo);
        record.setUserOrderNo(splitUserTradeNo);
    }

    protected void setOrderNo(String userId, TradeBasePO record, GoodsMainPO goods) {
        String orderNo = BizCodeUtil.generateTradeNo(goods.getGoodsId(), userId);
        record.setOrderNo(orderNo);
        String userOrderNo = BizCodeUtil.generateUserOrderNo(userId, goods.getGoodsId());
        record.setUserOrderNo(userOrderNo);
    }

    protected void setAddressParam(TradeAddressPO address, TradeBasePO record) {
        record.setAddressId(address.getAddressId());
        record.setAddress(address.getAddress());
        record.setCertificationCard(address.getCertificationCard());
        record.setCertificationNegativeKey(address.getCertificationNegativeKey());
        record.setCertificationPositiveKey(address.getCertificationPositiveKey());
        record.setLogisticsValue(address.getLogisticsValue());
        record.setReceiver(address.getReceiver());
        record.setTelephone(address.getTelephone());
    }

    /**
     * @author shenyb 2015年11月30日 下午11:37:58 @Method:
     * updatePayPriceByTransportFee @Description: TODO @param
     * transportFee @param resultList @throws
     */

    protected void updatePayPriceByTransportFee(int transportFee, List<TradeBasePO> resultList) {
        for (int i = 0; i < resultList.size(); i++) {
            if (i == 0) {
                resultList.get(i).setTransportFee(transportFee);
            }
            resultList.get(i).setPayPrice(resultList.get(i).getPayPrice() + transportFee);
        }
    }

    protected Message checkGoodsStore(TradeCartVO[] records) {
        Message message = Message.success();
        // 库存检查
        List<TradeGoodsStoreVO> goodsList = new ArrayList<TradeGoodsStoreVO>();
        for (TradeCartVO tradeCartVO : records) {
            TradeGoodsStoreVO goodsStoreVO = new TradeGoodsStoreVO();
            goodsStoreVO.setBuyCount(tradeCartVO.getGoodsCount());
            goodsStoreVO.setGoodsId(tradeCartVO.getGoodsId());
            goodsList.add(goodsStoreVO);
        }
        Message checkStoreMessge = tradeBaseService.checkGoodsStoreByList(goodsList);
        if (checkStoreMessge.getCode() != Message.SUCCESS) {
            return checkStoreMessge;
        }
        return message;
    }

    protected void updatePayPriceBySalesSum(List<TradeBasePO> resultList, int salesSum) {
        for (TradeBasePO po : resultList) {
            po.setPayPrice(salesSum);
        }
    }
}

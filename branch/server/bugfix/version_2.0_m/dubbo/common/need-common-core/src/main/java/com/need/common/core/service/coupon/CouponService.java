/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.coupon;


import com.need.common.domain.po.api.coupon.CouponUserPO;
import com.need.common.domain.pub.Message;

import java.util.Collection;

/**
 *
 * @author 庆凯
 */
public interface CouponService {
    
    //获取用户个人中心的优惠券列表
    Message getCouponListByUserId(String userId, String couponStatus, Integer pageNum, Integer pageSize);
    
    //根据交易金额获取用户支付时可以使用的优惠券列表
    Message getAvailableCouponListByUserIdAndTradeNo(String userId, String tradeNo, Integer pageNum, Integer pageSize);
    
    //根据交易金额获取用户支付时可以使用的优惠券列表
    Message getAvailableCouponListByUserIdAndCost(String userId, Integer cost, String isCheap, Integer pageNum, Integer pageSize);
    
    //确认支付前使用优惠券，更新优惠券状态
    Message useCouponPrepay(String couponNo, String tradeNo, String userId);
    
    //支付成功后更新优惠券状态
    void useCouponPayedSuccess(String tradeNo);
    
    //支付失败后更新优惠券状态
    void useCouponPayedFail(String tradeNo);
    
    //新用户注册检查之前是否使用手机号领取优惠券
    void checkRegisterCouponMobile(String mobile, String userId);
    
    //根据优惠券编号获取优惠券信息
    CouponUserPO getCouponUserPOByCouponNo(String couponNo);

    //根据交易编号返回优惠券信息
    CouponUserPO getCouponUserByTradeNo(String tradeNo);

    //根据优惠券模板id获取分享优惠券信息
    Message getShareCouponInfo(String couponTemplateId);
    
    //使用优惠券
	Message useCouponPrepay(String couponNo,String tradeNo, int totalPrice, String userId, Collection<String> goodsIds);
    
    //兑换优惠券
    Message exchangeCoupon(String couponExchangeCode, String userId);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.service.bops.coupon;

import com.need.domain.po.bops.coupon.BopsCouponExchangeAuditPO;
import com.need.domain.po.bops.coupon.BopsCouponExchangePO;
import com.need.domain.pub.Message;
import com.need.domain.vo.coupon.CouponExchangeVO;

/**
 * 
 * @author 庆凯 2015-9-14 2015-9-14 13:29:01
 * @ClassName BopsCouponService
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-14
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsCouponExchangeService {

    //添加优惠券兑换
    Message addCouponExchange(BopsCouponExchangePO bopsCouponExchangePO);

    //修改优惠券兑换
    Message updateCouponExchange(BopsCouponExchangePO bopsCouponExchangePO);

    //审核优惠券兑换码
    Message auditCouponExchange(BopsCouponExchangeAuditPO bopsCouponExchangeAuditPO, Integer userId);
    
    //优惠券兑换码
    CouponExchangeVO selectCouponExchangeDetial(String couponExchangeId);

    //冻结优惠券兑换码
    Message freezeCouponExchange(Integer couponExchangeId);


}

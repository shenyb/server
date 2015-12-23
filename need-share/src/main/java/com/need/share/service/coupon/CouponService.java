/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.share.service.coupon;

import com.need.share.dao.jdbc.api.coupon.po.CouponReceiveInfoPO;
import com.need.share.pub.Message;
import com.need.share.web.controller.coupon.vo.ReceiveCouponUserVO;
import java.util.List;

/**
 * 
 * @author 庆凯 2015-9-15 2015-9-15 16:10:07
 * @ClassName CouponService
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-15
 * @modify by reason:{方法名}:{原因}
 */
public interface CouponService {

    //获取用户分享优惠券被领取的用户列表
    List<ReceiveCouponUserVO> getCouponReviceListByUserId(String userId, String couponTemplateId);

    //获取用户分享优惠券被领取的用户列表
    List<CouponReceiveInfoPO> getCouponReviceListByUserId(String shareUserId, String couponTemplateId, String tradeNo);
    
    //根据手机号领取优惠券
    Message receiveCoupon(String mobile, String couponTemplateId, String shareUserId);
    
    //根据手机号领取优惠券
    Message receiveCoupon(CouponReceiveInfoPO receiveInfoPO);
}

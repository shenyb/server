/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.task.service.coupon;

import java.util.List;

/**
 *
 * @author 庆凯
 */
public interface CouponService {
    
    //支付失败后更新优惠券状态
    void useCouponPayedFail(List<String> tradeNo);
}

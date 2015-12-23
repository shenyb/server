/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.service.bops.coupon;

import java.util.List;

import com.need.domain.po.api.coupon.CouponOpsListPO;
import com.need.domain.po.api.coupon.CouponTemplatePO;
import com.need.domain.po.bops.coupon.BopsCouponExchangeAuditPO;
import com.need.domain.po.bops.coupon.BopsCouponExchangePO;
import com.need.domain.pub.Message;
import com.need.domain.vo.coupon.CouponExchangeVO;
import com.need.domain.vo.coupon.CouponOpsListVO;

/**
 * 
 * @author liuhongyang 2015年11月1日 下午12:53:58
 * @ClassName BopsCouponDiscountService
 * @Description 优惠券列表
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: liuhongyang 2015年11月1日
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsCouponDiscountService {

	//优惠券列表
    List<CouponOpsListVO> getCouponOpsList();
    //优惠券列表编辑回显
    List<CouponOpsListVO>  toCouponOpsListEdit();
    //优惠券列表编辑
    Message updateCouponDidscount(CouponOpsListPO couponOpsListPO);
}

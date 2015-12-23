/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.service.bops.coupon;

import com.need.domain.po.bops.coupon.BopsCouponExchangeTemplateAuditPO;
import com.need.domain.po.bops.coupon.BopsCouponExchangeTemplatePO;
import com.need.domain.pub.Message;
import com.need.domain.vo.coupon.CouponExchangeTemplateVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
    Message addCouponExchange(BopsCouponExchangeTemplatePO bopsCouponExchangePO);

    //修改优惠券兑换
    Message updateCouponExchange(BopsCouponExchangeTemplatePO bopsCouponExchangePO);

    //审核优惠券兑换码
    Message auditCouponExchange(BopsCouponExchangeTemplateAuditPO bopsCouponExchangeAuditPO, Integer userId);
    
    //优惠券兑换码
    CouponExchangeTemplateVO selectCouponExchangeDetial(String couponExchangeTemplateId);

    //冻结优惠券兑换码
    Message freezeCouponExchangeTemplate(String couponExchangeTemplateId);

    //冻结优惠券兑换码
    HSSFWorkbook exportCouponExchangeTemplate(String couponExchangeTemplateId);


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.service.bops.coupon;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.need.domain.po.bops.coupon.BopsCouponAuditPO;
import com.need.domain.po.bops.coupon.BopsCouponTemplatePO;
import com.need.domain.pub.Message;
import com.need.domain.vo.coupon.CouponTemplateResultVO;

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
public interface BopsCouponService {

    //添加优惠券模板
    Message addCouponTemplate(BopsCouponTemplatePO bopsCouponTemplatePO);

    //修改优惠券模板
    Message updateCouponTemplate(BopsCouponTemplatePO bopsCouponTemplatePO);

    //审核优惠券模板
    Message auditCouponTemplate(BopsCouponAuditPO bopsCouponAuditPO, Integer userId);
    
    //订单过期后更新优惠券状态
    void useCouponPayedFail(String tradeNo);

    //冻结优惠券模板
    Message freeze(String couponTemplateId);
    
    //发送优惠券
    Message sendCoupon(String couponTemplateId, String mobile);

    /**
     * 
     * @author peiboli 2015年12月2日 下午2:51:22
     * @Method: exportCoupon 
     * @Description: TODO导出又会券
     * @return 
     * @throws
     */
	HSSFWorkbook exportCoupon(List<CouponTemplateResultVO> couponTemplateList);

}

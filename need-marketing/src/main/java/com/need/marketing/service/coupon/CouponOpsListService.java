/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.marketing.service.coupon;

import com.need.marketing.pub.Message;
import com.need.marketing.dao.jdbc.api.coupon.po.CouponReceiveInfoPO;
import com.need.marketing.dao.jdbc.api.coupon.po.CouponUserPO;
import com.need.marketing.web.controller.coupon.vo.CouponOpsListVO;
import com.need.marketing.web.controller.coupon.vo.ReceiveCouponUserVO;
import java.util.List;

/**
 * 
 * @author liuhongyang 2015年10月30日 上午11:41:26
 * @ClassName CouponOpsListService
 * @Description 优惠券列表
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: liuhongyang 2015年10月30日
 * @modify by reason:{方法名}:{原因}
 */
public interface CouponOpsListService {
   //优惠券列表展示
   List<CouponOpsListVO> getCouponUserList(String userId);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.api.web.controller.coupon;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.coupon.CouponService;
import com.need.common.domain.pub.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author 庆凯 2015-9-9 2015-9-9 11:56:58
 * @ClassName GetUserAvailableCouponListController
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-9
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.COUPON_AVAILABLE_COST)
public class GetUserAvailableCouponListByCostController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(GetUserAvailableCouponListByCostController.class);
    
    @Autowired
    private CouponService couponService;
    
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.GET)
	public Message getAvailableCouponListByUserIdAndCost(
			@RequestParam(required = true) String userId,
            @RequestParam(required = true) Integer cost,
            @RequestParam(required = false)String isCheap,
            @RequestParam(required = false)Integer pageNum,
			@RequestParam(required = false)Integer pageSize){
		LOGGER.info("couponAvailableListByUserId userId :"+ userId + " cost:" + cost);
        Message message = couponService.getAvailableCouponListByUserIdAndCost(userId, cost, isCheap, pageNum, pageSize);
        return message;
    }
    

}

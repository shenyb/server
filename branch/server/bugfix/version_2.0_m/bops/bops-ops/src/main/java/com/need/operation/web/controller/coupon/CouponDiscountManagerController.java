/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Exchanges
 * and open the template in the editor.
 */

package com.need.operation.web.controller.coupon;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.domain.po.api.coupon.CouponOpsListPO;
import com.need.domain.pub.Message;
import com.need.domain.vo.coupon.CouponOpsListVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.service.bops.coupon.BopsCouponDiscountService;

import org.springframework.ui.Model;

@Controller
@RequestMapping(value = ControllerMappings.COUPON_DISCOUNT)
public class CouponDiscountManagerController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(CouponDiscountManagerController.class);
	@Autowired
	private BopsCouponDiscountService bopsCouponDiscountService;
    /**
     * 
     * @author liuhongyang 2015年11月1日 下午12:47:44
     * @Method: listCouponExchange 
     * @Description: 优惠券列表
     * @param couponExchangePageVO
     * @param model
     * @return 
     * @throws
     */
	@RequestMapping(method = RequestMethod.GET, value="/page")
	public String listCouponDiscount(CouponOpsListVO couponOpsListVO, Model model) {
		LOGGER.info("in listCouponDiscount page listCouponDiscount: " + couponOpsListVO);
		List<CouponOpsListVO> couponOpsList=bopsCouponDiscountService.getCouponOpsList();
		model.addAttribute("list", couponOpsList);
        return ViewMappings.COUPON_OPS_LIST;
    }
	
	/**
	 * 
	 * @author liuhongyang 2015年11月1日 下午1:30:16
	 * @Method: editCouponExchange 
	 * @Description: 跳转到优惠券列表编辑页
	 * @param couponExchangeId
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value="/updatePre")
	public String toUpdateDiscountCoupon(CouponOpsListVO couponOpsListVO,Model model) {
		LOGGER.info("in couponOpsListVO edit couponOpsListVO: " + couponOpsListVO);
		List<CouponOpsListVO> couponOpsList=bopsCouponDiscountService.toCouponOpsListEdit();
		model.addAttribute("list", couponOpsList);
        return ViewMappings.COUPON_OPS_EDIT;
    }
	/**
	 * 
	 * @author liuhongyang 2015年11月1日 下午2:25:07
	 * @Method: toUpdateDiscountCoupon 
	 * @Description: 优惠券编辑
	 * @param couponOpsListVO
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.POST, value="/update")
	@ResponseBody
	public Message UpdateDiscountCoupon(CouponOpsListPO  couponOpsListPO,Model model) {
		LOGGER.info("in couponOpsListVO edit couponOpsListVO: " + couponOpsListPO);
		Message message=bopsCouponDiscountService.updateCouponDidscount(couponOpsListPO);
        return message;
    }
	
}

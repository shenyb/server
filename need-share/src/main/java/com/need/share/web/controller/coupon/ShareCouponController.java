/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.share.web.controller.coupon;

import com.need.share.constant.Constants;
import com.need.share.constant.ControllerMappings;
import com.need.share.constant.ViewMappings;
import com.need.share.dao.jdbc.api.coupon.CouponTemplateDAO;
import com.need.share.dao.jdbc.api.trade.TradeBaseDAO;
import com.need.share.service.coupon.CouponService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author 庆凯 2015-9-15 2015-9-15 14:21:10
 * @ClassName ReceiveCouponController
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-15
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value=ControllerMappings.COUPON)
public class ShareCouponController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ShareCouponController.class);
    
    @Autowired
    private CouponService couponService;
    
    @Autowired
    private CouponTemplateDAO couponTemplateDAO;
    
    @Autowired
    private TradeBaseDAO tradeBaseDAO;
    
	@RequestMapping(method = RequestMethod.GET, value="/shareRegister/{shareUserId}")
	public String shareRegisterCoupon(@PathVariable String shareUserId,
            HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		LOGGER.debug("receiveCoupon in.. shareUserId :"+shareUserId);
        String marketHost = com.need.utils.PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "marketingHost");
        response.sendRedirect(marketHost + "/coupon/shareRegister/" + shareUserId);
//        model.addAttribute("shareUserId", shareUserId);
//        String couponTemplateId = PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "share_register_coupon_template_id");
//        List<CouponReceiveInfoPO> receiveCouponUserVOList = couponService.getCouponReviceListByUserId(shareUserId, couponTemplateId, null);
//        model.addAttribute("receilveCouponUserList", receiveCouponUserVOList);
//        model.addAttribute("couponTemplateId", couponTemplateId);
//        CouponTemplatePO couponTemplatePO = couponTemplateDAO.selectByPrimaryKey(couponTemplateId);
//        model.addAttribute("value", couponTemplatePO.getValue() / 100 + "");
//        model.addAttribute("minCost", couponTemplatePO.getMinCost() / 100 + "");
//        model.addAttribute("received", "none");
//        model.addAttribute("receive", "block");
        return ViewMappings.SHARE_COUPON;
    }
    
	@RequestMapping(method = RequestMethod.GET, value="/sharePayed/{shareUserId}")
	public String sharePayedCoupon(@PathVariable String shareUserId,
            HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
		LOGGER.debug("receiveCoupon in.. shareUserId :"+shareUserId);
        String marketHost = com.need.utils.PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "marketingHost");
        response.sendRedirect(marketHost + "/coupon/sharePayed/" + shareUserId);
//        model.addAttribute("shareUserId", shareUserId);
//        String couponTemplateId = PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "share_payed_coupon_template_id");
//        List<CouponReceiveInfoPO> receiveCouponUserVOList = couponService.getCouponReviceListByUserId(shareUserId, couponTemplateId, null);
//        model.addAttribute("receilveCouponUserList", receiveCouponUserVOList);
//        model.addAttribute("couponTemplateId", couponTemplateId);
//        CouponTemplatePO couponTemplatePO = couponTemplateDAO.selectByPrimaryKey(couponTemplateId);
//        model.addAttribute("value", couponTemplatePO.getValue() / 100 + "");
//        model.addAttribute("minCost", couponTemplatePO.getMinCost() / 100 + "");
//        model.addAttribute("received", "none");
//        model.addAttribute("receive", "block");
        return ViewMappings.SHARE_COUPON;
    }
    
	@RequestMapping(method = RequestMethod.GET, value="/shareTrade/{tradeNo}")
	public String shareTradeCoupon(@PathVariable String tradeNo,
            HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
		LOGGER.debug("receiveCoupon in.. tradeNo :"+tradeNo);
        String marketHost = com.need.utils.PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "marketingHost");
        response.sendRedirect(marketHost + "/coupon/shareTrade/" + tradeNo);
//        List<TradeBasePO> tradeList = tradeBaseDAO.getByTradeNo(tradeNo);
//        if(tradeList == null || tradeList.isEmpty()) {
//            return "";
//        }
//        String shareUserId = tradeList.get(0).getBuyerId();
//        model.addAttribute("code", 200);
//        model.addAttribute("shareUserId", shareUserId);
//        model.addAttribute("tradeNo", tradeNo);
//        model.addAttribute("received", "none");
//        model.addAttribute("receive", "block");
//        String couponTemplateId = PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "share_payed_coupon_template_id");
//        List<CouponReceiveInfoPO> receiveCouponUserVOList = couponService.getCouponReviceListByUserId(shareUserId, couponTemplateId, tradeNo);
//        model.addAttribute("receilveCouponUserList", receiveCouponUserVOList);
//        model.addAttribute("couponTemplateId", couponTemplateId);
//        CouponTemplatePO couponTemplatePO = couponTemplateDAO.selectByPrimaryKey(couponTemplateId);
//        model.addAttribute("value", couponTemplatePO.getValue() / 100 + "");
//        model.addAttribute("minCost", couponTemplatePO.getMinCost() / 100 + "");
        return ViewMappings.SHARE_COUPON;
    }
}

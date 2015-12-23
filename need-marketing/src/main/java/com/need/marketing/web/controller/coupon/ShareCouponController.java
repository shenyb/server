/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.marketing.web.controller.coupon;

import com.need.marketing.common.enums.SystemSettingEnum;
import com.need.marketing.constant.Constants;
import com.need.marketing.constant.ControllerMappings;
import com.need.marketing.constant.ViewMappings;
import com.need.marketing.dao.jdbc.api.coupon.CouponTemplateDAO;
import com.need.marketing.dao.jdbc.api.coupon.po.CouponReceiveInfoPO;
import com.need.marketing.dao.jdbc.api.coupon.po.CouponTemplatePO;
import com.need.marketing.dao.jdbc.api.trade.TradeBaseDAO;
import com.need.marketing.dao.jdbc.api.trade.po.TradeBasePO;
import com.need.marketing.service.coupon.CouponService;
import com.need.marketing.service.system.SystemSettingService;
import com.need.utils.PropertiesUtil;
import com.need.utils.StringUtil;
import java.util.List;
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
    
    @Autowired
    private SystemSettingService systemSettingService;
    
	@RequestMapping(method = RequestMethod.GET, value="/shareRegister/{shareUserId}")
	public String shareRegisterCoupon(@PathVariable String shareUserId,Model model) {
		LOGGER.debug("receiveCoupon in.. shareUserId :"+shareUserId);
        model.addAttribute("shareUserId", shareUserId);
        String couponTemplateId = systemSettingService.getSystemSettingByName(SystemSettingEnum.REGISTER_COUPON_TEMPLATE_ID.code);
        if(StringUtil.isBlank(couponTemplateId)) {
            couponTemplateId = PropertiesUtil.getProperty("/properties/constants.properties", "share_register_coupon_template_id");
        }
        LOGGER.info("shareRegisterCoupon in ..."+couponTemplateId);
        List<CouponReceiveInfoPO> receiveCouponUserVOList = couponService.getCouponReviceListByUserId(shareUserId, couponTemplateId, null);
        model.addAttribute("receilveCouponUserList", receiveCouponUserVOList);
        model.addAttribute("couponTemplateId", couponTemplateId);
        CouponTemplatePO couponTemplatePO = couponTemplateDAO.selectByPrimaryKey(couponTemplateId);
        model.addAttribute("value", couponTemplatePO.getValue() / 100 + "");
        model.addAttribute("minCost", couponTemplatePO.getMinCost() / 100 + "");
        model.addAttribute("received", "none");
        model.addAttribute("receive", "block");
        model.addAttribute("code", 200);
        model.addAttribute("shareTitle", PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "share_register_title"));
        return ViewMappings.SHARE_COUPON;
    }
    
	@RequestMapping(method = RequestMethod.GET, value="/sharePayed/{shareUserId}")
	public String sharePayedCoupon(@PathVariable String shareUserId,Model model) {
		LOGGER.debug("receiveCoupon in.. shareUserId :"+shareUserId);
        model.addAttribute("shareUserId", shareUserId);
        String couponTemplateId = systemSettingService.getSystemSettingByName(SystemSettingEnum.REGISTER_COUPON_TEMPLATE_ID.code);
        if(StringUtil.isBlank(couponTemplateId)) {
            couponTemplateId = PropertiesUtil.getProperty("/properties/constants.properties", "share_payed_coupon_template_id");
        }
        List<CouponReceiveInfoPO> receiveCouponUserVOList = couponService.getCouponReviceListByUserId(shareUserId, couponTemplateId, null);
        model.addAttribute("receilveCouponUserList", receiveCouponUserVOList);
        model.addAttribute("couponTemplateId", couponTemplateId);
        CouponTemplatePO couponTemplatePO = couponTemplateDAO.selectByPrimaryKey(couponTemplateId);
        model.addAttribute("value", couponTemplatePO.getValue() / 100 + "");
        model.addAttribute("minCost", couponTemplatePO.getMinCost() / 100 + "");
        model.addAttribute("received", "none");
        model.addAttribute("receive", "block");
        model.addAttribute("code", 200);
        model.addAttribute("shareTitle", PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "share_payed_title"));
        return ViewMappings.SHARE_COUPON;
    }
    
	@RequestMapping(method = RequestMethod.GET, value="/shareTrade/{tradeNo}")
	public String shareTradeCoupon(@PathVariable String tradeNo,Model model) {
		LOGGER.debug("receiveCoupon in.. tradeNo :"+tradeNo);
        List<TradeBasePO> tradeList = tradeBaseDAO.getByTradeNo(tradeNo);
        if(tradeList == null || tradeList.isEmpty()) {
            return "";
        }
        String shareUserId = tradeList.get(0).getBuyerId();
        model.addAttribute("code", 200);
        model.addAttribute("shareUserId", shareUserId);
        model.addAttribute("tradeNo", tradeNo);
        model.addAttribute("received", "none");
        model.addAttribute("receive", "block");
        String couponTemplateId = systemSettingService.getSystemSettingByName(SystemSettingEnum.REGISTER_COUPON_TEMPLATE_ID.code);
        if(StringUtil.isBlank(couponTemplateId)) {
            couponTemplateId = PropertiesUtil.getProperty("/properties/constants.properties", "share_payed_coupon_template_id");
        }
        List<CouponReceiveInfoPO> receiveCouponUserVOList = couponService.getCouponReviceListByUserId(shareUserId, couponTemplateId, tradeNo);
        model.addAttribute("receilveCouponUserList", receiveCouponUserVOList);
        model.addAttribute("couponTemplateId", couponTemplateId);
        CouponTemplatePO couponTemplatePO = couponTemplateDAO.selectByPrimaryKey(couponTemplateId);
        model.addAttribute("value", couponTemplatePO.getValue() / 100 + "");
        model.addAttribute("minCost", couponTemplatePO.getMinCost() / 100 + "");
        model.addAttribute("code", 200);
        model.addAttribute("shareTitle", PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "share_payed_title"));
        return ViewMappings.SHARE_COUPON;
    }
}

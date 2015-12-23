/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.marketing.web.controller.coupon;

import com.need.marketing.constant.BizReturnCode;
import com.need.marketing.constant.Constants;
import com.need.marketing.constant.ControllerMappings;
import com.need.marketing.constant.ViewMappings;
import com.need.marketing.pub.Message;
import com.need.marketing.dao.jdbc.api.coupon.CouponTemplateDAO;
import com.need.marketing.dao.jdbc.api.coupon.po.CouponReceiveInfoPO;
import com.need.marketing.dao.jdbc.api.coupon.po.CouponTemplatePO;
import com.need.marketing.service.coupon.CouponService;
import com.need.marketing.service.weixin.WeixinService;
import com.need.utils.PropertiesUtil;
import com.need.utils.StringUtil;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author 庆凯 2015-9-9 2015-9-9 11:56:58
 * @ClassName ReceiveCouponController
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-9
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.COUPON)
public class ReceiveCouponController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveCouponController.class);
    
    @Autowired
    private CouponService couponService;
    
    @Autowired
    private WeixinService weixinService;
    
    @Autowired
    private CouponTemplateDAO couponTemplateDAO;
    
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/receive")
	public Message getCouponListByUserId(
			@RequestParam(required = true) String mobile,
			@RequestParam(required = true) String couponTemplateId,
			@RequestParam(required = false) String shareUserId){
		LOGGER.info("receive coupon mobile :"+ mobile + " couponTemplateId:" + couponTemplateId + " shareUserId:" + shareUserId);
        if(StringUtil.isBlank(mobile)) {
            return Message.error(BizReturnCode.MOBILE_NOT_NOT_EXIST);
        }
        Message message = couponService.receiveCoupon(mobile, couponTemplateId, shareUserId);
        return message;
    }

	@RequestMapping(method = RequestMethod.GET, value="/login")
	public String WeiXinLogin(
            @RequestParam(required = false) String shareUserId,
            @RequestParam(required = false) String tradeNo,
			@RequestParam(required = true) String couponTemplateId,
            @RequestParam(required = true) String mobile,
            Model model, HttpServletResponse response, HttpServletRequest request) {
        String code = request.getParameter("code");
        if(StringUtil.isBlank(code)) {
            try {
                String time = ""+System.currentTimeMillis();
                String host = PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "marketingHost");
                String url = host + "/coupon/login?couponTemplateId="+couponTemplateId+"&shareUserId="+shareUserId+"&mobile="+mobile;
                if(!StringUtil.isBlank(tradeNo)) {
                    url += "&tradeNo=" + tradeNo;
                }
                String redirect = URLEncoder.encode(url,"utf-8");
                String appid = PropertiesUtil.getProperty("/properties/wechat.properties", "app_id");
                String redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+redirect+"&response_type=code&scope=snsapi_userinfo&state="+time+"#wechat_redirect";
                LOGGER.info(redirectUrl);
                response.sendRedirect(redirectUrl);
            } catch (IOException e) { 	
                // TODO Auto-generated catch block
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            CouponTemplatePO couponTemplatePO = couponTemplateDAO.selectByPrimaryKey(couponTemplateId);
            if(couponTemplatePO == null) {
                LOGGER.info("receive couponTemplate couponTemplateId : " + couponTemplateId);
                return "";
            }
            model.addAttribute("received", "none");
            model.addAttribute("receive", "block");
            model.addAttribute("value", couponTemplatePO.getValue() / 100);
            model.addAttribute("shareUserId", shareUserId);
            List<CouponReceiveInfoPO> receiveCouponUserVOList = couponService.getCouponReviceListByUserId(shareUserId, couponTemplateId, tradeNo);
            model.addAttribute("receilveCouponUserList", receiveCouponUserVOList);
            model.addAttribute("couponTemplateId", couponTemplateId);
            model.addAttribute("minCost", couponTemplatePO.getMinCost() / 100 + "");
            model.addAttribute("mobile", mobile);
            JSONObject userObject = this.weixinService.getWeiXinUserInfo(code);
            if (userObject == null) {
                model.addAttribute("code", "-1");
                model.addAttribute("message", "微信登陆失败请重试");
                return ViewMappings.SHARE_COUPON;
            }
            CouponReceiveInfoPO couponReceiveInfoPO = new CouponReceiveInfoPO();
            couponReceiveInfoPO.setCouponTemplateId(couponTemplateId);
            if(userObject.containsKey("headimgurl")) {
                couponReceiveInfoPO.setIconUrl(userObject.getString("headimgurl"));
            }
            couponReceiveInfoPO.setMobile(mobile);
            if(userObject.containsKey("nickname")) {
                couponReceiveInfoPO.setNickName(userObject.getString("nickname"));
            }
            couponReceiveInfoPO.setReceiveTime(Calendar.getInstance().getTime());
            couponReceiveInfoPO.setShareUserId(shareUserId);
            couponReceiveInfoPO.setTradeNo(tradeNo);
            Message message = couponService.receiveCoupon(couponReceiveInfoPO);
            model.addAttribute("code", message.getCode());
            if(message.getCode() != Message.SUCCESS) {
                model.addAttribute("message", message.getMsg());
            } else {
                model.addAttribute("receive", "none");
                model.addAttribute("received", "block");
            }
            return ViewMappings.SHARE_COUPON;
        }
        return null;
    }
    
 
}

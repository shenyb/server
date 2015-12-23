/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.api.web.controller.coupon;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.coupon.CouponService;
import com.need.common.core.service.system.SystemSettingService;
import com.need.common.domain.enums.SystemSettingEnum;
import com.need.common.domain.pub.Message;
import com.need.utils.PropertiesUtil;
import com.need.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping(ControllerMappings.COUPON_SHARE_REGISTER)
public class GetShareRegisterCouponInfoController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(GetShareRegisterCouponInfoController.class);
    
    @Autowired
    private CouponService couponService;
    
    @Autowired
    private SystemSettingService systemSettingService;
    
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.2", method = RequestMethod.GET)
	public Message getShareRegisterCouponInfo(){
        String registerCouponTemplateId = systemSettingService.getSystemSettingByName(SystemSettingEnum.REGISTER_COUPON_TEMPLATE_ID.code);
        if(StringUtil.isBlank(registerCouponTemplateId)) {
            registerCouponTemplateId = PropertiesUtil.getProperty("/properties/constants.properties", "share_register_coupon_template_id");
        }
		LOGGER.info("getShareRegisterCouponInfo registerCouponTemplateId :"+ registerCouponTemplateId);
        Message message = couponService.getShareCouponInfo(registerCouponTemplateId);
        return message;
    }
    

}

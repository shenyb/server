package com.need.share.service.coupon.impl;

import com.need.biz.utils.BizCodeUtil;
import com.need.gateway.sms.SMSService;
import com.need.share.common.enums.CouponStatusEnum;
import com.need.share.common.enums.CouponTypeEnum;
import com.need.share.constant.BizReturnCode;
import com.need.share.constant.Constants;
import com.need.share.dao.jdbc.api.coupon.CouponMobileDAO;
import com.need.share.dao.jdbc.api.coupon.CouponReceiveInfoDAO;
import com.need.share.dao.jdbc.api.coupon.CouponTemplateDAO;
import com.need.share.dao.jdbc.api.coupon.CouponUserDAO;
import com.need.share.dao.jdbc.api.coupon.po.CouponMobilePO;
import com.need.share.dao.jdbc.api.coupon.po.CouponReceiveInfoPO;
import com.need.share.dao.jdbc.api.coupon.po.CouponTemplatePO;
import com.need.share.dao.jdbc.api.coupon.po.CouponUserPO;
import com.need.share.dao.jdbc.api.user.po.UserBasePO;
import com.need.share.pub.ConstantsProConfig;
import com.need.share.pub.Message;
import com.need.share.pub.SMSProConfig;
import com.need.share.service.coupon.CouponService;
import com.need.share.service.user.UserBaseService;
import com.need.share.util.DateUtil;
import com.need.share.util.PropertiesUtil;
import com.need.share.util.StringUtil;
import com.need.share.web.controller.coupon.vo.ReceiveCouponUserVO;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 * 
 * @author 庆凯 2015-9-15 2015-9-15 20:34:08
 * @ClassName CouponServiceImpl
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-15
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class CouponServiceImpl implements CouponService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CouponServiceImpl.class);
    
    @Autowired
    private CouponUserDAO couponUserDAO;

    @Autowired
    private CouponMobileDAO couponMobileDAO;
    
    @Autowired
    private CouponTemplateDAO couponTemplateDAO;
    
    @Autowired
    private UserBaseService userBaseService;
    
    @Autowired
    private ConstantsProConfig constantsProConfig;
    
    @Autowired
    private SMSService smsService;
    
    @Autowired
    private CouponReceiveInfoDAO couponReceiveInfoDAO;
    @Autowired
    private SMSProConfig smsProConfig;
    
    @Override
    public List<ReceiveCouponUserVO> getCouponReviceListByUserId(String userId, String couponTemplateId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shareUserId", userId);
        params.put("couponTemplateId", couponTemplateId);
        List<ReceiveCouponUserVO> couponUserList = couponUserDAO.getCouponUserListByShareUserIdAndTemplateId(params);
        List<ReceiveCouponUserVO> couponMobileList = couponMobileDAO.getCouponMobileListByShareUserIdAndTemplateId(params);
        if (couponMobileList != null && !couponMobileList.isEmpty()) {
            couponUserList.addAll(couponMobileList);
            Collections.sort(couponUserList, new Comparator<ReceiveCouponUserVO>() {

                @Override
                public int compare(ReceiveCouponUserVO o1, ReceiveCouponUserVO o2) {
                    if (o1.getReceiveTime().getTime() >= o2.getReceiveTime().getTime()) {
                        return 1;
                    }
                    return -1;
                }
            });
        }
        for(ReceiveCouponUserVO receiveCouponUserVO : couponUserList) {
            receiveCouponUserVO.setValue(receiveCouponUserVO.getValue() / 100);
            receiveCouponUserVO.setReceiveDate(DateUtil.formatDateToStr(receiveCouponUserVO.getReceiveTime(), "yyyy-MM-dd"));
            if(!StringUtil.isEmptyOrNull(receiveCouponUserVO.getProfilePicKey())) {
                receiveCouponUserVO.setProfilePicKey(constantsProConfig.getPicDomain().concat("/").concat(receiveCouponUserVO.getProfilePicKey()));
            } else {
                receiveCouponUserVO.setProfilePicKey(constantsProConfig.getPicDomain().concat("/201509170000001defaultHeadImapedefault_avatar_img@3x.png"));
            }
            if(!StringUtil.isEmptyOrNull(receiveCouponUserVO.getMobile())) {
                receiveCouponUserVO.setMobile(StringUtil.subCentreString(receiveCouponUserVO.getMobile(), 4));
            }
        }
        return couponUserList;
    }

    @Override
    public List<CouponReceiveInfoPO> getCouponReviceListByUserId(String shareUserId, String couponTemplateId, String tradeNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shareUserId", shareUserId);
        params.put("couponTemplateId", couponTemplateId);
        params.put("tradeNo", tradeNo);
        List<CouponReceiveInfoPO> couponReceiveList = couponReceiveInfoDAO.getCouponReceiveInfoByParam(params);
        for(CouponReceiveInfoPO couponReceiveInfoPO : couponReceiveList) {
            couponReceiveInfoPO.setReceiveDate(DateUtil.formatDateToStr(couponReceiveInfoPO.getReceiveTime(), "yyyy-MM-dd"));        
            //Addy liyongran 20151004  修改优惠券面值
            couponReceiveInfoPO.setValue(couponReceiveInfoPO.getValue()/100);
            //Addy liyongran 20151004  end 
            if(StringUtil.isBlank(couponReceiveInfoPO.getIconUrl())) {
                couponReceiveInfoPO.setIconUrl(constantsProConfig.getPicDomain().concat("/201509170000001defaultHeadImapedefault_avatar_img@3x.png"));
            }
        }
        return couponReceiveList;
    }

    @Override
    public Message receiveCoupon(String mobile, String couponTemplateId, String shareUserId) {
        CouponTemplatePO couponTemplate = couponTemplateDAO.selectByPrimaryKey(couponTemplateId);
        Message message = checkCoupon(couponTemplate, shareUserId);
        if(message.getCode() != Message.SUUCESS) {
            return message;
        }
        List<UserBasePO> userBasePO = userBaseService.selectByMobile(mobile);
        if (userBasePO == null || userBasePO.isEmpty()) {
            if (couponTemplate.getDailyCount() > 0) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("mobile", mobile);
                param.put("couponTemplateId", couponTemplateId);
                param.put("createTime", DateUtil.getZeroOfToday());
                int count = couponMobileDAO.getCountByParams(param);
                if (count >= couponTemplate.getDailyCount()) {
                    return Message.error(BizReturnCode.COUPON_DAILY_OUT);
                }
            }
            if (couponTemplate.getMaxReceiveCount() > 0) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("mobile", mobile);
                param.put("couponTemplateId", couponTemplateId);
                int count = couponMobileDAO.getCountByParams(param);
                if (count >= couponTemplate.getMaxReceiveCount()) {
                    return Message.error(BizReturnCode.COUPON_REVEICE_OUT);
                }
            }
            Map<String, Object> param1 = new HashMap<String, Object>();
            param1.put("shareUserId", shareUserId);
            param1.put("couponTemplateId", couponTemplateId);
            if(couponMobileDAO.getCountByParams(param1) > 10) {
                return Message.error(BizReturnCode.COUPON_OUT);
            }
            CouponMobilePO couponMobile = new CouponMobilePO();
            couponMobile.setCouponStatus(CouponStatusEnum.UN_REGISTER.status);
            couponMobile.setCouponTemplateId(couponTemplateId);
            couponMobile.setCreateTime(Calendar.getInstance().getTime());
            couponMobile.setMobile(mobile);
            couponMobile.setShareUserId(shareUserId);
            try {
                couponMobileDAO.insert(couponMobile);
                //获取并储存储存微信用户信息
                
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                return Message.error(BizReturnCode.SYSTEM_ERR);
            }
        } else {
            String registerCouponTemplateId = PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "share_register_coupon_template_id");
            LOGGER.info("new user receive coupon couponTemplateId:{} and registerCouponTemplateId:{}", couponTemplateId, registerCouponTemplateId);
            if(couponTemplateId.equals(registerCouponTemplateId)) {
                return Message.error(BizReturnCode.COUPON_FOR_NEW);
            }
            String userId = userBasePO.get(0).getUserId();
            if(userId.equals(shareUserId)) {
                return Message.error(BizReturnCode.COUPON_REVEICE_SELF);
            }
            if (couponTemplate.getDailyCount() > 0) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("userId", userId);
                param.put("couponTemplateId", couponTemplateId);
                param.put("createTime", DateUtil.getZeroOfToday());
                int count = couponUserDAO.getCountByParams(param);
                if (count >= couponTemplate.getDailyCount()) {
                    return Message.error(BizReturnCode.COUPON_DAILY_OUT);
                }
            }
            if (couponTemplate.getMaxReceiveCount() > 0) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("userId", userId);
                param.put("couponTemplateId", couponTemplateId);
                int count = couponUserDAO.getCountByParams(param);
                if (count >= couponTemplate.getMaxReceiveCount()) {
                    return Message.error(BizReturnCode.COUPON_REVEICE_OUT);
                }
            }
            Map<String, Object> param1 = new HashMap<String, Object>();
            param1.put("shareUserId", shareUserId);
            param1.put("couponTemplateId", couponTemplateId);
            if(couponUserDAO.getCountByParams(param1) > 10) {
                return Message.error(BizReturnCode.COUPON_OUT);
            }
            createCouponUserByTemplate(couponTemplate, mobile, userId, shareUserId);
        }
        smsService.distributeSMSService(smsProConfig.getReceiveCouponSMSContent(),mobile);
        return message;
    }

    @Override
    @Transactional
    public Message receiveCoupon(CouponReceiveInfoPO receiveInfoPO) {
        String couponTemplateId = receiveInfoPO.getCouponTemplateId();
        CouponTemplatePO couponTemplate = couponTemplateDAO.selectByPrimaryKey(couponTemplateId);
        String shareUserId = receiveInfoPO.getShareUserId();
        Message message = checkCoupon(couponTemplate, shareUserId);
        if(message.getCode() != Message.SUUCESS) {
            return message;
        }
        String mobile = receiveInfoPO.getMobile();
        String tradeNo = receiveInfoPO.getTradeNo();
        if(!StringUtil.isBlank(tradeNo)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("tradeNo", tradeNo);
            if(couponReceiveInfoDAO.getCountByParams(param) >= 10) {
                return Message.error(BizReturnCode.COUPON_REVEICE_OUT);
            }
            param.put("mobile", mobile);
            if(couponReceiveInfoDAO.getCountByParams(param) > 0) {
                return Message.error(BizReturnCode.COUPON_OUT);
            }
        }
        if(couponTemplate.getDailyCount() > 0) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("mobile", mobile);
            param.put("receiveTime", DateUtil.getZeroOfToday());
            param.put("couponTemplateId", couponTemplateId);
            if (couponReceiveInfoDAO.getCountByParams(param) >= couponTemplate.getDailyCount()) {
                return Message.error(BizReturnCode.COUPON_DAILY_OUT);
            }
        }
        if(couponTemplate.getMaxReceiveCount() > 0) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("mobile", mobile);
            param.put("couponTemplateId", couponTemplateId);
            if (couponReceiveInfoDAO.getCountByParams(param) >= couponTemplate.getMaxReceiveCount()) {
                return Message.error(BizReturnCode.COUPON_REVEICE_OUT);
            }
        }
        List<UserBasePO> userBasePO = userBaseService.selectByMobile(mobile);
        if (userBasePO == null || userBasePO.isEmpty()) {
            CouponMobilePO couponMobile = new CouponMobilePO();
            couponMobile.setCouponStatus(CouponStatusEnum.UN_REGISTER.status);
            couponMobile.setCouponTemplateId(couponTemplateId);
            couponMobile.setCreateTime(Calendar.getInstance().getTime());
            couponMobile.setMobile(mobile);
            couponMobile.setShareUserId(shareUserId);
            try {
                couponMobileDAO.insert(couponMobile);
                //获取并储存储存微信用户信息
                
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                return Message.error(BizReturnCode.SYSTEM_ERR);
            }
        } else {
            String userId = userBasePO.get(0).getUserId();
            String registerCouponTemplateId = PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "share_register_coupon_template_id");
            LOGGER.info("new user receive coupon couponTemplateId:{} and registerCouponTemplateId:{}", couponTemplateId, registerCouponTemplateId);
            if(couponTemplateId.equals(registerCouponTemplateId)) {
                return Message.error(BizReturnCode.COUPON_FOR_NEW);
            }
            if(userId.equals(shareUserId)) {
                return Message.error(BizReturnCode.COUPON_REVEICE_SELF);
            }
            createCouponUserByTemplate(couponTemplate, mobile, userId, shareUserId);
        }
      
        smsService.distributeSMSService(smsProConfig.getReceiveCouponSMSContent(),mobile);
        
        receiveInfoPO.setValue(couponTemplate.getValue());
        couponReceiveInfoDAO.insert(receiveInfoPO);
        return message;
    }

    private void createCouponUserByTemplate(CouponTemplatePO couponTemplate, String mobile, String userId, String shareUserId) {
        CouponUserPO couponUserPO = new CouponUserPO();
        couponUserPO.setCouponName(couponTemplate.getCouponName());
        couponUserPO.setCouponNo(BizCodeUtil.generateCouponNo(mobile,couponTemplate.getCouponTemplateId()));
        couponUserPO.setCouponPicKey(couponTemplate.getCouponPicKey());
        couponUserPO.setDisabledPicKey(couponTemplate.getDisabledPicKey());
        couponUserPO.setCouponStatus(CouponStatusEnum.NOT_USE.status);
        couponUserPO.setCouponTemplateId(couponTemplate.getCouponTemplateId());
        couponUserPO.setCreateTime(Calendar.getInstance().getTime());
        couponUserPO.setDescription(couponTemplate.getDescription());
        couponUserPO.setEndTime(couponTemplate.getEndTime());
        couponUserPO.setMinCost(couponTemplate.getMinCost());
        couponUserPO.setStartTime(couponTemplate.getStartTime());
        couponUserPO.setCouponType(couponTemplate.getCouponType());
        if (couponTemplate.getCouponType().equals(CouponTypeEnum.USE_RETURN.status)) {
            couponUserPO.setShareUserId(shareUserId);
        }
        couponUserPO.setUserId(userId);
        couponUserPO.setValue(couponTemplate.getValue());
        couponUserDAO.insert(couponUserPO);
    }

	private Message checkCoupon(CouponTemplatePO couponTemplate, String shareUserId) {
        if (couponTemplate == null || couponTemplate.getCouponStatus() == null || CouponStatusEnum.INVALID.status.equals(couponTemplate.getCouponStatus())) {
            return Message.error(BizReturnCode.COUPON_INVALID);
        }
        if (CouponStatusEnum.OUT.status.equals(couponTemplate.getCouponStatus())) {
            return Message.error(BizReturnCode.COUPON_OUT);
        }
        if (CouponStatusEnum.FROZEN.status.equals(couponTemplate.getCouponStatus())) {
            return Message.error(BizReturnCode.COUPON_FROZE);
        }
        if (couponTemplate.getEndTime().getTime() < System.currentTimeMillis()) {
            return Message.error(BizReturnCode.COUPON_EXPIRED);
        }
        if(StringUtil.isBlank(shareUserId) || userBaseService.getUserInfo(shareUserId) == null) {
            return Message.error(BizReturnCode.COUPON_SHARE_USER_NOT_EXIST);
        }
        if (couponTemplate.getMaxCount() > 0) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("couponTemplateId", couponTemplate.getCouponTemplateId());
            int mobileCount = couponMobileDAO.getCountByParams(param);
            int userCount = couponUserDAO.getCountByParams(param);
            if (mobileCount + userCount >= couponTemplate.getMaxCount()) {
                couponTemplate.setCouponStatus(CouponStatusEnum.OUT.status);
                couponTemplateDAO.updateByPrimaryKey(couponTemplate);
                return Message.error(BizReturnCode.COUPON_OUT);
            }
        }
        return Message.success();
	}
}

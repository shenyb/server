/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.coupon.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.coupon.*;
import com.need.common.core.pub.SMSProConfig;
import com.need.common.core.service.coupon.CouponService;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.core.service.user.UserCacheService;
import com.need.common.domain.enums.CouponExchangeTypeEnum;
import com.need.common.domain.enums.CouponStatusEnum;
import com.need.common.domain.enums.CouponTypeEnum;
import com.need.common.domain.po.api.coupon.*;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.coupon.ShareCouponVO;
import com.need.gateway.sms.SMSService;
import com.need.utils.StringUtil;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *
 * @author 庆凯 2015-9-9 2015-9-9 15:46:42
 * @ClassName CouponServiceImpl
 * @Description TODO
 * @version V1.1
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-9
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
    private CouponExchangeDAO couponExchangeDAO;
    
    @Autowired
    private CouponExchangeUserDAO couponExchangeUserDAO;

    @Autowired
    private TradeBaseService tradeBaseService;

    @Autowired
    private UserCacheService userCacheService;
    
    @Autowired
    private SMSService smsService;

    @Autowired
   	private SMSProConfig smsProConfig;
    
    @Override
    public Message getCouponListByUserId(String userId, String couponStatus, Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        Page<CouponUserPO> list = (Page<CouponUserPO>) couponUserDAO.getCouponUserListByUserId(userId, couponStatus);
        Message message = Message.success();
        message.addData("couponList", list.getResult());
        message.addData("totalCount", list.getTotal());
        return message;
    }

    @Override
    public Message getAvailableCouponListByUserIdAndTradeNo(String userId, String tradeNo, Integer pageNum, Integer pageSize) {
        Message message = tradeBaseService.getTradeTotalPrice(tradeNo);
        if (message.getCode() != Message.SUCCESS) {
            return message;
        }
        int cost = (int) message.getData().get("totalPrice");
        return getAvailableCouponListByUserIdAndCost(userId, cost, Boolean.FALSE.toString().toUpperCase(), pageNum, pageSize);
    }

    @Override
    public Message getAvailableCouponListByUserIdAndCost(String userId, Integer cost, String isCheap, Integer pageNum, Integer pageSize) {
        Message message = Message.success();
        //团便宜商品不允许使用优惠券
        if(isCheap != null && Boolean.TRUE.toString().toUpperCase().equals(isCheap)) {
            message.addData("couponList", new Page<CouponUserPO>());
            message.addData("totalCount", 0);
            return message;
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("cost", cost);
        Page<CouponUserPO> list = (Page<CouponUserPO>) couponUserDAO.getCouponUserListByUserIdAndCost(param);
        message.addData("couponList", list.getResult());
        message.addData("totalCount", list.getTotal());
        return message;
    }

    @Override
    public Message useCouponPrepay(String couponNo, String tradeNo, String userId) {
        CouponUserPO coupon = couponUserDAO.selectByPrimaryKey(couponNo);
        if (coupon == null) {
            return Message.error(BizReturnCode.COUPONNO_NOT_EXIST);
        }
        if (CouponStatusEnum.FROZEN.status.equals(coupon.getCouponStatus())) {
            return Message.error(BizReturnCode.COUPON_FROZE);
        }
        if (CouponStatusEnum.USED.status.equals(coupon.getCouponStatus())) {
            return Message.error(BizReturnCode.COUPON_USED);
        }
        if (CouponStatusEnum.PRE_PAY.status.equals(coupon.getCouponStatus()) && (coupon.getTradeNo() == null || !coupon.getTradeNo().equals(tradeNo))) {
            return Message.error(BizReturnCode.COUPON_USED);
        }
        if (coupon.getUserId() == null || !coupon.getUserId().equals(userId)) {
            return Message.error(BizReturnCode.COUPON_NOT_OWN);
        }
        long now = System.currentTimeMillis();
        if (coupon.getStartTime().getTime() > now) {
            return Message.error(BizReturnCode.COUPON_NOT_BEGIN);
        }
        if (now > coupon.getEndTime().getTime()) {
            return Message.error(BizReturnCode.COUPON_EXPIRED);
        }
        LOGGER.debug("useCouponPrepay couponNo:{} tradeNo:{} userId:{}", couponNo, tradeNo, userId);
        if (coupon.getMinCost() > 0) {
            Message message = tradeBaseService.getTradeTotalPrice(tradeNo);
            if(message.getCode() != Message.SUCCESS) {
                return message;
            }
            int cost = (int) message.getData().get("totalPrice");
            if (coupon.getMinCost() > cost) {
                return Message.error(BizReturnCode.COUPON_COST_NOTENOUGH);
            }
        }
        coupon.setUseTime(Calendar.getInstance().getTime());
        coupon.setCouponStatus(CouponStatusEnum.PRE_PAY.status);
        coupon.setTradeNo(tradeNo);
        try {
            couponUserDAO.updateByPrimaryKey(coupon);
        } catch (Exception e) {
            return Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);
        }
        Message message = Message.success();
        message.addData("value", coupon.getValue());
        return message;
    }

    @Override
    public void useCouponPayedSuccess(String tradeNo) {
        List<CouponUserPO> couponList = couponUserDAO.getCouponUserListByTradeNo(tradeNo);
        if (couponList == null || couponList.isEmpty()) {
            return;
        }
        if (couponList.size() > 1) {
            LOGGER.error("multi coupon used in one trade which tradeNo:{}", tradeNo);
        }
        CouponUserPO coupon = couponList.get(0);
        if (CouponStatusEnum.FROZEN.status.equals(coupon.getCouponStatus())) {
            return;
        }
        if (CouponStatusEnum.USED.status.equals(coupon.getCouponStatus())) {
            return;
        }
        if (coupon.getTradeNo() == null) {
            return;
        }
        LOGGER.info("useCouponPayedSuccess tradeNo:{}", tradeNo);
        coupon.setCouponStatus(CouponStatusEnum.USED.status);
        couponUserDAO.updateByPrimaryKey(coupon);
        if(!CouponTypeEnum.USE_RETURN.status.equals(coupon.getCouponType())) {
            return;
        }
        String shareUserId = coupon.getShareUserId();
        if (shareUserId != null) {
            Map<String, String> userMap = userCacheService.getById(shareUserId);
            if (userMap == null) {
                return;
            }
            CouponUserPO couponUserPO = new CouponUserPO();
            couponUserPO.setCouponName(coupon.getCouponName());
            couponUserPO.setCouponNo(BizCodeUtil.generateCouponNo(userMap.get("mobile") == null ? userMap.get("userId") : userMap.get("mobile"),coupon.getCouponTemplateId()));
            couponUserPO.setCouponPicKey(coupon.getCouponPicKey());
            couponUserPO.setDisabledPicKey(coupon.getDisabledPicKey());
            couponUserPO.setCouponStatus(CouponStatusEnum.NOT_USE.status);
            couponUserPO.setCouponTemplateId(coupon.getCouponTemplateId());
            couponUserPO.setCreateTime(Calendar.getInstance().getTime());
            couponUserPO.setDescription(coupon.getDescription());
            couponUserPO.setEndTime(coupon.getEndTime());
            couponUserPO.setMinCost(coupon.getMinCost());
            couponUserPO.setStartTime(coupon.getStartTime());
            couponUserPO.setCouponType(coupon.getCouponType());
            couponUserPO.setUserId(shareUserId);
            couponUserPO.setValue(coupon.getValue());
            couponUserPO.setGoodsId(coupon.getGoodsId());
            couponUserDAO.insert(couponUserPO);
            LOGGER.info("use return coupon which shareUserId:{} and usedCouponNo:{} and newCouponNo:{} and tradeNo:{}", 
                    shareUserId, coupon.getCouponNo(), couponUserPO.getCouponNo(), tradeNo);
            if(!StringUtil.isBlank(userMap.get("mobile"))) {               
                smsService.distributeSMSService(smsProConfig.getReturnCouponSMSContent(),userMap.get("mobile"));
            }
        }
    }

    @Override
    public void useCouponPayedFail(String tradeNo) {
        List<CouponUserPO> couponList = couponUserDAO.getCouponUserListByTradeNo(tradeNo);
        if (couponList == null || couponList.isEmpty()) {
            return;
        }
        if (couponList.size() > 1) {
            LOGGER.error("multi coupon used in one trade which tradeNo:{}", tradeNo);
        }
        CouponUserPO coupon = couponList.get(0);
        if (!CouponStatusEnum.PRE_PAY.status.equals(coupon.getCouponStatus())) {
            return;
        }
        if (coupon.getTradeNo() == null) {
            return;
        }
        LOGGER.info("useCouponPayedFail tradeNo:{}", tradeNo);
        coupon.setCouponStatus(CouponStatusEnum.NOT_USE.status);
        couponUserDAO.updateByPrimaryKey(coupon);
    }

    @Override
    @Transactional
    public void checkRegisterCouponMobile(String mobile, String userId) {
        List<CouponMobilePO> couponMobileList = couponMobileDAO.getCouponMobileListByMobile(mobile);
        Map<String, CouponTemplatePO> couponTemplateMap = new HashMap<String, CouponTemplatePO>();
        for (CouponMobilePO couponMobilePO : couponMobileList) {
            String couponTemplateId = couponMobilePO.getCouponTemplateId();
            CouponTemplatePO couponTemplate = couponTemplateMap.get(couponTemplateId);
            if (couponTemplate == null) {
                couponTemplate = couponTemplateDAO.selectByPrimaryKey(couponTemplateId);
                couponTemplateMap.put(couponTemplateId, couponTemplate);
            }
            createCouponUserByTemplate(couponTemplate, mobile, userId, couponMobilePO.getShareUserId());
            couponMobilePO.setCouponStatus(CouponStatusEnum.REGISTERED.status);
            couponMobileDAO.updateByPrimaryKey(couponMobilePO);
        }
        List<CouponTemplatePO> couponTemplateList = couponTemplateDAO.getCouponListByType(CouponTypeEnum.NEW_USER.status);
        for (CouponTemplatePO couponTemplatePO : couponTemplateList) {
            if(CouponStatusEnum.FROZEN.status.equals(couponTemplatePO.getCouponStatus())) {
                continue;
            }
            createCouponUserByTemplate(couponTemplatePO, mobile, userId, null);
        }
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
        couponUserPO.setGoodsId(couponTemplate.getGoodsId());
        couponUserDAO.insert(couponUserPO);
    }

    @Override
    public CouponUserPO getCouponUserPOByCouponNo(String couponNo) {
        return couponUserDAO.selectByPrimaryKey(couponNo);
    }

    @Override
    public CouponUserPO getCouponUserByTradeNo(String tradeNo) {
        List<CouponUserPO> couponUserList = couponUserDAO.getCouponUserListByTradeNo(tradeNo);
        if(couponUserList == null || couponUserList.isEmpty()) {
            return null;
        }
        if(couponUserList.size() > 1) {
            LOGGER.error("multi coupon used in one trade which tradeNo:{}", tradeNo);
        }
        return couponUserList.get(0);
    }

    @Override
    public Message getShareCouponInfo(String couponTemplateId) {
        CouponTemplatePO couponTemplate = couponTemplateDAO.selectByPrimaryKey(couponTemplateId);
        if(couponTemplate == null) {
            return Message.error(BizReturnCode.COUPON_NOT_EXIST);
        }
        ShareCouponVO shareCouponVO = new ShareCouponVO();
        shareCouponVO.setCouponRecommend(couponTemplate.getCouponRecommend());
        if(!StringUtil.isBlank(couponTemplate.getCouponRule())) {
            try {
                JSONArray array = JSONArray.fromObject(couponTemplate.getCouponRule());
                List<String> list = new ArrayList<String>();
                for(Object object : array) {
                    if(object != null) {
                        list.add(object.toString());
                    }
                }
                shareCouponVO.setCouponRule(list);
            } catch(Exception e) {
                LOGGER.error("couponRule error which couponTeplateId is " + couponTemplateId, e);
            }
        }
        shareCouponVO.setCouponShareTitle(couponTemplate.getCouponShareTitle());
        Message message = Message.success();
        message.addData("couponInfo", shareCouponVO);
        return message;
    }
    /**
     * 
     * @author shenyb 2015年10月20日 下午2:22:30
     * @param goodsIds
     * @Method: useCouponPrepay 
     * @Description: TODO
     * @param couponNo
     * @param tradeNo
     * @param totalPrice
     * @param userId
     * @return 
     * @see com.need.api.service.coupon.CouponService#useCouponPrepay(java.lang.String, int, java.lang.String)
     */
    @Override
    public Message useCouponPrepay(String couponNo, String tradeNo,int totalPrice, String userId, Collection<String> goodsIds) {
        CouponUserPO coupon = couponUserDAO.selectByPrimaryKey(couponNo);
        if (coupon == null) {
            return Message.error(BizReturnCode.COUPONNO_NOT_EXIST);
        }
        if (CouponStatusEnum.FROZEN.status.equals(coupon.getCouponStatus())) {
            return Message.error(BizReturnCode.COUPON_FROZE);
        }
        if (CouponStatusEnum.USED.status.equals(coupon.getCouponStatus())) {
            return Message.error(BizReturnCode.COUPON_USED);
        }
        if (!StringUtil.isBlank(coupon.getGoodsId()) && (goodsIds == null || !goodsIds.contains(coupon.getGoodsId()))) {
            return Message.error(BizReturnCode.COUPON_GOODS_NOT_MATCH);
        }
        String status = CouponStatusEnum.PRE_PAY.status;
        if (tradeNo == null) {
            //TODO 临时解决多订单使用优惠券问题，有无法审计和无法退还等缺陷
            status = CouponStatusEnum.USED.status;
            tradeNo = "";
        }
        if (CouponStatusEnum.PRE_PAY.status.equals(coupon.getCouponStatus()) && (coupon.getTradeNo() == null || !coupon.getTradeNo().equals(tradeNo))) {
            return Message.error(BizReturnCode.COUPON_USED);
        }
        if (coupon.getUserId() == null || !coupon.getUserId().equals(userId)) {
            return Message.error(BizReturnCode.COUPON_NOT_OWN);
        }
        long now = System.currentTimeMillis();
        if (coupon.getStartTime().getTime() > now) {
            return Message.error(BizReturnCode.COUPON_NOT_BEGIN);
        }
        if (now > coupon.getEndTime().getTime()) {
            return Message.error(BizReturnCode.COUPON_EXPIRED);
        }
        LOGGER.debug("useCouponPrepay couponNo:{} tradeNo:{} userId:{} goodsIds:{} couponUser:{}", couponNo, tradeNo, userId, goodsIds, coupon);
        if (coupon.getMinCost() > 0) {
           // Message message = tradeBaseService.getTradeTotalPrice(tradeNo);
            int cost = totalPrice;//(int) message.getData().get("totalPrice");
            if (coupon.getMinCost() > cost) {
                return Message.error(BizReturnCode.COUPON_COST_NOTENOUGH);
            }
        }
        coupon.setUseTime(Calendar.getInstance().getTime());
        coupon.setCouponStatus(status);
        coupon.setTradeNo(tradeNo);
        try {
            couponUserDAO.updateByPrimaryKey(coupon);
        } catch (Exception e) {
            return Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);
        }
        Message message = Message.success();
        message.addData("value", coupon.getValue());
        return message;
    }

    @Transactional
    @Override
    public Message exchangeCoupon(String couponExchangeCode, String userId) {
        List<CouponExchangePO> couponExchangeList = couponExchangeDAO.queryByCouponExchangeCode(couponExchangeCode);
        if(couponExchangeList.isEmpty()) {
            return Message.error(BizReturnCode.COUPON_EXCHANGE_CODE_NOT_EXIST);
        }
        Map<String, String> userMap = userCacheService.getById(userId);
        if(userMap == null) {
            return Message.error(BizReturnCode.USERID_NOT_EXIST);
        }
        if(couponExchangeList.size() > 1) {
            LOGGER.error("couponExchangeCode duplicate :" + couponExchangeList.toString());
        }
        CouponExchangePO couponExchangePO = couponExchangeList.get(0);
        boolean isSingle = CouponExchangeTypeEnum.SINGLE.status.equals(couponExchangePO.getCouponExchangeType());
        if(isSingle && !StringUtil.isBlank(couponExchangePO.getUserId())) {
            return Message.error(BizReturnCode.COUPON_EXCHANGED);
        }
        if(CouponStatusEnum.FROZEN.status.equals(couponExchangePO.getCouponExchangeStatus())) {
            return Message.error(BizReturnCode.COUPON_EXCHANGE_CODE_NOT_EXIST);
        }
        Date now = Calendar.getInstance().getTime();
        if(couponExchangePO.getStartTime().after(now)) {
            return Message.error(BizReturnCode.COUPON_EXCHANGE_CODE_NOT_EXIST);
        }
        if(couponExchangePO.getEndTime().before(now)) {
            return Message.error(BizReturnCode.COUPON_EXCHANGE_EXPIRED);
        }
        CouponExchangeUserPOKey couponExchangeUserPOKey = new CouponExchangeUserPOKey();
        Integer couponExchangeId = couponExchangePO.getCouponExchangeId();
        couponExchangeUserPOKey.setCouponExchangeId(couponExchangeId);
        couponExchangeUserPOKey.setUserId(userId);
        if(couponExchangeUserDAO.selectByPrimaryKey(couponExchangeUserPOKey) != null) {
            return Message.error(BizReturnCode.COUPON_EXCHANGED);
        }
        String[] couponTemplateIds = couponExchangePO.getCouponTemplateIds().split(",");
        if(couponTemplateIds.length <= 0) {
            LOGGER.error("couponExchange template empty :" + couponExchangePO.toString());
        }
        if(CouponExchangeTypeEnum.NOTRADE.status.equals(couponExchangePO.getCouponExchangeType())) {
            if(tradeBaseService.getRealTradeCountByUserId(userId) > 0) {
                return Message.error(BizReturnCode.COUPON_EXCHANGE_NO_TRADE);
            }
        }
        String mobile = userMap.get("mobile");
        for(String couponTemplateId : couponTemplateIds) {
            CouponTemplatePO couponTemplate = couponTemplateDAO.selectByPrimaryKey(couponTemplateId);
            if (couponTemplate == null || couponTemplate.getCouponStatus() == null || CouponStatusEnum.INVALID.status.equals(couponTemplate.getCouponStatus())) {
                LOGGER.error("couponExchange template not ready which couponExchange : " + couponExchangePO.toString());
                return Message.error(BizReturnCode.COUPON_NOT_EXIST);
            }
            if (CouponStatusEnum.OUT.status.equals(couponTemplate.getCouponStatus())) {
                return Message.error(BizReturnCode.COUPON_OUT);
            }
            if (CouponStatusEnum.FROZEN.status.equals(couponTemplate.getCouponStatus())) {
                return Message.error(BizReturnCode.COUPON_FROZE);
            }
            if (couponTemplate.getMaxCount() > 0) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("couponTemplateId", couponTemplateId);
                int mobileCount = couponMobileDAO.getCountByParams(param);
                int userCount = couponUserDAO.getCountByParams(param);
                if (mobileCount + userCount >= couponTemplate.getMaxCount()) {
                    couponTemplate.setCouponStatus(CouponStatusEnum.OUT.status);
                    couponTemplateDAO.updateByPrimaryKey(couponTemplate);
                    return Message.error(BizReturnCode.COUPON_OUT);
                }
            }
            createCouponUserByTemplate(couponTemplate, mobile, userId, null);
        }
        if(isSingle) {
            couponExchangePO.setUserId(userId);
            couponExchangeDAO.updateByPrimaryKey(couponExchangePO);
        }
        CouponExchangeUserPO couponExchangeUserPO = new CouponExchangeUserPO();
        couponExchangeUserPO.setCouponExchangeId(couponExchangeId);
        couponExchangeUserPO.setUserId(userId);
        couponExchangeUserPO.setCreateTime(now);
        couponExchangeUserDAO.insert(couponExchangeUserPO);
        smsService.distributeSMSService(smsProConfig.getExchangeCouponSMSContent(),mobile);
        return Message.success();
    }
    


}

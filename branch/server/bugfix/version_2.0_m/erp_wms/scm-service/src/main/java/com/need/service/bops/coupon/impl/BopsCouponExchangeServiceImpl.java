/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Exchanges
 * and open the template in the editor.
 */

package com.need.service.bops.coupon.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.coupon.CouponExchangeDAO;
import com.need.dao.api.user.UserBaseDAO;
import com.need.dao.bops.coupon.BopsCouponExchangeAuditDAO;
import com.need.dao.bops.coupon.BopsCouponExchangeDAO;
import com.need.dao.bops.coupon.BopsCouponTemplateDAO;
import com.need.domain.common.enums.CouponExchangeStatusEnum;
import com.need.domain.po.api.coupon.CouponExchangePO;
import com.need.domain.po.bops.coupon.BopsCouponExchangeAuditPO;
import com.need.domain.po.bops.coupon.BopsCouponExchangePO;
import com.need.domain.po.bops.coupon.BopsCouponTemplatePO;
import com.need.domain.pub.Message;
import com.need.domain.vo.coupon.CouponExchangeVO;
import com.need.domain.vo.coupon.CouponTemplateDetailVO;
import com.need.service.bops.coupon.BopsCouponExchangeService;
import com.need.service.constant.BizReturnCode;

/**
 * 
 * @author 庆凯 2015-9-14 2015-9-14 13:29:19
 * @ClassName BopsCouponExchangeServiceImpl
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-14
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class BopsCouponExchangeServiceImpl implements BopsCouponExchangeService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BopsCouponExchangeServiceImpl.class);
    
    @Autowired
    private BopsCouponExchangeDAO bopsCouponExchangeDAO;
    
    @Autowired
    private BopsCouponExchangeAuditDAO bopsCouponExchangeAuditDAO;
    
    @Autowired
    private CouponExchangeDAO couponExchangeDAO;
    
    @Autowired
    private UserBaseDAO userBaseDAO;
    
    @Autowired
    private BopsCouponTemplateDAO bopsCouponTemplateDAO;

    @Override
    public Message addCouponExchange(BopsCouponExchangePO bopsCouponExchangePO) {
        if(!CouponExchangeStatusEnum.INVALID.status.equals(bopsCouponExchangePO.getAuditStatus()) && 
                !CouponExchangeStatusEnum.DRAFT.status.equals(bopsCouponExchangePO.getAuditStatus())) {
            return Message.error(BizReturnCode.COUPON_VALID_UPDATE);
        }
        bopsCouponExchangePO.setCreateTime(Calendar.getInstance().getTime());
        List<BopsCouponExchangePO> bopsCouponExchangeList = bopsCouponExchangeDAO.queryByCouponExchangeCode(bopsCouponExchangePO.getCouponExchangeCode());
        if(bopsCouponExchangeList.size() > 0) {
            return Message.error(BizReturnCode.COUPON_EXCHANGE_CODE_DUPLICATE);
        }
        try {
            bopsCouponExchangeDAO.insert(bopsCouponExchangePO);
        } catch(Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Message.error(BizReturnCode.SYSTEM_ERR);
        }
        return Message.success();
    }

    @Override
    public Message updateCouponExchange(BopsCouponExchangePO bopsCouponExchangePO) {
        if(!CouponExchangeStatusEnum.DRAFT.status.equals(bopsCouponExchangePO.getAuditStatus()) && 
                !CouponExchangeStatusEnum.INVALID.status.equals(bopsCouponExchangePO.getAuditStatus())) {
            return Message.error(BizReturnCode.COUPON_VALID_UPDATE);
        }
        List<BopsCouponExchangePO> bopsCouponExchangeList = bopsCouponExchangeDAO.queryByCouponExchangeCode(bopsCouponExchangePO.getCouponExchangeCode());
        if(bopsCouponExchangeList.size() > 0 && !bopsCouponExchangeList.get(0).getCouponExchangeId().equals(bopsCouponExchangePO.getCouponExchangeId())) {
            return Message.error(BizReturnCode.COUPON_EXCHANGE_CODE_DUPLICATE);
        }
        try {
            bopsCouponExchangeDAO.updateByPrimaryKey(bopsCouponExchangePO);
        } catch(Exception e) {
            return Message.error(BizReturnCode.SYSTEM_ERR);
        }
        return Message.success();
    }

    @Override
    public Message freezeCouponExchange(Integer couponExchangeId) {
        bopsCouponExchangeDAO.updateFrozen(couponExchangeId);
        couponExchangeDAO.updateFrozen(couponExchangeId);
        return Message.success();
    }

    @Override
    @Transactional
    public Message auditCouponExchange(BopsCouponExchangeAuditPO bopsCouponExchangeAuditPO, Integer userId) {
        bopsCouponExchangeAuditPO.setAuditUserId(userId);
        bopsCouponExchangeAuditPO.setAuditTime(Calendar.getInstance().getTime());
        bopsCouponExchangeAuditDAO.insert(bopsCouponExchangeAuditPO);
        BopsCouponExchangePO bopsCouponExchangePO = bopsCouponExchangeDAO.selectByPrimaryKey(bopsCouponExchangeAuditPO.getCouponExchangeId());
        if(bopsCouponExchangePO == null) {
            return Message.error(BizReturnCode.COUPON_NOT_EXIST);
        }
        bopsCouponExchangePO.setAuditStatus(bopsCouponExchangeAuditPO.getAuditStatus());
        bopsCouponExchangeDAO.updateByPrimaryKey(bopsCouponExchangePO);
        if(CouponExchangeStatusEnum.VALID.status.equals(bopsCouponExchangePO.getAuditStatus())) {
            CouponExchangePO couponExchangePO = new CouponExchangePO();
            couponExchangePO.setCouponExchangeId(bopsCouponExchangePO.getCouponExchangeId());
            couponExchangePO.setCouponExchangeCode(bopsCouponExchangePO.getCouponExchangeCode());
            couponExchangePO.setCouponExchangeType(bopsCouponExchangePO.getCouponExchangeType());
            couponExchangePO.setCouponTemplateIds(bopsCouponExchangePO.getCouponTemplateIds());
            couponExchangePO.setStartTime(bopsCouponExchangePO.getStartTime());
            couponExchangePO.setEndTime(bopsCouponExchangePO.getEndTime());
            couponExchangePO.setCouponExchangeStatus(CouponExchangeStatusEnum.VALID.status);
            couponExchangeDAO.insert(couponExchangePO);
        }
        return Message.success();
    }
    
    /**
     * 
     * @author liuhongyang 2015年10月27日 上午11:47:30
     * @Method: selectCouponExchangeDetial 
     * @Description: 优惠券兑换码编辑
     * @param couponExchangeId
     * @return 
     * @see com.need.service.bops.coupon.BopsCouponExchangeService#selectCouponExchangeDetial(java.lang.String)
     */
	@Override
	@Transactional
	public CouponExchangeVO selectCouponExchangeDetial(String couponExchangeId) {
		BopsCouponExchangePO bopsCouponExchangePO = bopsCouponExchangeDAO.selectByPrimaryKey(Integer.valueOf(couponExchangeId));
		String couponTemplateIds =bopsCouponExchangePO.getCouponTemplateIds();
		String [] couponTemplateIdArray=couponTemplateIds.split(",");
		List<BopsCouponTemplatePO> couponTemplateList=new ArrayList<BopsCouponTemplatePO>();
		CouponExchangeVO couponExchangeVO =new CouponExchangeVO();
		for (int i = 0; i < couponTemplateIdArray.length; i++) {
			CouponTemplateDetailVO bopsCouponTemplatePO=bopsCouponTemplateDAO.selectByPrimaryKey(couponTemplateIdArray[i]);
			couponTemplateList.add(bopsCouponTemplatePO);
		}
		System.out.println(couponTemplateList.size());
		couponExchangeVO.setCouponTemplateList(couponTemplateList);
		BeanUtils.copyProperties(bopsCouponExchangePO, couponExchangeVO);
		return couponExchangeVO;
	}

}

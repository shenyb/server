/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.task.service.coupon.impl;

import com.need.task.common.enums.CouponStatusEnum;
import com.need.task.dao.jdbc.api.coupon.CouponUserDAO;
import com.need.task.dao.jdbc.api.coupon.po.CouponUserPO;
import com.need.task.service.coupon.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void useCouponPayedFail(List<String> tradeNos) { 	
    	for (String tradeNo : tradeNos) {
    	    List<CouponUserPO> couponList = couponUserDAO.getCouponUserListByTradeNo(tradeNo,CouponStatusEnum.PRE_PAY.status);
            if (couponList == null || couponList.isEmpty()) {
                continue;
            }
            if (couponList.size() > 1) {
                LOGGER.error("multi coupon used in one trade which tradeNo:{}", tradeNo);
            }
            CouponUserPO coupon = couponList.get(0);
            if (!CouponStatusEnum.PRE_PAY.status.equals(coupon.getCouponStatus())) {
            	continue;
            }
            if (coupon.getTradeNo() == null) {
            	continue;
            }
            LOGGER.debug("useCouponPayedFail tradeNo:{}", tradeNo);
            coupon.setCouponStatus(CouponStatusEnum.NOT_USE.status);
            couponUserDAO.updateByPrimaryKey(coupon);
		}
        
    }

}

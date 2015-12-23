/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Exchanges
 * and open the template in the editor.
 */

package com.need.domain.vo.coupon;

import java.io.Serializable;

import com.need.domain.pub.Page;

/**
 * 
 * @author 庆凯 2015-9-14 2015-9-14 12:21:12
 * @ClassName CouponExchangePageVO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-14
 * @modify by reason:{方法名}:{原因}
 */
public class CouponExchangePageVO extends Page implements Serializable {
    
    private static final long serialVersionUID = 1328829545569351089L;
    
    private String auditStatus;
    private String couponExchangeCode;
    private Integer couponExchangeId;

    /**
     * @return the auditStatus
     */
    public String getAuditStatus() {
        return auditStatus;
    }

    /**
     * @param auditStatus the auditStatus to set
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @return the couponExchangeCode
     */
    public String getCouponExchangeCode() {
        return couponExchangeCode;
    }

    /**
     * @param couponExchangeCode the couponExchangeCode to set
     */
    public void setCouponExchangeCode(String couponExchangeCode) {
        this.couponExchangeCode = couponExchangeCode;
    }

    /**
     * @return the couponExchangeId
     */
    public Integer getCouponExchangeId() {
        return couponExchangeId;
    }

    /**
     * @param couponExchangeId the couponExchangeId to set
     */
    public void setCouponExchangeId(Integer couponExchangeId) {
        this.couponExchangeId = couponExchangeId;
    }

    @Override
    public String toString() {
        return "CouponExchangePageVO{" + "auditStatus=" + auditStatus + ", couponExchangeCode=" + couponExchangeCode + 
                ", couponExchangeId=" + couponExchangeId + '}';
    }

}

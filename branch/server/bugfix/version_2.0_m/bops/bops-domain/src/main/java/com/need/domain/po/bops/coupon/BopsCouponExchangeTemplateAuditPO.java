/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.domain.po.bops.coupon;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 庆凯 2015-10-24 2015-10-24 15:34:44
 * @ClassName BopsCouponExchangeAuditPO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-10-24
 * @modify by reason:{方法名}:{原因}
 */
public class BopsCouponExchangeTemplateAuditPO implements Serializable {

    private static final long serialVersionUID = 584356182835962404L;
    
    private Integer couponExchangeAuditId;
    private String couponExchangeTemplateId;
    private String auditStatus;
    private String description;
    private Integer auditUserId;
    private Date auditTime;

    /**
     * @return the couponExchangeAuditId
     */
    public Integer getCouponExchangeAuditId() {
        return couponExchangeAuditId;
    }

    /**
     * @param couponExchangeAuditId the couponExchangeAuditId to set
     */
    public void setCouponExchangeAuditId(Integer couponExchangeAuditId) {
        this.couponExchangeAuditId = couponExchangeAuditId;
    }

    /**
     * @return the couponExchangeTemplateId
     */
    public String getCouponExchangeTemplateId() {
        return couponExchangeTemplateId;
    }

    /**
     * @param couponExchangeTemplateId the couponExchangeTemplateId to set
     */
    public void setCouponExchangeTemplateId(String couponExchangeTemplateId) {
        this.couponExchangeTemplateId = couponExchangeTemplateId;
    }

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the auditUserId
     */
    public Integer getAuditUserId() {
        return auditUserId;
    }

    /**
     * @param auditUserId the auditUserId to set
     */
    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

    /**
     * @return the auditTime
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * @param auditTime the auditTime to set
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    @Override
    public String toString() {
        return "BopsCouponExchangeAuditPO{" + "couponExchangeAuditId=" + couponExchangeAuditId + ", couponExchangeTemplateId=" + couponExchangeTemplateId + 
                ", auditStatus=" + auditStatus + ", description=" + description + ", auditUserId=" + auditUserId + ", auditTime=" + auditTime + '}';
    }

}

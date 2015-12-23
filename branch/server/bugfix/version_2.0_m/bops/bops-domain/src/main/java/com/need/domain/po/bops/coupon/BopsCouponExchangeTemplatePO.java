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
 * @author 庆凯 2015-10-24 2015-10-24 15:32:27
 * @ClassName BopsCouponExchangePO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-10-24
 * @modify by reason:{方法名}:{原因}
 */
public class BopsCouponExchangeTemplatePO implements Serializable {
    
    private static final long serialVersionUID = 8107317397463217373L;
    
    protected String couponExchangeTemplateId;
    protected String couponExchangeCode;
    protected int couponExchangeCount;
    protected String couponTemplateIds;
    protected String couponExchangeType;
    protected Date startTime;
    protected Date endTime;
    protected String auditStatus;
    protected Integer bopsUserId;
    protected Date createTime;

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
     * @return the couponExchangeCount
     */
    public int getCouponExchangeCount() {
        return couponExchangeCount;
    }

    /**
     * @param couponExchangeCount the couponExchangeCount to set
     */
    public void setCouponExchangeCount(int couponExchangeCount) {
        this.couponExchangeCount = couponExchangeCount;
    }

    /**
     * @return the couponTemplateIds
     */
    public String getCouponTemplateIds() {
        return couponTemplateIds;
    }

    /**
     * @param couponTemplateIds the couponTemplateIds to set
     */
    public void setCouponTemplateIds(String couponTemplateIds) {
        this.couponTemplateIds = couponTemplateIds;
    }

    /**
     * @return the couponExchangeType
     */
    public String getCouponExchangeType() {
        return couponExchangeType;
    }

    /**
     * @param couponExchangeType the couponExchangeType to set
     */
    public void setCouponExchangeType(String couponExchangeType) {
        this.couponExchangeType = couponExchangeType;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
     * @return the bopsUserId
     */
    public Integer getBopsUserId() {
        return bopsUserId;
    }

    /**
     * @param bopsUserId the bopsUserId to set
     */
    public void setBopsUserId(Integer bopsUserId) {
        this.bopsUserId = bopsUserId;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BopsCouponExchangePO{" + "couponExchangeTemplateId=" + couponExchangeTemplateId + ", couponExchangeCode=" + couponExchangeCode + 
                ", couponExchangeCount=" + couponExchangeCount + ", couponTemplateIds=" + couponTemplateIds + ", couponExchangeType=" + couponExchangeType + 
                ", startTime=" + startTime + ", endTime=" + endTime + ", auditStatus=" + auditStatus + ", bopsUserId=" + bopsUserId + ", createTime=" + createTime + '}';
    }

}

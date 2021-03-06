package com.need.domain.po.api.coupon;

import java.io.Serializable;
import java.util.Date;

public class CouponExchangePO implements Serializable {
    
    private static final long serialVersionUID = -8942144087021878458L;
    
    private Integer couponExchangeId;
    private String couponExchangeTemplateId;
    private String couponExchangeCode;
    private String couponTemplateIds;
    private String couponExchangeType;
    private String couponExchangeStatus;
    private Date startTime;
    private Date endTime;
    private String userId;

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
     * @return the couponExchangeStatus
     */
    public String getCouponExchangeStatus() {
        return couponExchangeStatus;
    }

    /**
     * @param couponExchangeStatus the couponExchangeStatus to set
     */
    public void setCouponExchangeStatus(String couponExchangeStatus) {
        this.couponExchangeStatus = couponExchangeStatus;
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
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CouponExchangePO{" + "couponExchangeId=" + couponExchangeId + "couponExchangeTemplateId=" + couponExchangeTemplateId + 
                ", couponExchangeCode=" + couponExchangeCode + ", couponTemplateIds=" + couponTemplateIds + ", couponExchangeType=" + couponExchangeType + 
                ", couponExchangeStatus=" + couponExchangeStatus + ", startTime=" + startTime + ", endTime=" + endTime + ", userId=" + userId + '}';
    }
}
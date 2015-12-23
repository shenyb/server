package com.need.common.domain.po.api.coupon;

import java.io.Serializable;
import java.util.Date;

public class CouponExchangePO implements Serializable {
    
    private static final long serialVersionUID = -8942144087021878458L;
    
    private Integer couponExchangeId;
    private String couponExchangeCode;
    private String couponTemplateIds;
    private String couponExchangeType;
    private Date startTime;
    private Date endTime;
    private String userId;
    private String couponExchangeStatus;

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

    @Override
    public String toString() {
        return "CouponExchangePO{" + "couponExchangeId=" + couponExchangeId + ", couponExchangeCode=" + couponExchangeCode + 
                ", couponTemplateIds=" + couponTemplateIds + ", couponExchangeType=" + couponExchangeType + 
                ", startTime=" + startTime + ", endTime=" + endTime + ", userId=" + userId + ", couponExchangeStatus=" + couponExchangeStatus + '}';
    }
}
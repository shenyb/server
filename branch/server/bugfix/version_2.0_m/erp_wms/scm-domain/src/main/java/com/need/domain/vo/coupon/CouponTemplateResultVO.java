/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.domain.vo.coupon;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 庆凯 2015-9-14 2015-9-14 13:31:10
 * @ClassName CouponTemplateInfoVO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-14
 * @modify by reason:{方法名}:{原因}
 */
public class CouponTemplateResultVO implements Serializable {
    
    private static final long serialVersionUID = -5634059647207381095L;
    
    private String couponTemplateNo;
    private String couponStatus;
    private String couponTemplateId;
    private Integer value;
    private Integer maxCount;
    private Integer minCost;
    private int receiveCount;
    private int useCount;
    private String useRate;
    private Date startTime;
    private Date endTime;

    /**
     * @return the couponTemplateNo
     */
    public String getCouponTemplateNo() {
        return couponTemplateNo;
    }

    /**
     * @param couponTemplateNo the couponTemplateNo to set
     */
    public void setCouponTemplateNo(String couponTemplateNo) {
        this.couponTemplateNo = couponTemplateNo;
    }

    /**
     * @return the couponStatus
     */
    public String getCouponStatus() {
        return couponStatus;
    }

    /**
     * @param couponStatus the couponStatus to set
     */
    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    /**
     * @return the couponTemplateId
     */
    public String getCouponTemplateId() {
        return couponTemplateId;
    }

    /**
     * @param couponTemplateId the couponTemplateId to set
     */
    public void setCouponTemplateId(String couponTemplateId) {
        this.couponTemplateId = couponTemplateId;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the maxCount
     */
    public int getMaxCount() {
        return maxCount;
    }

    /**
     * @param maxCount the maxCount to set
     */
    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    /**
     * @return the minCost
     */
    public Integer getMinCost() {
        return minCost;
    }

    /**
     * @param minCost the minCost to set
     */
    public void setMinCost(Integer minCost) {
        this.minCost = minCost;
    }

    /**
     * @return the receiveCount
     */
    public Integer getReceiveCount() {
        return receiveCount;
    }

    /**
     * @param receiveCount the receiveCount to set
     */
    public void setReceiveCount(Integer receiveCount) {
        this.receiveCount = receiveCount;
    }

    /**
     * @return the useCount
     */
    public Integer getUseCount() {
        return useCount;
    }

    /**
     * @param useCount the useCount to set
     */
    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    /**
     * @return the useRate
     */
    public String getUseRate() {
        return useRate;
    }

    /**
     * @param useRate the useRate to set
     */
    public void setUseRate(String useRate) {
        this.useRate = useRate;
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

    @Override
    public String toString() {
        return "CouponTemplateResultVO{" + "couponTemplateNo=" + couponTemplateNo + ", couponStatus=" + couponStatus + 
                ", couponTemplateId=" + couponTemplateId + ", value=" + value + ", maxCount=" + maxCount + ", minCost=" + minCost + 
                ", receiveCount=" + receiveCount + ", useCount=" + useCount + ", useRate=" + useRate + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }

}

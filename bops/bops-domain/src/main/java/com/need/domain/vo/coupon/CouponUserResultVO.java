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
 * @author 庆凯 2015-9-14 2015-9-14 15:44:55
 * @ClassName CouponUserResultVO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-14
 * @modify by reason:{方法名}:{原因}
 */
public class CouponUserResultVO implements Serializable {
    
    private static final long serialVersionUID = 625289194746400659L;
    
    private String couponTemplateId;
    private String couponTemplateNo;
    private String couponNo;
    private String tradeNo;
    private Date useTime;
    private Date startTime;
    private Date endTime;
    private int value;
    private String userId;

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
     * @return the couponNo
     */
    public String getCouponNo() {
        return couponNo;
    }

    /**
     * @param couponNo the couponNo to set
     */
    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    /**
     * @return the tradeNo
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * @param tradeNo the tradeNo to set
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * @return the useTime
     */
    public Date getUseTime() {
        return useTime;
    }

    /**
     * @param useTime the useTime to set
     */
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
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
        return "CouponUserResultVO{" + "couponTemplateId=" + couponTemplateId + ", couponTemplateNo=" + couponTemplateNo + ", couponNo=" + couponNo + ", tradeNo=" + tradeNo + ", useTime=" + useTime + ", startTime=" + startTime + ", endTime=" + endTime + ", value=" + value + ", userId=" + userId + '}';
    }
    
}

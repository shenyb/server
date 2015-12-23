/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.domain.vo.coupon;

import java.io.Serializable;

import com.need.domain.pub.Page;

/**
 * 
 * @author 庆凯 2015-9-14 2015-9-14 12:21:12
 * @ClassName CouponPageVO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-14
 * @modify by reason:{方法名}:{原因}
 */
public class CouponPageVO extends Page {
    
    private static final long serialVersionUID = 1328829545569351089L;
    
    private String couponTemplateNo;
    private String couponStatus;
    private String couponTemplateId;
    private String couponTemplateIds;
    
    protected Integer bopsUserId;
    
    

    public Integer getBopsUserId() {
		return bopsUserId;
	}

	public void setBopsUserId(Integer bopsUserId) {
		this.bopsUserId = bopsUserId;
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

    @Override
    public String toString() {
        return "CouponPageVO{" + "couponTemplateNo=" + couponTemplateNo + ", couponStatus=" + couponStatus + 
                ", couponTemplateId=" + couponTemplateId + ", couponTemplateIds=" + couponTemplateIds + '}';
    }

}

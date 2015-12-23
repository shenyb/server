/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.share.dao.jdbc.api.coupon.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 庆凯 2015-9-9 2015-9-9 11:56:58
 * @ClassName CouponMobilePO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-9
 * @modify by reason:{方法名}:{原因}
 */
public class CouponMobilePO implements Serializable {
    
    private static final long serialVersionUID = 4569629227229636565L;
    
    private Integer couponMobileId;
    private String couponTemplateId;
    private String shareUserId;
    private String mobile;
    private Date createTime;
    private String couponStatus;

    /**
     * @return the couponMobileId
     */
    public Integer getCouponMobileId() {
        return couponMobileId;
    }

    /**
     * @param couponMobileId the couponMobileId to set
     */
    public void setCouponMobileId(Integer couponMobileId) {
        this.couponMobileId = couponMobileId;
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
     * @return the shareUserId
     */
    public String getShareUserId() {
        return shareUserId;
    }

    /**
     * @param shareUserId the shareUserId to set
     */
    public void setShareUserId(String shareUserId) {
        this.shareUserId = shareUserId;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
    
    @Override
    public String toString() {
        return "CouponMobile [couponMobileId=" + couponMobileId + ", couponTemplateId=" + couponTemplateId + ", shareUserId=" + shareUserId + ", mobile="
   				+ mobile + ", createTime=" + createTime + ", couponStatus=" + couponStatus + "]";
    }

}

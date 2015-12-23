package com.need.marketing.dao.jdbc.api.coupon.po;

import java.io.Serializable;
import java.util.Date;

public class CouponReceiveInfoPO implements Serializable {
    
    private static final long serialVersionUID = 3770880888143624908L;
    
    private Integer id;
    private String nickName;
    private String iconUrl;
    private String shareUserId;
    private String couponTemplateId;
    private String mobile;
    private String tradeNo;
    private Date receiveTime;
    private Integer value;
    private String receiveDate;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return the iconUrl
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * @param iconUrl the iconUrl to set
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
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
     * @return the receiveTime
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * @param receiveTime the receiveTime to set
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * @return the receiveDate
     */
    public String getReceiveDate() {
        return receiveDate;
    }

    /**
     * @param receiveDate the receiveDate to set
     */
    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    @Override
    public String toString() {
        return "CouponReceiveInfoPO{" + "id=" + id + ", nickName=" + nickName + ", iconUrl=" + iconUrl + ", shareUserId=" + shareUserId + ", couponTemplateId=" + couponTemplateId + ", mobile=" + mobile + ", tradeNo=" + tradeNo + ", receiveTime=" + receiveTime + ", value=" + value + ", receiveDate=" + receiveDate + '}';
    }
}
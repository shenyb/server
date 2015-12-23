/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.marketing.web.controller.coupon.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 庆凯 2015-9-10 2015-9-10 14:49:13
 * @ClassName ReceiveCouponUserVO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-10
 * @modify by reason:{方法名}:{原因}
 */
public class ReceiveCouponUserVO implements Serializable {
    
    private static final long serialVersionUID = -1413847816805435387L;
    
    private String profilePicKey;
    private String mobile;
    private String nickName;
    private Date receiveTime;
    private String receiveDate;
    private int value;
    private String couponName;
    private String couponPicKey;
    private String disabledPicKey;

    /**
     * @return the profilePicKey
     */
    public String getProfilePicKey() {
        return profilePicKey;
    }

    /**
     * @param profilePicKey the profilePicKey to set
     */
    public void setProfilePicKey(String profilePicKey) {
        this.profilePicKey = profilePicKey;
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
     * @return the couponName
     */
    public String getCouponName() {
        return couponName;
    }

    /**
     * @param couponName the couponName to set
     */
    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    /**
     * @return the couponPicKey
     */
    public String getCouponPicKey() {
        return couponPicKey;
    }

    /**
     * @param couponPicKey the couponPicKey to set
     */
    public void setCouponPicKey(String couponPicKey) {
        this.couponPicKey = couponPicKey;
    }

    /**
     * @return the disabledPicKey
     */
    public String getDisabledPicKey() {
        return disabledPicKey;
    }

    /**
     * @param disabledPicKey the disabledPicKey to set
     */
    public void setDisabledPicKey(String disabledPicKey) {
        this.disabledPicKey = disabledPicKey;
    }
    
    @Override
    public String toString() {
        return "ReceiveCouponUser [mobile=" + mobile + ", profilePicKey=" + profilePicKey + ", nickName=" + nickName + ", receiveTime=" + receiveTime + 
                ", value=" + value + ", couponName=" + couponName + ", couponPicKey=" + couponPicKey + ", disabledPicKey=" + disabledPicKey + "]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.marketing.dao.jdbc.api.coupon.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 庆凯 2015-9-8 2015-9-8 12:10:20
 * @ClassName CouponUserPo
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-8
 * @modify by reason:{方法名}:{原因}
 */
public class CouponUserPO implements Serializable {
    
    private static final long serialVersionUID = 8897560260754857869L;
    
    private String couponNo;//用户领取的优惠券编号
    private String couponTemplateId;//优惠券模板id
    private String shareUserId;//分享优惠券用户id
    private String userId;//领取优惠券用户id
    private String couponName;//优惠券名称
    private String couponPicKey;//优惠券图片
    private String disabledPicKey;//失效优惠券图片
    private String description;//优惠券介绍
    private int value;//优惠券折扣金额
    private int minCost;//优惠券最低消费额
    private Date startTime;//优惠券有效开始时间
    private Date endTime;//优惠券有效结束时间
    private Date useTime;//优惠券使用时间
    private String couponStatus;//优惠券状态
    private String tradeNo;//使用优惠券的订单编号
    private String couponType;//优惠券类型
    private Date createTime;//优惠券领取时间

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
     * @return the minCost
     */
    public int getMinCost() {
        return minCost;
    }

    /**
     * @param minCost the minCost to set
     */
    public void setMinCost(int minCost) {
        this.minCost = minCost;
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
     * @return the couponType
     */
    public String getCouponType() {
        return couponType;
    }

    /**
     * @param couponType the couponType to set
     */
    public void setCouponType(String couponType) {
        this.couponType = couponType;
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
        return "CouponUser [couponNo=" + couponNo + ", couponTemplateId=" + couponTemplateId + ", shareUserId=" + shareUserId + 
                ", userId=" + userId + ", couponName=" + couponName + ", couponPicKey=" + couponPicKey + ", disabledPicKey=" + disabledPicKey + 
                ", value="+ value + ", description=" + description + ", minCost=" + minCost + ", startTime=" + startTime + ", endTime=" + endTime + 
                ", useTime=" + useTime + ", couponStatus=" + couponStatus + ", tradeNo=" + tradeNo + ", createTime=" + createTime + "]";
    }
}

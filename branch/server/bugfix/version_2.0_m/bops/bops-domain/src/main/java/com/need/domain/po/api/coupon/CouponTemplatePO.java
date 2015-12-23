package com.need.domain.po.api.coupon;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 庆凯 2015-9-9 2015-9-9 11:56:58
 * @ClassName CouponTemplatePO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-9
 * @modify by reason:{方法名}:{原因}
 */
public class CouponTemplatePO implements Serializable {
    
    private static final long serialVersionUID = 7261605727831768131L;
    
    private String couponTemplateId;
    private String couponTemplateNo;
    private String couponName;
    private String couponPicKey;
    private String disabledPicKey;
    private String description;
    private Integer value;
    private Integer minCost;
    private Date startTime;
    private Date endTime;
    private Integer maxCount;
    private Integer dailyCount;
    private Integer maxReceiveCount;
    private String goodsCategoryIds;
    private String channelIds;
    private String goodsId;
    private String couponType;
    private String couponStatus;
    private String couponRule;
    private String couponRecommend;
    private String couponShareTitle;

    public String getCouponTemplateId() {
        return couponTemplateId;
    }

    public void setCouponTemplateId(String couponTemplateId) {
        this.couponTemplateId = couponTemplateId;
    }

    public String getCouponTemplateNo() {
        return couponTemplateNo;
    }

    public void setCouponTemplateNo(String couponTemplateNo) {
        this.couponTemplateNo = couponTemplateNo;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponPicKey() {
        return couponPicKey;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getMinCost() {
        return minCost;
    }

    public void setMinCost(Integer minCost) {
        this.minCost = minCost;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public Integer getDailyCount() {
        return dailyCount;
    }

    public void setDailyCount(Integer dailyCount) {
        this.dailyCount = dailyCount;
    }

    /**
     * @return the maxReceiveCount
     */
    public Integer getMaxReceiveCount() {
        return maxReceiveCount;
    }

    /**
     * @param maxReceiveCount the maxReceiveCount to set
     */
    public void setMaxReceiveCount(Integer maxReceiveCount) {
        this.maxReceiveCount = maxReceiveCount;
    }

    public String getGoodsCategoryIds() {
        return goodsCategoryIds;
    }

    public void setGoodsCategoryIds(String goodsCategoryIds) {
        this.goodsCategoryIds = goodsCategoryIds;
    }

    public String getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(String channelIds) {
        this.channelIds = channelIds;
    }

    /**
     * @return the goodsId
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * @param goodsId the goodsId to set
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
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
     * @return the couponRule
     */
    public String getCouponRule() {
        return couponRule;
    }

    /**
     * @param couponRule the couponRule to set
     */
    public void setCouponRule(String couponRule) {
        this.couponRule = couponRule;
    }

    /**
     * @return the couponRecommend
     */
    public String getCouponRecommend() {
        return couponRecommend;
    }

    /**
     * @param couponRecommend the couponRecommend to set
     */
    public void setCouponRecommend(String couponRecommend) {
        this.couponRecommend = couponRecommend;
    }

    /**
     * @return the couponShareTitle
     */
    public String getCouponShareTitle() {
        return couponShareTitle;
    }

    /**
     * @param couponShareTitle the couponShareTitle to set
     */
    public void setCouponShareTitle(String couponShareTitle) {
        this.couponShareTitle = couponShareTitle;
    }

    @Override
    public String toString() {
        return "CouponTemplatePO{" + "couponTemplateId=" + couponTemplateId + ", couponTemplateNo=" + couponTemplateNo + ", couponName=" + couponName + 
                ", couponPicKey=" + couponPicKey + ", disabledPicKey=" + disabledPicKey + ", description=" + description + ", value=" + value + 
                ", minCost=" + minCost + ", startTime=" + startTime + ", endTime=" + endTime + ", maxCount=" + maxCount + ", dailyCount=" + dailyCount + 
                ", maxReceiveCount=" + maxReceiveCount + ", goodsCategoryIds=" + goodsCategoryIds + ", channelIds=" + channelIds + ", goodsId=" + goodsId + 
                ", couponType=" + couponType + ", couponStatus=" + couponStatus + ", couponRule=" + couponRule + ", couponRecommend=" + couponRecommend + 
                ", couponShareTitle" + couponShareTitle + '}';
    }

}
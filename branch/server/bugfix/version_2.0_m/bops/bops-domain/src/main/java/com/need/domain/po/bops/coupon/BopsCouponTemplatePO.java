package com.need.domain.po.bops.coupon;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author 庆凯 2015-9-9 2015-9-9 11:56:58
 * @ClassName BopsCouponTemplatePO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-9
 * @modify by reason:{方法名}:{原因}
 */

public class BopsCouponTemplatePO implements Serializable {
    
    private static final long serialVersionUID = 3675066730223617224L;
    
    protected String couponTemplateId;
    @NotNull(message = "优惠券模板编号不能为空")
    protected String couponTemplateNo;
    protected Integer bopsUserId;
    @NotNull(message = "优惠券名称不能为空")
    protected String couponName;
    @NotNull(message = "优惠券图片不能为空")
    protected String couponPicKey;
    @NotNull(message = "优惠券失效图片不能为空")
    protected String disabledPicKey;
    protected String description;
    protected String remark;
    @NotNull(message = "优惠券金额不能为空")
    protected Integer value;
    protected Integer minCost;
    protected Date startTime;
    protected Date endTime;
    protected Integer maxCount;
    protected Integer dailyCount;
    protected Integer maxReceiveCount;
    protected String goodsCategoryIds;
    protected String channelIds;
    protected String goodsId;
    protected String couponType;
    protected String couponStatus;
    protected Date createTime;
    protected String couponRule;
    protected String couponRecommend;
    protected String couponShareTitle;
    private String userRealName;

    public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
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
     * @return the bopsUserId
     */
    public Integer getBopsUserId() {
        return bopsUserId;
    }

    /**
     * @param bopsUserId the bopsUserId to set
     */
    public void setBopsUserId(Integer bopsUserId) {
        this.bopsUserId = bopsUserId;
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
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
     * @return the maxCount
     */
    public Integer getMaxCount() {
        return maxCount;
    }

    /**
     * @param maxCount the maxCount to set
     */
    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    /**
     * @return the dailyCount
     */
    public Integer getDailyCount() {
        return dailyCount;
    }

    /**
     * @param dailyCount the dailyCount to set
     */
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

    /**
     * @return the goodsCategoryIds
     */
    public String getGoodsCategoryIds() {
        return goodsCategoryIds;
    }

    /**
     * @param goodsCategoryIds the goodsCategoryIds to set
     */
    public void setGoodsCategoryIds(String goodsCategoryIds) {
        this.goodsCategoryIds = goodsCategoryIds;
    }

    /**
     * @return the channelIds
     */
    public String getChannelIds() {
        return channelIds;
    }

    /**
     * @param channelIds the channelIds to set
     */
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
        return "BopsCouponTemplatePO{" + "couponTemplateId=" + couponTemplateId + ", couponTemplateNo=" + couponTemplateNo + ", bopsUserId=" + bopsUserId + 
                ", couponName=" + couponName + ", couponPicKey=" + couponPicKey + ", disabledPicKey=" + disabledPicKey + ", description=" + description + 
                ", remark=" + remark + ", value=" + value + ", minCost=" + minCost + ", startTime=" + startTime + ", endTime=" + endTime + 
                ", maxCount=" + maxCount + ", dailyCount=" + dailyCount + ", maxReceiveCount=" + maxReceiveCount + ", goodsCategoryIds=" + goodsCategoryIds + 
                ", channelIds=" + channelIds + ", goodsId=" + goodsId + ", couponType=" + couponType + ", couponStatus=" + couponStatus + ", createTime=" + createTime + 
                ", couponRule=" + couponRule + ", couponRecommend=" + couponRecommend + ", couponShareTitle" + couponShareTitle  + '}';
    }
}
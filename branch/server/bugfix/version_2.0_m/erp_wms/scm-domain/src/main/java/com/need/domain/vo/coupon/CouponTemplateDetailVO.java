/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.domain.vo.coupon;

import java.io.Serializable;

import com.need.domain.po.bops.coupon.BopsCouponTemplatePO;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author 庆凯 2015-9-14 2015-9-14 21:31:03
 * @ClassName CouponTemplateDetailVO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-14
 * @modify by reason:{方法名}:{原因}
 */
public class CouponTemplateDetailVO extends BopsCouponTemplatePO implements Serializable {
    
    private static final long serialVersionUID = 7395349182045946442L;
    
    private String userName;
    private String userDept;
    private String rejectDescription;
    @NotNull(message = "优惠券开始不能为空")
    private String startTimeString;
    @NotNull(message = "优惠券结束不能为空")
    private String endTimeString;
    private String goodsSku;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userDept
     */
    public String getUserDept() {
        return userDept;
    }

    /**
     * @param userDept the userDept to set
     */
    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }

    /**
     * @return the rejectDescription
     */
    public String getRejectDescription() {
        return rejectDescription;
    }

    /**
     * @param rejectDescription the rejectDescription to set
     */
    public void setRejectDescription(String rejectDescription) {
        this.rejectDescription = rejectDescription;
    }

    /**
     * @return the startTimeString
     */
    public String getStartTimeString() {
        return startTimeString;
    }

    /**
     * @param startTimeString the startTimeString to set
     */
    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    /**
     * @return the endTimeString
     */
    public String getEndTimeString() {
        return endTimeString;
    }

    /**
     * @param endTimeString the endTimeString to set
     */
    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    /**
     * @return the goodsSku
     */
    public String getGoodsSku() {
        return goodsSku;
    }

    /**
     * @param goodsSku the goodsSku to set
     */
    public void setGoodsSku(String goodsSku) {
        this.goodsSku = goodsSku;
    }
    
    @Override
    public String toString() {
        return "CouponTemplateDetailVO [couponTemplateNo=" + couponTemplateNo + ", couponTemplateId=" + couponTemplateId + ", bopsUserId=" + bopsUserId + 
                ", description=" + description + ", remark=" + remark + ", couponName=" + couponName + ", couponPicKey=" + couponPicKey + ", disabledPicKey=" + disabledPicKey + 
                ", value=" + value + ", minCost=" + minCost + ", startTime=" + startTime + ", endTime=" + endTime + ", maxCount=" + maxCount + 
                ", dailyCount=" + dailyCount + ", maxReceiveCount=" + maxReceiveCount + ", couponStatus=" + couponStatus + ", goodsCategoryIds=" + goodsCategoryIds + 
                ", channelIds=" + channelIds + ", couponType=" + couponType + ", createTime=" + createTime + ", nickName=" + userName + ", userDept=" + userDept + 
                ", rejectDescription=" + rejectDescription + ", startTimeString=" + startTimeString + ", endTimeString=" + endTimeString + ", goodsSku=" + goodsSku + "]";
    }

}

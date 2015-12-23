/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.domain.vo.coupon;

import com.need.domain.po.bops.coupon.BopsCouponExchangeTemplatePO;
import com.need.domain.po.bops.coupon.BopsCouponTemplatePO;

import java.io.Serializable;
import java.util.List;

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
public class CouponExchangeTemplateVO extends BopsCouponExchangeTemplatePO {
    
    private static final long serialVersionUID = 7395349182045946442L;
    
    private String userName;
    private String userDept;
    private String rejectDescription;
    @NotNull(message = "优惠券兑换开始不能为空")
    private String startTimeString;
    @NotNull(message = "优惠券兑换结束不能为空")
    private String endTimeString;
    private List<BopsCouponTemplatePO> couponTemplateList;//优惠券模板集合

	public List<BopsCouponTemplatePO> getCouponTemplateList() {
		return couponTemplateList;
	}

	public void setCouponTemplateList(List<BopsCouponTemplatePO> couponTemplateList) {
		this.couponTemplateList = couponTemplateList;
	}

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

    @Override
    public String toString() {
        return "CouponExchangeVO{" + "couponExchangeId=" + couponExchangeTemplateId + ", couponExchangeCode=" + couponExchangeCode + 
                ", couponExchangeCount=" + couponExchangeCount + ", couponTemplateIds=" + couponTemplateIds + 
                ", couponExchangeType=" + couponExchangeType + ", startTime=" + startTime + 
                ", endTime=" + endTime + ", auditStatus=" + auditStatus + ", bopsUserId=" + bopsUserId + 
                ", createTime=" + createTime + ", userName" + userName + ", userDept" + userDept + 
                ", rejectDescription" + rejectDescription + ", endTimeString" + endTimeString + '}';
    }

}

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
 * <p></p>
 * @author liuhongyang 2015年10月30日 上午11:17:03
 * @ClassName CouponOpsListVO
 * @Description 优惠券领取
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: liuhongyang 2015年10月30日
 * @modify by reason:{方法名}:{原因}
 */
public class CouponOpsListVO implements Serializable {
    
    private static final long serialVersionUID = -1413847816805435387L;
    
    Integer couponId;
	String couponTemplateId;
	String couponName;
	String value;
	String minCost;
	Date startTime;
	Date endTime;
	String flag;
	String startMonth;
	String endMonth;
	String userId;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public String getCouponTemplateId() {
		return couponTemplateId;
	}
	public void setCouponTemplateId(String couponTemplateId) {
		this.couponTemplateId = couponTemplateId;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMinCost() {
		return minCost;
	}
	public void setMinCost(String minCost) {
		this.minCost = minCost;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	@Override
	public String toString() {
		return "CouponOpsListVO [couponId=" + couponId + ", couponTemplateId=" + couponTemplateId + ", couponName="
				+ couponName + ", value=" + value + ", minCost=" + minCost + ", startTime=" + startTime + ", endTime="
				+ endTime + ", flag=" + flag + "]";
	}
	
	
}

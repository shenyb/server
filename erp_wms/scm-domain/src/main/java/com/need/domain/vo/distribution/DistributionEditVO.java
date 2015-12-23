package com.need.domain.vo.distribution;

import java.util.Date;

public class DistributionEditVO {

	private String id;
	private String goodsCode;
	private String goodsName;
	private Date startTime;
	private Date endTime;
	private String distributionStatus;
	private String commission;
	private String startTimeString;
	private String endTimeString;
	private String creatorName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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
	public String getDistributionStatus() {
		return distributionStatus;
	}
	public void setDistributionStatus(String distributionStatus) {
		this.distributionStatus = distributionStatus;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getStartTimeString() {
		return startTimeString;
	}
	public void setStartTimeString(String startTimeString) {
		this.startTimeString = startTimeString;
	}
	public String getEndTimeString() {
		return endTimeString;
	}
	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	@Override
	public String toString() {
		return "DistributionEditVO [id=" + id + ", goodsCode=" + goodsCode + ", goodsName=" + goodsName + ", startTime="
				+ startTime + ", endTime=" + endTime + ", distributionStatus=" + distributionStatus + ", commission="
				+ commission + ", startTimeString=" + startTimeString + ", endTimeString=" + endTimeString
				+ ", creatorName=" + creatorName + "]";
	}
	
	
}

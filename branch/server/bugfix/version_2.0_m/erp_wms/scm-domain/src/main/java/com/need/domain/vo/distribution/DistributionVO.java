package com.need.domain.vo.distribution;

import java.io.Serializable;
import java.util.Date;

public class DistributionVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -4544860010677197842L;

	private String goodsCode;
	private String commission;
	private String startTimeString;
	private String endTimeString;
	private Date startTime;
	private Date endTime;
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "DistributionVO [goodsCode=" + goodsCode + ", commission=" + commission + ", startTimeString="
				+ startTimeString + ", endTimeString=" + endTimeString + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}
	
	
	
	
}

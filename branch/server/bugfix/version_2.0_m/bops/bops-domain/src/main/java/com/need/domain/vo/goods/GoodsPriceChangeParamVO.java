package com.need.domain.vo.goods;

import com.need.domain.pub.Page;

public class GoodsPriceChangeParamVO extends Page {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -3332512923057377016L;

	private String startTime;
	private String endTime;
	private String pricechangeId;
	private String userId;
	private String pricechangeStatus;//审核状态
	private String excuted;//启用状态
	private String pricechangeType;//改价类型
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPricechangeId() {
		return pricechangeId;
	}
	public void setPricechangeId(String pricechangeId) {
		this.pricechangeId = pricechangeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getExcuted() {
		return excuted;
	}
	public void setExcuted(String excuted) {
		this.excuted = excuted;
	}
	public String getPricechangeStatus() {
		return pricechangeStatus;
	}
	public void setPricechangeStatus(String pricechangeStatus) {
		this.pricechangeStatus = pricechangeStatus;
	}
	public String getPricechangeType() {
		return pricechangeType;
	}
	public void setPricechangeType(String pricechangeType) {
		this.pricechangeType = pricechangeType;
	}
	@Override
	public String toString() {
		return "GoodsPriceChangeParamVO [startTime=" + startTime + ", endTime=" + endTime + ", pricechangeId="
				+ pricechangeId + ", userId=" + userId + ", pricechangeStatus=" + pricechangeStatus + ", excuted="
				+ excuted + ", pricechangeType=" + pricechangeType + "]";
	}
	
}

package com.need.marketing.web.controller.cheap.vo;

import java.util.Date;

import com.need.marketing.dao.jdbc.cheap.po.CheapBasePO;

public class CheapVO extends CheapBasePO {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;
	private String openCheapStatus;
	private String traded;
	private Integer cheapOpenId;
	private Date completeTime;
	private String userId;
	
	private Date createTime;
	
	private String downTime;
	
	
	
	

	public String getDownTime() {
		return downTime;
	}
	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getCheapOpenId() {
		return cheapOpenId;
	}
	public void setCheapOpenId(Integer cheapOpenId) {
		this.cheapOpenId = cheapOpenId;
	}
	public String getOpenCheapStatus() {
		return openCheapStatus;
	}
	public void setOpenCheapStatus(String openCheapStatus) {
		this.openCheapStatus = openCheapStatus;
	}
	public String getTraded() {
		return traded;
	}
	public void setTraded(String traded) {
		this.traded = traded;
	}
	@Override
	public String toString() {
		return "CheapVO [openCheapStatus=" + openCheapStatus + ", traded="
				+ traded + ", cheapOpenId=" + cheapOpenId + ", completeTime="
				+ completeTime + ", userId=" + userId + ", createTime="
				+ createTime + ", downTime=" + downTime + "]";
	}
	
	
	
}

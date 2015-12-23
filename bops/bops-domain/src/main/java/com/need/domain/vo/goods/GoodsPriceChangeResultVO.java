package com.need.domain.vo.goods;

import java.io.Serializable;
import java.util.Date;

public class GoodsPriceChangeResultVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7529700972209503309L;

	private String pricechangeId;
	private String pricechangeStatus;
	private Integer goodsCount;
	private String pricechangeType;
	private String excuted;
	private Integer userId;
	private String userName;
	private String userDept;
	private Date userTime;//申请时间
	private String auditorId;
	private String auditorName;
	private String auditDept;
	private Date auditTime;
	private String mark;
	private Date startTime;
	private Date endTime;
	public String getPricechangeId() {
		return pricechangeId;
	}
	public void setPricechangeId(String pricechangeId) {
		this.pricechangeId = pricechangeId;
	}
	public String getPricechangeStatus() {
		return pricechangeStatus;
	}
	public void setPricechangeStatus(String pricechangeStatus) {
		this.pricechangeStatus = pricechangeStatus;
	}
	public Integer getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getPricechangeType() {
		return pricechangeType;
	}
	public void setPricechangeType(String pricechangeType) {
		this.pricechangeType = pricechangeType;
	}
	public String getExcuted() {
		return excuted;
	}
	public void setExcuted(String excuted) {
		this.excuted = excuted;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDept() {
		return userDept;
	}
	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
	public Date getUserTime() {
		return userTime;
	}
	public void setUserTime(Date userTime) {
		this.userTime = userTime;
	}
	public String getAuditorId() {
		return auditorId;
	}
	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}
	public String getAuditorName() {
		return auditorName;
	}
	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
	public String getAuditDept() {
		return auditDept;
	}
	public void setAuditDept(String auditDept) {
		this.auditDept = auditDept;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
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
		return "GoodsPriceChangeResultVO [pricechangeId=" + pricechangeId + ", pricechangeStatus=" + pricechangeStatus
				+ ", goodsCount=" + goodsCount + ", pricechangeType=" + pricechangeType + ", excuted=" + excuted
				+ ", userId=" + userId + ", userName=" + userName + ", userDept=" + userDept + ", userTime=" + userTime
				+ ", auditorId=" + auditorId + ", auditorName=" + auditorName + ", auditDept=" + auditDept
				+ ", auditTime=" + auditTime + ", mark=" + mark + ", startTime=" + startTime + ", endTime=" + endTime
				+ "]";
	}
	
}

package com.need.domain.vo.goods;

import java.io.Serializable;
import java.util.Date;

public class AuditGoodsVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 3453259132003958936L;

	private String goodsId;
	
	private String isPass;
	
	private String opposeReason;
	
	private String auditStatus;
	
	private Integer auditorId;
	
	private Date updateTime;
	
	public Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getIsPass() {
		return isPass;
	}

	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	public String getOpposeReason() {
		return opposeReason;
	}

	public void setOpposeReason(String opposeReason) {
		this.opposeReason = opposeReason;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "AuditGoodsVO [goodsId=" + goodsId + ", isPass=" + isPass + ", opposeReason=" + opposeReason
				+ ", auditStatus=" + auditStatus + ", auditorId=" + auditorId + ", updateTime=" + updateTime + "]";
	}

}

package com.need.marketing.web.controller.cheap.vo;
import java.util.Date;

import com.need.marketing.dao.jdbc.cheap.po.CheapOpenUserPO;

public class ShareCheapVO extends CheapOpenUserPO {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5917125978643755299L;
	
	 private String cheapStatus;
	 private Date createTime;
	 private Date completeTime;
	public String getCheapStatus() {
		return cheapStatus;
	}
	public void setCheapStatus(String cheapStatus) {
		this.cheapStatus = cheapStatus;
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
	 
	 
	
}

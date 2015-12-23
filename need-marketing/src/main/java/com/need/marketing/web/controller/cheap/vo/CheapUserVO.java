package com.need.marketing.web.controller.cheap.vo;

import java.util.Date;

import com.need.marketing.dao.jdbc.cheap.po.CheapOpenUserPO;

public class CheapUserVO extends CheapOpenUserPO {
	 /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 3950633359267578708L;
	private String cheapStatus;
	private Date completeTime;
	private String userId;
	
		public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
		public String getCheapStatus() {
			return cheapStatus;
		}
		public void setCheapStatus(String cheapStatus) {
			this.cheapStatus = cheapStatus;
		}

		public Date getCompleteTime() {
			return completeTime;
		}
		public void setCompleteTime(Date completeTime) {
			this.completeTime = completeTime;
		}
	    
	    
	
}

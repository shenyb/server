package com.need.common.domain.vo.need;

import com.need.common.domain.pub.Page;

import java.io.Serializable;


public class FeedsParamsVO extends Page implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 3611073589236633755L;
	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}

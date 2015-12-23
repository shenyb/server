package com.need.domain.vo.device;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class FeedBackParamVO extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 5717173066284105129L;
	
    private String mobile;
    private String startTime;
    private String endTime;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

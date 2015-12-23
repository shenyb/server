package com.need.domain.vo.safeCenter;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class NeedFeedParamsVO extends Page implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5003317134470409218L;
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	
	
	private String timeStart;
	private String timeEnd;
	
	private String  feedStatus;

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getFeedStatus() {
		return feedStatus;
	}

	public void setFeedStatus(String feedStatus) {
		this.feedStatus = feedStatus;
	}

	
	
	
}

package com.need.domain.vo.ops;

import com.need.domain.pub.Page;

public class PageTopicVO extends Page{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8707750513279864364L;
	
	private  String topicName;
	private  String topicStatus;
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTopicStatus() {
		return topicStatus;
	}
	public void setTopicStatus(String topicStatus) {
		this.topicStatus = topicStatus;
	}
	
}

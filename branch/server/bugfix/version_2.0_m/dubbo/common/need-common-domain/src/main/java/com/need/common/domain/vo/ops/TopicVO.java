package com.need.common.domain.vo.ops;

import java.io.Serializable;

public class TopicVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4258384620129620625L;
	
	private String topicUrl;
	private String topicName;
	public String getTopicUrl() {
		return topicUrl;
	}
	public void setTopicUrl(String topicUrl) {
		this.topicUrl = topicUrl;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
	
}

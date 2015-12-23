package com.need.common.domain.vo.ops;

import java.io.Serializable;

public class CategoryTopicVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 3860612172598670149L;
	
	private Integer topicId;
	private String topicUrl;
	private String topPicKey;
	private String topicName;
	
	
	
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public String getTopicUrl() {
		return topicUrl;
	}
	public void setTopicUrl(String topicUrl) {
		this.topicUrl = topicUrl;
	}
	public String getTopPicKey() {
		return topPicKey;
	}
	public void setTopPicKey(String topPicKey) {
		this.topPicKey = topPicKey;
	}
	
	
	
}

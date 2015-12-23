package com.need.common.domain.vo.ops;

import java.io.Serializable;

public class XinhuanTemplateVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6106945145504221476L;

	private String topicUrl;
	private int topicId;
	private String topPicKey;
	private String topicName;
	
	
	
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTopicUrl() {
		return topicUrl;
	}
	public void setTopicUrl(String topicUrl) {
		this.topicUrl = topicUrl;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public String getTopPicKey() {
		return topPicKey;
	}
	public void setTopPicKey(String topPicKey) {
		this.topPicKey = topPicKey;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

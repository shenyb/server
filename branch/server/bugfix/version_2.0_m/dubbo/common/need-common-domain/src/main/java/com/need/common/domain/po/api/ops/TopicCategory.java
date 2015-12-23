package com.need.common.domain.po.api.ops;

import java.io.Serializable;


public class TopicCategory implements Serializable {
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4243456572977576327L;
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private Integer relationId;
	private Integer topicId;
	private Integer topicCategoryId;
	private String topPicKey;
	private String listPicKey;
	private String topicPosition;
  
   
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public Integer getTopicCategoryId() {
		return topicCategoryId;
	}
	public void setTopicCategoryId(Integer topicCategoryId) {
		this.topicCategoryId = topicCategoryId;
	}
	public String getTopPicKey() {
		return topPicKey;
	}
	public void setTopPicKey(String topPicKey) {
		this.topPicKey = topPicKey;
	}
	public String getListPicKey() {
		return listPicKey;
	}
	public void setListPicKey(String listPicKey) {
		this.listPicKey = listPicKey;
	}
	public String getTopicPosition() {
		return topicPosition;
	}
	public void setTopicPosition(String topicPosition) {
		this.topicPosition = topicPosition;
	}

	
	
}

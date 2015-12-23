package com.need.domain.vo.ops;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class TopicCategroyVO  extends Page implements Serializable  {
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -6611596706355318152L;
	private Integer relationId;
	private Integer topicId;
	private Integer topicCategoryId;
	private String topPicKey;
	private String listPicKey;
	private String topicPosition;
	
	private String newPicKey;
	private String publisherId;
	
	
	
	
	public String getNewPicKey() {
		return newPicKey;
	}
	public void setNewPicKey(String newPicKey) {
		this.newPicKey = newPicKey;
	}
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
	public String getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
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
	@Override
	public String toString() {
		return "TopicCategroyVO [relationId=" + relationId + ", topicId=" + topicId + ", topicCategoryId="
				+ topicCategoryId + ", topPicKey=" + topPicKey + ", listPicKey=" + listPicKey + ", topicPosition="
				+ topicPosition + ", publisherId=" + publisherId + "]";
	}

	
	
}

package com.need.domain.po.api.ops;

import java.io.Serializable;
import java.util.Date;


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
	private String newPicKey;
	private Date effDate;
    private Date expDate;
    private Integer topicScore;
	public Date getEffDate() {
		return effDate;
	}
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public Integer getTopicScore() {
		return topicScore;
	}
	public void setTopicScore(Integer topicScore) {
		this.topicScore = topicScore;
	}
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
		return "TopicCategory [relationId=" + relationId + ", topicId=" + topicId + ", topicCategoryId="
				+ topicCategoryId + ", topPicKey=" + topPicKey + ", listPicKey=" + listPicKey + ", topicPosition="
				+ topicPosition + ", newPicKey=" + newPicKey + "]";
	}
	
}

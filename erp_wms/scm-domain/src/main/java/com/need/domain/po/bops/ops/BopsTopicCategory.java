package com.need.domain.po.bops.ops;

import java.io.Serializable;
import java.util.Date;


public class BopsTopicCategory implements Serializable {
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -587665445902365292L;
	private Integer relationId;
	private Integer topicId;
	private Integer topicCategoryId;
	private String topPicKey;
	private String listPicKey;
	private String topicPosition;
	private Date createTime;
	private Date updateTime;
	private String publisherId;
	private String newPicKey;
	private Date effDate;
    private Date expDate;
    
    private String effDateString;
    private String expDateString;
    private Integer topicScore;
    
	
	
	
	
	
	public Integer getTopicScore() {
		return topicScore;
	}
	public void setTopicScore(Integer topicScore) {
		this.topicScore = topicScore;
	}
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
	public String getEffDateString() {
		return effDateString;
	}
	public void setEffDateString(String effDateString) {
		this.effDateString = effDateString;
	}
	public String getExpDateString() {
		return expDateString;
	}
	public void setExpDateString(String expDateString) {
		this.expDateString = expDateString;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}
	@Override
	public String toString() {
		return "BopsTopicCategory [relationId=" + relationId + ", topicId=" + topicId + ", topicCategoryId="
				+ topicCategoryId + ", topPicKey=" + topPicKey + ", listPicKey=" + listPicKey + ", topicPosition="
				+ topicPosition + ", createTime=" + createTime + ", updateTime=" + updateTime + ", publisherId="
				+ publisherId + ", newPicKey=" + newPicKey + "]";
	}
	
}

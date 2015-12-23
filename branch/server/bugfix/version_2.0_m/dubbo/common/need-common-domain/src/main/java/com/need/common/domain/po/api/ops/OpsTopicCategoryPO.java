package com.need.common.domain.po.api.ops;

import java.io.Serializable;
import java.util.Date;

public class OpsTopicCategoryPO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6544520803631064108L;
	
	private int topicCategoryId;
	private String topicCategoryName;
	private Date createTime;
	private Date updateTime;
	private String topicCategoryPosition;
	
	public String getTopicCategoryPosition() {
		return topicCategoryPosition;
	}
	public void setTopicCategoryPosition(String topicCategoryPosition) {
		this.topicCategoryPosition = topicCategoryPosition;
	}
	public int getTopicCategoryId() {
		return topicCategoryId;
	}
	public void setTopicCategoryId(int topicCategoryId) {
		this.topicCategoryId = topicCategoryId;
	}
	public String getTopicCategoryName() {
		return topicCategoryName;
	}
	public void setTopicCategoryName(String topicCategoryName) {
		this.topicCategoryName = topicCategoryName;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}

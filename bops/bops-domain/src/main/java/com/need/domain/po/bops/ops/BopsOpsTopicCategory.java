package com.need.domain.po.bops.ops;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class BopsOpsTopicCategory implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6544520803631064108L;
	
	private int topicCategoryId;
	@NotBlank(message="请输入专题分类")
	@Length(min=1,max=20,message="专题分类不能为空，支持20个字以内")
	private String topicCategoryName;
	private int publisherId;
	private Date createTime;
	private Date updateTime;
	private String topicCategoryPosition;
	private String auditStatus;
	private int auditorId;
	private String reason;
	private int categoryScore;
	
	
	
	
	public int getCategoryScore() {
		return categoryScore;
	}
	public void setCategoryScore(int categoryScore) {
		this.categoryScore = categoryScore;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public int getAuditorId() {
		return auditorId;
	}
	public void setAuditorId(int auditorId) {
		this.auditorId = auditorId;
	}
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
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
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
	@Override
	public String toString() {
		return "BopsOpsTopicCategory [topicCategoryId=" + topicCategoryId + ", topicCategoryName=" + topicCategoryName
				+ ", publisherId=" + publisherId + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", topicCategoryPosition=" + topicCategoryPosition + "]";
	}
	
}

package com.need.domain.po.api.device;

import java.io.Serializable;

public class DeviceFeedBackPO  implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -228025044164570759L;

	private int commentId;
	private String userId;
	private String content;
	private String createTime;
	private String version;
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "DeviceFeedBackPO [commentId=" + commentId + ", userId=" + userId + ", content=" + content
				+ ", createTime=" + createTime + ", version=" + version + "]";
	}
	
}

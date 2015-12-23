package com.need.common.domain.vo.need;

import java.io.Serializable;

public class FeedCommentVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1917555552215613306L;
	
	
	private String userId;
	private String userName;
	private String comment;
	private long commentTime;
	private String userProfilePicKey;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public long getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(long commentTime) {
		this.commentTime = commentTime;
	}
	public String getUserProfilePicKey() {
		return userProfilePicKey;
	}
	public void setUserProfilePicKey(String userProfilePicKey) {
		this.userProfilePicKey = userProfilePicKey;
	}
	
}

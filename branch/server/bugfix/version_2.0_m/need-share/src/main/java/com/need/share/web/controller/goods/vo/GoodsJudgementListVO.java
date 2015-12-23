package com.need.share.web.controller.goods.vo;

import java.io.Serializable;
import java.util.Date;

public class GoodsJudgementListVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1041683736631599468L;

	private String userId;
	
	private String userName;
	
	
	private String profilePicKey;
	
	private Date dateJudgementTime;
	
	private Long judgementTime;

	private String content;
	
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

	public String getProfilePicKey() {
		return profilePicKey;
	}

	public void setProfilePicKey(String profilePicKey) {
		this.profilePicKey = profilePicKey;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateJudgementTime() {
		return dateJudgementTime;
	}

	public void setDateJudgementTime(Date dateJudgementTime) {
		this.dateJudgementTime = dateJudgementTime;
	}

	public Long getJudgementTime() {
		return judgementTime;
	}

	public void setJudgementTime(Long judgementTime) {
		this.judgementTime = judgementTime;
	}

	@Override
	public String toString() {
		return "GoodsJudgementListResultVO [userId=" + userId + ", userName=" + userName + ", profilePicKey="
				+ profilePicKey + ", dateJudgementTime=" + dateJudgementTime + ", judgementTime=" + judgementTime
				+ ", content=" + content + "]";
	}
	

}

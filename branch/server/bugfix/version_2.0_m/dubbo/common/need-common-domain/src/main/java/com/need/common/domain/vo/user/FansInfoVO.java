package com.need.common.domain.vo.user;

import java.io.Serializable;

public class FansInfoVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6350670176522672283L;
	private String userId;
	private String nickName;
	private String profilePicKey;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getProfilePicKey() {
		return profilePicKey;
	}
	public void setProfilePicKey(String profilePicKey) {
		this.profilePicKey = profilePicKey;
	}
	@Override
	public String toString() {
		return "UserInfoVO [userId=" + userId + ", nickName=" + nickName + ", profilePicKey=" + profilePicKey + "]";
	}
	
	
}

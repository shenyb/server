package com.need.share.web.controller.kol.vo;

import java.io.Serializable;

public class UserInfoVO implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7238080260862021880L;

	private String userId;

    private String nickName;

    private String userType;
    private String kolCategories;

    private String userStatus;
    
    private String kolBrief;//简介
    
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getKolCategories() {
		return kolCategories;
	}
	public void setKolCategories(String kolCategories) {
		this.kolCategories = kolCategories;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getKolBrief() {
		return kolBrief;
	}
	public void setKolBrief(String kolBrief) {
		this.kolBrief = kolBrief;
	}
	public String getProfilePicKey() {
		return profilePicKey;
	}
	public void setProfilePicKey(String profilePicKey) {
		this.profilePicKey = profilePicKey;
	}
	@Override
	public String toString() {
		return "UserInfoVO [userId=" + userId + ", nickName=" + nickName + ", userType=" + userType + ", kolCategories="
				+ kolCategories + ", userStatus=" + userStatus + ", kolBrief=" + kolBrief + ", profilePicKey="
				+ profilePicKey + "]";
	}
    
    
	
    
    
}

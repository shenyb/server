package com.need.common.domain.vo.user;

import java.io.Serializable;

public class ContactsBookUserInfoVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 7637175824364458329L;
	private String userId;
	private String mobile;
	private String nickName;
	private String name;
	private String isFollow;
	private String profilePicKey;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsFollow() {
		return isFollow;
	}
	public void setIsFollow(String isFollow) {
		this.isFollow = isFollow;
	}
	public String getProfilePicKey() {
		return profilePicKey;
	}
	public void setProfilePicKey(String profilePicKey) {
		this.profilePicKey = profilePicKey;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ContactsBookUserInfoVO [userId=" + userId + ", mobile=" + mobile + ", nickName=" + nickName + ", name="
				+ name + ", isFollow=" + isFollow + ", profilePicKey=" + profilePicKey + "]";
	}
	

}

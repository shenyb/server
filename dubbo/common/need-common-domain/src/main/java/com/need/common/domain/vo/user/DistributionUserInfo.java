package com.need.common.domain.vo.user;

import java.io.Serializable;

public class DistributionUserInfo implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 519178444105407991L;
	private String nickName;
	private String profilePicKey;
	private String sut;
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
	public String getSut() {
		return sut;
	}
	public void setSut(String sut) {
		this.sut = sut;
	}
	@Override
	public String toString() {
		return "DistributionUserInfo [nickName=" + nickName + ", profilePicKey=" + profilePicKey + ", sut=" + sut + "]";
	}
	
	
	
}

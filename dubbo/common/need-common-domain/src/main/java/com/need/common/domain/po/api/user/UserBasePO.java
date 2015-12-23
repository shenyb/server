package com.need.common.domain.po.api.user;

import java.io.Serializable;
import java.util.Date;

public class UserBasePO implements Serializable{
	
	private static final long serialVersionUID = 2696096896884110154L;

	private String userId;

    private String mobile;

    private String nickName;

    private String loginPwd;

    private String userType;

    private String userStatus;

    private String profilePicKey;

    private Date createTime;

    private Date updateTime;
    
    private String userSut;
    
    

    public String getUserSut() {
		return userSut;
	}

	public void setUserSut(String userSut) {
		this.userSut = userSut;
	}

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

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getProfilePicKey() {
        return profilePicKey;
    }

    public void setProfilePicKey(String profilePicKey) {
        this.profilePicKey = profilePicKey;
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

	@Override
	public String toString() {
		return "UserBase [userId=" + userId + ", mobile=" + mobile + ", nickName=" + nickName + ", loginPwd=" + loginPwd
				+ ", userType=" + userType + ", userStatus=" + userStatus + ", profilePicKey=" + profilePicKey
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
    
        
}
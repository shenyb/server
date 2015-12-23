package com.need.domain.vo.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.need.domain.po.bops.role.BopsRole;

public class BopsUserResultVO implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7002292417184136361L;

private Integer userId;
	
	private String userStatus;
	
	private String userName;
	
	private String userEmail;
	
	private String userPwd;
	
	private String userDept;
	
	private String roleNames;
	
	private String roleIds;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String userToken;
	
	private String userMobile;
	
	private String userRealName;

    private List<BopsRole> bopsRoles;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
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

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public List<BopsRole> getBopsRoles() {
		return bopsRoles;
	}

	public void setBopsRoles(List<BopsRole> bopsRoles) {
		this.bopsRoles = bopsRoles;
	}

	@Override
	public String toString() {
		return "BopsUserResultVO [userId=" + userId + ", userStatus=" + userStatus + ", userName=" + userName
				+ ", userEmail=" + userEmail + ", userPwd=" + userPwd + ", userDept=" + userDept + ", roleNames="
				+ roleNames + ", roleIds=" + roleIds + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", userToken=" + userToken + ", userMobile=" + userMobile + ", userRealName=" + userRealName
				+ ", bopsRoles=" + bopsRoles + "]";
	}
    
  
	

    
    
    
}

package com.need.domain.po.bops.user;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class BopsUser implements Serializable {
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5926187651945487365L;
	
	private Integer userId;
	
	private String userStatus;
	
	@NotNull(message="用户名不能为空")
	private String userName;
	
	@NotNull(message="邮箱不能为空")
	@Email(message="邮箱格式不正确")
	private String userEmail;
	
	//@NotNull(message="用户密码不能为空")
	private String userPwd;
	
	@NotNull(message="用户部门不能为空")
	private String userDept;
	
	private String roleNames;
	
	@NotNull(message="用户角色不能为空")
	@Length(min=3,message="用户角色不能为空")
	private String roleIds;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String userToken;
	
	@NotNull(message="用户手机号不能为空")
	@Length(min=6,message="用户手机号格式不正确")
	private String userMobile;
	
	@NotNull(message="用户真实姓名不能为空")
	private String userRealName;
	
	public String getUserToken() {
		return userToken;
	}
	
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
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

	@Override
	public String toString() {
		return "BopsUser [userId=" + userId + ", userStatus=" + userStatus + ", userName=" + userName + ", userEmail="
				+ userEmail + ", userPwd=" + userPwd + ", userDept=" + userDept + ", roleNames=" + roleNames
				+ ", roleIds=" + roleIds + ", createTime=" + createTime + ", updateTime=" + updateTime + ", userToken="
				+ userToken + ", userMobile=" + userMobile + ", userRealName=" + userRealName + "]";
	}

	
	
}

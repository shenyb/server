package com.need.domain.vo.role;

import java.util.Date;
import java.util.List;

import com.need.domain.po.bops.auth.BopsAuth;

public class RoleResultVO {
	
	private Integer roleId;

    private String roleName;

    private String roleStatus;

    private String roleDesc;

    private String roleDept;

    private String authIds;
    
    private Date createTime;

    private Date updateTime;
    
    
    private List<BopsAuth> authList;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleDept() {
		return roleDept;
	}

	public void setRoleDept(String roleDept) {
		this.roleDept = roleDept;
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

	public List<BopsAuth> getAuthList() {
		return authList;
	}

	public void setAuthList(List<BopsAuth> authList) {
		this.authList = authList;
	}

	public String getAuthIds() {
		return authIds;
	}

	public void setAuthIds(String authIds) {
		this.authIds = authIds;
	}

}

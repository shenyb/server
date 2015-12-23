package com.need.domain.vo.role;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class RoleParamVO implements Serializable {

	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8270765374019798699L;

	private Integer roleId;

    private String roleName;

    private String roleStatus;

    private String roleDesc;

    private String roleDept;

    private String[] authIds;

    public String[] getAuthIds() {
		return authIds;
	}

	public void setAuthIds(String[] authIds) {
		this.authIds = authIds;
	}

	private Date createTime;

    private Date updateTime;

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

	@Override
	public String toString() {
		return "RoleParamVO [roleId=" + roleId + ", roleName=" + roleName + ", roleStatus=" + roleStatus + ", roleDesc="
				+ roleDesc + ", roleDept=" + roleDept + ", authIds=" + Arrays.toString(authIds) + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}

}

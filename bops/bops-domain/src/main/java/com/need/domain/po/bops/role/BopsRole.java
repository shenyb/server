package com.need.domain.po.bops.role;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class BopsRole implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1159971512871457563L;

	private Integer roleId;

	@NotNull(message="角色名不能为空")
    private String roleName;

    private String roleStatus;

    private String roleDesc;

    @NotNull(message="角色部门能为空")
    private String roleDept;

    @NotNull(message="角色权限不能为空")
    @Length(min=3,message="角色权限不能为空")
    private String authIds;

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

    public String getAuthIds() {
        return authIds;
    }

    public void setAuthIds(String authIds) {
        this.authIds = authIds;
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
		return "BopsRole [roleId=" + roleId + ", roleName=" + roleName + ", roleStatus=" + roleStatus + ", roleDesc="
				+ roleDesc + ", roleDept=" + roleDept + ", authIds=" + authIds + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
    
    
    
}
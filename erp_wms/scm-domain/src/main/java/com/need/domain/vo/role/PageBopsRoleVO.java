package com.need.domain.vo.role;

import com.need.domain.pub.Page;

public class PageBopsRoleVO extends Page{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2129260189864866619L;
	
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "PageBopsRoleVO [roleName=" + roleName + "]";
	}

}

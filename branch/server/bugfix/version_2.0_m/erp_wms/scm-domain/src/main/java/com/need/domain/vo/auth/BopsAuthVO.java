package com.need.domain.vo.auth;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class BopsAuthVO extends Page  implements Serializable{
   
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7823951964902485430L;

	private String authName;

    private String authStatus;
    
    private String moduleName;
    
    
	public String getModuleName() {
		return moduleName;
	}

	
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	
	

	@Override
	public String toString() {
		return "BopsAuthVO [authName=" + authName + ", authStatus=" + authStatus + "]";
	}
	
	


    
 
	
}
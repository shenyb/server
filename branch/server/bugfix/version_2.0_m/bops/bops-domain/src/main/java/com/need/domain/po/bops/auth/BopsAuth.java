package com.need.domain.po.bops.auth;

import java.io.Serializable;
import java.util.Date;

public class BopsAuth implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2904649835194161088L;

	private Integer authId;

    private String authName;

    private String authStatus;

    private String moduleName;

    private String authScope;

    private String authDesc;

    private Date createTime;
    
    private Integer authCode;
    
    

    public Integer getAuthCode() {
		return authCode;
	}

	public void setAuthCode(Integer authCode) {
		this.authCode = authCode;
	}

	public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAuthScope() {
        return authScope;
    }

    public void setAuthScope(String authScope) {
        this.authScope = authScope;
    }

    public String getAuthDesc() {
        return authDesc;
    }

    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "BopsAuth [authId=" + authId + ", authName=" + authName + ", authStatus=" + authStatus + ", moduleName="
				+ moduleName + ", authScope=" + authScope + ", authDesc=" + authDesc + ", createTime=" + createTime
				+ "]";
	}
    
}
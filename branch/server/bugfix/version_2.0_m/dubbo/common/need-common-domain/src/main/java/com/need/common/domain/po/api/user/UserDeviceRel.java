package com.need.common.domain.po.api.user;

import java.io.Serializable;
import java.util.Date;

public class UserDeviceRel implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6950450010964603620L;

	private Long relId;

    private String userId;

    private String deviceId;

    private String appVersion;

    private Date createTime;

    private String deviceChannle;
    
    private Date updateTime;
    
    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
	public String getDeviceChannle() {
		return deviceChannle;
	}

	public void setDeviceChannle(String deviceChannle) {
		this.deviceChannle = deviceChannle;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "UserDeviceRel [relId=" + relId + ", userId=" + userId
				+ ", deviceId=" + deviceId + ", appVersion=" + appVersion
				+ ", createTime=" + createTime + ", deviceChannle="
				+ deviceChannle + ", updateTime=" + updateTime + "]";
	}
        
    
}
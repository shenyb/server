package com.need.common.domain.po.api.device;

import java.io.Serializable;
import java.util.Date;

public class DeviceBasePO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2006892203587439581L;

	private String deviceId;

    private String osVersion;

    private String mac;

    private String deviceChannel;

    private String mobileVersion;

    private String mobile;

    private String resolution;

    private String memo;

    private Date createTime;
    
    private String channelId;
    
    

    public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDeviceChannel() {
        return deviceChannel;
    }

    public void setDeviceChannel(String deviceChannel) {
        this.deviceChannel = deviceChannel;
    }

    public String getMobileVersion() {
        return mobileVersion;
    }

    public void setMobileVersion(String mobileVersion) {
        this.mobileVersion = mobileVersion;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "DeviceBasePO [deviceId=" + deviceId + ", osVersion=" + osVersion
				+ ", mac=" + mac + ", deviceChannel=" + deviceChannel
				+ ", mobileVersion=" + mobileVersion + ", mobile=" + mobile
				+ ", resolution=" + resolution + ", memo=" + memo
				+ ", createTime=" + createTime + ", channelId=" + channelId
				+ "]";
	}   
    
}
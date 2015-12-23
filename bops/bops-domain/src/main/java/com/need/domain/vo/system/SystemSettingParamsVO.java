package com.need.domain.vo.system;

import java.io.Serializable;
import java.util.Date;

import com.need.domain.pub.Page;

public class SystemSettingParamsVO extends Page implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 2841984050636697438L;
	
	private String systemSettingName;
    private String systemSettingValue;
    private Date createTime;
    private Date updateTime;
	public String getSystemSettingName() {
		return systemSettingName;
	}
	public void setSystemSettingName(String systemSettingName) {
		this.systemSettingName = systemSettingName;
	}
	public String getSystemSettingValue() {
		return systemSettingValue;
	}
	public void setSystemSettingValue(String systemSettingValue) {
		this.systemSettingValue = systemSettingValue;
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
		return "SystemSettingParamsVO [systemSettingName=" + systemSettingName + ", systemSettingValue="
				+ systemSettingValue + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
    
    
	
}

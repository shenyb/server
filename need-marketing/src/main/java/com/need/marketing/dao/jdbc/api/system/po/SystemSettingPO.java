package com.need.marketing.dao.jdbc.api.system.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author YAN 2015-12-12 11:51:00
 * @ClassName SystemSettingPO
 * @Description TODO
 * @version V2.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: YAN 2015-12-12
 * @modify by reason:{方法名}:{原因}
 */
public class SystemSettingPO implements Serializable {

    private static final long serialVersionUID = -7882145696196459081L;
    
    private String systemSettingName;
    private String systemSettingValue;
    private Date createTime;
    private Date updateTime;

    /**
     * @return the systemSettingName
     */
    public String getSystemSettingName() {
        return systemSettingName;
    }

    /**
     * @param systemSettingName the systemSettingName to set
     */
    public void setSystemSettingName(String systemSettingName) {
        this.systemSettingName = systemSettingName;
    }

    /**
     * @return the systemSettingValue
     */
    public String getSystemSettingValue() {
        return systemSettingValue;
    }

    /**
     * @param systemSettingValue the systemSettingValue to set
     */
    public void setSystemSettingValue(String systemSettingValue) {
        this.systemSettingValue = systemSettingValue;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SystemSettingPO{" + ", systemSettingName=" + systemSettingName + 
                ", systemSettingValue=" + systemSettingValue + ", createTime=" + createTime + ", updateTime=" + updateTime + '}';
    }

}
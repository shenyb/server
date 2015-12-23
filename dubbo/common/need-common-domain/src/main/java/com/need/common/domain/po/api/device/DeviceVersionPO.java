package com.need.common.domain.po.api.device;

import java.io.Serializable;
import java.util.Date;

public class DeviceVersionPO  implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -8695002287134552280L;

	private Integer id;

    private String osVersion;

    private String versionNo;

    private String downloadUrl;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "DeviceVersion [id=" + id + ", osVersion=" + osVersion + ", versionNo=" + versionNo + ", downloadUrl="
				+ downloadUrl + ", createTime=" + createTime + "]";
	}
    
    
}
package com.need.domain.po.api.xinhuan;

import java.io.Serializable;
import java.util.Date;

public class OpsMain implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -3116719602082379567L;

	private String opsId;

    private Date createTime;

    private Date updateTime;

    private String opsName;

    private String opsPic;

    private String opsType;

    public String getOpsId() {
        return opsId;
    }

    public void setOpsId(String opsId) {
        this.opsId = opsId;
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

    public String getOpsName() {
        return opsName;
    }

    public void setOpsName(String opsName) {
        this.opsName = opsName;
    }

    public String getOpsPic() {
        return opsPic;
    }

    public void setOpsPic(String opsPic) {
        this.opsPic = opsPic;
    }

    public String getOpsType() {
        return opsType;
    }

    public void setOpsType(String opsType) {
        this.opsType = opsType;
    }

	@Override
	public String toString() {
		return "OpsXinhuan [opsId=" + opsId + ", createTime=" + createTime + ", updateTime=" + updateTime + ", opsName="
				+ opsName + ", opsPic=" + opsPic + ", opsType=" + opsType + "]";
	}
    
}
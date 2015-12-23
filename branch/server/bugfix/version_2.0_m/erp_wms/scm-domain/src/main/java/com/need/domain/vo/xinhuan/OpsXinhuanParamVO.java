package com.need.domain.vo.xinhuan;

import java.io.Serializable;
import java.util.Date;

import com.need.domain.pub.Page;

public class OpsXinhuanParamVO extends Page  implements Serializable{

	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 3478866971028928971L;

	private String opsId;
	
	private String opsName;

    private String opsPic;

    private String opsType;
    
    private Date createTime;

	public String getOpsId() {
		return opsId;
	}

	public void setOpsId(String opsId) {
		this.opsId = opsId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "OpsXinhuanParamVO [opsId=" + opsId + ", opsName=" + opsName + ", opsPic=" + opsPic + ", opsType="
				+ opsType + "]";
	}

}

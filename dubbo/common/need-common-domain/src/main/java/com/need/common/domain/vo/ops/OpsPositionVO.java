package com.need.common.domain.vo.ops;

import java.io.Serializable;

public class OpsPositionVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5551928561477205823L;
	
	private String opsType;
	private String opsId;
	private String opsPickey;
	
	private String opsName;
	
	
	
	public String getOpsName() {
		return opsName;
	}
	public void setOpsName(String opsName) {
		this.opsName = opsName;
	}
	public String getOpsType() {
		return opsType;
	}
	public void setOpsType(String opsType) {
		this.opsType = opsType;
	}
	public String getOpsId() {
		return opsId;
	}
	public void setOpsId(String opsId) {
		this.opsId = opsId;
	}
	public String getOpsPickey() {
		return opsPickey;
	}
	public void setOpsPickey(String opsPickey) {
		this.opsPickey = opsPickey;
	}
	@Override
	public String toString() {
		return "OpsVO [opsType=" + opsType + ", opsId=" + opsId + ", opsPickey=" + opsPickey + "]";
	}
	
}

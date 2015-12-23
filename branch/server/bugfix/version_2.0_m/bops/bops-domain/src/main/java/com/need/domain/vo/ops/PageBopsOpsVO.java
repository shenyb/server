package com.need.domain.vo.ops;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class PageBopsOpsVO extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -8398199155268948423L;
	
	private String opsId;
	private String typeId;
	private String auditStatus;
	private String opsNumber;
	
	

	public String getOpsNumber() {
		return opsNumber;
	}

	public void setOpsNumber(String opsNumber) {
		this.opsNumber = opsNumber;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getOpsId() {
		return opsId;
	}

	public void setOpsId(String opsId) {
		this.opsId = opsId;
	}

	@Override
	public String toString() {
		return "PageBopsOpsVO [opsId=" + opsId + ", typeId=" + typeId + ", auditStatus=" + auditStatus + "]";
	}
	
	
}

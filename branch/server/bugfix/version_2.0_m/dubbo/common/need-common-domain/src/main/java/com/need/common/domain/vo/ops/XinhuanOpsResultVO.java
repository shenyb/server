package com.need.common.domain.vo.ops;

import java.io.Serializable;

public class XinhuanOpsResultVO implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 3960331772667787444L;

	private String opsName;
	
	private String opsPicKey;
	
	private String opsId;
	
	private String opsType;
	
	

	public String getOpsType() {
		return opsType;
	}

	public void setOpsType(String opsType) {
		this.opsType = opsType;
	}

	public String getOpsName() {
		return opsName;
	}

	public void setOpsName(String opsName) {
		this.opsName = opsName;
	}

	public String getOpsPicKey() {
		return opsPicKey;
	}

	public void setOpsPicKey(String opsPicKey) {
		this.opsPicKey = opsPicKey;
	}

	public String getOpsId() {
		return opsId;
	}

	public void setOpsId(String opsId) {
		this.opsId = opsId;
	}

	@Override
	public String toString() {
		return "XinhuanOpsResultVO [opsName=" + opsName + ", opsPicKey=" + opsPicKey + ", opsId=" + opsId + "]";
	}

}

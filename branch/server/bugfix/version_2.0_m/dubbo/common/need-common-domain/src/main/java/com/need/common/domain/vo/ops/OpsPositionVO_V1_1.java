package com.need.common.domain.vo.ops;

import java.io.Serializable;

public class OpsPositionVO_V1_1 implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8542006670962064895L;
	
	private String opsType;
	private String opsId;
	private String opsPicKey;
	
	private String opsName;
	
	private String opsWebUrl;
	
	private String topicName;
	
	
	
	

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getOpsWebUrl() {
		return opsWebUrl;
	}

	public void setOpsWebUrl(String opsWebUrl) {
		this.opsWebUrl = opsWebUrl;
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

	public String getOpsPicKey() {
		return opsPicKey;
	}

	public void setOpsPicKey(String opsPicKey) {
		this.opsPicKey = opsPicKey;
	}

	public String getOpsName() {
		return opsName;
	}

	public void setOpsName(String opsName) {
		this.opsName = opsName;
	}
	
	

}

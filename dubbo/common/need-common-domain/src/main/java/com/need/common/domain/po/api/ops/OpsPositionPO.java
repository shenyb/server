package com.need.common.domain.po.api.ops;

import java.io.Serializable;

public class OpsPositionPO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1898465166947271524L;

	private String opsId;

    private String opsType;

    private String opsPicKey;

    private String opsNumber;

    private String typeId;
    
    private String opsPosition;
    
    private String opsName;
    

    public String getOpsName() {
		return opsName;
	}

	public void setOpsName(String opsName) {
		this.opsName = opsName;
	}

	public String getOpsPosition() {
		return opsPosition;
	}

	public void setOpsPosition(String opsPosition) {
		this.opsPosition = opsPosition;
	}

	public String getOpsId() {
        return opsId;
    }

    public void setOpsId(String opsId) {
        this.opsId = opsId;
    }

    public String getOpsType() {
        return opsType;
    }

    public void setOpsType(String opsType) {
        this.opsType = opsType;
    }

    public String getOpsPicKey() {
        return opsPicKey;
    }

    public void setOpsPicKey(String opsPicKey) {
        this.opsPicKey = opsPicKey;
    }

    public String getOpsNumber() {
        return opsNumber;
    }

    public void setOpsNumber(String opsNumber) {
        this.opsNumber = opsNumber;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
package com.need.domain.po.api.ops;

import java.io.Serializable;
import java.util.Date;

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
    
    private Integer categoryId;
    private Integer topicScore;
    
    private Date effDate;
    private Date expDate;
    
    
    
    
    


	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Integer getTopicScore() {
		return topicScore;
	}

	public void setTopicScore(Integer topicScore) {
		this.topicScore = topicScore;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	@Override
	public String toString() {
		return "OpsPositionPO [opsId=" + opsId + ", opsType=" + opsType + ", opsPicKey=" + opsPicKey + ", opsNumber="
				+ opsNumber + ", typeId=" + typeId + ", opsPosition=" + opsPosition + "]";
	}
    
}
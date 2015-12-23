package com.need.domain.po.bops.ops;

import java.io.Serializable;
import java.util.Date;

public class BopsOps implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4499921532502648488L;

	private String opsId;

    private String opsType;

    private String opsChannel;

    private String auditStatus;

    private Date createTime;

    private Integer publisherId;

    private Integer auditorId;
    
    private String opsPicKey;
    
    private String memo;
    
    private String opsNumber;
    
    private String typeId;
    
    private String opsPosition;
    
    private Integer categoryId;
    
    private Integer topicScore;
    
    private Date effDate;
    private Date expDate;
    
    private String effDateString;
    private String expDateString;
    
    
    
    
    
    
    


	public String getEffDateString() {
		return effDateString;
	}

	public void setEffDateString(String effDateString) {
		this.effDateString = effDateString;
	}

	public String getExpDateString() {
		return expDateString;
	}

	public void setExpDateString(String expDateString) {
		this.expDateString = expDateString;
	}

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

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getOpsPicKey() {
		return opsPicKey;
	}

	public void setOpsPicKey(String opsPicKey) {
		this.opsPicKey = opsPicKey;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

    public String getOpsChannel() {
        return opsChannel;
    }

    public void setOpsChannel(String opsChannel) {
        this.opsChannel = opsChannel;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

	public String getOpsNumber() {
		return opsNumber;
	}

	public void setOpsNumber(String opsNumber) {
		this.opsNumber = opsNumber;
	}

	@Override
	public String toString() {
		return "BopsOps [opsId=" + opsId + ", opsType=" + opsType + ", opsChannel=" + opsChannel + ", auditStatus="
				+ auditStatus + ", createTime=" + createTime + ", publisherId=" + publisherId + ", auditorId="
				+ auditorId + ", opsPicKey=" + opsPicKey + ", memo=" + memo + ", opsNumber=" + opsNumber + ", typeId="
				+ typeId + "]";
	}
}
package com.need.task.dao.jdbc.bops.goods.po;

import java.util.Date;

public class BopsPriceChangePO {
    private Integer pricechangeId;

    private Date startTime;

    private Date endTime;

    private String excuted;

    private String pricechangeStatus;

    private Integer bopsUserId;

    private Integer auditUserId;

    private Date createTime;
    
    private String pricechangeType;
    
    public Integer getPricechangeId() {
        return pricechangeId;
    }

    public void setPricechangeId(Integer pricechangeId) {
        this.pricechangeId = pricechangeId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getExcuted() {
        return excuted;
    }

    public void setExcuted(String excuted) {
        this.excuted = excuted;
    }

    public String getPricechangeStatus() {
        return pricechangeStatus;
    }

    public void setPricechangeStatus(String pricechangeStatus) {
        this.pricechangeStatus = pricechangeStatus;
    }

    public Integer getBopsUserId() {
        return bopsUserId;
    }

    public void setBopsUserId(Integer bopsUserId) {
        this.bopsUserId = bopsUserId;
    }

    public Integer getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPricechangeType() {
		return pricechangeType;
	}

	public void setPricechangeType(String pricechangeType) {
		this.pricechangeType = pricechangeType;
	}
    
    
}
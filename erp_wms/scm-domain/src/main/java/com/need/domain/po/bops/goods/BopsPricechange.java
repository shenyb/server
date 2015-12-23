package com.need.domain.po.bops.goods;

import java.io.Serializable;
import java.util.Date;

public class BopsPricechange implements Serializable{
	
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7871524303705340414L;

	private Integer pricechangeId;

    private Date startTime;

    private Date endTime;

    private String excuted;

    private String pricechangeStatus;

    private String pricechangeType;

    private Integer bopsUserId;

    private Integer auditUserId;

    private Date createTime;
    
    private Date auditTime;
    private Date updateTime;
    private String mark;

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

    public String getPricechangeType() {
        return pricechangeType;
    }

    public void setPricechangeType(String pricechangeType) {
        this.pricechangeType = pricechangeType;
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

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "BopsPricechange [pricechangeId=" + pricechangeId + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", excuted=" + excuted + ", pricechangeStatus=" + pricechangeStatus + ", pricechangeType="
				+ pricechangeType + ", bopsUserId=" + bopsUserId + ", auditUserId=" + auditUserId + ", createTime="
				+ createTime + ", auditTime=" + auditTime + ", updateTime=" + updateTime + ", mark=" + mark + "]";
	}

    
}
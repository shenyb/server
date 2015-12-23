package com.need.domain.po.bops.template;

import java.io.Serializable;
import java.util.Date;

public class BopsTopicTemplatePO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 5841637131372858521L;

	private Integer topicId;

    private String topicTitle;

    private String topicCode;

    private String visitUrl;

    private Integer publisherId;

    private Integer auditorId;

    private String opposeReason;

    private String auditStatus;

    private Date createTime;

    private Integer recordStatus;

    private String topicContents;
    private String tmpContents;//临时保存专题
    
    private String topicVersion;
    
    
    public String getTmpContents() {
		return tmpContents;
	}

	public void setTmpContents(String tmpContents) {
		this.tmpContents = tmpContents;
	}

	public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
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

    public String getOpposeReason() {
        return opposeReason;
    }

    public void setOpposeReason(String opposeReason) {
        this.opposeReason = opposeReason;
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

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getTopicContents() {
        return topicContents;
    }

    public void setTopicContents(String topicContents) {
        this.topicContents = topicContents;
    }
    
    

	public String getTopicVersion() {
		return topicVersion;
	}

	public void setTopicVersion(String topicVersion) {
		this.topicVersion = topicVersion;
	}

	@Override
	public String toString() {
		return "BopsTopicTemplatePO [topicId=" + topicId + ", topicTitle="
				+ topicTitle + ", topicCode=" + topicCode + ", visitUrl="
				+ visitUrl + ", publisherId=" + publisherId + ", auditorId="
				+ auditorId + ", opposeReason=" + opposeReason
				+ ", auditStatus=" + auditStatus + ", createTime=" + createTime
				+ ", recordStatus=" + recordStatus + ", topicContents="
				+ topicContents + ", tmpContents=" + tmpContents
				+ ", topicVersion=" + topicVersion + "]";
	}

	
    
}
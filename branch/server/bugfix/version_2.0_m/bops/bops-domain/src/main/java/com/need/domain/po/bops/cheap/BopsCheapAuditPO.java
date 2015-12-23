package com.need.domain.po.bops.cheap;

import java.io.Serializable;
import java.util.Date;

public class BopsCheapAuditPO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7383714563404658942L;

	private Integer cheapAuditId;

    private String cheapNo;

    private String auditStatus;

    private String description;

    private Integer auditUserId;

    private Date createTime;

    public Integer getCheapAuditId() {
        return cheapAuditId;
    }

    public void setCheapAuditId(Integer cheapAuditId) {
        this.cheapAuditId = cheapAuditId;
    }

    public String getCheapNo() {
        return cheapNo;
    }

    public void setCheapNo(String cheapNo) {
        this.cheapNo = cheapNo;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
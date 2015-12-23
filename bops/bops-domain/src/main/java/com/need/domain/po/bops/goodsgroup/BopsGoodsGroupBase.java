package com.need.domain.po.bops.goodsgroup;

import java.io.Serializable;
import java.util.Date;

public class BopsGoodsGroupBase implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 2858978830209464372L;

	private String groupBatch;

    private String ruleType;

    private String ruleContent;

    private String groupName;

    private String groupBrief;

    private String groupStatus;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    private Integer createId;

    private Integer updateId;

    private Integer auditId;

    public String getGroupBatch() {
        return groupBatch;
    }

    public void setGroupBatch(String groupBatch) {
        this.groupBatch = groupBatch;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleContent() {
        return ruleContent;
    }

    public void setRuleContent(String ruleContent) {
        this.ruleContent = ruleContent;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupBrief() {
        return groupBrief;
    }

    public void setGroupBrief(String groupBrief) {
        this.groupBrief = groupBrief;
    }

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

	@Override
	public String toString() {
		return "BopsGoodsGroupBase [groupBatch=" + groupBatch + ", ruleType=" + ruleType + ", ruleContent="
				+ ruleContent + ", groupName=" + groupName + ", groupBrief=" + groupBrief + ", groupStatus="
				+ groupStatus + ", startTime=" + startTime + ", endTime=" + endTime + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", createId=" + createId + ", updateId=" + updateId + ", auditId="
				+ auditId + "]";
	}
    
    
}
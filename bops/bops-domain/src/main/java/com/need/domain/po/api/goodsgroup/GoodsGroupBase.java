package com.need.domain.po.api.goodsgroup;

import java.io.Serializable;
import java.util.Date;

public class GoodsGroupBase implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8387889311483852558L;

	private String groupBatch;

    private String ruleType;

    private String ruleContent;

    private String groupName;

    private String groupBrief;

    private String groupStatus;

    private Date startTime;

    private Date endTime;

    private Date createTime;

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

	@Override
	public String toString() {
		return "GoodsGroupBase [groupBatch=" + groupBatch + ", ruleType=" + ruleType + ", ruleContent=" + ruleContent
				+ ", groupName=" + groupName + ", groupBrief=" + groupBrief + ", groupStatus=" + groupStatus
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", createTime=" + createTime + "]";
	}
    
    
}
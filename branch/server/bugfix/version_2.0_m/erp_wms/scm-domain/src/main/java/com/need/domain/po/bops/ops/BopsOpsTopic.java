package com.need.domain.po.bops.ops;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class BopsOpsTopic implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 3945533544702126682L;

	private Integer topicId;
    
	@NotBlank(message="请输入专题名称")
	@Length(min=1,max=10,message="专题名称在10个字以内")
    private String topicName;
    @NotBlank(message="请上传专题封面图片")
    private String coverKey;

    private String topicStatus;

    private Date createTime;

    private Integer publisherId;

    private Integer auditorId;
    @NotNull
    private String content;
    
    private String reason;
    @NotBlank(message="请输入专题简介")
    @Length(min=1,max=100,message="专题简介在100字以内")
    private String topicDesc;
    

    
    public String getTopicDesc() {
		return topicDesc;
	}

	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getCoverKey() {
        return coverKey;
    }

    public void setCoverKey(String coverKey) {
        this.coverKey = coverKey;
    }

    public String getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(String topicStatus) {
        this.topicStatus = topicStatus;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	@Override
	public String toString() {
		return "BopsOpsTopic [topicId=" + topicId + ", topicName=" + topicName + ", coverKey=" + coverKey
				+ ", topicStatus=" + topicStatus + ", createTime=" + createTime + ", publisherId=" + publisherId
				+ ", auditorId=" + auditorId + ", content=" + content + ", reason=" + reason + ", topicDesc="
				+ topicDesc + "]";
	}
    
}
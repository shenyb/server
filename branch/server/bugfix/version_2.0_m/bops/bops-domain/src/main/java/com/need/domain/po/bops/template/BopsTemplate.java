package com.need.domain.po.bops.template;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class BopsTemplate implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4505896493036225585L;

	private Integer templateId;

    @NotBlank(message="标题不能为空")
    private String templateName;

    private String templateCode;

    private Date createTime;

    private Integer recordStatus;

    private String templateContents;

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
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

    public String getTemplateContents() {
        return templateContents;
    }

    public void setTemplateContents(String templateContents) {
        this.templateContents = templateContents;
    }

	@Override
	public String toString() {
		return "BopsTemplate [templateId=" + templateId + ", templateName=" + templateName + ", templateCode="
				+ templateCode + ", createTime=" + createTime + ", recordStatus=" + recordStatus + ", templateContents="
				+ templateContents + "]";
	}
}
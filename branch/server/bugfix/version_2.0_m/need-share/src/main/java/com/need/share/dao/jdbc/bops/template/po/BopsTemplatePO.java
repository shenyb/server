package com.need.share.dao.jdbc.bops.template.po;

import java.io.Serializable;
import java.util.Date;

/**
 * <p></p>
 * @author Rylan 2015年9月7日 下午2:42:29
 * @ClassName BopsTemplatePO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月7日
 * @modify by reason:{方法名}:{原因}
 */
public class BopsTemplatePO implements Serializable{
	
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 7064871914601773127L;

	private int templateId;

	private String templateName;
	
    private String templateUrl;

    private Date createTime;

    private Integer recordStatus;

    private String templateContents;

    
    
    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
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

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
    
    
}
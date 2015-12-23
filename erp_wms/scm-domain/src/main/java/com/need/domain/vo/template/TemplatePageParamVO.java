package com.need.domain.vo.template;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class TemplatePageParamVO extends Page  implements Serializable{
	
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2121948536568188992L;

	private Integer topicId; //专题ID
	private String topicTitle;//专题内容

    private String topicCode;//专题编号(id)
    
    private String auditStatus;//审核状态
    
    private String topicCategoryId;//专题分类ID
    
    
    
    
	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getTopicCategoryId() {
		return topicCategoryId;
	}

	public void setTopicCategoryId(String topicCategoryId) {
		this.topicCategoryId = topicCategoryId;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
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
    
    public Page getParantPage(){
        Page p=new Page();
        p.setTotal(this.getTotal());
        p.setPage(this.getPage());
        p.setPageSize(this.getPageSize());
    	return p;
    }
    
	
}

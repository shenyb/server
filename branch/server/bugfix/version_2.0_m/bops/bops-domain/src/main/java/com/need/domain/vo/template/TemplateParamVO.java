package com.need.domain.vo.template;

import java.io.Serializable;

public class TemplateParamVO implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 2224355849289363684L;

	private Integer topicId;//id
	
	private String topicTitle;//专题内容

    private String topicCode;//专题编号(id)

    private String topicContents;//html 内容
    
    private Integer publisherId;//发布者

    private Integer auditorId;//审核者
    
    private String visitUrl;//访问地址
    
    private String saveUrl;//保存地址
    
    private String opposeReason;//意见

    private String auditStatus;//状态

    private String tmpContents;//临时保存专题
    
    private String topicVersion;
    
    private String[] goodsCodes;
    
    private String goodsCodesString;
    
    
    
    
    
    
    
    

	public String getGoodsCodesString() {
		return goodsCodesString;
	}

	public void setGoodsCodesString(String goodsCodesString) {
		this.goodsCodesString = goodsCodesString;
	}

	public String[] getGoodsCodes() {
		return goodsCodes;
	}

	public void setGoodsCodes(String[] goodsCodes) {
		this.goodsCodes = goodsCodes;
	}

	public String getTmpContents() {
		return tmpContents;
	}

	public void setTmpContents(String tmpContents) {
		this.tmpContents = tmpContents;
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

	public String getTopicContents() {
		return topicContents;
	}

	public void setTopicContents(String topicContents) {
		this.topicContents = topicContents;
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

	
	
	public String getVisitUrl() {
		return visitUrl;
	}

	public void setVisitUrl(String visitUrl) {
		this.visitUrl = visitUrl;
	}

	public String getSaveUrl() {
		return saveUrl;
	}

	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
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
	
	

	public String getTopicVersion() {
		return topicVersion;
	}

	public void setTopicVersion(String topicVersion) {
		this.topicVersion = topicVersion;
	}

	@Override
	public String toString() {
		return "TemplateParamVO [topicId=" + topicId + ", topicTitle="
				+ topicTitle + ", topicCode=" + topicCode + ", topicContents="
				+ topicContents + ", publisherId=" + publisherId
				+ ", auditorId=" + auditorId + ", visitUrl=" + visitUrl
				+ ", saveUrl=" + saveUrl + ", opposeReason=" + opposeReason
				+ ", auditStatus=" + auditStatus + ", tmpContents="
				+ tmpContents + ", topicVersion=" + topicVersion + "]";
	}

	


	
}

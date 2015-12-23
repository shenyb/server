package com.need.domain.vo.template;

import java.io.Serializable;
import java.util.Date;

public class TemplateResultVO  implements Serializable{
	    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -997158517950449507L;

		private String topicTitle;//标题

		private Integer topicId;//编号 (id)
	    
	    private String topicContents;//专题内容 html

	    private String visitUrl;//访问地址

	    private Integer publisherId;//发布者id

	    private Integer auditorId;//审核者id

	    private String opposeReason;//意见

	    private String auditStatus;//状态

	    private Date createTime;//创建时间
	    
	    private String categoryName; //專題分類名稱
	    
	    private String tmpContents;//临时保存专题
	    
	    private String topicVersion;
	    
	    private String goodsCodesString;
	    
	    private String[] goodsCodes;
	    
	    
	    
	    
	    
	    
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


		public String getTopicVersion() {
			return topicVersion;
		}

		public void setTopicVersion(String topicVersion) {
			this.topicVersion = topicVersion;
		}

		private Date effDate;
	    private Date expDate;
	    private Integer topicScore;
	    
	    
	    

		public Date getEffDate() {
			return effDate;
		}

		public void setEffDate(Date effDate) {
			this.effDate = effDate;
		}

		public Date getExpDate() {
			return expDate;
		}

		public void setExpDate(Date expDate) {
			this.expDate = expDate;
		}

		public Integer getTopicScore() {
			return topicScore;
		}

		public void setTopicScore(Integer topicScore) {
			this.topicScore = topicScore;
		}

		public String getTmpContents() {
			return tmpContents;
		}

		public void setTmpContents(String tmpContents) {
			this.tmpContents = tmpContents;
		}

		
		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public String getTopicTitle() {
			return topicTitle;
		}

		public void setTopicTitle(String topicTitle) {
			this.topicTitle = topicTitle;
		}

	

		public Integer getTopicId() {
			return topicId;
		}

		public void setTopicId(Integer topicId) {
			this.topicId = topicId;
		}

		public String getTopicContents() {
			return topicContents;
		}

		public void setTopicContents(String topicContents) {
			this.topicContents = topicContents;
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

		@Override
		public String toString() {
			return "TemplateResultVO [topicTitle=" + topicTitle + ", topicId=" + topicId + ", topicContents="
					+ topicContents + ", visitUrl=" + visitUrl + ", publisherId=" + publisherId + ", auditorId="
					+ auditorId + ", opposeReason=" + opposeReason + ", auditStatus=" + auditStatus + ", createTime="
					+ createTime + "]";
		}

		
	    
	    
    
    
}

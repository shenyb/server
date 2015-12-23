package com.need.domain.vo.template;

import java.io.Serializable;
import java.util.Date;

public class TemplateBaseVO implements Serializable{
	
	    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 134827837009635405L;

		private Integer topicId;

	    private String topicTitle;

	    private String topicCode;
	    
	    private String topicContents;

	    private String visitUrl;

	    private String auditStatus;

	    private Date createTime;

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
			return "TemplateBaseVO [topicId=" + topicId + ", topicTitle=" + topicTitle + ", topicCode=" + topicCode
					+ ", topicContents=" + topicContents + ", visitUrl=" + visitUrl + ", auditStatus=" + auditStatus
					+ ", createTime=" + createTime + "]";
		}

	 
    
    
    
    
	
}

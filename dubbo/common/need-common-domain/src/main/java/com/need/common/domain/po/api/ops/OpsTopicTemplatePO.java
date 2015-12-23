package com.need.common.domain.po.api.ops;

import java.io.Serializable;

public class OpsTopicTemplatePO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2409919707101912736L;

	private Integer topicId;

    private String topicTitle;

    private String topicCode;

    private String visitUrl;

    private String topicContents;

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

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }

    public String getTopicContents() {
        return topicContents;
    }

    public void setTopicContents(String topicContents) {
        this.topicContents = topicContents;
    }
}
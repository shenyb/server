package com.need.domain.po.api.template;

import java.io.Serializable;

/**
 * <p></p>
 * @author Rylan 2015年9月9日 下午11:27:40
 * @ClassName OpsTopicTemplate
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月9日
 * @modify by reason:{方法名}:{原因}
 */
public class OpsTopicTemplate implements Serializable{
	
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1420985840185311480L;

	private int topicId;

    private String topicTitle;

    private String topicCode;

    private String visitUrl;

    private String topicContents;
    
    private String goodsCodes;
    
    

    public String getGoodsCodes() {
		return goodsCodes;
	}

	public void setGoodsCodes(String goodsCodes) {
		this.goodsCodes = goodsCodes;
	}

	public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
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

	@Override
	public String toString() {
		return "OpsTopicTemplate [topicId=" + topicId + ", topicTitle=" + topicTitle + ", topicCode=" + topicCode
				+ ", visitUrl=" + visitUrl + ", topicContents=" + topicContents + "]";
	}
    
}
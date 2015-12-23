package com.need.domain.po.api.feed;

import java.io.Serializable;

public class FeedLabelPO implements Serializable {

    private static final long serialVersionUID = 7794585051854994319L;
    
    private String feedLabelId;
    private String feedLabelContent;
    private String feedLabelType;
    private String feedId;
    private String widthPercent;
    private String heightPercent;

    /**
     * @return the feedLabelId
     */
    public String getFeedLabelId() {
        return feedLabelId;
    }

    /**
     * @param feedLabelId the feedLabelId to set
     */
    public void setFeedLabelId(String feedLabelId) {
        this.feedLabelId = feedLabelId;
    }

    /**
     * @return the feedLabelContent
     */
    public String getFeedLabelContent() {
        return feedLabelContent;
    }

    /**
     * @param feedLabelContent the feedLabelContent to set
     */
    public void setFeedLabelContent(String feedLabelContent) {
        this.feedLabelContent = feedLabelContent;
    }

    /**
     * @return the feedLabelType
     */
    public String getFeedLabelType() {
        return feedLabelType;
    }

    /**
     * @param feedLabelType the feedLabelType to set
     */
    public void setFeedLabelType(String feedLabelType) {
        this.feedLabelType = feedLabelType;
    }

    /**
     * @return the feedId
     */
    public String getFeedId() {
        return feedId;
    }

    /**
     * @param feedId the feedId to set
     */
    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    /**
     * @return the widthPercent
     */
    public String getWidthPercent() {
        return widthPercent;
    }

    /**
     * @param widthPercent the widthPercent to set
     */
    public void setWidthPercent(String widthPercent) {
        this.widthPercent = widthPercent;
    }

    /**
     * @return the heightPercent
     */
    public String getHeightPercent() {
        return heightPercent;
    }

    /**
     * @param heightPercent the heightPercent to set
     */
    public void setHeightPercent(String heightPercent) {
        this.heightPercent = heightPercent;
    }

    @Override
    public String toString() {
        return "FeedLabelPO{" + "feedLabelId=" + feedLabelId + ", feedLabelContent=" + feedLabelContent + 
                ", feedLabelType=" + feedLabelType + ", feedId=" + feedId + 
                ", widthPercent=" + widthPercent + ", heightPercent=" + heightPercent + '}';
    }
}
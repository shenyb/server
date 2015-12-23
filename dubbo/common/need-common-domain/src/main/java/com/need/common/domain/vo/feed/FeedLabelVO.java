/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.domain.vo.feed;

import java.io.Serializable;

/**
 *
 * @author 庆凯
 */
public class FeedLabelVO implements Serializable {

    private static final long serialVersionUID = -7896450720816623193L;
    
    private String feedLabelContent;
    private String feedLabelType;
    private String widthPercent;
    private String heightPercent;

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
        return "FeedLabelVO{" + "feedLabelContent=" + feedLabelContent + ", feedLabelType=" + feedLabelType + 
                ", widthPercent=" + widthPercent + ", heightPercent=" + heightPercent + '}';
    }
    
}

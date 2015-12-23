package com.need.common.domain.po.api.feed;

import java.io.Serializable;
import java.util.Date;

public class FeedReportPO implements Serializable {

    private static final long serialVersionUID = 3666758144514684987L;
    
    private String feedReportId;
    private String userId;
    private String feedId;
    private String reportContent;
    private String reportStatus;
    private Date createTime;

    /**
     * @return the feedReportId
     */
    public String getFeedReportId() {
        return feedReportId;
    }

    /**
     * @param feedReportId the feedReportId to set
     */
    public void setFeedReportId(String feedReportId) {
        this.feedReportId = feedReportId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * @return the reportContent
     */
    public String getReportContent() {
        return reportContent;
    }

    /**
     * @param reportContent the reportContent to set
     */
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    /**
     * @return the reportStatus
     */
    public String getReportStatus() {
        return reportStatus;
    }

    /**
     * @param reportStatus the reportStatus to set
     */
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "FeedReportPO{" + "feedReportId=" + feedReportId + ", userId=" + userId + 
                ", feedId=" + feedId + ", reportContent=" + reportContent + ", reportStatus=" + reportStatus + 
                ", createTime=" + createTime + '}';
    }
}
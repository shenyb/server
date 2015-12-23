package com.need.domain.po.api.feed;

import java.io.Serializable;
import java.util.Date;

public class FeedUserPO implements Serializable {

    private static final long serialVersionUID = -4097098434356688705L;
    
    private String feedId;
    private String userId;
    private String feedContent;
    private String feedPicKey;
    private Date createTime;
    private String feedStatus;

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
     * @return the feedContent
     */
    public String getFeedContent() {
        return feedContent;
    }

    /**
     * @param feedContent the feedContent to set
     */
    public void setFeedContent(String feedContent) {
        this.feedContent = feedContent;
    }

    /**
     * @return the feedPicKey
     */
    public String getFeedPicKey() {
        return feedPicKey;
    }

    /**
     * @param feedPicKey the feedPicKey to set
     */
    public void setFeedPicKey(String feedPicKey) {
        this.feedPicKey = feedPicKey;
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

    /**
     * @return the feedStatus
     */
    public String getFeedStatus() {
        return feedStatus;
    }

    /**
     * @param feedStatus the feedStatus to set
     */
    public void setFeedStatus(String feedStatus) {
        this.feedStatus = feedStatus;
    }

    @Override
    public String toString() {
        return "FeedUserPO{" + "feedId=" + feedId + ", userId=" + userId + ", feedContent=" + feedContent + 
                ", feedPicKey=" + feedPicKey + ", createTime=" + createTime + ", feedStatus=" + feedStatus + '}';
    }
}
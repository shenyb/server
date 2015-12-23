package com.need.common.domain.po.api.feed;

import java.io.Serializable;
import java.util.Date;

public class FeedLikePO implements Serializable {

    private static final long serialVersionUID = -1070998883334410552L;
    
    private String feedId;
    private String userId;
    private String feedLikeStatus;
    private Date createTime;

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
     * @return the feedLikeStatus
     */
    public String getFeedLikeStatus() {
        return feedLikeStatus;
    }

    /**
     * @param feedLikeStatus the feedLikeStatus to set
     */
    public void setFeedLikeStatus(String feedLikeStatus) {
        this.feedLikeStatus = feedLikeStatus;
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
        return "FeedLikePO{" + "feedId=" + feedId + ", userId=" + userId + ", feedLikeStatus=" + feedLikeStatus + ", createTime=" + createTime + '}';
    }
}
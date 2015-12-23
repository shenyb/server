package com.need.common.domain.po.api.feed;

import java.io.Serializable;
import java.util.Date;

public class FeedCommentPO implements Serializable {

    private static final long serialVersionUID = 8740128489151271244L;
    
    private String feedCommentId;
    private String feedId;
    private String userId;
    private String replyUserId;
    private String commentContent;
    private String commentStatus;
    private Date createTime;

    /**
     * @return the feedCommentId
     */
    public String getFeedCommentId() {
        return feedCommentId;
    }

    /**
     * @param feedCommentId the feedCommentId to set
     */
    public void setFeedCommentId(String feedCommentId) {
        this.feedCommentId = feedCommentId;
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
     * @return the replyUserId
     */
    public String getReplyUserId() {
        return replyUserId;
    }

    /**
     * @param replyUserId the replyUserId to set
     */
    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    /**
     * @return the commentContent
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * @param commentContent the commentContent to set
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * @return the commentStatus
     */
    public String getCommentStatus() {
        return commentStatus;
    }

    /**
     * @param commentStatus the commentStatus to set
     */
    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
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
        return "FeedCommentPO{" + "feedCommentId=" + feedCommentId + ", feedId=" + feedId + 
                ", userId=" + userId + ", replyUserId=" + replyUserId + ", commentContent=" + commentContent + 
                ", commentStatus=" + commentStatus + ", createTime=" + createTime + '}';
    }
}
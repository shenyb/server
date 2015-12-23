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
public class FeedCommentVO implements Serializable {

    private static final long serialVersionUID = -8139703443013580338L;
    
    private String feedCommentId;
    private String feedId;
    private String commentUserId;
    private String commentUserName;
    private String profilePicKey;
    private String replyId;
    private String replyName;
    private String commentContent;
    private long createTime;

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
     * @return the commentUserId
     */
    public String getCommentUserId() {
        return commentUserId;
    }

    /**
     * @param commentUserId the commentUserId to set
     */
    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    /**
     * @return the commentUserName
     */
    public String getCommentUserName() {
        return commentUserName;
    }

    /**
     * @param commentUserName the commentUserName to set
     */
    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    /**
     * @return the profilePicKey
     */
    public String getProfilePicKey() {
        return profilePicKey;
    }

    /**
     * @param profilePicKey the profilePicKey to set
     */
    public void setProfilePicKey(String profilePicKey) {
        this.profilePicKey = profilePicKey;
    }

    /**
     * @return the replyId
     */
    public String getReplyId() {
        return replyId;
    }

    /**
     * @param replyId the replyId to set
     */
    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    /**
     * @return the replyName
     */
    public String getReplyName() {
        return replyName;
    }

    /**
     * @param replyName the replyName to set
     */
    public void setReplyName(String replyName) {
        this.replyName = replyName;
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
     * @return the createTime
     */
    public long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "FeedCommentVO{" + "feedCommentId=" + feedCommentId + ", feedId=" + feedId + 
                ", commentUserId=" + commentUserId + ", commentUserName=" + commentUserName + 
                ", profilePicKey=" + profilePicKey + ", replyId=" + replyId + ", replyName=" + replyName + 
                ", commentContent=" + commentContent + ", createTime=" + createTime + '}';
    }
    
}

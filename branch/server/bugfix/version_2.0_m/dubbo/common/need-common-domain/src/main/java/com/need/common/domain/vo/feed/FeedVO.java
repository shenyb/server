/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.domain.vo.feed;

import com.need.common.domain.po.api.feed.FeedLabelPO;
import com.need.common.domain.vo.user.UserVO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 庆凯
 */
public class FeedVO implements Serializable {
    
    private static final long serialVersionUID = -8830301482834796886L;
    
    private String feedId;
    private UserVO user;
    private String feedContent;
    private String feedPicKey;
    private long createTime;
    private List<FeedLabelPO> feedLabels;
    private int feedLikeCount;
    private int feedCommentCount;
    private String isLike;

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
     * @return the user
     */
    public UserVO getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserVO user) {
        this.user = user;
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
    public long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the feedLabels
     */
    public List<FeedLabelPO> getFeedLabels() {
        return feedLabels;
    }

    /**
     * @param feedLabels the feedLabels to set
     */
    public void setFeedLabels(List<FeedLabelPO> feedLabels) {
        this.feedLabels = feedLabels;
    }

    /**
     * @return the feedLikeCount
     */
    public int getFeedLikeCount() {
        return feedLikeCount;
    }

    /**
     * @param feedLikeCount the feedLikeCount to set
     */
    public void setFeedLikeCount(int feedLikeCount) {
        this.feedLikeCount = feedLikeCount;
    }

    /**
     * @return the feedCommentCount
     */
    public int getFeedCommentCount() {
        return feedCommentCount;
    }

    /**
     * @param feedCommentCount the feedCommentCount to set
     */
    public void setFeedCommentCount(int feedCommentCount) {
        this.feedCommentCount = feedCommentCount;
    }

    /**
     * @return the isLike
     */
    public String getIsLike() {
        return isLike;
    }

    /**
     * @param isLike the isLike to set
     */
    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    @Override
    public String toString() {
        return "FeedVO{" + "feedId=" + feedId + ", user=" + user + 
                ", feedContent=" + feedContent + ", feedPicKey=" + feedPicKey + 
                ", createTime=" + createTime + ", feedLabels=" + feedLabels + ", feedLikeCount=" + feedLikeCount + 
                ", feedCommentCount=" + feedCommentCount + ", isLike=" + isLike + '}';
    }
    
}

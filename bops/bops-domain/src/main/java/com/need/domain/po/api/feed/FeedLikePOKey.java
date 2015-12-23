/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.domain.po.api.feed;

/**
 *
 * @author 庆凯
 */
public class FeedLikePOKey {
    
    private String feedId;
    private String userId;
    
    public FeedLikePOKey(String feedId, String userId) {
        this.feedId = feedId;
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
    
}

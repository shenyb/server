package com.need.common.domain.po.api.follow;

public class FollowUserPOKey {
    
    protected String userId;
    protected String followId;

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
     * @return the followId
     */
    public String getFollowId() {
        return followId;
    }

    /**
     * @param followId the followId to set
     */
    public void setFollowId(String followId) {
        this.followId = followId;
    }
}
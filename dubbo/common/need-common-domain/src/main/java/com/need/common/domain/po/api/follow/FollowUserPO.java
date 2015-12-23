package com.need.common.domain.po.api.follow;

import java.io.Serializable;
import java.util.Date;

public class FollowUserPO extends FollowUserPOKey implements Serializable {

    private static final long serialVersionUID = 377269769465023337L;
    
    private String followStatus;
    private Date createTime;

    /**
     * @return the followStatus
     */
    public String getFollowStatus() {
        return followStatus;
    }

    /**
     * @param followStatus the followStatus to set
     */
    public void setFollowStatus(String followStatus) {
        this.followStatus = followStatus;
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
        return "FollowUserPO{" + "userId=" + userId + "followId=" + followId + 
                "followStatus=" + followStatus + ", createTime=" + createTime + '}';
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.domain.vo.user;

import com.need.common.domain.po.api.user.UserBasePO;

/**
 *
 * @author 庆凯
 */
public class UserFollowVO extends UserBasePO {
    
    private static final long serialVersionUID = 991214491021382825L;
    
    private int followCount;
    private int followedCount;
    private String isFollow;
    private String isSupportShare;

    /**
     * @return the followCount
     */
    
    
    public int getFollowCount() {
        return followCount;
    }

    public String getIsSupportShare() {
		return isSupportShare;
	}

	public void setIsSupportShare(String isSupportShare) {
		this.isSupportShare = isSupportShare;
	}

	/**
     * @param followCount the followCount to set
     */
    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    /**
     * @return the followedCount
     */
    public int getFollowedCount() {
        return followedCount;
    }

    /**
     * @param followedCount the followedCount to set
     */
    public void setFollowedCount(int followedCount) {
        this.followedCount = followedCount;
    }

    /**
     * @return the isFollow
     */
    public String getIsFollow() {
        return isFollow;
    }

    /**
     * @param isFollow the isFollow to set
     */
    public void setIsFollow(String isFollow) {
        this.isFollow = isFollow;
    }
    
}

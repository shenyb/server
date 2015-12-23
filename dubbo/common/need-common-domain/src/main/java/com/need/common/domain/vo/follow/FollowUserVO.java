/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.common.domain.vo.follow;

import java.io.Serializable;

/**
 * 
 * @author 庆凯 2015-10-14 2015-10-14 18:52:40
 * @ClassName FollowUserVO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-10-14
 * @modify by reason:{方法名}:{原因}
 */
    public class FollowUserVO implements Serializable {

    private static final long serialVersionUID = -7118170531604481005L;
    
    private String userId;
    private String nickName;
    private String profilePicKey;
    private String isFollow;

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
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    @Override
    public String toString() {
        return "FollowUserVO{" + "userId=" + userId + ", nickName=" + nickName + ", profilePicKey=" + profilePicKey + ", isFollow=" + isFollow + '}';
    }
}

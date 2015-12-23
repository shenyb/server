package com.need.common.domain.vo.user;

import java.io.Serializable;

/**
 * 
 * @author 庆凯 2015-12-7 15:58:03
 * @ClassName UserVO
 * @Description TODO
 * @version V2.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-12-7
 * @modify by reason:{方法名}:{原因}
 */
public class UserVO implements Serializable {

    private static final long serialVersionUID = -6743563662189513756L;
    
    private String userId;
    private String nickName;
    private String profilePicKey;
    private String userType;

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
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserVO{" + "userId=" + userId + ", nickName=" + nickName + 
                ", profilePicKey=" + profilePicKey + ", userType=" + userType + '}';
    }

}
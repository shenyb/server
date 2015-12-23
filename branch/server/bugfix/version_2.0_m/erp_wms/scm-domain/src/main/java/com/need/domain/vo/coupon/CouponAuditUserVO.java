/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.domain.vo.coupon;

import java.io.Serializable;

/**
 * 
 * @author 庆凯 2015-9-16 2015-9-16 16:13:44
 * @ClassName CouponAuditVO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-16
 * @modify by reason:{方法名}:{原因}
 */
public class CouponAuditUserVO implements Serializable {
    
    private static final long serialVersionUID = -3296560524433278599L;
    
    private Integer userId;
    private String userName;
    private String userDept;
    private String description;

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userDept
     */
    public String getUserDept() {
        return userDept;
    }

    /**
     * @param userDept the userDept to set
     */
    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CouponAuditUserVO{" + "userId=" + userId + ", userName=" + userName + ", userDept=" + userDept + ", description=" + description + '}';
    }

}

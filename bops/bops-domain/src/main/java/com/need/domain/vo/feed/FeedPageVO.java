/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.domain.vo.feed;

import com.need.domain.pub.Page;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 庆凯
 */
public class FeedPageVO extends Page implements Serializable {

    private static final long serialVersionUID = -3616108194786211757L;
    
    private String nickName;
    private String mobile;
    private String userId;
    private Date startTime;
    private Date endTime;
    private String startTimeString;
    private String endTimeString;

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
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the startTimeString
     */
    public String getStartTimeString() {
        return startTimeString;
    }

    /**
     * @param startTimeString the startTimeString to set
     */
    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    /**
     * @return the endTimeString
     */
    public String getEndTimeString() {
        return endTimeString;
    }

    /**
     * @param endTimeString the endTimeString to set
     */
    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    @Override
    public String toString() {
        return "FeedPageVO{" + "nickName=" + nickName + ", mobile=" + mobile + ", userId=" + userId + 
                ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }
    
}

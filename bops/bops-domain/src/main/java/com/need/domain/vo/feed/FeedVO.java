package com.need.domain.vo.feed;

import com.need.domain.po.api.feed.FeedUserPO;
import java.io.Serializable;

/**
 * 
 * @author 庆凯 2015-12-4 18:03:22
 * @ClassName FeedVO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-12-4
 * @modify by reason:{方法名}:{原因}
 */
public class FeedVO extends FeedUserPO implements Serializable {

    private static final long serialVersionUID = -900627362563025489L;
    
    private String nickName;
    private String mobile;
    private String feedReportId;
    private int reportCount;
    private String reportStatus;
    private String userStatus;

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
     * @return the reportCount
     */
    public int getReportCount() {
        return reportCount;
    }

    /**
     * @param reportCount the reportCount to set
     */
    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    /**
     * @return the feedReportId
     */
    public String getFeedReportId() {
        return feedReportId;
    }

    /**
     * @param feedReportId the feedReportId to set
     */
    public void setFeedReportId(String feedReportId) {
        this.feedReportId = feedReportId;
    }

    /**
     * @return the reportStatus
     */
    public String getReportStatus() {
        return reportStatus;
    }

    /**
     * @param reportStatus the reportStatus to set
     */
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    /**
     * @return the userStatus
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * @param userStatus the userStatus to set
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "FeedVO{" + "nickName=" + nickName + ", mobile=" + mobile + ", feedReportId=" + feedReportId + 
                ", reportCount=" + reportCount + ", reportStatus=" + reportStatus + ", userStatus=" + userStatus + '}';
    }

}
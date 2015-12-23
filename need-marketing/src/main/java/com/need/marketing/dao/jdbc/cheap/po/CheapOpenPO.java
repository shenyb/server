package com.need.marketing.dao.jdbc.cheap.po;

import java.io.Serializable;
import java.util.Date;

public class CheapOpenPO implements Serializable {
    
    private static final long serialVersionUID = 2238959441547928523L;
    
    private Integer cheapOpenId;
    private String cheapNo;
    private String userId;
    private String cheapStatus;
    private Date createTime;
    private Date completeTime;

    /**
     * @return the cheapOpenId
     */
    public Integer getCheapOpenId() {
        return cheapOpenId;
    }

    /**
     * @param cheapOpenId the cheapOpenId to set
     */
    public void setCheapOpenId(Integer cheapOpenId) {
        this.cheapOpenId = cheapOpenId;
    }

    /**
     * @return the cheapNo
     */
    public String getCheapNo() {
        return cheapNo;
    }

    /**
     * @param cheapNo the cheapNo to set
     */
    public void setCheapNo(String cheapNo) {
        this.cheapNo = cheapNo;
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
     * @return the cheapStatus
     */
    public String getCheapStatus() {
        return cheapStatus;
    }

    /**
     * @param cheapStatus the cheapStatus to set
     */
    public void setCheapStatus(String cheapStatus) {
        this.cheapStatus = cheapStatus;
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

    /**
     * @return the completeTime
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**
     * @param completeTime the completeTime to set
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    @Override
    public String toString() {
        return "CheapOpenPO{" + "cheapOpenId=" + cheapOpenId + ", cheapNo=" + cheapNo + ", userId=" + userId + 
                ", cheapStatus=" + cheapStatus + ", createTime=" + createTime + ", completeTime=" + completeTime + '}';
    }
}
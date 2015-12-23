package com.need.domain.po.api.cheap;

import java.io.Serializable;
import java.util.Date;

public class CheapOpen implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6080706420353480091L;

	private Integer cheapOpenId;

    private String cheapNo;

    private String userId;

    private String cheapStatus;

    private Date createTime;

    private Date completeTime;

    public Integer getCheapOpenId() {
        return cheapOpenId;
    }

    public void setCheapOpenId(Integer cheapOpenId) {
        this.cheapOpenId = cheapOpenId;
    }

    public String getCheapNo() {
        return cheapNo;
    }

    public void setCheapNo(String cheapNo) {
        this.cheapNo = cheapNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCheapStatus() {
        return cheapStatus;
    }

    public void setCheapStatus(String cheapStatus) {
        this.cheapStatus = cheapStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }
}
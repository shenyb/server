package com.need.domain.po.bops.kol;

import java.io.Serializable;
import java.util.Date;

public class BopsKol implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1973768185356848137L;

	
	private String kolId;

    private String mobile;

    private String nickName;

    private String profilePicKey;

    private String kolCategories;

    private String certification;

    private Integer auditorId;

    private String auditorStatus;

    private Integer publisherId;

    private String memo;

    private Date createTime;

    private Date updateTime;
    
    private String kolIntroduct;

    public String getKolId() {
        return kolId;
    }

    public void setKolId(String kolId) {
        this.kolId = kolId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProfilePicKey() {
        return profilePicKey;
    }

    public void setProfilePicKey(String profilePicKey) {
        this.profilePicKey = profilePicKey;
    }

    public String getKolCategories() {
        return kolCategories;
    }

    public void setKolCategories(String kolCategories) {
        this.kolCategories = kolCategories;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditorStatus() {
        return auditorStatus;
    }

    public void setAuditorStatus(String auditorStatus) {
        this.auditorStatus = auditorStatus;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    
	public String getKolIntroduct() {
		return kolIntroduct;
	}

	public void setKolIntroduct(String kolIntroduct) {
		this.kolIntroduct = kolIntroduct;
	}

	@Override
	public String toString() {
		return "BopsKol [kolId=" + kolId + ", mobile=" + mobile + ", nickName=" + nickName + ", profilePicKey="
				+ profilePicKey + ", kolCategories=" + kolCategories + ", certification=" + certification
				+ ", auditorId=" + auditorId + ", auditorStatus=" + auditorStatus + ", publisherId=" + publisherId
				+ ", memo=" + memo + ", createTime=" + createTime + ", updateTime=" + updateTime + ", kolIntroduct="
				+ kolIntroduct + "]";
	}

    
}
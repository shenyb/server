package com.need.domain.vo.kol;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.need.domain.pub.Page;

public class BopsKolParamVO  extends Page  implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 56207135540721480L;

	private String kolId;

    private String mobile;

    private String nickName;

    private String profilePicKey;//头像地址

    private String  kolCategories;//行家类别 数组
    
    private String certification;//认证信息
    
    private String  kolIntroduct;//简介
    
    private Integer publisherId;//创建者id

    private Integer auditorId;//审核者id

    private String auditorStatus;//审核状态

    private String memo;//审核评价
    
    private Date updateTime;//审核时间
    
    private String userId;

    private String[]  kolCategoriesList;//行家类别 数组
    
    

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
	
	

	public String getKolIntroduct() {
		return kolIntroduct;
	}

	public void setKolIntroduct(String kolIntroduct) {
		this.kolIntroduct = kolIntroduct;
	}


	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getKolCategories() {
		return kolCategories;
	}

	public void setKolCategories(String kolCategories) {
		this.kolCategories = kolCategories;
	}

	public String[] getKolCategoriesList() {
		return kolCategoriesList;
	}

	public void setKolCategoriesList(String[] kolCategoriesList) {
		this.kolCategoriesList = kolCategoriesList;
	}

	@Override
	public String toString() {
		return "BopsKolParamVO [kolId=" + kolId + ", mobile=" + mobile + ", nickName=" + nickName + ", profilePicKey="
				+ profilePicKey + ", kolCategories=" + kolCategories + ", certification=" + certification
				+ ", kolIntroduct=" + kolIntroduct + ", publisherId=" + publisherId + ", auditorId=" + auditorId
				+ ", auditorStatus=" + auditorStatus + ", memo=" + memo + ", updateTime=" + updateTime + ", userId="
				+ userId + ", kolCategoriesList=" + Arrays.toString(kolCategoriesList) + "]";
	}
    
}


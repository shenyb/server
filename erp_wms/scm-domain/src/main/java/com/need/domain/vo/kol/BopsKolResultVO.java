package com.need.domain.vo.kol;

import java.io.Serializable;
import java.util.List;

import com.need.domain.po.bops.basedata.BopsKolCategory;

public class BopsKolResultVO implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2069293466616444606L;

	private String kolId;

    private String mobile;

    private String nickName;

    private String profilePicKey;//头像地址

    private String kolCategories;//行家类别

    private String certification;//认证信息
    
    private Integer publisherId;//创建者id

    private Integer auditorId;//审核者id

    private String auditorStatus;//审核状态

    private String memo;//审核评价

    private String kolIntroduct;//  行家简介
    
    private List<BopsKolCategory> categoryList;
    
    
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

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
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

	@Override
	public String toString() {
		return "BopsKolResultVO [kolId=" + kolId + ", mobile=" + mobile + ", nickName=" + nickName + ", profilePicKey="
				+ profilePicKey + ", kolCategories=" + kolCategories + ", certification=" + certification
				+ ", publisherId=" + publisherId + ", auditorId=" + auditorId + ", auditorStatus=" + auditorStatus
				+ ", memo=" + memo + ", kolIntroduct=" + kolIntroduct + "]";
	}

	public List<BopsKolCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<BopsKolCategory> categoryList) {
		this.categoryList = categoryList;
	}


    
    
    
}

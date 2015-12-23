package com.need.domain.vo.kol;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class BopsKolUpdateParamVO implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -4082657891147370181L;

	private String kolId;

    private String mobile;

    private String nickName;
 
    private String profilePicKey;//头像地址
    @NotBlank(message="请选择行家类别")
    private String  kolCategories;//行家类别 数组
    @NotNull(message="请输入认证信息")
    @Length(min=1,max=100,message="认证信息支持在100字以内")
    private String certification;//认证信息
    @NotNull(message="请输入行家简介")
    @Length(min=1,max=42,message="行家简介支持42个字以内")
    private String  kolIntroduct;//简介
    
    private Integer publisherId;//创建者id

    private Integer auditorId;//审核者id

    private String auditorStatus;//审核状态

    private String memo;//审核评价
    
    private Date updateTime;//审核时间
    
    private String userId;

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

	public String getKolIntroduct() {
		return kolIntroduct;
	}

	public void setKolIntroduct(String kolIntroduct) {
		this.kolIntroduct = kolIntroduct;
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

	@Override
	public String toString() {
		return "BopsKolUpdateParamVO [kolId=" + kolId + ", mobile=" + mobile + ", nickName=" + nickName
				+ ", profilePicKey=" + profilePicKey + ", kolCategories=" + kolCategories + ", certification="
				+ certification + ", kolIntroduct=" + kolIntroduct + ", publisherId=" + publisherId + ", auditorId="
				+ auditorId + ", auditorStatus=" + auditorStatus + ", memo=" + memo + ", updateTime=" + updateTime
				+ ", userId=" + userId + "]";
	}

	

	
}

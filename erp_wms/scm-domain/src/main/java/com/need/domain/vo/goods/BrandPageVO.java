package com.need.domain.vo.goods;

import java.io.Serializable;
import java.util.Date;

import com.need.domain.pub.Page;

public class BrandPageVO extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5692362563035070330L;
	
	private Integer brandId;

    private String brandName;

    private String brandArea;

    private String brandWebsite;

    private String afterPhone;

    private String brandStatus;

    private String brandDescriptionText;

    private String brandPicKey;

    private Integer createId;
    
    private String createName;

    private Integer updateId;

    private String updateName;
    
    private Date createTime;

    private Date updateTime;

    
    
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandArea() {
		return brandArea;
	}

	public void setBrandArea(String brandArea) {
		this.brandArea = brandArea;
	}

	public String getBrandWebsite() {
		return brandWebsite;
	}

	public void setBrandWebsite(String brandWebsite) {
		this.brandWebsite = brandWebsite;
	}

	public String getAfterPhone() {
		return afterPhone;
	}

	public void setAfterPhone(String afterPhone) {
		this.afterPhone = afterPhone;
	}

	public String getBrandStatus() {
		return brandStatus;
	}

	public void setBrandStatus(String brandStatus) {
		this.brandStatus = brandStatus;
	}

	public String getBrandDescriptionText() {
		return brandDescriptionText;
	}

	public void setBrandDescriptionText(String brandDescriptionText) {
		this.brandDescriptionText = brandDescriptionText;
	}

	public String getBrandPicKey() {
		return brandPicKey;
	}

	public void setBrandPicKey(String brandPicKey) {
		this.brandPicKey = brandPicKey;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BrandPageVO [brandId=" + brandId + ", brandName=" + brandName + ", brandArea=" + brandArea
				+ ", brandWebsite=" + brandWebsite + ", afterPhone=" + afterPhone + ", brandStatus=" + brandStatus
				+ ", brandDescriptionText=" + brandDescriptionText + ", brandPicKey=" + brandPicKey + ", createId="
				+ createId + ", updateId=" + updateId + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

    
	
}

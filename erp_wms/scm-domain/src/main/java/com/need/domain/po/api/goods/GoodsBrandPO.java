package com.need.domain.po.api.goods;

import java.io.Serializable;
import java.util.Date;

public class GoodsBrandPO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 7351233459605522638L;

	private Integer brandId;

    private String brandName;

    private String brandArea;

    private String brandWebsite;

    private String afterPhone;

    private String brandStatus;

    private String brandDescriptionText;

    private String brandPicKey;

    private Date createTime;

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

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "GoodsBrandPO [brandId=" + brandId + ", brandName=" + brandName + ", brandArea=" + brandArea
				+ ", brandWebsite=" + brandWebsite + ", afterPhone=" + afterPhone + ", brandStatus=" + brandStatus
				+ ", brandDescriptionText=" + brandDescriptionText + ", brandPicKey=" + brandPicKey + ", createTime="
				+ createTime + "]";
	}
    
    
}
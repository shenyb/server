package com.need.domain.po.bops.goods;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class BopsGoodsBrandPO  implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4183825483208407417L;

	private Integer brandId;
    @NotBlank(message="品牌名称不能为空")
    @Length(max=50,message="品牌名称最大不能超过50个字")
    private String brandName;
    @NotBlank(message="品牌归属地不能为空")
    private String brandArea;
    
    private String brandWebsite;
    
    private String afterPhone;

    private String brandStatus;
    @Length(max=100,message="品牌介绍最大不能超过100个字")
    private String brandDescriptionText;

    private String brandPicKey;

    private Integer createId;

    private Integer updateId;

    private Date createTime;

    private Date updateTime;

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

	@Override
	public String toString() {
		return "BopsGoodsBrandPO [brandId=" + brandId + ", brandName=" + brandName + ", brandArea=" + brandArea
				+ ", brandWebsite=" + brandWebsite + ", afterPhone=" + afterPhone + ", brandStatus=" + brandStatus
				+ ", brandDescriptionText=" + brandDescriptionText + ", brandPicKey=" + brandPicKey + ", createId="
				+ createId + ", updateId=" + updateId + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
    
}
package com.need.domain.vo.goods;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class BopsGoodsBrandVO  implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 7053537038258285553L;

	private Integer brandId;
	
    private String brandName;
    
    private String brandArea;
    
    private String brandWebsite;
    
    private String afterPhone;

    private String brandStatus;
    
    private String brandDescriptionText;

    private String brandPicKey;

    private Integer createId;

    private Integer updateId;

    private String createTime;

    private String updateTime;
    
    private String createName;
    
    private String updateName;

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
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
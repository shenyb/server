package com.need.domain.po.bops.basedata;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class BopsKolCategory implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;

	private Integer categoryId;

	@Length(min=1,max=10,message="行家分类名称在10个字以内")
    private String categoryName;

    private Integer publisherId;

    private Integer auditorId;

    private Date createTime;

    private Date updateTime;
    
    private String  categoryDesc;
    @NotNull(message="行家分类图片不能为空")
    private String categoryProfileKey;
    
    


	public String getCategoryProfileKey() {
		return categoryProfileKey;
	}

	public void setCategoryProfileKey(String categoryProfileKey) {
		this.categoryProfileKey = categoryProfileKey;
	}

	public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	@Override
	public String toString() {
		return "BopsKolCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + ", publisherId="
				+ publisherId + ", auditorId=" + auditorId + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", categoryDesc=" + categoryDesc + ", categoryProfileKey=" + categoryProfileKey + "]";
	}
    
}
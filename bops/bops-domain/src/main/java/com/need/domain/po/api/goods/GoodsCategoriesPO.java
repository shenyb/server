package com.need.domain.po.api.goods;

import java.io.Serializable;
import java.util.Date;

public class GoodsCategoriesPO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1172540820933018965L;

	private Integer categoryId;

    private String categoryName;

    private String categoryLevel;

    private Integer parentId;

    private String categoryStatus;

    private Date createTime;

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

    public String getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(String categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(String categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
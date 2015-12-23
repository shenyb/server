package com.need.domain.po.api.kol;

import java.io.Serializable;
import java.util.Date;

public class KolCategoryPO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -3511997668283422965L;

	private Integer categoryId;

    private String categoryName;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "KolCategoryPO [categoryId=" + categoryId + ", categoryName=" + categoryName + ", createTime="
				+ createTime + "]";
	}
    
}
package com.need.common.domain.po.api.kol;

import java.io.Serializable;
import java.util.Date;

public class KolCategoryPO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1821127241900211236L;

	private Integer categoryId;

    private String categoryName;

    private Date createTime;
    
    private String  categoryProfileKey;
    
    

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
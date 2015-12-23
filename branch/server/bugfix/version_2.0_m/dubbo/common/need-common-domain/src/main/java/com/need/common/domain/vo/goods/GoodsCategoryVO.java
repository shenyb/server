package com.need.common.domain.vo.goods;

import java.io.Serializable;

public class GoodsCategoryVO  implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 7770806048431491451L;
	private Integer categoryId;
	private String categoryName;
	private String categoryPicKey;
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
	public String getCategoryPicKey() {
		return categoryPicKey;
	}
	public void setCategoryPicKey(String categoryPicKey) {
		this.categoryPicKey = categoryPicKey;
	}
	
	
}

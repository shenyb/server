package com.need.common.domain.vo.goods;

import java.io.Serializable;
import java.util.List;

public class CategoryVO implements Serializable {
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1198504943969881969L;
	private Integer categoryId;
	private String categoryName;
	private String categoryPicKey;
	private List<GoodsCategoryVO> categoryList;
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
	public List<GoodsCategoryVO> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<GoodsCategoryVO> categoryList) {
		this.categoryList = categoryList;
	}
	
	
	
}

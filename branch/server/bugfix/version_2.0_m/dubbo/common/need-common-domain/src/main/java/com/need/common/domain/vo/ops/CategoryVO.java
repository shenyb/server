package com.need.common.domain.vo.ops;

import java.io.Serializable;
import java.util.List;

public class CategoryVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8113710908264772531L;
	
	private Integer categoryId;
	private String categoryName;
	private List<CategoryTopicVO> topicList;
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
	public List<CategoryTopicVO> getTopicList() {
		return topicList;
	}
	public void setTopicList(List<CategoryTopicVO> topicList) {
		this.topicList = topicList;
	}
	
	
	
}

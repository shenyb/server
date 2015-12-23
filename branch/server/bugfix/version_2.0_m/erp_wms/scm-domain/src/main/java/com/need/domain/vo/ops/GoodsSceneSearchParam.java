package com.need.domain.vo.ops;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class GoodsSceneSearchParam  extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1249362997915951109L;
	
	
	private String searchKey;

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	@Override
	public String toString() {
		return super.toString()+"GoodsSceneSearchParam [searchKey=" + searchKey + "]";
	}
	
	
	
	
}

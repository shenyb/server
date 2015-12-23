package com.need.domain.vo.ops;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class OpsTopicPage extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -3743844528266385834L;

	private String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	
}

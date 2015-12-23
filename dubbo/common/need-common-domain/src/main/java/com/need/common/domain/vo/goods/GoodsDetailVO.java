package com.need.common.domain.vo.goods;

import java.io.Serializable;
import java.util.List;

public class GoodsDetailVO implements Serializable {
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -8273430703495573195L;

	private List<String> picList;
	
	private String goodsDesc;

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	
}

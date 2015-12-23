package com.need.domain.vo.ops;

import java.io.Serializable;
import java.util.Arrays;

import javax.validation.constraints.NotNull;

import com.need.domain.pub.Page;

public class HotGoodsVO extends Page implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 169744315782275205L;
	@NotNull
	private String popularityId;
	private String goodsName;
	private String goodsId;
	private String goodsCode;
	private String [] goodsIds;
	
	
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String[] getGoodsIds() {
		return goodsIds;
	}
	public void setGoodsIds(String[] goodsIds) {
		this.goodsIds = goodsIds;
	}
	@NotNull
	private String goodsSort;
	
	public String getGoodsSort() {
		return goodsSort;
	}
	public void setGoodsSort(String goodsSort) {
		this.goodsSort = goodsSort;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getPopularityId() {
		return popularityId;
	}
	public void setPopularityId(String popularityId) {
		this.popularityId = popularityId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	@Override
	public String toString() {
		return "HotGoodsVO [popularityId=" + popularityId + ", goodsName=" + goodsName + ", goodsId=" + goodsId
				+ ", goodsCode=" + goodsCode + ", goodsIds=" + Arrays.toString(goodsIds) + ", goodsSort=" + goodsSort
				+ "]";
	}
	
}

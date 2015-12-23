package com.need.domain.vo.ops;

import java.io.Serializable;
import java.util.Arrays;

import com.need.domain.pub.Page;

public class SelectedGoodsVO extends Page implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7250884184758702245L;
	
	private String selectionId;
	private String goodsName;
	private String goodsId;
	private String goodsSort;
	private String goodsCode;
	
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsSort() {
		return goodsSort;
	}
	public void setGoodsSort(String goodsSort) {
		this.goodsSort = goodsSort;
	}
	private String [] goodsIds;
	
	
	
	
	
	
	
	
	public String[] getGoodsIds() {
		return goodsIds;
	}
	public void setGoodsIds(String[] goodsIds) {
		this.goodsIds = goodsIds;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getSelectionId() {
		return selectionId;
	}
	public void setSelectionId(String selectionId) {
		this.selectionId = selectionId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	@Override
	public String toString() {
		return "SelectedGoodsVO [selectionId=" + selectionId + ", goodsName=" + goodsName + ", goodsId=" + goodsId
				+ ", goodsSort=" + goodsSort + ", goodsCode=" + goodsCode + ", goodsIds=" + Arrays.toString(goodsIds)
				+ "]";
	}
	
}

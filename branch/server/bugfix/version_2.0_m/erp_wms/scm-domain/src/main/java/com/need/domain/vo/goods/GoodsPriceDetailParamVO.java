package com.need.domain.vo.goods;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class GoodsPriceDetailParamVO extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2167936009305023845L;

	private String goodsCode;
	private String goodsBarcode;
	private String goodsName;
	private String userId;
	
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsBarcode() {
		return goodsBarcode;
	}
	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "GoodsPriceDetailParamVO [goodsCode=" + goodsCode + ", goodsBarcode=" + goodsBarcode + ", goodsName="
				+ goodsName + ", userId=" + userId + "]";
	}
	
}

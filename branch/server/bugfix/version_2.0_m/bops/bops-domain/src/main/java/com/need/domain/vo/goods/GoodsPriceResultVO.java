package com.need.domain.vo.goods;

import java.io.Serializable;

public class GoodsPriceResultVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8198995363712660705L;
	
	private Integer pricechangeGoodsId;
	private String goodsId;
	private String goodsCode;
	private String goodsBarcode;
	private Double discountPrice;
	private Double originalPrice;
	private Double purchasePrice;
	private Double profit;
	private String goodsName;
	
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
	public Double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Double getProfit() {
		return profit;
	}
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getPricechangeGoodsId() {
		return pricechangeGoodsId;
	}
	public void setPricechangeGoodsId(Integer pricechangeGoodsId) {
		this.pricechangeGoodsId = pricechangeGoodsId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	@Override
	public String toString() {
		return "GoodsPriceResultVO [pricechangeGoodsId=" + pricechangeGoodsId + ", goodsId=" + goodsId + ", goodsCode="
				+ goodsCode + ", goodsBarcode=" + goodsBarcode + ", discountPrice=" + discountPrice + ", originalPrice="
				+ originalPrice + ", purchasePrice=" + purchasePrice + ", profit=" + profit + ", goodsName=" + goodsName
				+ "]";
	}
	
}

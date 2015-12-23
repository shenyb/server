package com.need.domain.vo.goods;

import java.io.Serializable;

public class GoodsPriceChangeVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1278823137335951216L;
	private String goodsCode;
	private String goodsId;
	private Double discountPrice;
	private Double originalPrice;
	private Double purchasePrice;
	private Integer historyPurchasePrice;
	private String goodsName;
	private Double profit;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
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
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Double getProfit() {
		return profit;
	}
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	public Integer getHistoryPurchasePrice() {
		return historyPurchasePrice;
	}
	public void setHistoryPurchasePrice(Integer historyPurchasePrice) {
		this.historyPurchasePrice = historyPurchasePrice;
	}
	@Override
	public String toString() {
		return "GoodsPriceChangeVO [goodsCode=" + goodsCode + ", goodsId=" + goodsId + ", discountPrice="
				+ discountPrice + ", originalPrice=" + originalPrice + ", purchasePrice=" + purchasePrice
				+ ", historyPurchasePrice=" + historyPurchasePrice + ", goodsName=" + goodsName + ", profit=" + profit
				+ ", message=" + message + "]";
	}
	
}

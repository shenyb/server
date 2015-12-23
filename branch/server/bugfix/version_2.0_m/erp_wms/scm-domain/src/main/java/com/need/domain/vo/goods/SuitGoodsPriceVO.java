package com.need.domain.vo.goods;
/**
 *套装商品的原价，折扣价
 * @author zhangmengbin
 *
 */
public class SuitGoodsPriceVO {
	private String skuId;//套装商品的id
	private double onsalePrice; //商品售价
	private double discountPrice;//商品折扣价
	private double purchasePrice;//采购价
	private double weight; //重量
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public double getOnsalePrice() {
		return onsalePrice;
	}
	public void setOnsalePrice(double onsalePrice) {
		this.onsalePrice = onsalePrice;
	}
	public double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}
	public double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	
	
}

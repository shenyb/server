package com.need.integration.common.haimeiSdk.domain.out;

public class OrderDet {
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getOrderLineType() {
		return orderLineType;
	}
	public void setOrderLineType(String orderLineType) {
		this.orderLineType = orderLineType;
	}
	public Double getDiscountFee() {
		return discountFee;
	}
	public void setDiscountFee(Double discountFee) {
		this.discountFee = discountFee;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * 购买单价
	 */
	Double sellPrice;
	String orderLineType;
	/**
	 * 优惠金额
	 */
	Double discountFee;
	Long shopId;
	/**
	 * 支付金额，购买单价＊数量－优惠＋运费
	 */
	Double payment;
	String productCode;
	String productName;
	Long ownerId;
	Long qty;
	String skuId;
	String brandName; 

}

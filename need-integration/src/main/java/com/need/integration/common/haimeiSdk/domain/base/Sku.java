package com.need.integration.common.haimeiSdk.domain.base;

import java.util.Date;

public class Sku {
	String skuId;
	String skuName;
	String skuImgUrl;
	Double length;
	Double width;
	Double height;
	String productCode;
	String productStockType;
	Long isKit;
	String category;
	String flammable;
	String explosive;
	String liquid;
	Double unitWeight;
	String unitName;
	Double unitSellPrice;
	Double unitDiscountPrice;
	Double unitCost;
	Double unitPurchaseCost;
	String barcode;
	Long shopId ; 
	Long ownerId ; 
	Long qty;
	
	private String supplierProductCode ;
	
	private Long returnQty ; 
	
	private Long totalCount ; 
	
	private Date lastReceivedDate ; 
	
	private Long backQty ; 
	
	private Long qualifiedQty ; 
	
	private Long unqualifiedQty;
	
	private Long qualifiedCount ; 
	
	private Long unqualifiedCount ; 
	
	public Long getBackQty() {
		return backQty;
	}

	public void setBackQty(Long backQty) {
		this.backQty = backQty;
	}

	public Long getQualifiedQty() {
		return qualifiedQty;
	}

	public void setQualifiedQty(Long qualifiedQty) {
		this.qualifiedQty = qualifiedQty;
	}

	public Long getUnqualifiedQty() {
		return unqualifiedQty;
	}

	public void setUnqualifiedQty(Long unqualifiedQty) {
		this.unqualifiedQty = unqualifiedQty;
	}
	
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
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
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getSkuImgUrl() {
		return skuImgUrl;
	}
	public void setSkuImgUrl(String skuImgUrl) {
		this.skuImgUrl = skuImgUrl;
	}
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductStockType() {
		return productStockType;
	}
	public void setProductStockType(String productStockType) {
		this.productStockType = productStockType;
	}
	public Long getIsKit() {
		return isKit;
	}
	public void setIsKit(Long isKit) {
		this.isKit = isKit;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFlammable() {
		return flammable;
	}
	public void setFlammable(String flammable) {
		this.flammable = flammable;
	}
	public String getExplosive() {
		return explosive;
	}
	public void setExplosive(String explosive) {
		this.explosive = explosive;
	}
	public String getLiquid() {
		return liquid;
	}
	public void setLiquid(String liquid) {
		this.liquid = liquid;
	}
	public Double getUnitWeight() {
		return unitWeight;
	}
	public void setUnitWeight(Double unitWeight) {
		this.unitWeight = unitWeight;
	}
	public Double getUnitSellPrice() {
		return unitSellPrice;
	}
	public void setUnitSellPrice(Double unitSellPrice) {
		this.unitSellPrice = unitSellPrice;
	}
	public Double getUnitDiscountPrice() {
		return unitDiscountPrice;
	}
	public void setUnitDiscountPrice(Double unitDiscountPrice) {
		this.unitDiscountPrice = unitDiscountPrice;
	}
	public Double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
	public Double getUnitPurchaseCost() {
		return unitPurchaseCost;
	}
	public void setUnitPurchaseCost(Double unitPurchaseCost) {
		this.unitPurchaseCost = unitPurchaseCost;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Date getLastReceivedDate() {
		return lastReceivedDate;
	}

	public void setLastReceivedDate(Date lastReceivedDate) {
		this.lastReceivedDate = lastReceivedDate;
	}

	public Long getQualifiedCount() {
		return qualifiedCount;
	}

	public void setQualifiedCount(Long qualifiedCount) {
		this.qualifiedCount = qualifiedCount;
	}

	public Long getUnqualifiedCount() {
		return unqualifiedCount;
	}

	public void setUnqualifiedCount(Long unqualifiedCount) {
		this.unqualifiedCount = unqualifiedCount;
	}

	public String getSupplierProductCode() {
		return supplierProductCode;
	}

	public void setSupplierProductCode(String supplierProductCode) {
		this.supplierProductCode = supplierProductCode;
	}

	public Long getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(Long returnQty) {
		this.returnQty = returnQty;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}

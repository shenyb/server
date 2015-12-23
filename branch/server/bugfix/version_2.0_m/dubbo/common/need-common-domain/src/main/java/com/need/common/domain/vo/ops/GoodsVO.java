package com.need.common.domain.vo.ops;

public class GoodsVO {
	
	private String goodsId;
	private String goodsName;
	private int onsalePrice;
	private int discountPrice;
	private String brief;
	private String isGlobal;
	private String topPicKey;
	private String warehouseType;
	private String opsName;
	
	
	
	
	
   
	public String getOpsName() {
		return opsName;
	}
	public void setOpsName(String opsName) {
		this.opsName = opsName;
	}
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getOnsalePrice() {
		return onsalePrice;
	}
	public void setOnsalePrice(int onsalePrice) {
		this.onsalePrice = onsalePrice;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getIsGlobal() {
		return isGlobal;
	}
	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
	}
	public String getTopPicKey() {
		return topPicKey;
	}
	public void setTopPicKey(String topPicKey) {
		this.topPicKey = topPicKey;
	}
	@Override
	public String toString() {
		return "GoodsVO [goodsId=" + goodsId + ", goodsName=" + goodsName + ", onsalePrice=" + onsalePrice
				+ ", discountPrice=" + discountPrice + ", brief=" + brief + ", isGlobal=" + isGlobal + ", topPicKey="
				+ topPicKey + "]";
	}

}

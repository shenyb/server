package com.need.common.domain.vo.goods;

import java.io.Serializable;

public class GoodsVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4200307771815648284L;

	private String goodsName;
	
	private String goodsId;
	
	private String topPicKeys;
	
	private String brief;
	
	private Integer onsalePrice;
	
	private Integer discountPrice;
	
	private String topPicKey;
	
	private String warehouseType;//仓库类型
	
	

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getTopPicKeys() {
		return topPicKeys;
	}

	public void setTopPicKeys(String topPicKeys) {
		this.topPicKeys = topPicKeys;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Integer getOnsalePrice() {
		return onsalePrice;
	}

	public void setOnsalePrice(Integer onsalePrice) {
		this.onsalePrice = onsalePrice;
	}

	public Integer getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getTopPicKey() {
		return topPicKey;
	}

	public void setTopPicKey(String topPicKey) {
		this.topPicKey = topPicKey;
	}
   
	
	
	
}

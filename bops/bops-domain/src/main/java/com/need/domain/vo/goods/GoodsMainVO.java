package com.need.domain.vo.goods;

public class GoodsMainVO {
	
	private String goodsId;
	
	private String goodsCode;
	
	private String goodsName;
	
	private String topPicKeys;
	
	private Integer storeCount;
	
	private Integer soldCount;
	

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public Integer getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(Integer storeCount) {
		this.storeCount = storeCount;
	}

	public Integer getSoldCount() {
		return soldCount;
	}

	public void setSoldCount(Integer soldCount) {
		this.soldCount = soldCount;
	}

	public String getTopPicKeys() {
		return topPicKeys;
	}

	public void setTopPicKeys(String topPicKeys) {
		this.topPicKeys = topPicKeys;
	}

	@Override
	public String toString() {
		return "GoodsMainVO [goodsId=" + goodsId + ", goodsCode=" + goodsCode + ", goodsName=" + goodsName
				+ ", topPicKeys=" + topPicKeys + ", storeCount=" + storeCount + ", soldCount=" + soldCount + "]";
	}
	
}

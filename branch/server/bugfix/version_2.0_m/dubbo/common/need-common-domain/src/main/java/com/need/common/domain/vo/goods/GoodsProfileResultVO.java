package com.need.common.domain.vo.goods;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GoodsProfileResultVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4268355778045664346L;
	
	private String goodsId;
	
	private String goodsName;
	
	private int onsalePrice;
	
	private String brief;
	
	private String isGlobal;
	
	private int discountPrice;
	
	private String[] topPicKeys;
	
	private String topPicKeyString;
	
	private String isNeed;
	
	private String isSoldout;
	
	private String goodsStatus;//商品的上下架状态
	
	private String scenePicKey;//商品场景主图;
	
	private String warehouseType;//仓库类型
    
    private int amount;//满多少包邮
	
	private String isShare;
	
	private String isNew;
	
	private Date goodsOnlineTime;

	private String goodsType;//goodsTypeEnums
	
	
	private String goodsDesc;
	
	private List<String> picList;
	
	private String size;
	private String originPlace;
	private String color;
	private String weight;
	
	

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getOriginPlace() {
		return originPlace;
	}

	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Date getGoodsOnlineTime() {
		return goodsOnlineTime;
	}

	public void setGoodsOnlineTime(Date goodsOnlineTime) {
		this.goodsOnlineTime = goodsOnlineTime;
	}


	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getIsShare() {
		return isShare;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
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

	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getIsNeed() {
		return isNeed;
	}

	public void setIsNeed(String isNeed) {
		this.isNeed = isNeed;
	}

	public String getTopPicKeyString() {
		return topPicKeyString;
	}

	public void setTopPicKeyString(String topPicKeyString) {
		this.topPicKeyString = topPicKeyString;
	}

	public String[] getTopPicKeys() {
		return topPicKeys;
	}

	public void setTopPicKeys(String[] topPicKeys) {
		this.topPicKeys = topPicKeys;
	}

	public int getOnsalePrice() {
		return onsalePrice;
	}

	public void setOnsalePrice(int onsalePrice) {
		this.onsalePrice = onsalePrice;
	}

	public String getIsSoldout() {
		return isSoldout;
	}

	public void setIsSoldout(String isSoldout) {
		this.isSoldout = isSoldout;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getScenePicKey() {
		return scenePicKey;
	}

	public void setScenePicKey(String scenePicKey) {
		this.scenePicKey = scenePicKey;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

	@Override
	public String toString() {
		return "GoodsProfileResultVO [goodsId=" + goodsId + ", goodsName=" + goodsName + ", onsalePrice=" + onsalePrice
				+ ", brief=" + brief + ", isGlobal=" + isGlobal + ", discountPrice=" + discountPrice + ", topPicKeys="
				+ Arrays.toString(topPicKeys) + ", topPicKeyString=" + topPicKeyString + ", isNeed=" + isNeed
				+ ", isSoldout=" + isSoldout + ", goodsStatus=" + goodsStatus + ", scenePicKey=" + scenePicKey
				+ ", warehouseType=" + warehouseType + ", amount=" + amount + "]";
	}
	
}

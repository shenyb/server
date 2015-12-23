package com.need.domain.vo.goodsgroup;

import java.io.Serializable;
import java.util.Date;

public class GoodsShowVO implements Serializable {
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1864018773455231760L;
	private String goodsId;
	private String goodsName;
	private String scenePicKey;
	private Integer discountPrice;
	private Integer onsalePrice;
	private String warehouseType;
	private String isSoldout;
	private Integer storeCount;
	private String goodsStatus;
	private Date updateTime;
	
	
	
	
	
	
	
	
	
	
	

	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getGoodsStatus() {
		return goodsStatus;
	}
	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}
	public Integer getStoreCount() {
		return storeCount;
	}
	public void setStoreCount(Integer storeCount) {
		this.storeCount = storeCount;
	}
    
	public String getIsSoldout() {
		return isSoldout;
	}
	public void setIsSoldout(String isSoldout) {
		this.isSoldout = isSoldout;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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
   
	
   
    
	public String getScenePicKey() {
		return scenePicKey;
	}
	public void setScenePicKey(String scenePicKey) {
		this.scenePicKey = scenePicKey;
	}
	public Integer getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Integer getOnsalePrice() {
		return onsalePrice;
	}
	public void setOnsalePrice(Integer onsalePrice) {
		this.onsalePrice = onsalePrice;
	}
	@Override
	public String toString() {
		return "GoodsShowVO [goodsId=" + goodsId + ", goodsName=" + goodsName + ", scenePicKey=" + scenePicKey
				+ ", discountPrice=" + discountPrice + ", onsalePrice=" + onsalePrice + ", warehouseType="
				+ warehouseType + ", isSoldout=" + isSoldout + ", storeCount=" + storeCount + ", goodsStatus="
				+ goodsStatus + ", updateTime=" + updateTime + "]";
	}
	
	
	
	
}

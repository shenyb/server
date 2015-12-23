package com.need.common.domain.po.api.goods;

import java.io.Serializable;
import java.util.Date;

public class GoodsMainPO implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 804648369802966734L;

	private String goodsId;

	private String goodsName;

	private String goodsStatus;

	private Integer sceneId;

	private String brief;

	private String topPicKeys;

	private Integer discountPrice;

	private Integer onsalePrice;

	private Integer storeCount;

	private Date createTime;

	private Date updateTime;

	private String goodsCode;

	private String oppseReason;

	private String scenePicKey;
	
	private int lockCount;// 库存锁定数量
	
	private String isGlobal;//是否是保税仓 TRUE/FALSE
	
	private String warehouseType;//仓库类型
	
	private String isShare;//是否为分销
	
	private String goodsType;//GoodsTypeEnums
	

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getIsShare() {
		return isShare;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}

	public int getLockCount() {
		return lockCount;
	}

	public void setLockCount(int lockCount) {
		this.lockCount = lockCount;
	}

	public String getScenePicKey() {
		return scenePicKey;
	}

	public void setScenePicKey(String scenePicKey) {
		this.scenePicKey = scenePicKey;
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

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getTopPicKeys() {
		return topPicKeys;
	}

	public void setTopPicKeys(String topPicKeys) {
		this.topPicKeys = topPicKeys;
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

	public Integer getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(Integer storeCount) {
		this.storeCount = storeCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getOppseReason() {
		return oppseReason;
	}

	public void setOppseReason(String oppseReason) {
		this.oppseReason = oppseReason;
	}

	public String getIsGlobal() {
		return isGlobal;
	}

	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	@Override
	public String toString() {
		return "GoodsMainPO [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsStatus=" + goodsStatus
				+ ", sceneId=" + sceneId + ", brief=" + brief + ", topPicKeys=" + topPicKeys + ", discountPrice="
				+ discountPrice + ", onsalePrice=" + onsalePrice + ", storeCount=" + storeCount + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", goodsCode=" + goodsCode + ", oppseReason="
				+ oppseReason + ", scenePicKey=" + scenePicKey + ", lockCount=" + lockCount + ", isGlobal=" + isGlobal
				+ ", warehouseType=" + warehouseType + "]";
	}

}
package com.need.integration.dao.jdbc.api.goods.po;

import java.util.Date;

public class GoodsMainPO {
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

	private String goodsBarcode;

	private String isGlobal;// 是否是保税仓 TRUE/FALSE

	// 国捡和海关计量单位
	public String guojianCount;
	public String haiguanCount;

	// 原产国编码
	private String guojianCountryCode;
	private String haiguanCountryCode;
	
	private Integer goodsCategoryId;//商品分类类型
	
	private String warehouseType;//仓库类型

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getGuojianCount() {
		return guojianCount;
	}

	public void setGuojianCount(String guojianCount) {
		this.guojianCount = guojianCount;
	}

	public String getHaiguanCount() {
		return haiguanCount;
	}

	public void setHaiguanCount(String haiguanCount) {
		this.haiguanCount = haiguanCount;
	}

	public String getGuojianCountryCode() {
		return guojianCountryCode;
	}

	public void setGuojianCountryCode(String guojianCountryCode) {
		this.guojianCountryCode = guojianCountryCode;
	}

	public String getHaiguanCountryCode() {
		return haiguanCountryCode;
	}

	public void setHaiguanCountryCode(String haiguanCountryCode) {
		this.haiguanCountryCode = haiguanCountryCode;
	}

	public String getGoodsBarcode() {
		return goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
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

    /**
     * @return the goodsCategoryId
     */
    public Integer getGoodsCategoryId() {
        return goodsCategoryId;
    }

    /**
     * @param goodsCategoryId the goodsCategoryId to set
     */
    public void setGoodsCategoryId(Integer goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

	@Override
	public String toString() {
		return "GoodsMainPO [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsStatus=" + goodsStatus
				+ ", sceneId=" + sceneId + ", brief=" + brief + ", topPicKeys=" + topPicKeys + ", discountPrice="
				+ discountPrice + ", onsalePrice=" + onsalePrice + ", storeCount=" + storeCount + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", goodsCode=" + goodsCode + ", oppseReason="
				+ oppseReason + ", scenePicKey=" + scenePicKey + ", lockCount=" + lockCount + ", goodsBarcode="
				+ goodsBarcode + ", isGlobal=" + isGlobal + ", goodsCategoryId=" + goodsCategoryId + "]";
	}

}
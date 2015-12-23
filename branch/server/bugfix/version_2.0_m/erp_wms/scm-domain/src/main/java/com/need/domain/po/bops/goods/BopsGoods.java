package com.need.domain.po.bops.goods;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class BopsGoods implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 719902853928093799L;

	private String goodsId;

	private String goodsName;

	private String goodsStatus;

	private Integer sceneId;

	private String brief;

	private String topPicKeys;

	private Integer discountPrice;

	private Integer onsalePrice;

	private Date createTime;

	private Date updateTime;

	private String auditStatus;

	private Integer publisherId;

	private Integer auditorId;

	private String opposeReason;

	private String goodsCode;

	private String scenePicKey;

	private String[] goodsTopPics;

	private String goodsBarcode;

	private String isGlobal;// 是否是保税仓 TRUE/FALSE

	private String haiguanCount;// 海关计量单位

	private String guojianCount;// 国检计量单位
	
	private String haiguanCountryCode;//海关国家编码
	
	private String guojianCountryCode;// 国检国家编码
	
	private String haiguanGoodsPlace;//海关商品产地编码
	
	private String guojianGoodsPlace;//国检商品产地编码
	
	private String warehouseType;//仓库类型
	
	private Integer goodsCategoryId;//商品分类类型
	
	private Integer purchasePrice;//进货价
	
	private String purchaseManager;//采购经理
	
	private String purchaseLeader;//采购主管
	
	private String shortName;//短名称
	
	
	private Integer brandId;//品牌ID
	
	private String isValidDate; //是否有有效期
	
	private String isGift; //是否是赠品
	
	private Integer validDate; //有效期
	
	private String goodsType;//商品类型
	
	private Integer goodsIndexCategory; //商品检索分类
	
	private Integer updateOnlineId;
	
	private Date updateOnlineTime;
	
	public Integer getUpdateOnlineId() {
		return updateOnlineId;
	}

	public void setUpdateOnlineId(Integer updateOnlineId) {
		this.updateOnlineId = updateOnlineId;
	}

	public Date getUpdateOnlineTime() {
		return updateOnlineTime;
	}

	public void setUpdateOnlineTime(Date updateOnlineTime) {
		this.updateOnlineTime = updateOnlineTime;
	}

	public Integer getGoodsIndexCategory() {
		return goodsIndexCategory;
	}

	public void setGoodsIndexCategory(Integer goodsIndexCategory) {
		this.goodsIndexCategory = goodsIndexCategory;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getIsValidDate() {
		return isValidDate;
	}

	public void setIsValidDate(String isValidDate) {
		this.isValidDate = isValidDate;
	}



	public String getIsGift() {
		return isGift;
	}

	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}

	public Integer getValidDate() {
		return validDate;
	}

	public void setValidDate(Integer validDate) {
		this.validDate = validDate;
	}

	public Integer getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Integer purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getPurchaseManager() {
		return purchaseManager;
	}

	public void setPurchaseManager(String purchaseManager) {
		this.purchaseManager = purchaseManager;
	}

	public String getPurchaseLeader() {
		return purchaseLeader;
	}

	public void setPurchaseLeader(String purchaseLeader) {
		this.purchaseLeader = purchaseLeader;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getGoodsBarcode() {
		return goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	public String[] getGoodsTopPics() {
		return goodsTopPics;
	}

	public void setGoodsTopPics(String[] goodsTopPics) {
		this.goodsTopPics = goodsTopPics;
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

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public String getOpposeReason() {
		return opposeReason;
	}

	public void setOpposeReason(String opposeReason) {
		this.opposeReason = opposeReason;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getIsGlobal() {
		return isGlobal;
	}

	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
	}

	public String getHaiguanCount() {
		return haiguanCount;
	}

	public void setHaiguanCount(String haiguanCount) {
		this.haiguanCount = haiguanCount;
	}

	public String getGuojianCount() {
		return guojianCount;
	}

	public void setGuojianCount(String guojianCount) {
		this.guojianCount = guojianCount;
	}
	
	public String getHaiguanCountryCode() {
		return haiguanCountryCode;
	}

	public void setHaiguanCountryCode(String haiguanCountryCode) {
		this.haiguanCountryCode = haiguanCountryCode;
	}

	public String getGuojianCountryCode() {
		return guojianCountryCode;
	}

	public void setGuojianCountryCode(String guojianCountryCode) {
		this.guojianCountryCode = guojianCountryCode;
	}

	public String getHaiguanGoodsPlace() {
		return haiguanGoodsPlace;
	}

	public void setHaiguanGoodsPlace(String haiguanGoodsPlace) {
		this.haiguanGoodsPlace = haiguanGoodsPlace;
	}

	public String getGuojianGoodsPlace() {
		return guojianGoodsPlace;
	}

	public void setGuojianGoodsPlace(String guojianGoodsPlace) {
		this.guojianGoodsPlace = guojianGoodsPlace;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public Integer getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(Integer goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	@Override
	public String toString() {
		return "BopsGoods [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsStatus=" + goodsStatus
				+ ", sceneId=" + sceneId + ", brief=" + brief + ", topPicKeys=" + topPicKeys + ", discountPrice="
				+ discountPrice + ", onsalePrice=" + onsalePrice + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", auditStatus=" + auditStatus + ", publisherId=" + publisherId + ", auditorId="
				+ auditorId + ", opposeReason=" + opposeReason + ", goodsCode=" + goodsCode + ", scenePicKey="
				+ scenePicKey + ", goodsTopPics=" + Arrays.toString(goodsTopPics) + ", goodsBarcode=" + goodsBarcode
				+ ", isGlobal=" + isGlobal + ", haiguanCount=" + haiguanCount + ", guojianCount=" + guojianCount
				+ ", haiguanCountryCode=" + haiguanCountryCode + ", guojianCountryCode=" + guojianCountryCode
				+ ", haiguanGoodsPlace=" + haiguanGoodsPlace + ", guojianGoodsPlace=" + guojianGoodsPlace
				+ ", warehouseType=" + warehouseType + ", goodsCategoryId=" + goodsCategoryId + "]";
	}

}
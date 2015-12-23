package com.need.domain.vo.goods;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class GoodsStoreDetailVO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 3443340229600296700L;

	private String goodsId;

	@NotNull(message = "商品名称不能为空")
	private String goodsName;

	private String goodsStatus;

	private Integer sceneId;

	@NotNull(message = "商品简介不能为空")
	private String brief;

	private String[] topPicKeys;
	
	private String topPicKeysString;

	private Double discountPrice;

	private Double onsalePrice;

	private Date createTime;

	private Date updateTime;

	private String auditStatus;

	private Integer publisherId;

	private Integer auditorId;

	private String opposeReason;

	private String goodsCode;

	private String size;
	private String color;
	private String originPlace;
	private String weight;

	private String goodsDesc;

	private String[] detailPicKeys;

	private String detailPicKeysString;
	
	private String[] goodsTopPics;

	private String goodsBarcode;

	private String isGlobal;// 是否是保税仓 TRUE/FALSE

	private String haiguanCount;// 海关计量单位

	private String guojianCount;// 国检计量单位

	private String haiguanCountryCode;// 海关国家编码

	private String guojianCountryCode;// 国检国家编码
	
	private String haiguanGoodsPlace;//海关商品产地编码
	
	private String guojianGoodsPlace;//国检商品产地编码
	
	private String scenePicKey;

	private String goodsParams;
	
	private String warehouseType;//仓库类型
	
	private Integer goodsCategoryId;//商品分类类型
	
	private Double purchasePrice;//进货价
	
	private String purchaseManager;//采购经理
	
	private String purchaseLeader;//采购主管
	
	private String shortName;//短名称
	
    private Integer brandId;//品牌ID
	
	private String isValidDate; //是否有有效期
	
	private String isGift; //是否是赠品
	
	private Integer validDate; //有效期
	
	private Integer goodsCategoryIdOne; //一级
	
	private Integer goodsCategoryIdTwo; //二级
	
	private String goodsType;//商品类型
	
	private Integer nowStoreCount;//库存

	public Integer getNowStoreCount() {
		return nowStoreCount;
	}

	public void setNowStoreCount(Integer nowStoreCount) {
		this.nowStoreCount = nowStoreCount;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public Integer getGoodsCategoryIdOne() {
		return goodsCategoryIdOne;
	}

	public void setGoodsCategoryIdOne(Integer goodsCategoryIdOne) {
		this.goodsCategoryIdOne = goodsCategoryIdOne;
	}

	public Integer getGoodsCategoryIdTwo() {
		return goodsCategoryIdTwo;
	}

	public void setGoodsCategoryIdTwo(Integer goodsCategoryIdTwo) {
		this.goodsCategoryIdTwo = goodsCategoryIdTwo;
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

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
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

	public String getGoodsParams() {
		return goodsParams;
	}

	public void setGoodsParams(String goodsParams) {
		this.goodsParams = goodsParams;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOriginPlace() {
		return originPlace;
	}

	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String[] getTopPicKeys() {
		return topPicKeys;
	}

	public void setTopPicKeys(String[] topPicKeys) {
		this.topPicKeys = topPicKeys;
	}

	public String[] getDetailPicKeys() {
		return detailPicKeys;
	}

	public void setDetailPicKeys(String[] detailPicKeys) {
		this.detailPicKeys = detailPicKeys;
	}

	public String getScenePicKey() {
		return scenePicKey;
	}

	public void setScenePicKey(String scenePicKey) {
		this.scenePicKey = scenePicKey;
	}

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Double getOnsalePrice() {
		return onsalePrice;
	}

	public void setOnsalePrice(Double onsalePrice) {
		this.onsalePrice = onsalePrice;
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


	public String getDetailPicKeysString() {
		return detailPicKeysString;
	}

	public void setDetailPicKeysString(String detailPicKeysString) {
		this.detailPicKeysString = detailPicKeysString;
	}

	public String getTopPicKeysString() {
		return topPicKeysString;
	}

	public void setTopPicKeysString(String topPicKeysString) {
		this.topPicKeysString = topPicKeysString;
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
		return "GoodsStoreDetailVO [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsStatus=" + goodsStatus
				+ ", sceneId=" + sceneId + ", brief=" + brief + ", topPicKeys=" + Arrays.toString(topPicKeys)
				+ ", topPicKeysString=" + topPicKeysString + ", discountPrice=" + discountPrice + ", onsalePrice="
				+ onsalePrice + ", createTime=" + createTime + ", updateTime=" + updateTime + ", auditStatus="
				+ auditStatus + ", publisherId=" + publisherId + ", auditorId=" + auditorId + ", opposeReason="
				+ opposeReason + ", goodsCode=" + goodsCode + ", size=" + size + ", color=" + color + ", originPlace="
				+ originPlace + ", weight=" + weight + ", goodsDesc=" + goodsDesc + ", detailPicKeys="
				+ Arrays.toString(detailPicKeys) + ", detailPicKeysString=" + detailPicKeysString + ", goodsTopPics="
				+ Arrays.toString(goodsTopPics) + ", goodsBarcode=" + goodsBarcode + ", isGlobal=" + isGlobal
				+ ", haiguanCount=" + haiguanCount + ", guojianCount=" + guojianCount + ", haiguanCountryCode="
				+ haiguanCountryCode + ", guojianCountryCode=" + guojianCountryCode + ", haiguanGoodsPlace="
				+ haiguanGoodsPlace + ", guojianGoodsPlace=" + guojianGoodsPlace + ", scenePicKey=" + scenePicKey
				+ ", goodsParams=" + goodsParams + ", warehouseType=" + warehouseType + ", goodsCategoryId="
				+ goodsCategoryId + "]";
	}

}

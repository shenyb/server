package com.need.domain.vo.goods;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class GoodsPageVO extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2037869619825888093L;
	
	private String goodsCode;
	
	private String goodsName;
	
	private String goodsStatus;
	
	private String auditStatus;
	
	private String goodsBarcode;
	
	private String isGlobal;

	private String warehouseType;//仓库类型
	
	private String shortName;//短名称
	
	private Double purchasePrice;//进货价
	
	private String purchaseManager;//采购经理
	
	private String purchaseLeader;//采购主管
	
    private Integer brandId;//品牌ID
	
	private String isValidDate; //是否有有效期
	
	private String isGift; //是否是赠品
	
	private Integer validDate; //有效期
	
	
	private String goodsType;//商品类型
	
	private String goodsIndexCategory;//商品二级分类
	private String goodsIndexCategoryOne;//商品一级分类
	
	


	

	public String getGoodsIndexCategoryOne() {
		return goodsIndexCategoryOne;
	}

	public void setGoodsIndexCategoryOne(String goodsIndexCategoryOne) {
		this.goodsIndexCategoryOne = goodsIndexCategoryOne;
	}

	public String getGoodsIndexCategory() {
		return goodsIndexCategory;
	}

	public void setGoodsIndexCategory(String goodsIndexCategory) {
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

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getGoodsBarcode() {
		return goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
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
		return "GoodsPageVO [goodsCode=" + goodsCode + ", goodsName=" + goodsName + ", goodsStatus=" + goodsStatus
				+ ", auditStatus=" + auditStatus + ", goodsBarcode=" + goodsBarcode + ", isGlobal=" + isGlobal
				+ ", warehouseType=" + warehouseType + "]";
	}

}

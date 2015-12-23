package com.need.domain.vo.wms;

import java.io.Serializable;

public class ErpSkuToWmsVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 7574003845542121978L;
	
	private Long id;
	private Long productId;
	private String chineseName;
	private String skuStandard;
	private String producingArea;
	private String oneCategory;
	private String twoCategory;
	private String threeCategory;
	private String fourCategory;
	private String activeFlg;
	private Long retailPrice;
	private Integer qualityDate;
	private String qualityUnit;
	private String qualityDateFlg;
	private String sPackBarcode;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getSkuStandard() {
		return skuStandard;
	}
	public void setSkuStandard(String skuStandard) {
		this.skuStandard = skuStandard;
	}
	public String getProducingArea() {
		return producingArea;
	}
	public void setProducingArea(String producingArea) {
		this.producingArea = producingArea;
	}
	public String getOneCategory() {
		return oneCategory;
	}
	public void setOneCategory(String oneCategory) {
		this.oneCategory = oneCategory;
	}
	public String getTwoCategory() {
		return twoCategory;
	}
	public void setTwoCategory(String twoCategory) {
		this.twoCategory = twoCategory;
	}
	public String getThreeCategory() {
		return threeCategory;
	}
	public void setThreeCategory(String threeCategory) {
		this.threeCategory = threeCategory;
	}
	public String getFourCategory() {
		return fourCategory;
	}
	public void setFourCategory(String fourCategory) {
		this.fourCategory = fourCategory;
	}
	public String getActiveFlg() {
		return activeFlg;
	}
	public void setActiveFlg(String activeFlg) {
		this.activeFlg = activeFlg;
	}
	public Long getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(Long retailPrice) {
		this.retailPrice = retailPrice;
	}
	public Integer getQualityDate() {
		return qualityDate;
	}
	public void setQualityDate(Integer qualityDate) {
		this.qualityDate = qualityDate;
	}
	public String getQualityUnit() {
		return qualityUnit;
	}
	public void setQualityUnit(String qualityUnit) {
		this.qualityUnit = qualityUnit;
	}
	public String getQualityDateFlg() {
		return qualityDateFlg;
	}
	public void setQualityDateFlg(String qualityDateFlg) {
		this.qualityDateFlg = qualityDateFlg;
	}
	public String getsPackBarcode() {
		return sPackBarcode;
	}
	public void setsPackBarcode(String sPackBarcode) {
		this.sPackBarcode = sPackBarcode;
	}
	@Override
	public String toString() {
		return "ErpSkuToWmsVO [id=" + id + ", productId=" + productId + ", chineseName=" + chineseName
				+ ", skuStandard=" + skuStandard + ", producingArea=" + producingArea + ", oneCategory=" + oneCategory
				+ ", twoCategory=" + twoCategory + ", threeCategory=" + threeCategory + ", fourCategory=" + fourCategory
				+ ", activeFlg=" + activeFlg + ", retailPrice=" + retailPrice + ", qualityDate=" + qualityDate
				+ ", qualityUnit=" + qualityUnit + ", qualityDateFlg=" + qualityDateFlg + ", sPackBarcode="
				+ sPackBarcode + "]";
	}
	
}

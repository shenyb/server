package com.need.domain.vo.distribution;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class DistributionPageVO extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4889137845557318129L;

	private String goodsName;
	private String creatorName;
	private String distributionStatus;
	private String goodsCode;
	private String mobile;
	private String minAmount;
	private String maxAmount;
	private String warehouseType;
	
	
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}
	
	public String getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getDistributionStatus() {
		return distributionStatus;
	}
	public void setDistributionStatus(String distributionStatus) {
		this.distributionStatus = distributionStatus;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "DistributionPageVO [goodsName=" + goodsName + ", creatorName=" + creatorName + ", distributionStatus="
				+ distributionStatus + ", goodsCode=" + goodsCode + ", mobile=" + mobile + ", minAmount=" + minAmount
				+ ", maxAmount=" + maxAmount + ", warehouseType=" + warehouseType + "]";
	}
	
	
	
}

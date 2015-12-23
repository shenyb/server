package com.need.integration.common.haimeiSdk.domain.in;

import java.util.List;

import com.need.integration.common.haimeiSdk.domain.base.PrReturnDet;

public class PrReturnHeader {
	private Long stockId ; 
	
	private Long shopId ; 
	
	private Long ownerId ;
	
	private Long supplierId ; 
	
	private String receiverProvince ; 
	
	private String receiverCity ; 
	
	private String receiverCounty ; 
	
	private String receiverAddress ; 
	
	private Long transporterId ;
	
	private String qualityType ;
	
	private String receiverName ; 
	
	private String isOutStock ; 
	
	private String platReturnType ;
	
	private String returnType ; 
	
	private Long serviceUkid ; 
	
	private String receiverZip ; 
	
	private String receiverMobile ; 
	
	private String receiverPhone ; 
	
	private String remark ; 
	
	private String platformReturnId;
	
	private List<PrReturnDet> skus ;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getReceiverProvince() {
		return receiverProvince;
	}

	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverCounty() {
		return receiverCounty;
	}

	public void setReceiverCounty(String receiverCounty) {
		this.receiverCounty = receiverCounty;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public Long getTransporterId() {
		return transporterId;
	}

	public void setTransporterId(Long transporterId) {
		this.transporterId = transporterId;
	}

	public String getQualityType() {
		return qualityType;
	}

	public void setQualityType(String qualityType) {
		this.qualityType = qualityType;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getIsOutStock() {
		return isOutStock;
	}

	public void setIsOutStock(String isOutStock) {
		this.isOutStock = isOutStock;
	}

	public String getPlatReturnType() {
		return platReturnType;
	}

	public void setPlatReturnType(String platReturnType) {
		this.platReturnType = platReturnType;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public Long getServiceUkid() {
		return serviceUkid;
	}

	public void setServiceUkid(Long serviceUkid) {
		this.serviceUkid = serviceUkid;
	}

	public String getReceiverZip() {
		return receiverZip;
	}

	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPlatformReturnId() {
		return platformReturnId;
	}

	public void setPlatformReturnId(String platformReturnId) {
		this.platformReturnId = platformReturnId;
	}

	public List<PrReturnDet> getSkus() {
		return skus;
	}

	public void setSkus(List<PrReturnDet> skus) {
		this.skus = skus;
	} 
	


}

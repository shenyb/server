package com.need.domain.vo.purchase;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.need.domain.pub.Page;

public class BopsPurchaseVO extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -8472735744442733481L;
	    private Integer purchaseId;
	    
	    private String receiveNo;
	    
	    private String goodsId;
	    
	    private String createTimeStart;
	    
	    private String createTimeEnd;

	    private String purchaseStatus;

	    private Integer providerId;
	    
	    private String providerName;

	    private String warehouseId;
	    
	    private String warehouseName;

	    private String  createTime;
	    
	    private String havestTime;

	    private String deliveryTime;

	    private Integer purchaseUserId;
	    
	    private String purchaseUserName;

	    private String purchaseDepartment;

	    private String purchaseIsHavest;

	    private String havestUserid;
	    
	    private String havestUsername;

	    private String purchaseIsDifferent;

	    private Integer havestCount;

	    private Integer havestDisability;

	    private Integer havestNormal;

	    private double purchasePriceAll;

	    private Integer purchaseCountAll;

	    private double purchasePriceReal;

	    private Integer purchaseCountReal;

	    private String purchaseFreight;

	    private double differencePrice;

	    private Integer differenceCount;

	    private String purchaseCurrency;

	    private String purchaseRemark;

	    private String purchasePriceOther; //其他费用
	    
	    private Integer purchaseInfoId;

	    private Integer purchasePrice;
	    
	    private Double purchasePrice2; //用于金额转换的商品单价

	    private Integer purchaseCount;

	    private String goodsCode;

	    private String goodsBarcode;

	    private String goodsName;

	    private Integer havestInfoCount;

	    private double havestInfoPrice;

	    private Integer havestInfoDisability;

	    private Integer havestInfoNormal;
	
        private String message;
	    
	    private boolean isSuccess;
	    
	    private String flag;
	    
	    private String successList;
	    
	    private String bopsPurchaseVOAll;
	    
	    private String wareHouseName;
	    
	    private String dataAll;
	    
	    private String vendorName;
	    
	    private Integer vendorId;
	    
	    private String dredge;
	    
		public String getDredge() {
			return dredge;
		}

		public void setDredge(String dredge) {
			this.dredge = dredge;
		}

		public String getHavestUsername() {
			return havestUsername;
		}

		public void setHavestUsername(String havestUsername) {
			this.havestUsername = havestUsername;
		}

		public String getPurchaseFreight() {
			return purchaseFreight;
		}

		public void setPurchaseFreight(String purchaseFreight) {
			this.purchaseFreight = purchaseFreight;
		}

		public String getPurchasePriceOther() {
			return purchasePriceOther;
		}

		public void setPurchasePriceOther(String purchasePriceOther) {
			this.purchasePriceOther = purchasePriceOther;
		}

		public String getVendorName() {
			return vendorName;
		}

		public void setVendorName(String vendorName) {
			this.vendorName = vendorName;
		}

		public Integer getVendorId() {
			return vendorId;
		}

		public void setVendorId(Integer vendorId) {
			this.vendorId = vendorId;
		}

		public String getProviderName() {
			return providerName;
		}

		public void setProviderName(String providerName) {
			this.providerName = providerName;
		}

		public String getDataAll() {
			return dataAll;
		}

		public void setDataAll(String dataAll) {
			this.dataAll = dataAll;
		}

		public Double getPurchasePrice2() {
			return purchasePrice2;
		}

		public void setPurchasePrice2(Double purchasePrice2) {
			this.purchasePrice2 = purchasePrice2;
		}

		public String getHavestTime() {
			return havestTime;
		}

		public void setHavestTime(String havestTime) {
			this.havestTime = havestTime;
		}

		public String getPurchaseUserName() {
			return purchaseUserName;
		}

		public void setPurchaseUserName(String purchaseUserName) {
			this.purchaseUserName = purchaseUserName;
		}

		public String getWareHouseName() {
			return wareHouseName;
		}

		public void setWareHouseName(String wareHouseName) {
			this.wareHouseName = wareHouseName;
		}

		public String getCreateTimeStart() {
			return createTimeStart;
		}

		public void setCreateTimeStart(String createTimeStart) {
			this.createTimeStart = createTimeStart;
		}

		public String getCreateTimeEnd() {
			return createTimeEnd;
		}

		public void setCreateTimeEnd(String createTimeEnd) {
			this.createTimeEnd = createTimeEnd;
		}

		public String getGoodsId() {
			return goodsId;
		}

		public void setGoodsId(String goodsId) {
			this.goodsId = goodsId;
		}

		public String getWarehouseName() {
			return warehouseName;
		}

		public void setWarehouseName(String warehouseName) {
			this.warehouseName = warehouseName;
		}

		public Integer getPurchaseId() {
			return purchaseId;
		}

		public void setPurchaseId(Integer purchaseId) {
			this.purchaseId = purchaseId;
		}

		public String getPurchaseStatus() {
			return purchaseStatus;
		}

		public void setPurchaseStatus(String purchaseStatus) {
			this.purchaseStatus = purchaseStatus;
		}

		public Integer getProviderId() {
			return providerId;
		}

		public void setProviderId(Integer providerId) {
			this.providerId = providerId;
		}

		public String getWarehouseId() {
			return warehouseId;
		}

		public void setWarehouseId(String warehouseId) {
			this.warehouseId = warehouseId;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getDeliveryTime() {
			return deliveryTime;
		}

		public void setDeliveryTime(String deliveryTime) {
			this.deliveryTime = deliveryTime;
		}

		public Integer getPurchaseUserId() {
			return purchaseUserId;
		}

		public void setPurchaseUserId(Integer purchaseUserId) {
			this.purchaseUserId = purchaseUserId;
		}

		public String getPurchaseDepartment() {
			return purchaseDepartment;
		}

		public void setPurchaseDepartment(String purchaseDepartment) {
			this.purchaseDepartment = purchaseDepartment;
		}

		public String getPurchaseIsHavest() {
			return purchaseIsHavest;
		}

		public void setPurchaseIsHavest(String purchaseIsHavest) {
			this.purchaseIsHavest = purchaseIsHavest;
		}

		public String getHavestUserid() {
			return havestUserid;
		}

		public void setHavestUserid(String havestUserid) {
			this.havestUserid = havestUserid;
		}

		public String getPurchaseIsDifferent() {
			return purchaseIsDifferent;
		}

		public void setPurchaseIsDifferent(String purchaseIsDifferent) {
			this.purchaseIsDifferent = purchaseIsDifferent;
		}

		public Integer getHavestCount() {
			return havestCount;
		}

		public void setHavestCount(Integer havestCount) {
			this.havestCount = havestCount;
		}

		public Integer getHavestDisability() {
			return havestDisability;
		}

		public void setHavestDisability(Integer havestDisability) {
			this.havestDisability = havestDisability;
		}

		public Integer getHavestNormal() {
			return havestNormal;
		}

		public void setHavestNormal(Integer havestNormal) {
			this.havestNormal = havestNormal;
		}

		public double getPurchasePriceAll() {
			return purchasePriceAll;
		}

		public void setPurchasePriceAll(double purchasePriceAll) {
			this.purchasePriceAll = purchasePriceAll;
		}

		public Integer getPurchaseCountAll() {
			return purchaseCountAll;
		}

		public void setPurchaseCountAll(Integer purchaseCountAll) {
			this.purchaseCountAll = purchaseCountAll;
		}

		public double getPurchasePriceReal() {
			return purchasePriceReal;
		}

		public void setPurchasePriceReal(double purchasePriceReal) {
			this.purchasePriceReal = purchasePriceReal;
		}

		public Integer getPurchaseCountReal() {
			return purchaseCountReal;
		}

		public void setPurchaseCountReal(Integer purchaseCountReal) {
			this.purchaseCountReal = purchaseCountReal;
		}

		public double getDifferencePrice() {
			return differencePrice;
		}

		public void setDifferencePrice(double differencePrice) {
			this.differencePrice = differencePrice;
		}

		public Integer getDifferenceCount() {
			return differenceCount;
		}

		public void setDifferenceCount(Integer differenceCount) {
			this.differenceCount = differenceCount;
		}

		public String getPurchaseCurrency() {
			return purchaseCurrency;
		}

		public void setPurchaseCurrency(String purchaseCurrency) {
			this.purchaseCurrency = purchaseCurrency;
		}

		public String getPurchaseRemark() {
			return purchaseRemark;
		}

		public void setPurchaseRemark(String purchaseRemark) {
			this.purchaseRemark = purchaseRemark;
		}

		public Integer getPurchaseInfoId() {
			return purchaseInfoId;
		}

		public void setPurchaseInfoId(Integer purchaseInfoId) {
			this.purchaseInfoId = purchaseInfoId;
		}

		public Integer getPurchasePrice() {
			return purchasePrice;
		}

		public void setPurchasePrice(Integer purchasePrice) {
			this.purchasePrice = purchasePrice;
		}

		public Integer getPurchaseCount() {
			return purchaseCount;
		}

		public void setPurchaseCount(Integer purchaseCount) {
			this.purchaseCount = purchaseCount;
		}

		public String getGoodsCode() {
			return goodsCode;
		}

		public void setGoodsCode(String goodsCode) {
			this.goodsCode = goodsCode;
		}

		public String getGoodsBarcode() {
			return goodsBarcode;
		}

		public void setGoodsBarcode(String goodsBarcode) {
			this.goodsBarcode = goodsBarcode;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public Integer getHavestInfoCount() {
			return havestInfoCount;
		}

		public void setHavestInfoCount(Integer havestInfoCount) {
			this.havestInfoCount = havestInfoCount;
		}

		public double getHavestInfoPrice() {
			return havestInfoPrice;
		}

		public void setHavestInfoPrice(double havestInfoPrice) {
			this.havestInfoPrice = havestInfoPrice;
		}

		public Integer getHavestInfoDisability() {
			return havestInfoDisability;
		}

		public void setHavestInfoDisability(Integer havestInfoDisability) {
			this.havestInfoDisability = havestInfoDisability;
		}

		public Integer getHavestInfoNormal() {
			return havestInfoNormal;
		}

		public void setHavestInfoNormal(Integer havestInfoNormal) {
			this.havestInfoNormal = havestInfoNormal;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public boolean isSuccess() {
			return isSuccess;
		}

		public void setSuccess(boolean isSuccess) {
			this.isSuccess = isSuccess;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public String getSuccessList() {
			return successList;
		}

		public void setSuccessList(String successList) {
			this.successList = successList;
		}

		public String getBopsPurchaseVOAll() {
			return bopsPurchaseVOAll;
		}

		public void setBopsPurchaseVOAll(String bopsPurchaseVOAll) {
			this.bopsPurchaseVOAll = bopsPurchaseVOAll;
		}

		public String getReceiveNo() {
			return receiveNo;
		}

		public void setReceiveNo(String receiveNo) {
			this.receiveNo = receiveNo;
		}
	    
	    
}

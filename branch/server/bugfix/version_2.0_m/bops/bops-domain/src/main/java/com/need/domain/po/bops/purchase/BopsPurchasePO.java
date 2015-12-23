package com.need.domain.po.bops.purchase;

import java.io.Serializable;
import java.util.Date;

public class BopsPurchasePO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1459153507710654037L;

	private Integer purchaseId;

    private String purchaseStatus;

    private Integer providerId;

    private Integer warehouseId;

    private Date createTime;
    
    private Date havestTime;
    
    private Date deliveryTime;

    private Integer purchaseUserId;

    private String purchaseDepartment;

    private String purchaseIsHavest;

    private String havestUserid;
    
    private String havestUsername;

    private String purchaseIsDifferent;

    private Integer havestCount;

    private Integer havestDisability;

    private Integer havestNormal;

    private Integer purchasePriceAll;

    private Integer purchaseCountAll;

    private Integer purchasePriceReal;

    private Integer purchaseCountReal;

    private Integer purchaseFreight;

    private Integer differencePrice;

    private Integer differenceCount;

    private String purchaseCurrency;

    private String purchaseRemark;

    private Integer purchasePriceOther;

    public String getHavestUsername() {
		return havestUsername;
	}

	public void setHavestUsername(String havestUsername) {
		this.havestUsername = havestUsername;
	}

	public Date getHavestTime() {
		return havestTime;
	}

	public void setHavestTime(Date havestTime) {
		this.havestTime = havestTime;
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

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
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

    public Integer getPurchasePriceAll() {
        return purchasePriceAll;
    }

    public void setPurchasePriceAll(Integer purchasePriceAll) {
        this.purchasePriceAll = purchasePriceAll;
    }

    public Integer getPurchaseCountAll() {
    	
    	if(purchaseCountAll==null){
    		  return 0;
    	}else{
    		  return purchaseCountAll;
    	}
      
    }

    public void setPurchaseCountAll(Integer purchaseCountAll) {
        this.purchaseCountAll = purchaseCountAll;
    }

    public Integer getPurchasePriceReal() {
        return purchasePriceReal;
    }

    public void setPurchasePriceReal(Integer purchasePriceReal) {
        this.purchasePriceReal = purchasePriceReal;
    }

    public Integer getPurchaseCountReal() {
        if(purchaseCountReal==null){
  		  return 0;
  	}else{
  		  return purchaseCountReal;
  	}
    }

    public void setPurchaseCountReal(Integer purchaseCountReal) {
        this.purchaseCountReal = purchaseCountReal;
    }

    public Integer getPurchaseFreight() {
        return purchaseFreight;
    }

    public void setPurchaseFreight(Integer purchaseFreight) {
        this.purchaseFreight = purchaseFreight;
    }

    public Integer getDifferencePrice() {
        return differencePrice;
    }

    public void setDifferencePrice(Integer differencePrice) {
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

    public Integer getPurchasePriceOther() {
        return purchasePriceOther;
    }

    public void setPurchasePriceOther(Integer purchasePriceOther) {
        this.purchasePriceOther = purchasePriceOther;
    }
}
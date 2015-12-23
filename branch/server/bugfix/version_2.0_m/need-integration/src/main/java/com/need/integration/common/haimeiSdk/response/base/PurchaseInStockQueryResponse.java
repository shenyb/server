package com.need.integration.common.haimeiSdk.response.base;

import java.util.Date;
import java.util.List;

import com.need.integration.common.haimeiSdk.domain.base.Sku;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class PurchaseInStockQueryResponse  extends AbstractResponse<PurchaseInStockQueryResponse> {
	
	private String purchaseNo ; 
	
	private String supplierId ; 
	
	private Long status ; 
	
	private Date startTime ; 
	
	private Date endTime ; 
	
	List<Sku> skus  ;

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<Sku> getSkus() {
		return skus;
	}

	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}
	
	
}

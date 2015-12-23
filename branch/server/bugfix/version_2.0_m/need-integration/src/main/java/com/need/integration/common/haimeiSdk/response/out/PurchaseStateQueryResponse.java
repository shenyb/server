package com.need.integration.common.haimeiSdk.response.out;

import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class PurchaseStateQueryResponse extends AbstractResponse<PurchaseStateQueryResponse> {
	private Long stockId ; 
	
	private Long ownerId ; 
	
	private String purchaseNo ; 
	
	private Long billType ; 
	
	private Long status ;
	
	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	
	public Long getBillType() {
		return billType;
	}

	public void setBillType(Long billType) {
		this.billType = billType;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
}

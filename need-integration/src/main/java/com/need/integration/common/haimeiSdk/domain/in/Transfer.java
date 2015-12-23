package com.need.integration.common.haimeiSdk.domain.in;

import java.util.List;

public class Transfer {
	private String transferNo ; 
	
	private String transferType ; 
	
	private Long stockIdFrom ; 
	
	private Long stockIdTo ; 
	
	private Long ownerIdFrom ; 
	
	private Long ownerIdTo ; 
	
	private String transporterId ; 
	
	private String qualityType ;
	
	private List<TransferDet> transferDetails ; 

	public String getTransferNo() {
		return transferNo;
	}

	public void setTransferNo(String transferNo) {
		this.transferNo = transferNo;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public Long getStockIdFrom() {
		return stockIdFrom;
	}

	public void setStockIdFrom(Long stockIdFrom) {
		this.stockIdFrom = stockIdFrom;
	}

	public Long getStockIdTo() {
		return stockIdTo;
	}

	public void setStockIdTo(Long stockIdTo) {
		this.stockIdTo = stockIdTo;
	}

	public Long getOwnerIdFrom() {
		return ownerIdFrom;
	}

	public void setOwnerIdFrom(Long ownerIdFrom) {
		this.ownerIdFrom = ownerIdFrom;
	}

	public Long getOwnerIdTo() {
		return ownerIdTo;
	}

	public void setOwnerIdTo(Long ownerIdTo) {
		this.ownerIdTo = ownerIdTo;
	}

	public String getTransporterId() {
		return transporterId;
	}

	public void setTransporterId(String transporterId) {
		this.transporterId = transporterId;
	}

	public String getQualityType() {
		return qualityType;
	}

	public void setQualityType(String qualityType) {
		this.qualityType = qualityType;
	}

	public List<TransferDet> getTransferDetails() {
		return transferDetails;
	}

	public void setTransferDetails(List<TransferDet> transferDetails) {
		this.transferDetails = transferDetails;
	} 
	
	
}

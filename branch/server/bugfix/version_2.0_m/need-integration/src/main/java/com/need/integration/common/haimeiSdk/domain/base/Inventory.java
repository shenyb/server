package com.need.integration.common.haimeiSdk.domain.base;

import java.util.List;


public class Inventory {
	private String productCode ; 
	
	private Long qtyAvailable ; 
	
	private Long qtyOnLocation ; 
	
	private Long qtyUnqualified  ;
	
	private List<InventoryLot> lots;
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Long getQtyAvailable() {
		return qtyAvailable;
	}

	public void setQtyAvailable(Long qtyAvailable) {
		this.qtyAvailable = qtyAvailable;
	}

	public Long getQtyOnLocation() {
		return qtyOnLocation;
	}

	public void setQtyOnLocation(Long qtyOnLocation) {
		this.qtyOnLocation = qtyOnLocation;
	}

	public Long getQtyUnqualified() {
		return qtyUnqualified;
	}

	public void setQtyUnqualified(Long qtyUnqualified) {
		this.qtyUnqualified = qtyUnqualified;
	}

	public List<InventoryLot> getLots() {
		return lots;
	}

	public void setLots(List<InventoryLot> lots) {
		this.lots = lots;
	}
	
}

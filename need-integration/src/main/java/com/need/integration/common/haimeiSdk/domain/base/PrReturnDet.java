package com.need.integration.common.haimeiSdk.domain.base;

public class PrReturnDet {
	private String productCode ; 
	
	private String supplierProductCode ; 
	
	private Long returnQty ; 
	
	private Long receivedQty ;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSupplierProductCode() {
		return supplierProductCode;
	}

	public void setSupplierProductCode(String supplierProductCode) {
		this.supplierProductCode = supplierProductCode;
	}

	public Long getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(Long returnQty) {
		this.returnQty = returnQty;
	}

	public Long getReceivedQty() {
		return receivedQty;
	}

	public void setReceivedQty(Long receivedQty) {
		this.receivedQty = receivedQty;
	} 
	
	

}

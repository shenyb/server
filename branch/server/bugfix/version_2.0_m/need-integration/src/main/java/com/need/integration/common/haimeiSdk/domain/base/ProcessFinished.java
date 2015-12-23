package com.need.integration.common.haimeiSdk.domain.base;

public class ProcessFinished {
	
	private String productCode ; 
	
	private String productTitle ; 
	
	private Long planFinishedQty  ;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public Long getPlanFinishedQty() {
		return planFinishedQty;
	}

	public void setPlanFinishedQty(Long planFinishedQty) {
		this.planFinishedQty = planFinishedQty;
	}
	
	
}

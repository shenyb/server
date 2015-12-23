package com.need.integration.common.haimeiSdk.domain.base;

public class ProcessMaterial {
	private String productCode ; 
	
	private String productTitle ;
	
	private String qualityType ; 
	
	private Long planMaterialQty ;

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

	public String getQualityType() {
		return qualityType;
	}

	public void setQualityType(String qualityType) {
		this.qualityType = qualityType;
	}

	public Long getPlanMaterialQty() {
		return planMaterialQty;
	}

	public void setPlanMaterialQty(Long planMaterialQty) {
		this.planMaterialQty = planMaterialQty;
	} 
	
	
}

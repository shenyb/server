package com.need.integration.common.haimeiSdk.domain.in;

import java.util.Date;

public class PurchaseDet {
	private String productCode ; 
	
	private Long qty ;
	
	private Double unitPurchaseCost ; 
	
	private Date planShipDate  ;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public Double getUnitPurchaseCost() {
		return unitPurchaseCost;
	}

	public void setUnitPurchaseCost(Double unitPurchaseCost) {
		this.unitPurchaseCost = unitPurchaseCost;
	}

	public Date getPlanShipDate() {
		return planShipDate;
	}

	public void setPlanShipDate(Date planShipDate) {
		this.planShipDate = planShipDate;
	}
	
	
}

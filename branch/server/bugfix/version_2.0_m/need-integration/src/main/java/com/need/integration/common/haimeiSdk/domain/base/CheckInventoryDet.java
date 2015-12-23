package com.need.integration.common.haimeiSdk.domain.base;

import java.util.Date;

public class CheckInventoryDet {
	private Long gainQty ; 
	
	private Date endTime ; 
	
	private Date dateUpdated ;
	
	private String qualityType;
	
	private Long checkedQty ; 
	
	private String productCode ; 
	
	private String checkInventoryStatus ; 
	
	private Long systemQty ;

	public Long getGainQty() {
		return gainQty;
	}

	public void setGainQty(Long gainQty) {
		this.gainQty = gainQty;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getQualityType() {
		return qualityType;
	}

	public void setQualityType(String qualityType) {
		this.qualityType = qualityType;
	}

	public Long getCheckedQty() {
		return checkedQty;
	}

	public void setCheckedQty(Long checkedQty) {
		this.checkedQty = checkedQty;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCheckInventoryStatus() {
		return checkInventoryStatus;
	}

	public void setCheckInventoryStatus(String checkInventoryStatus) {
		this.checkInventoryStatus = checkInventoryStatus;
	}

	public Long getSystemQty() {
		return systemQty;
	}

	public void setSystemQty(Long systemQty) {
		this.systemQty = systemQty;
	} 
	
}

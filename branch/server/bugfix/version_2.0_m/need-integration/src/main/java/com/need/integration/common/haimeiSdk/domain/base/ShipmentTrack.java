package com.need.integration.common.haimeiSdk.domain.base;

import java.util.Date;

public class ShipmentTrack {
	private Date operationDate ; 
	
	private String operationRemark ;

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public String getOperationRemark() {
		return operationRemark;
	}

	public void setOperationRemark(String operationRemark) {
		this.operationRemark = operationRemark;
	} 
	
}

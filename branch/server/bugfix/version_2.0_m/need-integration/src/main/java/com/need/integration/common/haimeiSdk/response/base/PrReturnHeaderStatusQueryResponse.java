package com.need.integration.common.haimeiSdk.response.base;

import java.util.Date;

import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class PrReturnHeaderStatusQueryResponse extends AbstractResponse<PrReturnHeaderStatusQueryResponse>{
	private Date shipDate ;
	
	private Long returnStatus ;

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public Long getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(Long returnStatus) {
		this.returnStatus = returnStatus;
	} 
	
	
}

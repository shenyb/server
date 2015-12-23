package com.need.integration.common.haimeiSdk.response.in;

import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class PushStockProcessResponse extends AbstractResponse<PushStockProcessResponse>  {

	private boolean result ; 
	
	private String stockProcessNo ; 
	
	private Long  ownerId ; 
	
	private String reason ;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getStockProcessNo() {
		return stockProcessNo;
	}

	public void setStockProcessNo(String stockProcessNo) {
		this.stockProcessNo = stockProcessNo;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	} 
	
}

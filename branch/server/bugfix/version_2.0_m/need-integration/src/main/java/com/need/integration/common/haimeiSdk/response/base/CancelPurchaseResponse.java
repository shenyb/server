package com.need.integration.common.haimeiSdk.response.base;

import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class CancelPurchaseResponse  extends AbstractResponse<CancelPurchaseResponse> {
	private boolean flag ; 
	
	private String reason ;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}

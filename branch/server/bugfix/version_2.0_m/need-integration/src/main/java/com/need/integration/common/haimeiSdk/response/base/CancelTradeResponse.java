package com.need.integration.common.haimeiSdk.response.base;

import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class CancelTradeResponse  extends AbstractResponse<CancelTradeResponse> {
	private String flag ;
	private String reason ;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}

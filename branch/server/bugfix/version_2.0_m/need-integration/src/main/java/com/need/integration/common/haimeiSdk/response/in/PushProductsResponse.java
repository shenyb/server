package com.need.integration.common.haimeiSdk.response.in;

import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class PushProductsResponse extends AbstractResponse<PushProductsResponse> {
	String flag;
	String reason;
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

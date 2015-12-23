package com.need.integration.common.haimeiSdk.response.base;

import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class PushSupplierResponse extends AbstractResponse<PushSupplierResponse> {
	private Long supplierId;

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	private String flag;
}

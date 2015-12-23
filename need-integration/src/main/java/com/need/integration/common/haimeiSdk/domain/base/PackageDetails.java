package com.need.integration.common.haimeiSdk.domain.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PackageDetails {
	public PackageDetail getPackageDetail() {
		return packageDetail;
	}

	public void setPackageDetail(PackageDetail packageDetail) {
		this.packageDetail = packageDetail;
	}
	
	@JsonProperty("packageDetail")
	public PackageDetail packageDetail;
}

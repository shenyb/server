package com.need.integration.common.haimeiSdk.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public interface IscsRequest<T extends AbstractResponse> {
	@JsonIgnore
	String getApiMethod();
	@JsonIgnore
	public Class<T> getResponseClass();
}

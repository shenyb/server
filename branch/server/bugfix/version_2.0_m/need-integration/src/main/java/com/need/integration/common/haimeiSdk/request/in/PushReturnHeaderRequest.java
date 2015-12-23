package com.need.integration.common.haimeiSdk.request.in;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.need.integration.common.haimeiSdk.domain.in.PrReturnHeader;
import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.in.PushReturnHeaderResponse;

public class PushReturnHeaderRequest extends AbstractRequest implements IscsRequest {
	
	private Long count ; 
	@JsonProperty("prReturnHeaders")
	List<PrReturnHeader> prReturnHeaders;
	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	public List<PrReturnHeader> getPrReturnHeaders() {
		return prReturnHeaders;
	}

	public void setPrReturnHeaders(List<PrReturnHeader> prReturnHeaders) {
		this.prReturnHeaders = prReturnHeaders;
	}

	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "pushReturnHeader";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return PushReturnHeaderResponse.class;
	}

}

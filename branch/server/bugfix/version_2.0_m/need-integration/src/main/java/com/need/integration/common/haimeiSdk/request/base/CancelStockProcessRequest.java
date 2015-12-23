package com.need.integration.common.haimeiSdk.request.base;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.CancelStockProcessResponse;

public class CancelStockProcessRequest extends AbstractRequest implements IscsRequest{

	private String tids ; 
	
	private Long ownerId ; 
	
	private Long stockId ; 
	
	public String getTids() {
		return tids;
	}

	public void setTids(String tids) {
		this.tids = tids;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "cancelStockProcess";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return CancelStockProcessResponse.class;
	}

}

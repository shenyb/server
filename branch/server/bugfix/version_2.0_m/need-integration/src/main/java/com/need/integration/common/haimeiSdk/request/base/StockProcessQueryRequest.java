package com.need.integration.common.haimeiSdk.request.base;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.StockProcessQueryResponse;

public class StockProcessQueryRequest extends AbstractRequest implements IscsRequest{

	private String tids ; 
	
	private String stockProcessNo ; 
	
	private Long ownerId ; 
	
	private Long stockId ; 
	
	private String stockProecessStatus ;
	
	public String getTids() {
		return tids;
	}

	public void setTids(String tids) {
		this.tids = tids;
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

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public String getStockProecessStatus() {
		return stockProecessStatus;
	}

	public void setStockProecessStatus(String stockProecessStatus) {
		this.stockProecessStatus = stockProecessStatus;
	}

	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "stockProcessQuery";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return StockProcessQueryResponse.class;
	}

}

package com.need.integration.common.haimeiSdk.response.base;

import java.util.List;

import com.need.integration.common.haimeiSdk.domain.base.StockProcess;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class StockProcessQueryResponse extends AbstractResponse<StockProcessQueryResponse>{
	
	List<StockProcess> stockProcessList  ;

	public List<StockProcess> getStockProcessList() {
		return stockProcessList;
	}

	public void setStockProcessList(List<StockProcess> stockProcessList) {
		this.stockProcessList = stockProcessList;
	}
	
}

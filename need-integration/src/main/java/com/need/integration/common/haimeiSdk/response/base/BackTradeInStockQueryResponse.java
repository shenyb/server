package com.need.integration.common.haimeiSdk.response.base;

import java.util.List;

import com.need.integration.common.haimeiSdk.domain.in.OrderReturn;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;
/**
 * 
 * @author Administrator
 *
 */
public class BackTradeInStockQueryResponse extends AbstractResponse<List<OrderReturn>>  {
	List<OrderReturn> backTrades ;

	public List<OrderReturn> getBackTrades() {
		return backTrades;
	}

	public void setBackTrades(List<OrderReturn> backTrades) {
		this.backTrades = backTrades;
	} 
	
}

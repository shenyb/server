package com.need.integration.common.haimeiSdk.request.in;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.need.integration.common.haimeiSdk.domain.in.OrderReturn;
import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.in.PushBackTradesResponse;

/**
 * 销售退货单导入
 * @author 刘胜超
 *
 */
public class PushBackTradesRequest extends AbstractRequest implements IscsRequest {
	
	private String totalCount ; 
	@JsonProperty("backTrades")
	private List<OrderReturn> backTrades ;
	
	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public List<OrderReturn> getBackTrades() {
		return backTrades;
	}

	public void setBackTrades(List<OrderReturn> backTrades) {
		this.backTrades = backTrades;
	}

	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "pushBackTrades";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return PushBackTradesResponse.class;
	}

}

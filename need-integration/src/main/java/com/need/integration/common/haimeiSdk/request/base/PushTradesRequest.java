package com.need.integration.common.haimeiSdk.request.base;

import java.util.ArrayList;
import java.util.List;

import com.need.integration.common.haimeiSdk.domain.out.Order;
import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.PushTradesResponse;

/**
 * 订单导入
 * @author 徐纯
 *
 *  2015-6-24 下午03:44:44
 */
public class PushTradesRequest extends AbstractRequest implements IscsRequest {

	Long count;
	List<Order> trades = new ArrayList<Order>();
	
	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<Order> getTrades() {
		return trades;
	}

	public void setTrades(List<Order> trades) {
		this.trades = trades;
	}

	@Override
	public String getApiMethod() {
		return "pushTrades";
	}

	@Override
	public Class getResponseClass() {
		return PushTradesResponse.class;
	}
	 
}

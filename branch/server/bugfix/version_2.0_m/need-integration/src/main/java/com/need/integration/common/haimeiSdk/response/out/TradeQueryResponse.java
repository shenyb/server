package com.need.integration.common.haimeiSdk.response.out;

import java.util.List;

import com.need.integration.common.haimeiSdk.domain.out.Order;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class TradeQueryResponse extends AbstractResponse<TradeQueryResponse> {
	String pageSize ;
	String pageNo;
	String pageNum;
	String totalCount;
	List<Order> trades;
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public List<Order> getTrades() {
		return trades;
	}
	public void setTrades(List<Order> trades) {
		this.trades = trades;
	}
}

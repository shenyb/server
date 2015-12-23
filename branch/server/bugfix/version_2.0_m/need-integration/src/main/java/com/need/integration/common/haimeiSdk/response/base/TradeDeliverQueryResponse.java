package com.need.integration.common.haimeiSdk.response.base;

import java.util.List;

import com.need.integration.common.haimeiSdk.domain.out.Order;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;

/**
 * 订单发货查询
 * @author 刘胜超
 *
 */
public class TradeDeliverQueryResponse  extends AbstractResponse<TradeDeliverQueryResponse> {
	
	private Long totalCount ; 
	
	private Long pageNo ; 
	
	private Long pageSize ; 
	
	private Long pageNum ; 
	
	private List<Order> trades ;

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getPageNo() {
		return pageNo;
	}

	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getPageNum() {
		return pageNum;
	}

	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}

	public List<Order> getTrades() {
		return trades;
	}

	public void setTrades(List<Order> trades) {
		this.trades = trades;
	} 
	

}

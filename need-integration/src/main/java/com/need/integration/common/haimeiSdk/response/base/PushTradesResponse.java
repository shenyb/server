package com.need.integration.common.haimeiSdk.response.base;

import java.util.List;

import com.need.integration.common.haimeiSdk.domain.out.Order;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class PushTradesResponse extends AbstractResponse<PushTradesResponse> {
	Long totalCount;
	Long failedCount;
	Long successCount;
	
	public Long getSuccessCount() {
		return successCount;
	}
	public void setSuccesssCount(Long successCount) {
		this.successCount = successCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getFailedCount() {
		return failedCount;
	}
	public void setFailedCount(Long failedCount) {
		this.failedCount = failedCount;
	}
	public List<Order> getFailedList() {
		return failedList;
	}
	public void setFailedList(List<Order> failedList) {
		this.failedList = failedList;
	}
	List<Order> failedList;
}

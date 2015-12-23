package com.need.integration.common.haimeiSdk.response.in;

import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class PushBackTradesResponse extends AbstractResponse<PushBackTradesResponse>  {
	private Long totalCount ; 
	
	private Long successCount ; 
	
	private Long failedCount ;

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Long successCount) {
		this.successCount = successCount;
	}

	public Long getFailedCount() {
		return failedCount;
	}

	public void setFailedCount(Long failedCount) {
		this.failedCount = failedCount;
	} 
	
	

}

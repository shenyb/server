package com.need.integration.common.haimeiSdk.response.in;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.need.integration.common.haimeiSdk.domain.base.PrBackTrade;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class PushReturnHeaderResponse  extends AbstractResponse<PushReturnHeaderResponse>{

	private boolean flag ; 

	private Long successCount ; 
	
	private Long totalCount ; 
	
	private Long failedCount ; 
	
	private String reason ; 
	@JsonProperty("backTrades")
	private List<PrBackTrade> backTrades ; 
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Long getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Long successCount) {
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<PrBackTrade> getBackTrades() {
		return backTrades;
	}

	public void setBackTrades(List<PrBackTrade> backTrades) {
		this.backTrades = backTrades;
	}

}

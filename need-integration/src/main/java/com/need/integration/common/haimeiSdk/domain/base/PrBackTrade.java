package com.need.integration.common.haimeiSdk.domain.base;

public class PrBackTrade {
	private String tradeId ; 
	
	private String reason ;
	
	private String iscsReturnNo;
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getIscsReturnNo() {
		return iscsReturnNo;
	}
	public void setIscsReturnNo(String iscsReturnNo) {
		this.iscsReturnNo = iscsReturnNo;
	}
}

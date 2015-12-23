package com.need.common.domain.vo.trade;

public class CreateTradeBaseParamVO {
	private String addressId;
	private String userId;
	private String useBalance;
	
	private String orderType;
	
	public String getUseBalance() {
		return useBalance;
	}
	public void setUseBalance(String useBalance) {
		this.useBalance = useBalance;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	@Override
	public String toString() {
		return "CreateTradeBaseParamVO [addressId=" + addressId + ", userId="
				+ userId + ", useBalance=" + useBalance + ", orderType="
				+ orderType + "]";
	}
	
	
	
	
}

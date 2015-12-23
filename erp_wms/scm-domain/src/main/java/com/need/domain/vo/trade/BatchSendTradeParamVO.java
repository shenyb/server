package com.need.domain.vo.trade;

import java.io.Serializable;

public class BatchSendTradeParamVO implements Serializable{

	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5047045935678390293L;
	private String orderNo;
	private String serviceProvider;
	private String logisticsNo;
	private String message;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getLogisticsNo() {
		return logisticsNo;
	}
	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}
	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "BatchSendTradeParamVO [orderNo=" + orderNo + ", serviceProvider=" + serviceProvider + ", logisticsNo="
				+ logisticsNo + ", message=" + message + "]";
	}
	
}

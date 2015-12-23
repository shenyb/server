package com.need.integration.common.birdex.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class TradeOrder implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7127506735356645552L;
	
	private String tradeOrderCode;
	private String paymentName;
	private String paymentCode;
	private String paymentTime;
	private BigDecimal tradeOrderValue;
	private String tradeOrderValueUnit;
	private List<PaymentItem> paymentItems;
	private String extraService;
	private Object extendInfo;
	private String remarks;
	private LogisticsOrder logisticsOrder;
	private List<Item> items;
	private ReceiverDetail receiverDetail;

	public String getTradeOrderCode() {
		return tradeOrderCode;
	}

	public void setTradeOrderCode(String tradeOrderCode) {
		this.tradeOrderCode = tradeOrderCode;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public BigDecimal getTradeOrderValue() {
		return tradeOrderValue;
	}

	public void setTradeOrderValue(BigDecimal tradeOrderValue) {
		this.tradeOrderValue = tradeOrderValue;
	}

	public String getTradeOrderValueUnit() {
		return tradeOrderValueUnit;
	}

	public void setTradeOrderValueUnit(String tradeOrderValueUnit) {
		this.tradeOrderValueUnit = tradeOrderValueUnit;
	}

	public List<PaymentItem> getPaymentItems() {
		return paymentItems;
	}

	public void setPaymentItems(List<PaymentItem> paymentItems) {
		this.paymentItems = paymentItems;
	}

	public String getExtraService() {
		return extraService;
	}

	public void setExtraService(String extraService) {
		this.extraService = extraService;
	}

	public Object getExtendInfo() {
		return extendInfo;
	}

	public void setExtendInfo(Object extendInfo) {
		this.extendInfo = extendInfo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LogisticsOrder getLogisticsOrder() {
		return logisticsOrder;
	}

	public void setLogisticsOrder(LogisticsOrder logisticsOrder) {
		this.logisticsOrder = logisticsOrder;
	}

	public ReceiverDetail getReceiverDetail() {
		return receiverDetail;
	}

	public void setReceiverDetail(ReceiverDetail receiverDetail) {
		this.receiverDetail = receiverDetail;
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
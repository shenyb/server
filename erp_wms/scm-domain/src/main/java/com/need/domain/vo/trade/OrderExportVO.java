package com.need.domain.vo.trade;

import java.io.Serializable;

public class OrderExportVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6762156904311354326L;	
	private String tradeNo;
	private String goodsCode;
	private double buyPrice;
	private double totalPrice;
	private int buyCount;
	private String mobile;
	private String receiver;
	private String address;
	private String logisticsValue;
	private String message;
	private double payPrice;
	private String userTradeNo;
	private String goodsCategories;
	private String warehouse;
	private String tradeStatus;
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLogisticsValue() {
		return logisticsValue;
	}
	public void setLogisticsValue(String logisticsValue) {
		this.logisticsValue = logisticsValue;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public double getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(double payPrice) {
		this.payPrice = payPrice;
	}
	
	public String getUserTradeNo() {
		return userTradeNo;
	}
	public void setUserTradeNo(String userTradeNo) {
		this.userTradeNo = userTradeNo;
	}
	public String getGoodsCategories() {
		return goodsCategories;
	}
	public void setGoodsCategories(String goodsCategories) {
		this.goodsCategories = goodsCategories;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	@Override
	public String toString() {
		return "OrderExportVO [tradeNo=" + tradeNo + ", goodsCode=" + goodsCode + ", buyPrice=" + buyPrice
				+ ", totalPrice=" + totalPrice + ", buyCount=" + buyCount + ", mobile=" + mobile + ", receiver="
				+ receiver + ", address=" + address + ", logisticsValue=" + logisticsValue + ", message=" + message
				+ ", payPrice=" + payPrice + ", userTradeNo=" + userTradeNo + ", goodsCategories=" + goodsCategories
				+ ", warehouse=" + warehouse + ", tradeStatus=" + tradeStatus + "]";
	}
	
}

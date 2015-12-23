package com.need.domain.vo.trade;

import java.io.Serializable;
import java.sql.Date;


public class TradeSendVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8968636686941834040L;
	private String tradeNo;
	private String goodsCode;
	private String goodsBarcode;
	private String goodsName;
	private Integer buyCount;
	private String tradeStatus;
	private String nickName;
	private String mobile;
	private String isInvoice;
	private Date tradeTime;
	private double totalPrice;
	private double payPrice;
	private String userTradeNo;
	private String goodsCategories;
	private String warehouse;
	private String categoryNameThree;
	
	
	public String getGoodsBarcode() {
		return goodsBarcode;
	}
	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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
	public String getCategoryNameThree() {
		return categoryNameThree;
	}
	public void setCategoryNameThree(String categoryNameThree) {
		this.categoryNameThree = categoryNameThree;
	}
	@Override
	public String toString() {
		return "TradeSendVO [tradeNo=" + tradeNo + ", goodsCode=" + goodsCode + ", goodsBarcode=" + goodsBarcode
				+ ", goodsName=" + goodsName + ", buyCount=" + buyCount + ", tradeStatus=" + tradeStatus + ", nickName="
				+ nickName + ", mobile=" + mobile + ", isInvoice=" + isInvoice + ", tradeTime=" + tradeTime
				+ ", totalPrice=" + totalPrice + ", payPrice=" + payPrice + ", userTradeNo=" + userTradeNo
				+ ", goodsCategories=" + goodsCategories + ", warehouse=" + warehouse + ", categoryNameThree="
				+ categoryNameThree + "]";
	}
	
}

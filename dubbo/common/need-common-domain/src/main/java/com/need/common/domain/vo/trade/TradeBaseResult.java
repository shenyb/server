package com.need.common.domain.vo.trade;

import java.io.Serializable;
import java.util.Date;

public class TradeBaseResult implements Serializable{	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -3431681316852221056L;
	
    //交易总信息
	private String tradeNo;
    private String tradeStatus;
    private String buyerId;
    private Date tradeTime;//tradeTime
    private int payAmount;//payAmount
    private String payChannel;//
    private String userTradeNo;
    private int payAmountTotal;
    
    //地址信息
    private String addressId;
    private String receiver;
    private String telephone;   
    private String myAddress;//具体收货地址
    
    private String address;
    private String logisticsValue;
    private String certificationCard;
    
    
    //订单信息
    private String orderNo;
    private String orderStatus;
	private String goodsId;
	private String goodsName;
	private String topPicKey;
	private Integer buyCount;//buyCount
	private Integer discountPrice;//discountPrice
	private Integer onsalePrice;//onsalePrice
	private Integer totalPrice;
	private String isGlobal;
	private String userOrderNo;
	
	private int transportFee;//运费

	private int commission;//佣金 add by shenyb 20151205
	
	
	public int getCommission() {
		return commission;
	}
	public void setCommission(int commission) {
		this.commission = commission;
	}
	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}
	public int getPayAmountTotal() {
		return payAmountTotal;
	}
	public void setPayAmountTotal(int payAmountTotal) {
		this.payAmountTotal = payAmountTotal;
	}
	public int getTransportFee() {
		return transportFee;
	}
	public void setTransportFee(int transportFee) {
		this.transportFee = transportFee;
	}
	public String getCertificationCard() {
		return certificationCard;
	}
	public void setCertificationCard(String certificationCard) {
		this.certificationCard = certificationCard;
	}
	public String getLogisticsValue() {
		return logisticsValue;
	}
	public void setLogisticsValue(String logisticsValue) {
		this.logisticsValue = logisticsValue;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIsGlobal() {
		return isGlobal;
	}
	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public Integer getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getTopPicKey() {
		return topPicKey;
	}
	public void setTopPicKey(String topPicKey) {
		this.topPicKey = topPicKey;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	public Integer getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMyAddress() {
		return myAddress;
	}
	public void setMyAddress(String myAddress) {
		this.myAddress = myAddress;
	}
	@Override
	public String toString() {
		return "TradeBaseResult [tradeNo=" + tradeNo + ", tradeStatus=" + tradeStatus + ", buyerId=" + buyerId
				+ ", tradeTime=" + tradeTime + ", payAmount=" + payAmount + ", payChannel=" + payChannel
				+ ", addressId=" + addressId + ", receiver=" + receiver + ", telephone=" + telephone + ", myAddress="
				+ myAddress + ", orderNo=" + orderNo + ", orderStatus=" + orderStatus + ", goodsId=" + goodsId
				+ ", goodsName=" + goodsName + ", topPicKey=" + topPicKey + ", buyCount=" + buyCount
				+ ", discountPrice=" + discountPrice + ", totalPrice=" + totalPrice + "]";
	}
	public Integer getOnsalePrice() {
		return onsalePrice;
	}
	public void setOnsalePrice(Integer onsalePrice) {
		this.onsalePrice = onsalePrice;
	}
	public String getUserTradeNo() {
		return userTradeNo;
	}
	public void setUserTradeNo(String userTradeNo) {
		this.userTradeNo = userTradeNo;
	}
	public String getUserOrderNo() {
		return userOrderNo;
	}
	public void setUserOrderNo(String userOrderNo) {
		this.userOrderNo = userOrderNo;
	}
	
	
	
}

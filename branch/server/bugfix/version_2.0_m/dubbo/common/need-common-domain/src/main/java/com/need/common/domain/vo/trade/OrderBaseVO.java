package com.need.common.domain.vo.trade;

import java.io.Serializable;

public class OrderBaseVO implements Serializable{
    
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 5315369074248979352L;
	//订单信息
    private String orderNo;
    private String tradeNo;
    private String orderStatus;
    private String tradeStatus;
	private String goodsId;
	private String goodsName;
	private String topPicKey;
	private Integer buyCount;//buyCount
	private Integer discountPrice;//discountPrice
	private Integer onsalePrice;//discountPrice
	private Integer totalPrice;	
	private Integer buyPrice;
	private String isGlobal;
	private int payAmount;
	private int payAmountTotal;
	
	
	
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public int getPayAmountTotal() {
		return payAmountTotal;
	}
	public void setPayAmountTotal(int payAmountTotal) {
		this.payAmountTotal = payAmountTotal;
	}
	private String userOrderNo;
	
	
	public int getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}
	public String getIsGlobal() {
		return isGlobal;
	}
	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
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
	
	
	
	public Integer getOnsalePrice() {
		return onsalePrice;
	}
	public void setOnsalePrice(Integer onsalePrice) {
		this.onsalePrice = onsalePrice;
	}
	public Integer getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Integer buyPrice) {
		this.buyPrice = buyPrice;
	}
	@Override
	public String toString() {
		return "OrderBaseVO [orderNo=" + orderNo + ", orderStatus=" + orderStatus + ", goodsId=" + goodsId
				+ ", goodsName=" + goodsName + ", topPicKey=" + topPicKey + ", buyCount=" + buyCount
				+ ", discountPrice=" + discountPrice + ", totalPrice=" + totalPrice + "]";
	}
	public String getUserOrderNo() {
		return userOrderNo;
	}
	public void setUserOrderNo(String userOrderNo) {
		this.userOrderNo = userOrderNo;
	}
	
	
	
	    
}

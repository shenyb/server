package com.need.common.domain.po.api.trade;

import com.need.trade.enums.TradeStatus;

import java.io.Serializable;
import java.util.Date;

public class TradeBasePO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 2568536897666934438L;

	private String orderNo;

	private String tradeNo;

	private TradeStatus orderStatus;

	private TradeStatus tradeStatus;

	private String buyerId;

	private String goodsId;

	private int buyCount;

	private int buyPrice;

	private int totalPrice;

	private String payChannel;

	private int payPrice;

	private Date createTime;

	private Date payTime;

	private Date tradeTime;

	private String addressId;

	private String userTradeNo;// Addy liyongran 20150922 新增用户交易编号

	private String userOrderNo;// Addy liyongran 20150922 新增用户交易子编号

	private String address;

	private String logisticsValue;

	private String telephone;

	private String receiver;

	private String certificationCard;// 身份证号
	
	private String certificationPositiveKey;// 身份证正面照片

	private String certificationNegativeKey;// 身份证反面照片

	private String warehouseType;// 仓库类型
	
	private String isCheap;//是否为团便宜
	
	private int transportFee;//运费
	
	private int discountAmount;//优惠
	private int commission;//佣金
	private String batchNo;//组合购买的批次
	private String orderType;//订单类型
	private String distributionShareId;//分销者id
	private String isNormal; //库存是否缺货
	
	
	public String getIsNormal() {
		return isNormal;
	}

	public void setIsNormal(String isNormal) {
		this.isNormal = isNormal;
	}

	public String getDistributionShareId() {
		return distributionShareId;
	}

	public void setDistributionShareId(String distributionShareId) {
		this.distributionShareId = distributionShareId;
	}

	public int getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(int discountAmount) {
		this.discountAmount = discountAmount;
	}

	public int getCommission() {
		return commission;
	}

	public void setCommission(int commission) {
		this.commission = commission;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public int getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(int transportFee) {
		this.transportFee = transportFee;
	}

	public String getIsCheap() {
		return isCheap;
	}

	public void setIsCheap(String isCheap) {
		this.isCheap = isCheap;
	}

	public String getCertificationPositiveKey() {
		return certificationPositiveKey;
	}

	public void setCertificationPositiveKey(String certificationPositiveKey) {
		this.certificationPositiveKey = certificationPositiveKey;
	}

	public String getCertificationNegativeKey() {
		return certificationNegativeKey;
	}

	public void setCertificationNegativeKey(String certificationNegativeKey) {
		this.certificationNegativeKey = certificationNegativeKey;
	}

	

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getCertificationCard() {
		return certificationCard;
	}

	public void setCertificationCard(String certificationCard) {
		this.certificationCard = certificationCard;
	}

	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public TradeStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(TradeStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public TradeStatus getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public Integer getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Integer payPrice) {
		this.payPrice = payPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
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

	@Override
	public String toString() {
		return "TradeBasePO [orderNo=" + orderNo + ", tradeNo=" + tradeNo + ", orderStatus=" + orderStatus
				+ ", tradeStatus=" + tradeStatus + ", buyerId=" + buyerId + ", goodsId=" + goodsId + ", buyCount="
				+ buyCount + ", buyPrice=" + buyPrice + ", totalPrice=" + totalPrice + ", payChannel=" + payChannel
				+ ", payPrice=" + payPrice + ", createTime=" + createTime + ", payTime=" + payTime + ", tradeTime="
				+ tradeTime + ", addressId=" + addressId + "]";
	}

}
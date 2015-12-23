package com.need.integration.dao.jdbc.api.trade.po;

import java.io.Serializable;
import java.util.Date;

public class TradeBasePO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 2568536897666934438L;

	private String orderNo;

	private String tradeNo;

	private String orderStatus;

	private String tradeStatus;

	private String deliverStatus;

	private String memoDeliver;

	private String pushStatus;

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

	private String alipayRetrieveStatus;

	private String memoAlipayRetrieve;

	private String userTradeNo;

	private String userOrderNo;

	private String address;

	private String logisticsValue;

	private String telephone;

	private String receiver;

	private String certificationCard;// 身份证号

	private int transportFee;// 运费

	private String certificationPositiveKey;// 身份证正面

	private String certificationNegativeKey;// 身份证反面

	private String warehouseType;// 仓库类型

	private Date pushTime;

	private String isCheap;// 是否为团便宜

	private int discountAmount;// 优惠
	private int commission;// 佣金
	private String batchNo;// 组合购买的批次
	private String orderType;// 订单类型
	private String distributionShareId;// 分销者id
	
	
	private String wechatRetrieveStatus;// wechat 发送请求清关状态，请求成功：TRUE，失败：FALSE
	private String memoWechatRetrieve;//wechat 发送请求清关状态的饭后结果
	

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

	public String getDistributionShareId() {
		return distributionShareId;
	}

	public void setDistributionShareId(String distributionShareId) {
		this.distributionShareId = distributionShareId;
	}

	public String getIsCheap() {
		return isCheap;
	}

	public void setIsCheap(String isCheap) {
		this.isCheap = isCheap;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public int getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(int transportFee) {
		this.transportFee = transportFee;
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

	public String getAlipayRetrieveStatus() {
		return alipayRetrieveStatus;
	}

	public void setAlipayRetrieveStatus(String alipayRetrieveStatus) {
		this.alipayRetrieveStatus = alipayRetrieveStatus;
	}

	public String getMemoAlipayRetrieve() {
		return memoAlipayRetrieve;
	}

	public void setMemoAlipayRetrieve(String memoAlipayRetrieve) {
		this.memoAlipayRetrieve = memoAlipayRetrieve;
	}

	public String getDeliverStatus() {
		return deliverStatus;
	}

	public void setDeliverStatus(String deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	public String getMemoDeliver() {
		return memoDeliver;
	}

	public void setMemoDeliver(String memoDeliver) {
		this.memoDeliver = memoDeliver;
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	public String getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}

	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
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

	public String getWechatRetrieveStatus() {
		return wechatRetrieveStatus;
	}

	public void setWechatRetrieveStatus(String wechatRetrieveStatus) {
		this.wechatRetrieveStatus = wechatRetrieveStatus;
	}

	public String getMemoWechatRetrieve() {
		return memoWechatRetrieve;
	}

	public void setMemoWechatRetrieve(String memoWechatRetrieve) {
		this.memoWechatRetrieve = memoWechatRetrieve;
	}

	@Override
	public String toString() {
		return "TradeBasePO [orderNo=" + orderNo + ", tradeNo=" + tradeNo + ", orderStatus=" + orderStatus
				+ ", tradeStatus=" + tradeStatus + ", deliverStatus=" + deliverStatus + ", memoDeliver=" + memoDeliver
				+ ", pushStatus=" + pushStatus + ", buyerId=" + buyerId + ", goodsId=" + goodsId + ", buyCount="
				+ buyCount + ", buyPrice=" + buyPrice + ", totalPrice=" + totalPrice + ", payChannel=" + payChannel
				+ ", payPrice=" + payPrice + ", createTime=" + createTime + ", payTime=" + payTime + ", tradeTime="
				+ tradeTime + ", addressId=" + addressId + ", alipayRetrieveStatus=" + alipayRetrieveStatus
				+ ", memoAlipayRetrieve=" + memoAlipayRetrieve + ", userTradeNo=" + userTradeNo + ", userOrderNo="
				+ userOrderNo + ", address=" + address + ", logisticsValue=" + logisticsValue + ", telephone="
				+ telephone + ", receiver=" + receiver + ", certificationCard=" + certificationCard + ", transportFee="
				+ transportFee + ", certificationPositiveKey=" + certificationPositiveKey
				+ ", certificationNegativeKey=" + certificationNegativeKey + ", warehouseType=" + warehouseType
				+ ", pushTime=" + pushTime + ", isCheap=" + isCheap + ", wechatRetrieveStatus=" + wechatRetrieveStatus
				+ ", memoWechatRetrieve=" + memoWechatRetrieve + "]";
	}


}
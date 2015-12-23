package com.need.domain.vo.trade;

import java.util.Date;

public class TradeVO {

	private String orderNo;

	private String tradeNo;

	private String userOrderNo;

	private String userTradeNo;

	private String orderStatus;

	private String tradeStatus;

	private String buyerId;

	private String goodsId;

	private Integer buyCount;

	private Integer buyPrice;

	private Integer totalPrice;

	private String payChannel;

	private double payPrice;

	private Date createTime;

	private Date payTime;

	private Date tradeTime;

	private String addressId;

	private String receiver;// 收货人姓名
	private String certificationCard;// 收货人身份证号
	private String telephone;// 收货人联系电话
	private String address;// 收货地址
	private String logisticsValue;// 省市区
	private double yunfei;// 运费
	private double youhuiquan;// 优惠券金额
	private double minCost;// 最低消费金额
	private String youhuiquanDescription;//优惠券描述
	
	private String alipayRetrieveStatus;
	private String wechatRetrieveStatus;
	private String certificationPositiveKey;//身份证正面
	private String certificationNegativeKey;//身份证反面
	
	private double privilege;//优惠金额
	private double transportFee;//邮费
	
	private String warehouseType;
	private String isCheap;//是否为团便宜
	private Long tradePrice;
	


	public Long getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(Long tradePrice) {
		this.tradePrice = tradePrice;
	}
	public String getWechatRetrieveStatus() {
		return wechatRetrieveStatus;
	}

	public void setWechatRetrieveStatus(String wechatRetrieveStatus) {
		this.wechatRetrieveStatus = wechatRetrieveStatus;
	}

	public String getIsCheap() {
		return isCheap;
	}

	public void setIsCheap(String isCheap) {
		this.isCheap = isCheap;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public double getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(double transportFee) {
		this.transportFee = transportFee;
	}

	public double getPrivilege() {
		return privilege;
	}

	public void setPrivilege(double privilege) {
		this.privilege = privilege;
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

	public String getAlipayRetrieveStatus() {
		return alipayRetrieveStatus;
	}

	public void setAlipayRetrieveStatus(String alipayRetrieveStatus) {
		this.alipayRetrieveStatus = alipayRetrieveStatus;
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

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public Integer getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Integer buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public double getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(double payPrice) {
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getYunfei() {
		return yunfei;
	}

	public void setYunfei(double yunfei) {
		this.yunfei = yunfei;
	}

	public double getYouhuiquan() {
		return youhuiquan;
	}

	public void setYouhuiquan(double youhuiquan) {
		this.youhuiquan = youhuiquan;
	}

	public String getLogisticsValue() {
		return logisticsValue;
	}

	public void setLogisticsValue(String logisticsValue) {
		this.logisticsValue = logisticsValue;
	}

	public double getMinCost() {
		return minCost;
	}

	public void setMinCost(double minCost) {
		this.minCost = minCost;
	}

	public String getUserOrderNo() {
		return userOrderNo;
	}

	public void setUserOrderNo(String userOrderNo) {
		this.userOrderNo = userOrderNo;
	}

	public String getUserTradeNo() {
		return userTradeNo;
	}

	public void setUserTradeNo(String userTradeNo) {
		this.userTradeNo = userTradeNo;
	}

	public String getYouhuiquanDescription() {
		return youhuiquanDescription;
	}

	public void setYouhuiquanDescription(String youhuiquanDescription) {
		this.youhuiquanDescription = youhuiquanDescription;
	}

	@Override
	public String toString() {
		return "TradeVO [orderNo=" + orderNo + ", tradeNo=" + tradeNo + ", userOrderNo=" + userOrderNo
				+ ", userTradeNo=" + userTradeNo + ", orderStatus=" + orderStatus + ", tradeStatus=" + tradeStatus
				+ ", buyerId=" + buyerId + ", goodsId=" + goodsId + ", buyCount=" + buyCount + ", buyPrice=" + buyPrice
				+ ", totalPrice=" + totalPrice + ", payChannel=" + payChannel + ", payPrice=" + payPrice
				+ ", createTime=" + createTime + ", payTime=" + payTime + ", tradeTime=" + tradeTime + ", addressId="
				+ addressId + ", receiver=" + receiver + ", certificationCard=" + certificationCard + ", telephone="
				+ telephone + ", address=" + address + ", logisticsValue=" + logisticsValue + ", yunfei=" + yunfei
				+ ", youhuiquan=" + youhuiquan + ", minCost=" + minCost + ", youhuiquanDescription="
				+ youhuiquanDescription + "]";
	}

}

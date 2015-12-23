package com.need.domain.vo.trade;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单导出
 * @author zhangmengbin
 *
 */
public class TradeExportVO {
	private String tradeNo;
	private String userTradeNo; //交易单号
	private String payChannel; //支付渠道
	private BigDecimal totalPrice; //应支付金额
	private BigDecimal payPrice;//实际支付总价
	private BigDecimal goodsPrice; //商品总价
	private BigDecimal discountAmount; //优惠金额
	private BigDecimal transportFee; //运费
	private String tradeStatus;//交易状态
	private Date createTime;//交易时间
	private Date payTime;//支付时间
	private String warehouseType;//订单仓库
	private String buyerId;//注册用户ID
	private String nickName;//	买家名
	private String mobile;//买家手机号
	private String receiver;//收件人手机号
	private String logisticsValue;//收货区域
	private String address;//收货地址
	private String logisticsServiceId;//快递公司
	private String logisticsNums;//快递单号
	private String trackingStatus; //流转状态
	private String telephone;
	
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUserTradeNo() {
		return userTradeNo;
	}
	public void setUserTradeNo(String userTradeNo) {
		this.userTradeNo = userTradeNo;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public BigDecimal getTransportFee() {
		return transportFee;
	}
	public void setTransportFee(BigDecimal transportFee) {
		this.transportFee = transportFee;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
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
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
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
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
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
	public String getLogisticsServiceId() {
		return logisticsServiceId;
	}
	public void setLogisticsServiceId(String logisticsServiceId) {
		this.logisticsServiceId = logisticsServiceId;
	}
	public String getLogisticsNums() {
		return logisticsNums;
	}
	public void setLogisticsNums(String logisticsNums) {
		this.logisticsNums = logisticsNums;
	}
	public String getTrackingStatus() {
		return trackingStatus;
	}
	public void setTrackingStatus(String trackingStatus) {
		this.trackingStatus = trackingStatus;
	}






}

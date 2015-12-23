package com.need.domain.vo.trade;

import java.util.Date;
import java.util.List;

public class TradeBaseDetailVO {
	private String tradeNo;
	
	private String userOrderNo;

	private String userTradeNo;
	
	private double minCost;// 最低消费金额
	private String youhuiquanDescription;//优惠券描述

	private List<TradeBaseDetailGoodsVO> goodsList;

	private Float totalFee;

	private String nickName;

	private String mobile;

	private String tradeStatus;

	private String payChannel;
	
	private double payPrice;

	private Date tradeTime;
	private Date payTime;
	private Date sentTime;
	
	private String receiver;
	private String certificationCard;
	private String telephone;
	private String address;
	private String logisticsValue;
	private double yunfei;
	private double youhuiquan;
	private String duepay;
	
	private String logisticsNo;//快递单号
	private String serviceProvider;//快递公司
	private String certificationPositiveKey;//身份证正面
	private String certificationNegativeKey;//身份证反面
	private double privilege;//优惠
	
	private String isCheap;//是否为团便宜
	private String warehouseType;
	
	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getIsCheap() {
		return isCheap;
	}

	public void setIsCheap(String isCheap) {
		this.isCheap = isCheap;
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

	public String getDuepay() {
		return duepay;
	}

	public void setDuepay(String duepay) {
		this.duepay = duepay;
	}

	public double getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(double payPrice) {
		this.payPrice = payPrice;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public List<TradeBaseDetailGoodsVO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<TradeBaseDetailGoodsVO> goodsList) {
		this.goodsList = goodsList;
	}

	public Float getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Float totalFee) {
		this.totalFee = totalFee;
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

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
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

	public double getMinCost() {
		return minCost;
	}

	public void setMinCost(double minCost) {
		this.minCost = minCost;
	}

	public String getYouhuiquanDescription() {
		return youhuiquanDescription;
	}

	public void setYouhuiquanDescription(String youhuiquanDescription) {
		this.youhuiquanDescription = youhuiquanDescription;
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

	public static class TradeBaseDetailGoodsVO {
		private String goodsName;
		private Integer goodsCount;
		private String goodsCode;
		private String goodsBarcode;
		private String discountPrice;
		
		
		public String getDiscountPrice() {
			return discountPrice;
		}

		public void setDiscountPrice(String discountPrice) {
			this.discountPrice = discountPrice;
		}

		public String getGoodsBarcode() {
			return goodsBarcode;
		}

		public void setGoodsBarcode(String goodsBarcode) {
			this.goodsBarcode = goodsBarcode;
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

		public Integer getGoodsCount() {
			return goodsCount;
		}

		public void setGoodsCount(Integer goodsCount) {
			this.goodsCount = goodsCount;
		}

	}
}

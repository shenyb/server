package com.need.domain.vo.trade;

import java.io.Serializable;
import java.util.Date;

public class OrderExportResultVO implements Serializable {
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 7124479076510502099L;
	private String orderNo;
	private String userOrderNo;
	private String tradeNo;
	private String userTradeNo;
	private String nickName;
	private Date createTime;
	private Date payTime;
	private double payPrice;
	private String address;
	private String receiver;
	private String logisticsValue;
	private String country;
	private String province;
	private String city;
	private String area;
	private String telephone;
	private String goodsInfo;
	private String goodsName;
	private String goodsBarcode;
	private int buyCount;
	private String logisticsNo;
	private double youhuiquanValue;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserOrderNo() {
		return userOrderNo;
	}

	public void setUserOrderNo(String userOrderNo) {
		this.userOrderNo = userOrderNo;
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

	public double getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(double payPrice) {
		this.payPrice = payPrice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public String getLogisticsValue() {
		return logisticsValue;
	}

	public void setLogisticsValue(String logisticsValue) {
		this.logisticsValue = logisticsValue;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsBarcode() {
		return goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public double getYouhuiquanValue() {
		return youhuiquanValue;
	}

	public void setYouhuiquanValue(double youhuiquanValue) {
		this.youhuiquanValue = youhuiquanValue;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getUserTradeNo() {
		return userTradeNo;
	}

	public void setUserTradeNo(String userTradeNo) {
		this.userTradeNo = userTradeNo;
	}

	@Override
	public String toString() {
		return "OrderExportResultVO [orderNo=" + orderNo + ", userOrderNo=" + userOrderNo + ", tradeNo=" + tradeNo
				+ ", userTradeNo=" + userTradeNo + ", nickName=" + nickName + ", createTime=" + createTime
				+ ", payTime=" + payTime + ", payPrice=" + payPrice + ", address=" + address + ", receiver=" + receiver
				+ ", logisticsValue=" + logisticsValue + ", country=" + country + ", province=" + province + ", city="
				+ city + ", area=" + area + ", telephone=" + telephone + ", goodsInfo=" + goodsInfo + ", goodsName="
				+ goodsName + ", goodsBarcode=" + goodsBarcode + ", buyCount=" + buyCount + ", logisticsNo="
				+ logisticsNo + ", youhuiquanValue=" + youhuiquanValue + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(obj.equals(this.userTradeNo)){
			return true;
		}
		return false;
	}
	
}

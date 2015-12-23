package com.need.domain.vo.goods;

import java.io.Serializable;
import java.util.Date;

public class GoodsPriceDetailResultVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5240182495928405684L;

	private String goodsCode;
	private Integer pricechangeId;
	private String goodsBarcode;
	private String goodsName;
	private Double discountPrice;
	private Double originalPrice;
	private Double purchasePrice;
	private Double profit;
	private String pricechangeStatus;
	private String excuted;
	private String pricechangeType;
	private String userId;
	private String userName;
	private String userDept;
	private Date userTime;
	private Date startTime;
	private Date endTime;
	
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public Integer getPricechangeId() {
		return pricechangeId;
	}
	public void setPricechangeId(Integer pricechangeId) {
		this.pricechangeId = pricechangeId;
	}
	public String getGoodsBarcode() {
		return goodsBarcode;
	}
	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Double getProfit() {
		return profit;
	}
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	public String getPricechangeStatus() {
		return pricechangeStatus;
	}
	public void setPricechangeStatus(String pricechangeStatus) {
		this.pricechangeStatus = pricechangeStatus;
	}
	public String getExcuted() {
		return excuted;
	}
	public void setExcuted(String excuted) {
		this.excuted = excuted;
	}
	public String getPricechangeType() {
		return pricechangeType;
	}
	public void setPricechangeType(String pricechangeType) {
		this.pricechangeType = pricechangeType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDept() {
		return userDept;
	}
	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
	public Date getUserTime() {
		return userTime;
	}
	public void setUserTime(Date userTime) {
		this.userTime = userTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "GoodsPriceDetailResultVO [goodsCode=" + goodsCode + ", pricechangeId=" + pricechangeId
				+ ", goodsBarcode=" + goodsBarcode + ", goodsName=" + goodsName + ", discountPrice=" + discountPrice
				+ ", originalPrice=" + originalPrice + ", purchasePrice=" + purchasePrice + ", profit=" + profit
				+ ", pricechangeStatus=" + pricechangeStatus + ", excuted=" + excuted + ", pricechangeType="
				+ pricechangeType + ", userId=" + userId + ", userName=" + userName + ", userDept=" + userDept
				+ ", userTime=" + userTime + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
}

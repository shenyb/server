package com.need.domain.vo.cheap;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class CheapStatVO extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 7060767096904537558L;
	private String cheapNo;
	private String goodsCode;
	private String goodsId;
	private String goodsName;
	private int openCount;
	private int joinCount;
	private int duringTime;
	private int payCount;
	private String cheapPrice;
	private String onsalePrice;
	private int completeCount;
	private String discount;
	
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getCheapNo() {
		return cheapNo;
	}
	public void setCheapNo(String cheapNo) {
		this.cheapNo = cheapNo;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
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
	
	public int getOpenCount() {
		return openCount;
	}
	public void setOpenCount(int openCount) {
		this.openCount = openCount;
	}
	public int getJoinCount() {
		return joinCount;
	}
	public void setJoinCount(int joinCount) {
		this.joinCount = joinCount;
	}
	public int getDuringTime() {
		return duringTime;
	}
	public void setDuringTime(int duringTime) {
		this.duringTime = duringTime;
	}
	public int getPayCount() {
		return payCount;
	}
	public void setPayCount(int payCount) {
		this.payCount = payCount;
	}
	
	public String getCheapPrice() {
		return cheapPrice;
	}
	public void setCheapPrice(String cheapPrice) {
		this.cheapPrice = cheapPrice;
	}
	public String getOnsalePrice() {
		return onsalePrice;
	}
	public void setOnsalePrice(String onsalePrice) {
		this.onsalePrice = onsalePrice;
	}
	public int getCompleteCount() {
		return completeCount;
	}
	public void setCompleteCount(int completeCount) {
		this.completeCount = completeCount;
	}
	@Override
	public String toString() {
		return "CheapStatVO [cheapNo=" + cheapNo + ", goodsCode=" + goodsCode + ", goodsId=" + goodsId + ", goodsName="
				+ goodsName + ", openCount=" + openCount + ", joinCount=" + joinCount + ", duringTime=" + duringTime
				+ ", payCount=" + payCount + ", cheapPrice=" + cheapPrice + ", onsalePrice=" + onsalePrice
				+ ", completeCount=" + completeCount + "]";
	}
	
	
}

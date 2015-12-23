package com.need.domain.vo.trade;

import com.need.domain.pub.Page;

public class TradeBaseParam extends Page{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2415343786211313037L;
	
	private String tradeNo;
	private String tradeTimeStart;
	private String tradeTimeEnd;
	
	private String  tradeStatus;
	
	private String mobile;
	private String userTradeNo;
	private String telephone;
	
	private String warehouseType;
	private String originCallNo;
	private String custNo;
	
	private String goodsCode;
	//zhangmengbin
	private String storeStatus; //库存状态
	
	public String getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getOriginCallNo() {
		return originCallNo;
	}

	public void setOriginCallNo(String originCallNo) {
		this.originCallNo = originCallNo;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTradeTimeStart() {
		return tradeTimeStart;
	}

	public void setTradeTimeStart(String tradeTimeStart) {
		this.tradeTimeStart = tradeTimeStart;
	}

	public String getTradeTimeEnd() {
		return tradeTimeEnd;
	}

	public void setTradeTimeEnd(String tradeTimeEnd) {
		this.tradeTimeEnd = tradeTimeEnd;
	}

	public String getUserTradeNo() {
		return userTradeNo;
	}

	public void setUserTradeNo(String userTradeNo) {
		this.userTradeNo = userTradeNo;
	}

	@Override
	public String toString() {
		return "TradeBaseParam [tradeNo=" + tradeNo + ", tradeTimeStart=" + tradeTimeStart + ", tradeTimeEnd="
				+ tradeTimeEnd + ", tradeStatus=" + tradeStatus + ", mobile=" + mobile + ", userTradeNo=" + userTradeNo
				+ "]";
	}

}

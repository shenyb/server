package com.need.domain.vo.trade;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class TradeOrderParamsVO extends Page implements  Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -868347923506309684L;

	
	private String tradeTimeStart;
	private String tradeTimeEnd;
	private String mobile;
	private String tradeNo;
	private String tradeStatus;
	private String warehouseType;
	private String storeStatus; //库存状态
	
	public String getStoreStatus() {
		return storeStatus;
	}
	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	@Override
	public String toString() {
		return "TradeOrderParamsVO [tradeTimeStart=" + tradeTimeStart + ", tradeTimeEnd=" + tradeTimeEnd + ", mobile="
				+ mobile + ", tradeNo=" + tradeNo + ", tradeStatus=" + tradeStatus + ", warehouseType=" + warehouseType
				+ "]";
	}
	
}

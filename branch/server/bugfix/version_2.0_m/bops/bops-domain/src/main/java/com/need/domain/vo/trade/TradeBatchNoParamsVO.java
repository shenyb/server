package com.need.domain.vo.trade;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class TradeBatchNoParamsVO extends Page  implements  Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -868347923506309684L;

	
	private String batchNo;
	
	private String userTradeNo;
    
	private String tradeNo;
	
	

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


	public String getBatchNo() {
		return batchNo;
	}


	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
}

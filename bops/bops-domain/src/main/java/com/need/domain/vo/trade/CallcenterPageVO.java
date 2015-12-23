package com.need.domain.vo.trade;

import com.need.domain.pub.Page;

public class CallcenterPageVO extends Page{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1720526465746318656L;
	private String originCallNo;
	private String userTradeNo;
	private String telephone;
	private String custNo;
	

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getUserTradeNo() {
		return userTradeNo;
	}

	public void setUserTradeNo(String userTradeNo) {
		this.userTradeNo = userTradeNo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOriginCallNo() {
		return originCallNo;
	}

	public void setOriginCallNo(String originCallNo) {
		this.originCallNo = originCallNo;
	}
	
}

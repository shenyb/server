package com.need.common.domain.vo.trade;

public class ConfirmBuyReturnTradeInfoVO {
	/**
	 * long 
	 */
	private long createTime;
	private int payMoney;
	private String userTradeNo;
	private String isSupportALPAY;
	private String isSupportWECHAT;
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public int getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(int payMoney) {
		this.payMoney = payMoney;
	}
	public String getUserTradeNo() {
		return userTradeNo;
	}
	public void setUserTradeNo(String userTradeNo) {
		this.userTradeNo = userTradeNo;
	}
	public String getIsSupportALPAY() {
		return isSupportALPAY;
	}
	public void setIsSupportALPAY(String isSupportALPAY) {
		this.isSupportALPAY = isSupportALPAY;
	}
	public String getIsSupportWECHAT() {
		return isSupportWECHAT;
	}
	public void setIsSupportWECHAT(String isSupportWECHAT) {
		this.isSupportWECHAT = isSupportWECHAT;
	}
	
}

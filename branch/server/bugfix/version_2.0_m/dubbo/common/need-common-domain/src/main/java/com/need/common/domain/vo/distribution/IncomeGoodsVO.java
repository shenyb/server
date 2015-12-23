package com.need.common.domain.vo.distribution;

import java.io.Serializable;

public class IncomeGoodsVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6812417877943446519L;
	
	private String goodsId;
	private String goodsName;
	private String topPicKey;
	private Integer commission;
	private long inTime;
	private String userId;
	private String nickName;
	private String userTradeNo;
	private long tradeTime;
	private String commissionStatus;
	private String failReason;
	
	
	public String getTopPicKey() {
		return topPicKey;
	}
	public void setTopPicKey(String topPicKey) {
		this.topPicKey = topPicKey;
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
	public Integer getCommission() {
		return commission;
	}
	public void setCommission(Integer commission) {
		this.commission = commission;
	}
	public long getInTime() {
		return inTime;
	}
	public void setInTime(long inTime) {
		this.inTime = inTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserTradeNo() {
		return userTradeNo;
	}
	public void setUserTradeNo(String userTradeNo) {
		this.userTradeNo = userTradeNo;
	}
	public long getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(long tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getCommissionStatus() {
		return commissionStatus;
	}
	public void setCommissionStatus(String commissionStatus) {
		this.commissionStatus = commissionStatus;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	
	
	
}

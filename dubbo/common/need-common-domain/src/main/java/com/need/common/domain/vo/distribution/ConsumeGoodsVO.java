package com.need.common.domain.vo.distribution;

import java.io.Serializable;

public class ConsumeGoodsVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4921373950766032252L;
	
	
	private String goodsId;
	private String goodsName;
	private Integer commission;
	private String userTradeNo;
	private long tradeTime;
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
	
	
	
	
	
}

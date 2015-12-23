package com.need.common.domain.vo.trade;

import java.io.Serializable;

public class TradeCartCompleteParma implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -4713420231546046319L;
	
	
	private String userId;
	private String goodsList;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(String goodsList) {
		this.goodsList = goodsList;
	}
	
	
	
	
	
}

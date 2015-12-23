package com.need.show.web.controller.trade.vo;

import java.io.Serializable;

public class ShowTradeInfoVO implements  Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5139082490751617212L;
	

	private Integer  totalTradeNoCount;
	
	private Integer  totalTradePrice;
	
	private Integer  totalUserCount;

	public Integer getTotalTradeNoCount() {
		return totalTradeNoCount;
	}

	public void setTotalTradeNoCount(Integer totalTradeNoCount) {
		this.totalTradeNoCount = totalTradeNoCount;
	}

	public Integer getTotalTradePrice() {
		return totalTradePrice;
	}

	public void setTotalTradePrice(Integer totalTradePrice) {
		this.totalTradePrice = totalTradePrice;
	}

	public Integer getTotalUserCount() {
		return totalUserCount;
	}

	public void setTotalUserCount(Integer totalUserCount) {
		this.totalUserCount = totalUserCount;
	}

	
	
	
	
	
	
	
	
}

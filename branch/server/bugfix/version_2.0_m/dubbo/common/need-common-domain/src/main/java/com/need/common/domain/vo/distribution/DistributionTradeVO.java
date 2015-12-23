package com.need.common.domain.vo.distribution;

import java.io.Serializable;

public class DistributionTradeVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 934277864514227871L;
	
	private String buyerId;
	
	private String nickName;

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
	
}

package com.need.domain.vo.distribution;

import java.io.Serializable;
import java.util.Date;

public class DistributionUserStatisticsVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1736658836233105090L;
	private String nickName;
	private String mobile;
	private String amount;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "DistributionUserStatisticsVO [nickName=" + nickName + ", mobile=" + mobile + ", amount=" + amount + "]";
	}

	

	
}

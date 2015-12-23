package com.need.common.domain.vo.user;

import java.io.Serializable;

public class UserDataStatisticsVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7580354615390615672L;

	private Integer userFocus;	//用户关注的数量
	private Integer userFans;	//用户粉丝
	private Integer userOrder;	//用户订单数
	
	public Integer getUserFocus() {
		return userFocus;
	}
	public void setUserFocus(Integer userFocus) {
		this.userFocus = userFocus;
	}
	public Integer getUserFans() {
		return userFans;
	}
	public void setUserFans(Integer userFans) {
		this.userFans = userFans;
	}
	public Integer getUserOrder() {
		return userOrder;
	}
	public void setUserOrder(Integer userOrder) {
		this.userOrder = userOrder;
	}
	@Override
	public String toString() {
		return "UserDataStatisticsVO [userFocus=" + userFocus + ", userFans=" + userFans + ", userOrder=" + userOrder
				+ "]";
	}
	
}

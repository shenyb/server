package com.need.common.domain.vo.user;

public class UserExternVO {
	
	private int userFans;
	
	private int userFocus;
	
	private int userOrders;

	public int getUserFans() {
		return userFans;
	}

	public void setUserFans(int userFans) {
		this.userFans = userFans;
	}

	public int getUserFocus() {
		return userFocus;
	}

	public void setUserFocus(int userFocus) {
		this.userFocus = userFocus;
	}

	public int getUserOrders() {
		return userOrders;
	}

	public void setUserOrders(int userOrders) {
		this.userOrders = userOrders;
	}

	@Override
	public String toString() {
		return "UserExternVO [userFans=" + userFans + ", userFocus=" + userFocus + ", userOrders=" + userOrders + "]";
	}
	
}

package com.need.common.domain.vo.trade;

import java.io.Serializable;

public class DefaultAddressVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2824019665035493716L;
	
	private String oldAddressId;
	private String addressId;
	private String userId;
	public String getOldAddressId() {
		return oldAddressId;
	}
	public void setOldAddressId(String oldAddressId) {
		this.oldAddressId = oldAddressId;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "DefaultAddressVO [oldAddressId=" + oldAddressId + ", addressId=" + addressId + ", userId=" + userId
				+ "]";
	}
	
	

}

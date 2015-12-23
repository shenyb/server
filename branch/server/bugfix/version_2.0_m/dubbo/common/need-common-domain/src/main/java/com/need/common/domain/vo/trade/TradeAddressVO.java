package com.need.common.domain.vo.trade;

import java.io.Serializable;

public class TradeAddressVO implements Serializable{
	
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -8545685520272488658L;
	//地址信息
    private String addressId;
    private String receiver;
    private String telephone;   
    private String myAddress;//具体收货地址
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMyAddress() {
		return myAddress;
	}
	public void setMyAddress(String myAddress) {
		this.myAddress = myAddress;
	}
	@Override
	public String toString() {
		return "OrderAddressVO [addressId=" + addressId + ", receiver=" + receiver + ", telephone=" + telephone
				+ ", myAddress=" + myAddress + "]";
	}
	
	
    
    
    
    
}

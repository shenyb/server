package com.need.common.domain.vo.trade;

public class TradeAdrressAndPayChannelParamVO {
	
	private String tradeNo;

	private String address;

	private String logisticsValue;

	private String telephone;

	private String receiver;

	private String certificationCard;// 身份证号
	
	private String certificationPositiveKey;
	private String certificationNegativeKey;
	

	private String payChannel;
	private String addressId;
	
	

	public String getCertificationPositiveKey() {
		return certificationPositiveKey;
	}

	public void setCertificationPositiveKey(String certificationPositiveKey) {
		this.certificationPositiveKey = certificationPositiveKey;
	}

	public String getCertificationNegativeKey() {
		return certificationNegativeKey;
	}

	public void setCertificationNegativeKey(String certificationNegativeKey) {
		this.certificationNegativeKey = certificationNegativeKey;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogisticsValue() {
		return logisticsValue;
	}

	public void setLogisticsValue(String logisticsValue) {
		this.logisticsValue = logisticsValue;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getCertificationCard() {
		return certificationCard;
	}

	public void setCertificationCard(String certificationCard) {
		this.certificationCard = certificationCard;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	

}

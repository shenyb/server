package com.need.domain.po.api.trade;

import java.io.Serializable;
import java.util.Date;

public class TradeAddressPO implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -4645071206813980473L;

	private String addressId;

	private String userId;

	private Integer logisticsId;

	private String address;

	private Date createTime;

	private String logisticsValue;

	private String telephone;

	private String receiver;

	private String isDefault;

	private String certificationPositiveKey;// 身份证正面照片

	private String certificationNegativeKey;// 身份证反面照片

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

	public Integer getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

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

	@Override
	public String toString() {
		return "TradeAddressPO [addressId=" + addressId + ", userId=" + userId + ", logisticsId=" + logisticsId
				+ ", address=" + address + ", createTime=" + createTime + ", logisticsValue=" + logisticsValue
				+ ", telephone=" + telephone + ", receiver=" + receiver + ", isDefault=" + isDefault
				+ ", certificationPositiveKey=" + certificationPositiveKey + ", certificationNegativeKey="
				+ certificationNegativeKey + "]";
	}

}
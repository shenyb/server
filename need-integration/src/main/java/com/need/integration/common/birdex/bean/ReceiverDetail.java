package com.need.integration.common.birdex.bean;

import java.io.Serializable;

public class ReceiverDetail implements Serializable {

	/**
	 * <p>
	 * </p>
	 * 
	 * @author LV 2015年10月23日 上午11:42:59
	 * @ClassName ReceiverDetail
	 * @Description http://openapi.birdex.cn/help/Api/POST/order/create
	 * @version V1.0
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: LV 2015年10月23日
	 * @modify by reason:{方法名}:{原因}
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String phone;
	private String mobile;
	private String identityNumber;
	private String identityImages1;
	private String identityImages2;
	private Integer identityNumberGa;
	private String country;
	private String province;
	private String city;
	private String district;
	private String street;
	private String addressDetail;
	private String zipCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getIdentityImages1() {
		return identityImages1;
	}

	public void setIdentityImages1(String identityImages1) {
		this.identityImages1 = identityImages1;
	}

	public String getIdentityImages2() {
		return identityImages2;
	}

	public void setIdentityImages2(String identityImages2) {
		this.identityImages2 = identityImages2;
	}

	public Integer getIdentityNumberGa() {
		return identityNumberGa;
	}

	public void setIdentityNumberGa(Integer identityNumberGa) {
		this.identityNumberGa = identityNumberGa;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}

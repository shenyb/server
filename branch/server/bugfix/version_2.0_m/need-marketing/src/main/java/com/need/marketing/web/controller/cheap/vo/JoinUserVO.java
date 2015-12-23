package com.need.marketing.web.controller.cheap.vo;

import java.io.Serializable;

public class JoinUserVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -3322077296126960918L;
	
	private Integer cheapOpenId;
	private String profilePicKey;
	private String mobile;
	private String goodsName;
	
	
	
	
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getCheapOpenId() {
		return cheapOpenId;
	}
	public void setCheapOpenId(Integer cheapOpenId) {
		this.cheapOpenId = cheapOpenId;
	}
	public String getProfilePicKey() {
		return profilePicKey;
	}
	public void setProfilePicKey(String profilePicKey) {
		this.profilePicKey = profilePicKey;
	}
	
	
	
}

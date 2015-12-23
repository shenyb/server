package com.need.common.domain.vo.cheap;

import java.io.Serializable;

public class CheapShowStatus implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -3267848547422046391L;

	private String cheapNo;
	private String mobile;
	private String cheapOpenId;
	private String traded;
	private String cheapStatus;
	public String getCheapNo() {
		return cheapNo;
	}
	public void setCheapNo(String cheapNo) {
		this.cheapNo = cheapNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCheapOpenId() {
		return cheapOpenId;
	}
	public void setCheapOpenId(String cheapOpenId) {
		this.cheapOpenId = cheapOpenId;
	}
	public String getTraded() {
		return traded;
	}
	public void setTraded(String traded) {
		this.traded = traded;
	}
	public String getCheapStatus() {
		return cheapStatus;
	}
	public void setCheapStatus(String cheapStatus) {
		this.cheapStatus = cheapStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

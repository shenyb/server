package com.need.domain.vo.device;

import com.need.domain.po.api.device.DeviceFeedBackPO;

public class FeedBackVO extends DeviceFeedBackPO{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 3772929745064165694L;
	
	private String nickName;
	private String mobile;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

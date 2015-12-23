package com.need.integration.common.haimeiSdk.response.in;

import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class PushPurchaseResponse extends AbstractResponse<PushPurchaseResponse> {
	
	 private boolean flag ; 
	 
	 private String season ;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	} 
	 
	 
}

package com.need.integration.common.haimeiSdk.response.in;

import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class PushTransferOrderResponse  extends AbstractResponse<PushTransferOrderResponse>{
	private boolean flag ; 
	
	private String result ;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	} 
	
	
}

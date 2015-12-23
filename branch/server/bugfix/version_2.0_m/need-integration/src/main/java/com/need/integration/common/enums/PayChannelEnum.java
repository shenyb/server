package com.need.integration.common.enums;

/**
 * 
 * @author david.tan
 *
 */
public enum PayChannelEnum {
	
	ALIPAY("ALIPAY", "支付宝"),
	WECHAT("WECHAT", "微信");
	
	public String code;
	public String message;
	
	
	private PayChannelEnum(String code,String message){
		this.code=code;
		this.message=message;
	}
}



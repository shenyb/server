package com.need.gateway.common.enums;

/**
 * <p></p>
 * @author Rylan 2015年10月26日 下午3:00:17
 * @ClassName PushEnum
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年10月26日
 * @modify by reason:{方法名}:{原因}
 */
public enum PushEnum {

	
	PUSHALL("PUSHALL","PUSHALL"),PUSHREGISTRATIONIDS("PUSHREGISTRATIONIDS","PUSHREGISTRATIONIDS"),
	PUSHANDROIDS("PUSHANDROIDS","PUSHANDROIDS"),PUSHIOS("PUSHIOS","PUSHIOS"),PUSHALLWITHTAG("PUSHALLWITHTAG","PUSHALLWITHTAG");
	
	public String code;
	public String message;
	
	private PushEnum(String code,String message){
		this.code=code;
		this.message=message;
	}
	
}

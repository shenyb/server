package com.need.common.domain.enums;

import com.need.utils.StringUtil;

/**
 * 
 * <p></p>
 * @author peiboli 2015年8月20日 上午10:44:18
 * @ClassName DeviceChannelEnum
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月20日
 * @modify by reason:{方法名}:{原因}
 */
public enum DeviceChannelEnum {

	IOS("IOS", "苹果用户"), ANDROID("ANDROID", "安卓用户");
	public String code;
	public String desc;

	private DeviceChannelEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getCode(String userType) {
		String result = "";
		if (StringUtil.isBlank(userType)) {
			return result;
		}
		switch (userType) {
		case "IOS":
			result = IOS.code;
			break;
		case "ANDROID":
			result = ANDROID.code;
		default:
			break;
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(DeviceChannelEnum.IOS.code);
	}
	
}

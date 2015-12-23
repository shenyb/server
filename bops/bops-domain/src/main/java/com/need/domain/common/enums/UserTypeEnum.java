package com.need.domain.common.enums;

import com.need.utils.StringUtil;

/**
 * <p>
 * </p>
 * 
 * @author shenyb 2015年7月24日 下午11:51:05
 * @ClassName UserType
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年7月24日
 * @modify by reason:{方法名}:{原因}
 */
public enum UserTypeEnum {

	COMMON("COMMON", "普通用户"), KOL("KOL", "行家用户");
	public String code;
	public String desc;

	private UserTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getCode(String userType) {
		String result = "";
		if (StringUtil.isBlank(userType)) {
			return result;
		}
		switch (userType) {
		case "COMMON":
			result = COMMON.code;
			break;
		case "KOL":
			result = KOL.code;
		default:
			break;
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(UserTypeEnum.COMMON.code);
	}
	
}

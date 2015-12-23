package com.need.domain.common.enums;

import com.need.utils.StringUtil;
/**
 * 
 * <p></p>
 * @author peiboli 2015年12月3日 下午5:16:14
 * @ClassName UserStatusEnum
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年12月3日
 * @modify by reason:{方法名}:{原因}
 */
public enum UserStatusEnum {

	NORMAL("NORMAL", "正常"), FREEZE("FREEZE", "冻结");
	public String code;
	public String desc;

	private UserStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	
	
	public static void main(String[] args) {
		System.out.println(UserStatusEnum.FREEZE.code);
	}
	
}

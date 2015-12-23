package com.need.common.domain.enums;

/**
 * <p>
 * </p>
 * 
 * @author shenyb 2015年7月24日 下午11:56:25
 * @ClassName UserStatus
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年7月24日
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

	public static void main(String[] arg){
		System.out.println(UserStatusEnum.FREEZE.code);
	}
	
}

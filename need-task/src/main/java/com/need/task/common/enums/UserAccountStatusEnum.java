package com.need.task.common.enums;

public enum UserAccountStatusEnum {
	USE("USE", "正常"), FREEZE("FREEZE", "冻结");
	public String code;
	public String desc;

	private UserAccountStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}

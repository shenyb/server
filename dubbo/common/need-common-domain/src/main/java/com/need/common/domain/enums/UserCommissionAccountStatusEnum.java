package com.need.common.domain.enums;

public enum UserCommissionAccountStatusEnum {
	   USE("USE", "可用"), FREEZE("FREEZE", "冻结");

	public String status;
	public String desc;

	private UserCommissionAccountStatusEnum(String status, String desc) {
		this.status = status;
		this.desc = desc;
	}
}

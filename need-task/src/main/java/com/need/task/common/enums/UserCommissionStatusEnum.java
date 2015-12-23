package com.need.task.common.enums;

public enum UserCommissionStatusEnum {
	   WAIT_TO_IN("WAIT_TO_IN", "待入账"), 
	   FAIL_INCOME("FAIL_INCOME", "退款，入账失败"),
	   HAS_INCOME("HAS_INCOME", "已入账");

	public String status;
	public String desc;

	private UserCommissionStatusEnum(String status, String desc) {
		this.status = status;
		this.desc = desc;
	}
}

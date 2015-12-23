package com.need.common.domain.enums;
public enum UserCommissionAccountOperateTypeEnum {
	/**交易消费*/
	TRADE_CONFIRM("TRADE_CONFIRM", "交易消费"),
	 /**佣金进账*/
	INCOME_BY_COMMISSION("INCOME_BY_COMMISSION", "佣金进账"),
	/**退货退款入账*/
	TRADE_REFUND_SUCCESS("TRADE_REFUND_SUCCESS", "退货退款入账"),
	 /**交易关闭入账*/
	TRADE_CLOSE_SUCCESS("TRADE_CLOSE_SUCCESS", "交易关闭入账"),
	/**取消订单入账*/
	TRADE_CANCEL_SUCCESS("TRADE_CANCEL_SUCCESS", "取消订单入账");

	public String code;
	public String desc;

	UserCommissionAccountOperateTypeEnum(String status, String desc) {
		this.code = status;
		this.desc = desc;
	}
}
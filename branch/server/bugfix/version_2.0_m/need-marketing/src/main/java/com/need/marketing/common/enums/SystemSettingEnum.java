package com.need.marketing.common.enums;

/**
 * 
 * @author YAN 2015-12-12 12:27:04
 * @ClassName SystemSettingEnum
 * @Description TODO
 * @version V2.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: YAN 2015-12-12
 * @modify by reason:{方法名}:{原因}
 */
public enum SystemSettingEnum {

	REGISTER_COUPON_TEMPLATE_ID("REGISTER_COUPON_TEMPLATE_ID","个人中心分享优惠券id"),
	PAYED_COUPON_TEMPLATE_ID("PAYED_COUPON_TEMPLATE_ID", "支付完成分享优惠券id"),
    
	TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE_LIMIT("TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE_LIMIT", "海外仓包邮限制"),
	TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE("TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE", "海外仓邮费"),
	TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE_LIMIT("TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE_LIMIT", "自营仓包邮限制"),
	TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE("TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE", "自营仓邮费"),
	TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE_LIMIT("TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE_LIMIT", "保税仓包邮限制"),
	TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE("TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE","保税仓邮费");

	
	public String code;
	public String desc;

	private SystemSettingEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
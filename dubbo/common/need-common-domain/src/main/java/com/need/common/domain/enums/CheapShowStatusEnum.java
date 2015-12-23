package com.need.common.domain.enums;

public enum CheapShowStatusEnum {

	ORDERS("ORDERS", "已下单"),
	    OPEN("OPEN", "开团中"),
	    NORMAL("NORMAL", "正常"),
	    WAITPAY("WAITPAY", "待支付");
	    
		public String status;
		public String desc;
	    
	    private CheapShowStatusEnum(String status, String desc) {
	        this.status = status;
	        this.desc = desc;
	    }
}

package com.need.domain.common.enums;

public enum ExpressEnums {
	ESPRESSNO("logisticsNums", "运单号"),
	ESPRESSNAME("logisticsServiceId", "快递公司");
	public String code;
	public String desc;
    
    private ExpressEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

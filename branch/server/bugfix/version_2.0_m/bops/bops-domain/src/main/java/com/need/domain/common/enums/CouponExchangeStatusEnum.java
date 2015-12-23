/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.domain.common.enums;

/**
 *
 * @author 庆凯
 */
public enum CouponExchangeStatusEnum {
    
    DRAFT("DRAFT", "草稿"),
    REJECT("REJECT", "驳回"),
    INVALID("INVALID", "已生效"),
    VALID("VALID", "已生效"),
    FROZEN("FROZEN", "已冻结");
    
	public String status;
	public String desc;
    
    private CouponExchangeStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

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
public enum CouponStatusEnum {
    
    UN_REGISTER("unRegister", "未注册发放"),
    REGISTERED("registered", "已注册发放"),
    USEABLE("useable","当前可用"),
    DRAFT("draft", "草稿"),
    REJECT("reject", "驳回"),
    INVALID("invalid", "已失效"),
    VALID("valid", "已生效"),
    FROZEN("frozen", "已冻结"),
    NOT_USE("notUse", "未使用"),
    PRE_PAY("prePay", "待支付"),
    USED("used", "已使用"),
    OUT("out", "已领完");
    
	public String status;
	public String desc;
    
    private CouponStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

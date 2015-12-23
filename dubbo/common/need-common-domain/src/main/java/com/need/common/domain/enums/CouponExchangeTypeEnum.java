/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.domain.enums;

/**
 *
 * @author 庆凯
 */
public enum CouponExchangeTypeEnum {
    
    ALL("ALL", "所有用户可以领取"),
    NOTRADE("NOTRADE", "没有下过订单的用户可以领取"),
    SINGLE("SINGLE", "单用户可以领取");
    
	public String status;
	public String desc;
    
    private CouponExchangeTypeEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

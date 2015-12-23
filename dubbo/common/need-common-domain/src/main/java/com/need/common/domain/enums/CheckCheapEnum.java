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
public enum CheckCheapEnum {
    
    OPEN("OPEN", "去开团"),
    CALL("CALL", "招呼好友"),
    PAY("PAY", "已付款"),
    SOLDOUT("SOLDOUT", "商品售罄"),
    NOTPAY("NOTPAY", "未支付");
    
	public String status;
	public String desc;
    
    private CheckCheapEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

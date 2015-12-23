/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.marketing.common.enums;

/**
 *
 * @author 庆凯
 */
public enum CheckCheapEnum {
    
    OPEN("OPEN", "去开团"),
    CALL("CALL", "招呼好友"),
    PAY("PAY", "已付款"),
    SOLDOUT("SOLDOUT", "商品售罄"),
    NOTPAY("NOTPAY", "未支付"),
    MYCLOSE("MYCLOSE", "我结止的团"),
    VALIDCLOSE("VALIDCLOSE", "我开的团中结止的团"),
    VALIDCPAY("VALIDPAY", "我开的团中已支付"),
	CLOSE("CLOSE","团截至"),
	FROZEN("FROZEN","已冻结"),
	OPENFAIL("OPENFAIL","已冻结");
	
    
	public String status;
	public String desc;
    
    private CheckCheapEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

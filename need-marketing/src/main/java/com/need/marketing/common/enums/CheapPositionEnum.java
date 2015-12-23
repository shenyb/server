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
public enum CheapPositionEnum {
    
    ALL("all", "团便宜列表"),
    VALID("valid", "我的团"),
    CLOSE("close", "已结束的团");
    
	public String status;
	public String desc;
    
    private CheapPositionEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

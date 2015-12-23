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
public enum FeedStatusEnums {
    
    VALID("VALID", "已生效"),
    CANCELED("CANCELED", "已取消"),
    DELETED("DELETED", "已删除"),
    FROZEN("FROZEN", "已冻结");
    
	public String status;
	public String desc;
    
    private FeedStatusEnums(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

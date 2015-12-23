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
public enum ListStatusEnum {
    
    REFRESH("REFRESH", "刷新"),
    MIDDILE("MIDDILE", "到底了"),
    TOP("TOP", "到顶了"),
    BOTTOM("BOTTOM", "还有数据");
    
	public String status;
	public String desc;
    
    private ListStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
    
}

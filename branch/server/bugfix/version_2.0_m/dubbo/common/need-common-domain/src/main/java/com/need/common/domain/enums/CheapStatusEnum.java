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
public enum CheapStatusEnum {
    
    OPEN("OPEN", "开团"),
    COMPLETE("COMPLETE", "成团"),
    CLOSED("CLOSED", "已关闭");
    
	public String status;
	public String desc;
    
    private CheapStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

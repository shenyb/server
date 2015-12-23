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
public enum CheapStatusEnum {
    
    DRAFT("DRAFT", "草稿"),
    REJECT("REJECT", "驳回"),
    INVALID("INVALID", "待审核"),
    VALID("VALID", "生效"),
	FROZEN("FROZEN", "冻结");
    
	public String status;
	public String desc;
    
    private CheapStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

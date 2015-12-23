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
public enum CouponTypeEnum {
    
    FULL_CUT("fullCut", "满减"),
    USE_RETURN("useReturn", "使用后返还邀请人"),
    CASH("cash", "现金券"),
    NEW_USER("newUser", "新人券");
    
	public String status;
	public String desc;
    
    private CouponTypeEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

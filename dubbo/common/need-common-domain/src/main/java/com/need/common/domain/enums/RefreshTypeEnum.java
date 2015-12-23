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
public enum RefreshTypeEnum {
    
    UP("UP", "上拉加载"),
    DOWN("DOWN", "下拉刷新");
    
	public String status;
	public String desc;
    
    private RefreshTypeEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
    
}

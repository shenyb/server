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
public enum FollowStatusEnum {
    
    NEITHER("NEITHER", "互不关注"),
    FOLLOW("FOLLOW", "关注了"),
    FOLLOWED("FOLLOWED", "被关注"),
    BOTH("BOTH", "互相关注");
    
	public String status;
	public String desc;
    
    private FollowStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
    
}

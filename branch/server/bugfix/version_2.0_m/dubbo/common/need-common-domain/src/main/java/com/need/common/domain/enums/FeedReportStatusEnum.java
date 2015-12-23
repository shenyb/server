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
public enum FeedReportStatusEnum {
    
    VALID("VALID", "未处理"),
    FROZEN("FROZEN", "封禁用户"),
    IGNORE("IGNORE", "暂不处理");
    
	public String status;
	public String desc;
    
    private FeedReportStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

package com.need.integration.common.haimeiSdk.domain.base;

/**
 * 业务单位
 * @author 徐纯
 *
 *  2015-6-24 下午02:55:44
 */
public class Unit {
	Long id;
	String name;
	Long type ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
}

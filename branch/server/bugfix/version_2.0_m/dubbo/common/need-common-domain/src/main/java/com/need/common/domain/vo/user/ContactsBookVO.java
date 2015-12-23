package com.need.common.domain.vo.user;

import java.io.Serializable;

public class ContactsBookVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8934908313361332266L;
	private String name;
	private String phone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "ContactsBookVO [name=" + name + ", phone=" + phone + "]";
	}
	

}

package com.need.domain.vo.user;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class QueryBopsUserVO extends Page implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 3989128807758297147L;
	
	private String userName;
	
	private String userDept;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	@Override
	public String toString() {
		return "QueryBopsUserVO [userName=" + userName + ", userDept=" + userDept + "]";
	}
	
	

}

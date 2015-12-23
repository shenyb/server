package com.need.common.domain.vo.need;

import java.io.Serializable;

public class FocusKolListParam implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4603514847900852169L;

	private String userId;
	private String KolIds;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getKolIds() {
		return KolIds;
	}
	public void setKolIds(String kolIds) {
		KolIds = kolIds;
	}
	@Override
	public String toString() {
		return "FocusKolListParam [userId=" + userId + ", KolIds=" + KolIds + "]";
	}
	
	
	
}

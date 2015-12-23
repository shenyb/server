package com.need.common.domain.vo.need;

import java.io.Serializable;

public class FocusKolListVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7002731978370254736L;
	
	private String userId;
	private String kolId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getKolId() {
		return kolId;
	}
	public void setKolId(String kolId) {
		this.kolId = kolId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}

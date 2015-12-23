package com.need.common.domain.vo.cheap;

import com.need.common.domain.po.api.cheap.CheapBasePO;

import java.io.Serializable;


public class CheapBaseBymobileVO extends CheapBasePO  implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -3816343999371217143L;
	private String cheapShowStatus;
	
	public String getCheapShowStatus() {
		return cheapShowStatus;
	}
	public void setCheapShowStatus(String cheapShowStatus) {
		this.cheapShowStatus = cheapShowStatus;
	}
	
	
}

package com.need.common.domain.vo.cheap;

import com.need.common.domain.po.api.cheap.CheapBasePO;

public class CheapVO extends CheapBasePO {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;
	private String openCheapStatus;
	private String traded;
	public String getOpenCheapStatus() {
		return openCheapStatus;
	}
	public void setOpenCheapStatus(String openCheapStatus) {
		this.openCheapStatus = openCheapStatus;
	}
	public String getTraded() {
		return traded;
	}
	public void setTraded(String traded) {
		this.traded = traded;
	}
	
}

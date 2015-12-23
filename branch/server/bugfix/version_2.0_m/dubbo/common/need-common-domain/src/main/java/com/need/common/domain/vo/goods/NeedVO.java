package com.need.common.domain.vo.goods;

import java.io.Serializable;

public class NeedVO implements Serializable{

	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -193794445147676848L;

	private Integer needCount;
	
	private String isNeed;

	public Integer getNeedCount() {
		return needCount;
	}

	public void setNeedCount(Integer needCount) {
		this.needCount = needCount;
	}

	public String getIsNeed() {
		return isNeed;
	}

	public void setIsNeed(String isNeed) {
		this.isNeed = isNeed;
	}

	@Override
	public String toString() {
		return "NeedVO [needCount=" + needCount + ", isNeed=" + isNeed + "]";
	}
	
}

package com.need.common.domain.vo.ops;

public class NeedVO {

	private int needCount;
	
	private String isNeed;

	public int getNeedCount() {
		return needCount;
	}

	public void setNeedCount(int needCount) {
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

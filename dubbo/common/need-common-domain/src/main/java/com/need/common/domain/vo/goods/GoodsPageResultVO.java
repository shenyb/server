package com.need.common.domain.vo.goods;

import java.io.Serializable;
import java.util.List;

public class GoodsPageResultVO implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1869835124125209236L;

	private long totalCount;
	
	private List<GoodsNeedResultVO> goodsList;

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<GoodsNeedResultVO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<GoodsNeedResultVO> goodsList) {
		this.goodsList = goodsList;
	}

}

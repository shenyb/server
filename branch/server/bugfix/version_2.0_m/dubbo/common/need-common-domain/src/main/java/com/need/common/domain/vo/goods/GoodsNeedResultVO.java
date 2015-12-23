package com.need.common.domain.vo.goods;

import java.io.Serializable;


public class GoodsNeedResultVO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 751878697011328318L;


	private GoodsVO goods;
	
	private NeedVO need;

	public GoodsVO getGoods() {
		return goods;
	}

	public void setGoods(GoodsVO goods) {
		this.goods = goods;
	}

	public NeedVO getNeed() {
		return need;
	}

	public void setNeed(NeedVO need) {
		this.need = need;
	}

	@Override
	public String toString() {
		return "GoodsNeedResultVO [goods=" + goods + ", need=" + need + "]";
	}
	
}

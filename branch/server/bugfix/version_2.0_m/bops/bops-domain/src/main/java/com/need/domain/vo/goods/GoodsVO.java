package com.need.domain.vo.goods;

import java.io.Serializable;
import java.util.Arrays;

public class GoodsVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 498420673705044843L;
	
	private String[] goodsIds;
	
	private String[] goodsCodes;
	
	

	public String[] getGoodsCodes() {
		return goodsCodes;
	}

	public void setGoodsCodes(String[] goodsCodes) {
		this.goodsCodes = goodsCodes;
	}

	public String[] getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(String[] goodsIds) {
		this.goodsIds = goodsIds;
	}

	@Override
	public String toString() {
		return "GoodsVO [goodsIds=" + Arrays.toString(goodsIds)
				+ ", goodsCodes=" + Arrays.toString(goodsCodes) + "]";
	}
	
	
	
	

}

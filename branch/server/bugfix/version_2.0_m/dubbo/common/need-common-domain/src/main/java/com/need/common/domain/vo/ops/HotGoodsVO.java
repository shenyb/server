package com.need.common.domain.vo.ops;

import java.io.Serializable;

public class HotGoodsVO implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1239742908543543713L;
	private OpsHotGoodsVO goods;
	private NeedVO need;
	public OpsHotGoodsVO getGoods() {
		return goods;
	}
	public void setGoods(OpsHotGoodsVO goods) {
		this.goods = goods;
	}
	public NeedVO getNeed() {
		return need;
	}
	public void setNeed(NeedVO need) {
		this.need = need;
	}
	
	
}

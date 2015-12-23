package com.need.common.domain.vo.ops;



public class XinhuanGoodsResultVO {
	
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
		return "XinhuanGoodsResultVO [goods=" + goods + ", need=" + need + "]";
	}
	
}

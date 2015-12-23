package com.need.common.domain.vo.trade;

public class CreateCheapTradeParamVO extends CreateTradeBaseParamVO {
	private int cheapOpenId;

	private int cheapPrice;
	private String GoodsId;
	public int getCheapOpenId() {
		return cheapOpenId;
	}

	public void setCheapOpenId(int cheapOpenId) {
		this.cheapOpenId = cheapOpenId;
	}

	public int getCheapPrice() {
		return cheapPrice;
	}

	public void setCheapPrice(int cheapPrice) {
		this.cheapPrice = cheapPrice;
	}

	public String getGoodsId() {
		return GoodsId;
	}

	public void setGoodsId(String goodsId) {
		GoodsId = goodsId;
	}

	
}

package com.need.common.domain.vo.trade;

import java.io.Serializable;

public class TradeCartGoodsItemVO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private GoodsMainVO goods;
	private String cartId;
	private int goodsCount;
	public GoodsMainVO getGoods() {
		return goods;
	}
	public void setGoods(GoodsMainVO goods) {
		this.goods = goods;
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	
	

}

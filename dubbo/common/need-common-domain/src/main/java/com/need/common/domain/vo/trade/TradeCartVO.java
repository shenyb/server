package com.need.common.domain.vo.trade;

import java.io.Serializable;

public class TradeCartVO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private String cartId;

	private Integer goodsCount;

	private TradeCartGoodsVO goods;

	private Integer onsalePrice;

	public Integer getOnsalePrice() {
		return onsalePrice;
	}

	public void setOnsalePrice(Integer onsalePrice) {
		this.onsalePrice = onsalePrice;
	}

	private String goodsId;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public TradeCartGoodsVO getGoods() {
		return goods;
	}

	public void setGoods(TradeCartGoodsVO goods) {
		this.goods = goods;
	}

	private Integer count;// 数量

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "TradeCartVO [cartId=" + cartId + ", goodsCount=" + goodsCount + ", goods=" + goods + ", onsalePrice="
				+ onsalePrice + ", goodsId=" + goodsId + ", count=" + count + "]";
	}
	
	

}

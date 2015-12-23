package com.need.common.domain.vo.trade;

import java.io.Serializable;

public class TradeCartGoodsVO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private String goodsId;
	private String goodsName;
	private int onsalePrice;
	private int discountPrice;
	private String topicPicKey;
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getOnsalePrice() {
		return onsalePrice;
	}
	public void setOnsalePrice(int onsalePrice) {
		this.onsalePrice = onsalePrice;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getTopicPicKey() {
		return topicPicKey;
	}
	public void setTopicPicKey(String topicPicKey) {
		this.topicPicKey = topicPicKey;
	}

}

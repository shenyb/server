package com.need.common.domain.vo.trade;

import java.io.Serializable;

public class GoodsMainVO implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 804648369802966734L;

	private String goodsId;

	private String goodsName;

	private String topPicKey;

	private Integer discountPrice;

	private Integer onsalePrice;

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

	public String getTopPicKey() {
		return topPicKey;
	}

	public void setTopPicKey(String topPicKey) {
		this.topPicKey = topPicKey;
	}

	public Integer getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Integer getOnsalePrice() {
		return onsalePrice;
	}

	public void setOnsalePrice(Integer onsalePrice) {
		this.onsalePrice = onsalePrice;
	}

	

}
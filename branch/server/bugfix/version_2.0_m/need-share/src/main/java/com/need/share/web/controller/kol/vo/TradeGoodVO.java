package com.need.share.web.controller.kol.vo;

import java.io.Serializable;

public class TradeGoodVO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -1647303133183173580L;

	private String goodsId;

	private String goodsName;
	private String ownPicKey;

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


	public String getOwnPicKey() {
		return ownPicKey;
	}

	public void setOwnPicKey(String ownPicKey) {
		this.ownPicKey = ownPicKey;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

package com.need.common.domain.vo.ops;

import java.io.Serializable;

public class OpsSelectedGoodsVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 3747574063653466297L;
	
	
	private String discountFeedId;
	private String  goodsId;
	private String goodsName;
	private int goodsOriginalPrice;
	private int goodsDiscountPrice;
	private String goodsDesc;
	private String goodsImgKey;
	private int needCount;
	public String getDiscountFeedId() {
		return discountFeedId;
	}
	public void setDiscountFeedId(String discountFeedId) {
		this.discountFeedId = discountFeedId;
	}
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
	public int getGoodsOriginalPrice() {
		return goodsOriginalPrice;
	}
	public void setGoodsOriginalPrice(int goodsOriginalPrice) {
		this.goodsOriginalPrice = goodsOriginalPrice;
	}
	public int getGoodsDiscountPrice() {
		return goodsDiscountPrice;
	}
	public void setGoodsDiscountPrice(int goodsDiscountPrice) {
		this.goodsDiscountPrice = goodsDiscountPrice;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getGoodsImgKey() {
		return goodsImgKey;
	}
	public void setGoodsImgKey(String goodsImgKey) {
		this.goodsImgKey = goodsImgKey;
	}
	public int getNeedCount() {
		return needCount;
	}
	public void setNeedCount(int needCount) {
		this.needCount = needCount;
	}
	
	
	
	
	
}

package com.need.common.domain.vo.ops;

import java.io.Serializable;

public class OpsHotGoodsVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1025444747545686086L;
	
	
	private String goodsId;
	private String goodsName;
	private Integer onsalePrice;
	private Integer discountPrice;
	private String brief;
	private String topicPicKey;
	
	
	

	public String getTopicPicKey() {
		return topicPicKey;
	}
	public void setTopicPicKey(String topicPicKey) {
		this.topicPicKey = topicPicKey;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
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
	
	public Integer getOnsalePrice() {
		return onsalePrice;
	}
	public void setOnsalePrice(Integer onsalePrice) {
		this.onsalePrice = onsalePrice;
	}
	public Integer getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	
	
	
}

package com.need.common.domain.vo.distribution;

import java.io.Serializable;

public class DistributionGoodsVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -872463281247084695L;
	
	
	private String goodsId;
	
	private String topicPicKey;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getTopicPicKey() {
		return topicPicKey;
	}

	public void setTopicPicKey(String topicPicKey) {
		this.topicPicKey = topicPicKey;
	}
	

	
	
	
}

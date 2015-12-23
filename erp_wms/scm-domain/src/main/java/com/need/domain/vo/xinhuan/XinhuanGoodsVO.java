package com.need.domain.vo.xinhuan;

import java.io.Serializable;
import java.util.Arrays;

public class XinhuanGoodsVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 7370810116372577697L;
	
	private String id;
	
	private String opsId;
	
	private String [] goodsIds;
	
	private String goodsCodes;
	
	private Integer goodsScore;
	
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getGoodsScore() {
		return goodsScore;
	}

	public void setGoodsScore(Integer goodsScore) {
		this.goodsScore = goodsScore;
	}

	public String getGoodsCodes() {
		return goodsCodes;
	}

	public void setGoodsCodes(String goodsCodes) {
		this.goodsCodes = goodsCodes;
	}

	public String[] getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(String[] goodsIds) {
		this.goodsIds = goodsIds;
	}
	
	public String getOpsId() {
		return opsId;
	}

	public void setOpsId(String opsId) {
		this.opsId = opsId;
	}

	@Override
	public String toString() {
		return "XinhuanGoodsVO [opsId=" + opsId + ", goodsIds=" + Arrays.toString(goodsIds) + "]";
	}
	
}

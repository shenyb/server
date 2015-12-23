package com.need.domain.vo.goodsgroup;

import java.io.Serializable;

import com.need.domain.po.bops.goodsgroup.BopsGoodsGroupBase;

public class GoodsGroupResultVO extends BopsGoodsGroupBase implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	
	private static final long serialVersionUID = 8767196513114981880L;
	
	private String userRealName;
	
	private Integer goodsNumber;
	
	private Integer saledNumber;
	
	
	

	public Integer getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public Integer getSaledNumber() {
		return saledNumber;
	}

	public void setSaledNumber(Integer saledNumber) {
		this.saledNumber = saledNumber;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	
	
	
}

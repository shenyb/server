package com.need.common.domain.vo.trade;

import java.io.Serializable;

public class EditTradeCardParma implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 5602543060485934642L;
	
	private int goodsCount;
	private String cartId;
	private String userId;
	private int operType;
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getOperType() {
		return operType;
	}
	public void setOperType(int operType) {
		this.operType = operType;
	}
	@Override
	public String toString() {
		return "EditTradeCardParma [goodsCount=" + goodsCount + ", cartId=" + cartId + ", userId=" + userId
				+ ", operType=" + operType + "]";
	}
	
	
	
	
	
}

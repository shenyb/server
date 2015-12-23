package com.need.integration.common.birdex.bean.orderResult;

import java.io.Serializable;

public class ItemVO implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 7334811428864255379L;
	/**
	 * 用户产品唯一编号
	 */
	private String itemNo;
	/**
	 * 问题商品个数
	 */
	private Integer itemErrorQty;
	/**
	 * 问题商品状态码
		ITEM_UNDERSTOCK:库存不足
		ITEM_PROHIBIT:禁运品
		ITEM_OTHER:其他
	 */
	private String itemErrorType;
	/**
	 * 其它问题原因  笨鸟提供文字描述
	 */
	private String itemErrorMessage;
	/**
	 * 问题商品图片
	 */
	private String itemErrorImgUrl;
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public Integer getItemErrorQty() {
		return itemErrorQty;
	}
	public void setItemErrorQty(Integer itemErrorQty) {
		this.itemErrorQty = itemErrorQty;
	}
	public String getItemErrorType() {
		return itemErrorType;
	}
	public void setItemErrorType(String itemErrorType) {
		this.itemErrorType = itemErrorType;
	}
	public String getItemErrorMessage() {
		return itemErrorMessage;
	}
	public void setItemErrorMessage(String itemErrorMessage) {
		this.itemErrorMessage = itemErrorMessage;
	}
	public String getItemErrorImgUrl() {
		return itemErrorImgUrl;
	}
	public void setItemErrorImgUrl(String itemErrorImgUrl) {
		this.itemErrorImgUrl = itemErrorImgUrl;
	}
	@Override
	public String toString() {
		return "ItemVO [itemNo=" + itemNo + ", itemErrorQty=" + itemErrorQty + ", itemErrorType=" + itemErrorType
				+ ", itemErrorMessage=" + itemErrorMessage + ", itemErrorImgUrl=" + itemErrorImgUrl + "]";
	}
}

package com.need.common.domain.vo.trade;

import java.io.Serializable;
import java.util.Date;

public class TradeBaseResultVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2223985860049147894L;
	/**常量------------------------------------------------------------------*/

	/**类变量----------------------------------------------------------------*/

	/**实例变量--------------------------------------------------------------*/

	/**共有字段--------------------------------------------------------------*/

	/**受保护字段------------------------------------------------------------*/

	/**私有字段--------------------------------------------------------------*/
	private String orderNo;

    private String tradeNo;

    private String orderStatus;

    private String tradeStatus;

    private String buyerId;

    private String goodsId;

    private Integer buyCount;

    private Integer buyPrice;

    private Integer totalPrice;

    private String payChannel;

    private Integer payPrice;

    private Date createTime;

    private Date payTime;

    private Date tradeTime;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public Integer getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Integer buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public Integer getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Integer payPrice) {
		this.payPrice = payPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	@Override
	public String toString() {
		return "TradeBaseVO [orderNo=" + orderNo + ", tradeNo=" + tradeNo + ", orderStatus=" + orderStatus
				+ ", tradeStatus=" + tradeStatus + ", buyerId=" + buyerId + ", goodsId=" + goodsId + ", buyCount="
				+ buyCount + ", buyPrice=" + buyPrice + ", totalPrice=" + totalPrice + ", payChannel=" + payChannel
				+ ", payPrice=" + payPrice + ", createTime=" + createTime + ", payTime=" + payTime + ", tradeTime="
				+ tradeTime + "]";
	}

	/**构造方法--------------------------------------------------------------*/

	/**公共方法--------------------------------------------------------------*/

	/**受保护方法------------------------------------------------------------*/

	/**私有方法--------------------------------------------------------------*/

	/**重载Object方法--------------------------------------------------------*/

	/**get/set方法-----------------------------------------------------------*/
    
    
}

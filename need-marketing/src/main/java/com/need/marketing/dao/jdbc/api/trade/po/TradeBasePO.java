package com.need.marketing.dao.jdbc.api.trade.po;

import java.io.Serializable;
import java.util.Date;

import com.need.trade.enums.TradeStatus;

public class TradeBasePO implements Serializable{
	
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 2568536897666934438L;

	private String orderNo;

    private String tradeNo;

    private TradeStatus orderStatus;

    private TradeStatus tradeStatus;

    private String buyerId;

    private String goodsId;

    private int buyCount;

    private int buyPrice;

    private int totalPrice;

    private String payChannel;

    private int payPrice;

    private Date createTime;

    private Date payTime;

    private Date tradeTime;

    private String addressId;

    private String userTradeNo;//Addy liyongran 20150922 新增用户交易编号
    
    private String userOrderNo;//Addy liyongran 20150922 新增用户交易子编号
    
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

    public TradeStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(TradeStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public TradeStatus getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(TradeStatus tradeStatus) {
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

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
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

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    
    
	public String getUserTradeNo() {
		return userTradeNo;
	}

	public void setUserTradeNo(String userTradeNo) {
		this.userTradeNo = userTradeNo;
	}

	public String getUserOrderNo() {
		return userOrderNo;
	}

	public void setUserOrderNo(String userOrderNo) {
		this.userOrderNo = userOrderNo;
	}

	@Override
	public String toString() {
		return "TradeBasePO [orderNo=" + orderNo + ", tradeNo=" + tradeNo + ", orderStatus=" + orderStatus
				+ ", tradeStatus=" + tradeStatus + ", buyerId=" + buyerId + ", goodsId=" + goodsId + ", buyCount="
				+ buyCount + ", buyPrice=" + buyPrice + ", totalPrice=" + totalPrice + ", payChannel=" + payChannel
				+ ", payPrice=" + payPrice + ", createTime=" + createTime + ", payTime=" + payTime + ", tradeTime="
				+ tradeTime + ", addressId=" + addressId + "]";
	}
    
    
}
package com.need.domain.po.api.trade;

import java.io.Serializable;
import java.util.Date;

public class TradeBasePO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5728424593318315963L;

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

    private String addressId;
    
    private String userTradeNo;
    
    private String userOrderNo;
    
    private String alipayRetrieveStatus;
    
    private String address;
    
    private String certificationCard;
    
    private String telephone;
    
    private String receiver;
    
    private String logisticsValue;
    
    private String transportFee;
    
    private String isNormal; //是否缺货 //true：正常   缺货：false   

    public String getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(String transportFee) {
		this.transportFee = transportFee;
	}

	public String getAlipayRetrieveStatus() {
		return alipayRetrieveStatus;
	}

	public void setAlipayRetrieveStatus(String alipayRetrieveStatus) {
		this.alipayRetrieveStatus = alipayRetrieveStatus;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCertificationCard() {
		return certificationCard;
	}

	public void setCertificationCard(String certificationCard) {
		this.certificationCard = certificationCard;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getLogisticsValue() {
		return logisticsValue;
	}

	public void setLogisticsValue(String logisticsValue) {
		this.logisticsValue = logisticsValue;
	}

	public String getIsNormal() {
		return isNormal;
	}

	public void setIsNormal(String isNormal) {
		this.isNormal = isNormal;
	}

	@Override
	public String toString() {
		return "TradeBasePO [orderNo=" + orderNo + ", tradeNo=" + tradeNo + ", orderStatus=" + orderStatus
				+ ", tradeStatus=" + tradeStatus + ", buyerId=" + buyerId + ", goodsId=" + goodsId + ", buyCount="
				+ buyCount + ", buyPrice=" + buyPrice + ", totalPrice=" + totalPrice + ", payChannel=" + payChannel
				+ ", payPrice=" + payPrice + ", createTime=" + createTime + ", payTime=" + payTime + ", tradeTime="
				+ tradeTime + ", addressId=" + addressId + ", userTradeNo=" + userTradeNo + ", userOrderNo="
				+ userOrderNo + ", alipayRetrieveStatus=" + alipayRetrieveStatus + ", address=" + address
				+ ", certificationCard=" + certificationCard + ", telephone=" + telephone + ", receiver=" + receiver
				+ ", logisticsValue=" + logisticsValue + "]";
	}

}
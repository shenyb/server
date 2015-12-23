package com.need.integration.dao.jdbc.api.trade.po;

import java.util.Date;

public class TradePayPO {
    private String payId;

    private String tradeNo;

    private String outPayNo;

    private String payChannel;

    private Integer amount;

    private String memo;

    private Date payTime;

    private String payType;

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

   
    public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOutPayNo() {
        return outPayNo;
    }

    public void setOutPayNo(String outPayNo) {
        this.outPayNo = outPayNo;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
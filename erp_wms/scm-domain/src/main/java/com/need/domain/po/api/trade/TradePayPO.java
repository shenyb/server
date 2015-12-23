package com.need.domain.po.api.trade;

import java.io.Serializable;
import java.util.Date;

public class TradePayPO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 2829783952045780545L;

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

	@Override
	public String toString() {
		return "TradePayPO [payId=" + payId + ", tradeNo=" + tradeNo + ", outPayNo=" + outPayNo + ", payChannel="
				+ payChannel + ", amount=" + amount + ", memo=" + memo + ", payTime=" + payTime + ", payType=" + payType
				+ "]";
	}
    
}
package com.need.domain.po.api.trade;

import java.io.Serializable;
import java.util.Date;

public class TradeRefundPO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6012888605436381178L;

	private String tradeNo;

    private String orderNo;

    private Integer refundAmount;

    private String tradeStatus;

    private String memo;

    private Date createTime;

    private Date updateTime;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Integer refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "TradeRefundPO [tradeNo=" + tradeNo + ", orderNo=" + orderNo + ", refundAmount=" + refundAmount
				+ ", tradeStatus=" + tradeStatus + ", memo=" + memo + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
    
}
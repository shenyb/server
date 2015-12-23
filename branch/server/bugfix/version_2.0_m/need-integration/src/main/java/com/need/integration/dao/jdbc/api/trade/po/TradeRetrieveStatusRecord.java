package com.need.integration.dao.jdbc.api.trade.po;

import java.util.Date;

public class TradeRetrieveStatusRecord {
    private Integer retrieveId;

    private String orderNo;

    private String tradeNo;

    private String userOrderNo;

    private String userTradeNo;

    private String retrieveStatus;

    private Date createTime;

    private Date updateTime;

    private String trackingCode;

    private String trackingDesc;
    
    private String birdexResult;

    public Integer getRetrieveId() {
        return retrieveId;
    }

    public void setRetrieveId(Integer retrieveId) {
        this.retrieveId = retrieveId;
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

    public String getUserOrderNo() {
        return userOrderNo;
    }

    public void setUserOrderNo(String userOrderNo) {
        this.userOrderNo = userOrderNo;
    }

    public String getUserTradeNo() {
        return userTradeNo;
    }

    public void setUserTradeNo(String userTradeNo) {
        this.userTradeNo = userTradeNo;
    }

    public String getRetrieveStatus() {
        return retrieveStatus;
    }

    public void setRetrieveStatus(String retrieveStatus) {
        this.retrieveStatus = retrieveStatus;
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

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public String getTrackingDesc() {
        return trackingDesc;
    }

    public void setTrackingDesc(String trackingDesc) {
        this.trackingDesc = trackingDesc;
    }

	public String getBirdexResult() {
		return birdexResult;
	}

	public void setBirdexResult(String birdexResult) {
		this.birdexResult = birdexResult;
	}

	@Override
	public String toString() {
		return "TradeRetrieveStatusRecord [retrieveId=" + retrieveId + ", orderNo=" + orderNo + ", tradeNo=" + tradeNo
				+ ", userOrderNo=" + userOrderNo + ", userTradeNo=" + userTradeNo + ", retrieveStatus=" + retrieveStatus
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", trackingCode=" + trackingCode
				+ ", trackingDesc=" + trackingDesc + ", birdexResult=" + birdexResult + "]";
	}
    
}
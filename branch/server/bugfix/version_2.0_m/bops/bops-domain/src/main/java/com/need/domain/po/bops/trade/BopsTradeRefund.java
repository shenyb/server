package com.need.domain.po.bops.trade;

import java.io.Serializable;
import java.util.Date;

public class BopsTradeRefund implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4970499170649524711L;

	private String tradeNo;

	private String orderNo;

	private Integer refundAmount;

	private String tradeStatus;

	private String payChannel;

	private String memo;

	private Date createTime;

	private Date updateTime;

	private Integer publisherId;

	private Integer auditorId;

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

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
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

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	@Override
	public String toString() {
		return "BopsTradeRefund [tradeNo=" + tradeNo + ", orderNo=" + orderNo + ", refundAmount=" + refundAmount
				+ ", tradeStatus=" + tradeStatus + ", payChannel=" + payChannel + ", memo=" + memo + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", publisherId=" + publisherId + ", auditorId="
				+ auditorId + "]";
	}
	
}
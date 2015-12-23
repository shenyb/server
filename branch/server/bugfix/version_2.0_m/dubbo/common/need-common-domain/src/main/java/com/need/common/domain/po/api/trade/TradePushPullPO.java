package com.need.common.domain.po.api.trade;

import java.util.Date;

public class TradePushPullPO {
	private String tradeNo;

	private String orderNo;

	private Date createTime;

	private String outTradeNo;

	private String logisticsNo;

	private String orderStatus;

	private String tradeStatus;

	private String retrieveStatus;
	private String deliverStatus;

	private String memoDeliver;

	private Date updateTime;

	private String memoPush;
	private String memoLogistics;
	private String memoRetrieve;

	private String pushReturnStatus;
	
	private String batchNo; //批次号
	
	private int batchCount; //该批次对应的数量记录
	private String warehouseType;
	
	
	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public int getBatchCount() {
		return batchCount;
	}

	public void setBatchCount(int batchCount) {
		this.batchCount = batchCount;
	}

	public String getDeliverStatus() {
		return deliverStatus;
	}

	public void setDeliverStatus(String deliverStatus) {
		this.deliverStatus = deliverStatus;
	}


	public String getMemoDeliver() {
		return memoDeliver;
	}

	public void setMemoDeliver(String memoDeliver) {
		this.memoDeliver = memoDeliver;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
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

	public String getRetrieveStatus() {
		return retrieveStatus;
	}

	public void setRetrieveStatus(String retrieveStatus) {
		this.retrieveStatus = retrieveStatus;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getMemoPush() {
		return memoPush;
	}

	public void setMemoPush(String memoPush) {
		this.memoPush = memoPush;
	}

	public String getMemoLogistics() {
		return memoLogistics;
	}

	public void setMemoLogistics(String memoLogistics) {
		this.memoLogistics = memoLogistics;
	}

	public String getMemoRetrieve() {
		return memoRetrieve;
	}

	public void setMemoRetrieve(String memoRetrieve) {
		this.memoRetrieve = memoRetrieve;
	}

	public String getPushReturnStatus() {
		return pushReturnStatus;
	}

	public void setPushReturnStatus(String pushReturnStatus) {
		this.pushReturnStatus = pushReturnStatus;
	}

}
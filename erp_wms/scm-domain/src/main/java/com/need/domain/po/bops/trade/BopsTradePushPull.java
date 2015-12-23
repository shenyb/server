package com.need.domain.po.bops.trade;

import java.io.Serializable;
import java.util.Date;

public class BopsTradePushPull implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8557839267421191698L;

	private String orderNo;

	private String tradeNo;

	private String outTradeNo;

	private String logisticsNo;

	private String orderStatus;

	private String tradeStatus;

	private String retrieveStatus;

	private String deliverStatus;

	private String memoDeliver;

	private String pushReturnStatus;

	private String memoPush;

	private String memoRetrieve;

	private String memoLogistics;

	private String batchNo; // 批次号

	private int batchCount; // 该批次对应的数量记录

	private Date createTime;

	private Date updateTime;
	
	private String payChannel;
	
    private String alipayRetrieveStatus;
    
    private String wechatRetrieveStatus;
    
    private String warehouseType;
    
    
	
	private String deliverPushStatus;
	private String memoDeliverPush;
	private String deliverPullStatus;
	

	
	public String getWechatRetrieveStatus() {
		return wechatRetrieveStatus;
	}

	public void setWechatRetrieveStatus(String wechatRetrieveStatus) {
		this.wechatRetrieveStatus = wechatRetrieveStatus;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getAlipayRetrieveStatus() {
		return alipayRetrieveStatus;
	}

	public void setAlipayRetrieveStatus(String alipayRetrieveStatus) {
		this.alipayRetrieveStatus = alipayRetrieveStatus;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
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

	public String getPushReturnStatus() {
		return pushReturnStatus;
	}

	public void setPushReturnStatus(String pushReturnStatus) {
		this.pushReturnStatus = pushReturnStatus;
	}

	public String getMemoPush() {
		return memoPush;
	}

	public void setMemoPush(String memoPush) {
		this.memoPush = memoPush;
	}

	public String getMemoRetrieve() {
		return memoRetrieve;
	}

	public void setMemoRetrieve(String memoRetrieve) {
		this.memoRetrieve = memoRetrieve;
	}

	public String getMemoLogistics() {
		return memoLogistics;
	}

	public void setMemoLogistics(String memoLogistics) {
		this.memoLogistics = memoLogistics;
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

	public String getDeliverPushStatus() {
		return deliverPushStatus;
	}

	public void setDeliverPushStatus(String deliverPushStatus) {
		this.deliverPushStatus = deliverPushStatus;
	}

	public String getMemoDeliverPush() {
		return memoDeliverPush;
	}

	public void setMemoDeliverPush(String memoDeliverPush) {
		this.memoDeliverPush = memoDeliverPush;
	}

	public String getDeliverPullStatus() {
		return deliverPullStatus;
	}

	public void setDeliverPullStatus(String deliverPullStatus) {
		this.deliverPullStatus = deliverPullStatus;
	}
	
}
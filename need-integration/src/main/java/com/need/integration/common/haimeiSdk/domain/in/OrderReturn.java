package com.need.integration.common.haimeiSdk.domain.in;

import java.util.Date;
import java.util.List;

import com.need.integration.common.haimeiSdk.domain.base.Sku;

public class OrderReturn {
	private String orderReturnNo ; 
	
	private String orderNo ; 
	
	private Long stockId ; 
	
	private Long transporterId ; 
	
	private String outSid ; 
	
	private String buyerNick ; 
	
	private String receiverName ; 
	
	private String receiverMobile ; 
	
	private String receiverPhone ; 
	
	private Long shopId ; 
	
	private String iscsOrderReturnNo ; 
	
	private String status ; 
	
	private Date receiptDate ; 

	private List<Sku> skus ;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}


	public String getOrderReturnNo() {
		return orderReturnNo;
	}

	public void setOrderReturnNo(String orderReturnNo) {
		this.orderReturnNo = orderReturnNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getTransporterId() {
		return transporterId;
	}

	public void setTransporterId(Long transporterId) {
		this.transporterId = transporterId;
	}

	public String getOutSid() {
		return outSid;
	}

	public void setOutSid(String outSid) {
		this.outSid = outSid;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public List<Sku> getSkus() {
		return skus;
	}

	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	} 

	public String getIscsOrderReturnNo() {
		return iscsOrderReturnNo;
	}

	public void setIscsOrderReturnNo(String iscsOrderReturnNo) {
		this.iscsOrderReturnNo = iscsOrderReturnNo;
	}
	
}

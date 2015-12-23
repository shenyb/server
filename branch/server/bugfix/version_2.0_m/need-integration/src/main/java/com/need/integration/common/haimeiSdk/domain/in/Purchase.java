package com.need.integration.common.haimeiSdk.domain.in;

import java.util.Date;
import java.util.List;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;

public class Purchase extends AbstractRequest {
	Long stockId;
	String purchaseNo;
	Long ownerId;
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	Long transporterId;
	String outSid;
	Long supplierId;
	Date createDate;
	Date planShipDate;
	Date planArrivalDate;
	String remark;
	Date auditDate;
	String autoDelivery;
	List<PurchaseDet> skus;
	
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(long l) {
		this.stockId = l;
	}
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public Long getTransporterId() {
		return transporterId;
	}
	public void setTransporterId(Long transporterId) {
		this.transporterId = transporterId;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	public String getOutSid() {
		return outSid;
	}
	public void setOutSid(String outSid) {
		this.outSid = outSid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getPlanShipDate() {
		return planShipDate;
	}
	public void setPlanShipDate(Date planShipDate) {
		this.planShipDate = planShipDate;
	}
	public Date getPlanArrivalDate() {
		return planArrivalDate;
	}
	public void setPlanArrivalDate(Date planArrivalDate) {
		this.planArrivalDate = planArrivalDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public String getAutoDelivery() {
		return autoDelivery;
	}
	public void setAutoDelivery(String autoDelivery) {
		this.autoDelivery = autoDelivery;
	}
	public List<PurchaseDet> getSkus() {
		return skus;
	}
	public void setSkus(List<PurchaseDet> skus) {
		this.skus = skus;
	}
}
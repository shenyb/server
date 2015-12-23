package com.need.domain.vo.purchase;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PurchaseOutVO implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -3104118889705044057L;
	private Long id;
	private String purchaseNo;
	private Long warehouseId;
	private Date createDate;
	private Long vendorId;
	private Long skuId;
	private Long quantity;
	private String createBy;
	private String remark;
	private List<PurchaseItemOutVO> items;
	private String cardBatchNo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public List<PurchaseItemOutVO> getItems() {
		return items;
	}
	public void setItems(List<PurchaseItemOutVO> items) {
		this.items = items;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCardBatchNo() {
		return cardBatchNo;
	}
	public void setCardBatchNo(String cardBatchNo) {
		this.cardBatchNo = cardBatchNo;
	}
	
	
}

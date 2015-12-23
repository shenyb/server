package com.need.domain.vo.wms;

import java.util.Date;

public class OutCheckInventoryRecordVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long warehouseId;
	private String checkNo;
	private String superionBill;
	private Long skuId;
	private String lotNumber;
	private Long qty;
	private Date checkDate;
	private String checkType;
	private Long checkStatus;
	private Long localtionId;
	private String areaNo;
	private String groupNo;
	private String checkWorker;
	private String workCategory;
	private String remark;
	private String processOpinion;
	private Long isAudit;
	private Long inventoryState;
	private String reason;
	private Long planQty;
	private Long realQty;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public String getSuperionBill() {
		return superionBill;
	}
	public void setSuperionBill(String superionBill) {
		this.superionBill = superionBill;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public Long getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(Long checkStatus) {
		this.checkStatus = checkStatus;
	}
	public Long getLocaltionId() {
		return localtionId;
	}
	public void setLocaltionId(Long localtionId) {
		this.localtionId = localtionId;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public String getCheckWorker() {
		return checkWorker;
	}
	public void setCheckWorker(String checkWorker) {
		this.checkWorker = checkWorker;
	}
	public String getWorkCategory() {
		return workCategory;
	}
	public void setWorkCategory(String workCategory) {
		this.workCategory = workCategory;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProcessOpinion() {
		return processOpinion;
	}
	public void setProcessOpinion(String processOpinion) {
		this.processOpinion = processOpinion;
	}
	public Long getIsAudit() {
		return isAudit;
	}
	public void setIsAudit(Long isAudit) {
		this.isAudit = isAudit;
	}
	public Long getInventoryState() {
		return inventoryState;
	}
	public void setInventoryState(Long inventoryState) {
		this.inventoryState = inventoryState;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getPlanQty() {
		return planQty;
	}
	public void setPlanQty(Long planQty) {
		this.planQty = planQty;
	}
	public Long getRealQty() {
		return realQty;
	}
	public void setRealQty(Long realQty) {
		this.realQty = realQty;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}

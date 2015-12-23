package com.need.domain.po.bops.store;

import java.io.Serializable;
import java.util.Date;

public class BopsCreditMemoPO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -173663934385788597L;

	private Long id;

    private String reportType;

    private Integer totalCost;

    private Integer creditAmount;

    private String serialNumber;

    private Integer eWarehouseId;

    private String remark;

    private Date createdAt;

    private String createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Integer creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer geteWarehouseId() {
        return eWarehouseId;
    }

    public void seteWarehouseId(Integer eWarehouseId) {
        this.eWarehouseId = eWarehouseId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

	@Override
	public String toString() {
		return "BopsCreditMemoPO [id=" + id + ", reportType=" + reportType + ", totalCost=" + totalCost
				+ ", creditAmount=" + creditAmount + ", serialNumber=" + serialNumber + ", eWarehouseId=" + eWarehouseId
				+ ", remark=" + remark + ", createdAt=" + createdAt + ", createdBy=" + createdBy + "]";
	}
    
}
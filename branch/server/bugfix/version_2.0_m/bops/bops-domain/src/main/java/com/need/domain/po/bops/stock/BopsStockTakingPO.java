package com.need.domain.po.bops.stock;

import java.io.Serializable;
import java.util.Date;

public class BopsStockTakingPO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7667318367551141105L;

	private Long id;

    private String stockTakingNo;

    private String stockStatus;

    private Date createAt;

    private String createBy;

    private Date auditAt;

    private String auditBy;

    private Integer warehouseid;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockTakingNo() {
        return stockTakingNo;
    }

    public void setStockTakingNo(String stockTakingNo) {
        this.stockTakingNo = stockTakingNo;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getAuditAt() {
        return auditAt;
    }

    public void setAuditAt(Date auditAt) {
        this.auditAt = auditAt;
    }

    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    public Integer getWarehouseid() {
        return warehouseid;
    }

    public void setWarehouseid(Integer warehouseid) {
        this.warehouseid = warehouseid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
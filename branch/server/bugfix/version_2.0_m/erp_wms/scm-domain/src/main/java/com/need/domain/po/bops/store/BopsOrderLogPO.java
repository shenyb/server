package com.need.domain.po.bops.store;

import java.io.Serializable;
import java.util.Date;

public class BopsOrderLogPO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8605438618350109562L;

	private Long id;

    private Long userTradeNo;

    private String operType;

    private String olderOrderStatus;

    private String newOrderStatus;

    private String remark;

    private Date createAt;

    private String createBy;

    private Date changeAt;

    private String changeBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserTradeNo() {
        return userTradeNo;
    }

    public void setUserTradeNo(Long userTradeNo) {
        this.userTradeNo = userTradeNo;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getOlderOrderStatus() {
        return olderOrderStatus;
    }

    public void setOlderOrderStatus(String olderOrderStatus) {
        this.olderOrderStatus = olderOrderStatus;
    }

    public String getNewOrderStatus() {
        return newOrderStatus;
    }

    public void setNewOrderStatus(String newOrderStatus) {
        this.newOrderStatus = newOrderStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Date getChangeAt() {
        return changeAt;
    }

    public void setChangeAt(Date changeAt) {
        this.changeAt = changeAt;
    }

    public String getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(String changeBy) {
        this.changeBy = changeBy;
    }

	@Override
	public String toString() {
		return "BopsOrderLogPO [id=" + id + ", userTradeNo=" + userTradeNo + ", operType=" + operType
				+ ", olderOrderStatus=" + olderOrderStatus + ", newOrderStatus=" + newOrderStatus + ", remark=" + remark
				+ ", createAt=" + createAt + ", createBy=" + createBy + ", changeAt=" + changeAt + ", changeBy="
				+ changeBy + "]";
	}
    
}
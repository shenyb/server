package com.need.domain.po.bops.store;

import java.io.Serializable;
import java.util.Date;

public class BopsInventoryFreezeLogPO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4840553836681493788L;

	private Long id;

    private Long bopsInventoryId;

    private String billNo;

    private Integer orderFreezeQty;

    private Integer otherFreezeQty;

    private Integer demageFreezeQty;

    private String remark;

    private Date createAt;

    private String createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBopsInventoryId() {
        return bopsInventoryId;
    }

    public void setBopsInventoryId(Long bopsInventoryId) {
        this.bopsInventoryId = bopsInventoryId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Integer getOrderFreezeQty() {
        return orderFreezeQty;
    }

    public void setOrderFreezeQty(Integer orderFreezeQty) {
        this.orderFreezeQty = orderFreezeQty;
    }

    public Integer getOtherFreezeQty() {
        return otherFreezeQty;
    }

    public void setOtherFreezeQty(Integer otherFreezeQty) {
        this.otherFreezeQty = otherFreezeQty;
    }

    public Integer getDemageFreezeQty() {
        return demageFreezeQty;
    }

    public void setDemageFreezeQty(Integer demageFreezeQty) {
        this.demageFreezeQty = demageFreezeQty;
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

	@Override
	public String toString() {
		return "BopsInventoryFreezeLogPO [id=" + id + ", bopsInventoryId=" + bopsInventoryId + ", billNo=" + billNo
				+ ", orderFreezeQty=" + orderFreezeQty + ", otherFreezeQty=" + otherFreezeQty + ", demageFreezeQty="
				+ demageFreezeQty + ", remark=" + remark + ", createAt=" + createAt + ", createBy=" + createBy + "]";
	}
    
}
package com.need.domain.vo.wms;

import java.util.Date;
import java.util.List;

public class BarterSaleExchangeOutVo {
	private Long orderId;
	private String billNo;
	private Long warehouseId;
	private List<BarterSaleExchangeItemOutVo> item;
	private Long barterId;
	private String businessType;
	private Date changedAt;
    private String rejectReason;
	private String needPay;

    public String getNeedPay() {
        return needPay;
    }

    public void setNeedPay(String needPay) {
        this.needPay = needPay;
    }

    public Date getChangedAt() {
		return changedAt;
	}
	public void setChangedAt(Date changedAt) {
		this.changedAt = changedAt;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public List<BarterSaleExchangeItemOutVo> getItem() {
		return item;
	}
	public void setItem(List<BarterSaleExchangeItemOutVo> item) {
		this.item = item;
	}
	public Long getBarterId() {
		return barterId;
	}
	public void setBarterId(Long barterId) {
		this.barterId = barterId;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}

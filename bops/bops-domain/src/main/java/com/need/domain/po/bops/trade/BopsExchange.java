package com.need.domain.po.bops.trade;

import java.util.Date;

public class BopsExchange {
    private Long id;

    private String billNo;

    private String status;

    private String reason;

    private String ramark;

    private Long bopsOrderId;

    private Long totalPay;

    private Integer allReject;

    private Integer bopsWarehouseId;

    private String userName;

    private String address;

    private String phone;

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

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRamark() {
        return ramark;
    }

    public void setRamark(String ramark) {
        this.ramark = ramark;
    }

    public Long getBopsOrderId() {
        return bopsOrderId;
    }

    public void setBopsOrderId(Long bopsOrderId) {
        this.bopsOrderId = bopsOrderId;
    }

    public Long getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Long totalPay) {
        this.totalPay = totalPay;
    }

    public Integer getAllReject() {
        return allReject;
    }

    public void setAllReject(Integer allReject) {
        this.allReject = allReject;
    }

    public Integer getBopsWarehouseId() {
        return bopsWarehouseId;
    }

    public void setBopsWarehouseId(Integer bopsWarehouseId) {
        this.bopsWarehouseId = bopsWarehouseId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
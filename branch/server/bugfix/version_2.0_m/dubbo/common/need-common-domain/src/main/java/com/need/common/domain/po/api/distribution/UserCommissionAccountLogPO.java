package com.need.common.domain.po.api.distribution;

import com.need.common.domain.enums.UserCommissionAccountOperateTypeEnum;

import java.util.Date;

public class UserCommissionAccountLogPO {
    private Long id;

    private String accountId;

    private String userId;

    private Integer price;

    private UserCommissionAccountOperateTypeEnum operateStatus;

    private Date createTime;

    private String tradeNo;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public UserCommissionAccountOperateTypeEnum getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(UserCommissionAccountOperateTypeEnum operateStatus) {
        this.operateStatus = operateStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
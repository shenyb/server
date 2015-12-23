package com.need.task.dao.jdbc.api.distribution.po;

import com.need.task.common.enums.UserCommissionAccountOperateTypeEnum;

import java.util.Date;

public class UserCommissionAccountLogPO {
    private Long id;

    private String accountId;

    private String userId;

    private Integer price;

    private UserCommissionAccountOperateTypeEnum operateStatus;

    private String tradeNo;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAccountId() {
        return accountId;
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
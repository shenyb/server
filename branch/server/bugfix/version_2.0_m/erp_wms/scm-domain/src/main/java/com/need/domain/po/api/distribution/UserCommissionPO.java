package com.need.domain.po.api.distribution;

import java.io.Serializable;
import java.util.Date;

public class UserCommissionPO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4468844561947881140L;

	private Long id;

    private String userId;

    private String goodsId;

    private String goodsName;

    private String tradeNo;

    private String userTradeNo;

    private Integer payPrice;

    private Integer commission;

    private String commissionStatus;

    private Date createTime;

    private Date updateTime;

    private String topPicKey;

    private String failReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getUserTradeNo() {
        return userTradeNo;
    }

    public void setUserTradeNo(String userTradeNo) {
        this.userTradeNo = userTradeNo;
    }

    public Integer getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Integer payPrice) {
        this.payPrice = payPrice;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public String getCommissionStatus() {
        return commissionStatus;
    }

    public void setCommissionStatus(String commissionStatus) {
        this.commissionStatus = commissionStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTopPicKey() {
        return topPicKey;
    }

    public void setTopPicKey(String topPicKey) {
        this.topPicKey = topPicKey;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
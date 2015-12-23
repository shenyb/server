package com.need.common.domain.po.api.goods;

import java.util.Date;

public class GoodsLockCountTempPO {
    private String orderNo;

    private String goodsId;

    private Integer buyCount;

    private Date createTime;

    private Date updateTime;

    private String synchFlag;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
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

    public String getSynchFlag() {
        return synchFlag;
    }

    public void setSynchFlag(String synchFlag) {
        this.synchFlag = synchFlag;
    }
}
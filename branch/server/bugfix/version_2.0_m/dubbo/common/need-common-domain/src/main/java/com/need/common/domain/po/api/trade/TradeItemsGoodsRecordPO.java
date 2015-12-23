package com.need.common.domain.po.api.trade;

import java.util.Date;

public class TradeItemsGoodsRecordPO {
    private Long tradeItemsGoodsId;

    private String tradeNo;

    private String goodsGroupId;

    private String goodsId;

    private Integer goodsCount;

    private Integer goodsGroup;

    private Date createTime;

    public Long getTradeItemsGoodsId() {
        return tradeItemsGoodsId;
    }

    public void setTradeItemsGoodsId(Long tradeItemsGoodsId) {
        this.tradeItemsGoodsId = tradeItemsGoodsId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getGoodsGroupId() {
        return goodsGroupId;
    }

    public void setGoodsGroupId(String goodsGroupId) {
        this.goodsGroupId = goodsGroupId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Integer getGoodsGroup() {
        return goodsGroup;
    }

    public void setGoodsGroup(Integer goodsGroup) {
        this.goodsGroup = goodsGroup;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
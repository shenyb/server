package com.need.common.domain.po.api.ops;

import java.util.Date;

public class OpsHotGooodsPO {
    private String popularityId;

    private String goodsId;

    private String goodsName;

    private String goodsProfilePicKey;

    private Integer goodsSort;

    private Date createTime;

    private String goodsStatus;

    public String getPopularityId() {
        return popularityId;
    }

    public void setPopularityId(String popularityId) {
        this.popularityId = popularityId;
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

    public String getGoodsProfilePicKey() {
        return goodsProfilePicKey;
    }

    public void setGoodsProfilePicKey(String goodsProfilePicKey) {
        this.goodsProfilePicKey = goodsProfilePicKey;
    }

    public Integer getGoodsSort() {
        return goodsSort;
    }

    public void setGoodsSort(Integer goodsSort) {
        this.goodsSort = goodsSort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }
}
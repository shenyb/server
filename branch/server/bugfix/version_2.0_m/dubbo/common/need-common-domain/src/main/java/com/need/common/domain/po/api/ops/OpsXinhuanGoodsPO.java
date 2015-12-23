package com.need.common.domain.po.api.ops;

import java.util.Date;

public class OpsXinhuanGoodsPO {
    private String id;

    private String goodsId;

    private String opsId;

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getOpsId() {
        return opsId;
    }

    public void setOpsId(String opsId) {
        this.opsId = opsId;
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
}
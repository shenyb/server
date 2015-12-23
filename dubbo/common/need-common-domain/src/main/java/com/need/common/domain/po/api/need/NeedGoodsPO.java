package com.need.common.domain.po.api.need;

import java.io.Serializable;
import java.util.Date;

public class NeedGoodsPO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -8037238931320095145L;

	private Integer id;

    private String userId;

    private String goodsId;

    private String needStatus;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getNeedStatus() {
        return needStatus;
    }

    public void setNeedStatus(String needStatus) {
        this.needStatus = needStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
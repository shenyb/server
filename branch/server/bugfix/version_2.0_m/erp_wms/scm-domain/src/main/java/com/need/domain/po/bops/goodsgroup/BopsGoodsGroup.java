package com.need.domain.po.bops.goodsgroup;

import java.io.Serializable;
import java.util.Date;

public class BopsGoodsGroup extends BopsGoodsGroupKey implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -4305371929742724297L;

	private String goodsCode;

    private String goodsPicKey;

    private Date createTime;

    private Date updateTime;

    private Integer createId;

    private Integer updateId;

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsPicKey() {
        return goodsPicKey;
    }

    public void setGoodsPicKey(String goodsPicKey) {
        this.goodsPicKey = goodsPicKey;
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

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

	@Override
	public String toString() {
		return "bopsGoodsGroup [goodsCode=" + goodsCode + ", goodsPicKey=" + goodsPicKey + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", createId=" + createId + ", updateId=" + updateId + "]";
	}
    
    
}
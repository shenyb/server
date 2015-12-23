package com.need.common.domain.po.api.goodsGroup;

import java.io.Serializable;
import java.util.Date;

public class GoodsGroup extends GoodsGroupKey implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -6955112690967304910L;

	private String goodsCode;

    private String goodsPicKey;

    private Date createTime;

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

	@Override
	public String toString() {
		return "GoodsGroup [goodsCode=" + goodsCode + ", goodsPicKey=" + goodsPicKey + ", createTime=" + createTime
				+ "]";
	}
    
    
}
package com.need.domain.po.bops.goodsgroup;

import java.io.Serializable;

public class BopsGoodsGroupKey implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -6379984660999892847L;

	private String goodsId;

    private String groupBatch;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGroupBatch() {
        return groupBatch;
    }

    public void setGroupBatch(String groupBatch) {
        this.groupBatch = groupBatch;
    }

	@Override
	public String toString() {
		return "bopsGoodsGroupKey [goodsId=" + goodsId + ", groupBatch=" + groupBatch + "]";
	}
    
}
package com.need.share.dao.jdbc.api.goodsGroup.po;

import java.io.Serializable;

public class GoodsGroupKey implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 5086606250545818121L;

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
		return "GoodsGroupKey [goodsId=" + goodsId + ", groupBatch=" + groupBatch + "]";
	}
    
}
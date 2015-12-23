package com.need.share.dao.jdbc.api.trade.po;

import java.util.Date;

public class TradeJudgementPO {

	private String orderNo;
	
    private String userId;

    private String judgement;

    private Date createTime;
    
    private String goodsId;

    public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJudgement() {
        return judgement;
    }

    public void setJudgement(String judgement) {
        this.judgement = judgement;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
    
    
}
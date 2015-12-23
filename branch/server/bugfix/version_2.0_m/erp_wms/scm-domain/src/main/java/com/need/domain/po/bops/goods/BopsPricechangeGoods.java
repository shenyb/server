package com.need.domain.po.bops.goods;

import java.io.Serializable;

public class BopsPricechangeGoods implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7059406019646029591L;

	private Integer pricechangeGoodsId;

    private Integer pricechangeId;

    private String goodsId;

    private Integer startPrice;

    private Integer endPrice;
    
    private Integer historyPurchasePrice;
    
    public Integer getPricechangeGoodsId() {
        return pricechangeGoodsId;
    }

    public void setPricechangeGoodsId(Integer pricechangeGoodsId) {
        this.pricechangeGoodsId = pricechangeGoodsId;
    }

    public Integer getPricechangeId() {
        return pricechangeId;
    }

    public void setPricechangeId(Integer pricechangeId) {
        this.pricechangeId = pricechangeId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Integer endPrice) {
        this.endPrice = endPrice;
    }

	public Integer getHistoryPurchasePrice() {
		return historyPurchasePrice;
	}

	public void setHistoryPurchasePrice(Integer historyPurchasePrice) {
		this.historyPurchasePrice = historyPurchasePrice;
	}

	@Override
	public String toString() {
		return "BopsPricechangeGoods [pricechangeGoodsId=" + pricechangeGoodsId + ", pricechangeId=" + pricechangeId
				+ ", goodsId=" + goodsId + ", startPrice=" + startPrice + ", endPrice=" + endPrice
				+ ", historyPurchasePrice=" + historyPurchasePrice + "]";
	}

}
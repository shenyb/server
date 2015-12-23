package com.need.task.dao.jdbc.bops.goods.po;

public class BopsPriceChangeGoodsPO {
    private Integer pricechangeGoodsId;

    private Integer pricechangeId;

    private String goodsId;

    private Integer startPrice;

    private Integer endPrice;

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

	@Override
	public String toString() {
		return "BopsPriceChangeGoodsPO [pricechangeGoodsId="
				+ pricechangeGoodsId + ", pricechangeId=" + pricechangeId
				+ ", goodsId=" + goodsId + ", startPrice=" + startPrice
				+ ", endPrice=" + endPrice + "]";
	}
    
    
    
    
    
}
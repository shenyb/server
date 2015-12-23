package com.need.domain.po.bops.stock;

import java.io.Serializable;

public class BopsStockTakingItemPO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 815426706886462431L;

	private Long id;

    private Long stockTakingId;

    private String goodsCode;

    private String goodsName;

    private Integer inventoryState;

    private Integer qty;

    private Integer planQty;

    private Integer realQty;

    private String reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockTakingId() {
        return stockTakingId;
    }

    public void setStockTakingId(Long stockTakingId) {
        this.stockTakingId = stockTakingId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getInventoryState() {
        return inventoryState;
    }

    public void setInventoryState(Integer inventoryState) {
        this.inventoryState = inventoryState;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getPlanQty() {
        return planQty;
    }

    public void setPlanQty(Integer planQty) {
        this.planQty = planQty;
    }

    public Integer getRealQty() {
        return realQty;
    }

    public void setRealQty(Integer realQty) {
        this.realQty = realQty;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
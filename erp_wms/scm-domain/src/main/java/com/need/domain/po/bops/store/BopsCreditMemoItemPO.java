package com.need.domain.po.bops.store;

import java.io.Serializable;
import java.util.Date;

public class BopsCreditMemoItemPO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -3022734613506172168L;

	private Long id;

    private Long bopsCreditMemoId;

    private String goodsCode;

    private Long bopsInventoryId;

    private String goodsName;

    private Integer normalQuantity;

    private Integer demageQuantity;

    private Integer normalChange;

    private Integer demageChange;

    private Integer price;

    private Integer cost;

    private Long sourceId;

    private Long sourceItemId;

    private Date createdAt;

    private String createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBopsCreditMemoId() {
        return bopsCreditMemoId;
    }

    public void setBopsCreditMemoId(Long bopsCreditMemoId) {
        this.bopsCreditMemoId = bopsCreditMemoId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Long getBopsInventoryId() {
        return bopsInventoryId;
    }

    public void setBopsInventoryId(Long bopsInventoryId) {
        this.bopsInventoryId = bopsInventoryId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getNormalQuantity() {
        return normalQuantity;
    }

    public void setNormalQuantity(Integer normalQuantity) {
        this.normalQuantity = normalQuantity;
    }

    public Integer getDemageQuantity() {
        return demageQuantity;
    }

    public void setDemageQuantity(Integer demageQuantity) {
        this.demageQuantity = demageQuantity;
    }

    public Integer getNormalChange() {
        return normalChange;
    }

    public void setNormalChange(Integer normalChange) {
        this.normalChange = normalChange;
    }

    public Integer getDemageChange() {
        return demageChange;
    }

    public void setDemageChange(Integer demageChange) {
        this.demageChange = demageChange;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getSourceItemId() {
        return sourceItemId;
    }

    public void setSourceItemId(Long sourceItemId) {
        this.sourceItemId = sourceItemId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

	@Override
	public String toString() {
		return "BopsCreditMemoItemPO [id=" + id + ", bopsCreditMemoId=" + bopsCreditMemoId + ", goodsCode=" + goodsCode
				+ ", bopsInventoryId=" + bopsInventoryId + ", goodsName=" + goodsName + ", normalQuantity="
				+ normalQuantity + ", demageQuantity=" + demageQuantity + ", normalChange=" + normalChange
				+ ", demageChange=" + demageChange + ", price=" + price + ", cost=" + cost + ", sourceId=" + sourceId
				+ ", sourceItemId=" + sourceItemId + ", createdAt=" + createdAt + ", createdBy=" + createdBy + "]";
	}
    
}
package com.need.domain.vo.store;

import java.io.Serializable;
import java.util.Date;

public class BopsInventoryVO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 9090238215312415061L;

	private Long id;

    private Long vendorId;

    private String goodsId;

    private String goodsCode;

    private Integer cost;

    private Integer orderFreeze;

    private Integer otherFreeze;

    private Integer normalQuantity;
    
    private Integer saleQuantity;

    private Integer demageQuantity;

    private Integer demageFreeze;

    private Integer purchasePrice;

    private Date createdAt;

    private String createdBy;

    private Date changeAt;

    private String changeBy;

    private Integer warehouseId;
    
    private String isHavest;
    
    public String getIsHavest() {
		return isHavest;
	}

	public void setIsHavest(String isHavest) {
		this.isHavest = isHavest;
	}

	public Integer getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(Integer saleQuantity) {
		this.saleQuantity = saleQuantity;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getOrderFreeze() {
        return orderFreeze;
    }

    public void setOrderFreeze(Integer orderFreeze) {
        this.orderFreeze = orderFreeze;
    }

    public Integer getOtherFreeze() {
        return otherFreeze;
    }

    public void setOtherFreeze(Integer otherFreeze) {
        this.otherFreeze = otherFreeze;
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

    public Integer getDemageFreeze() {
        return demageFreeze;
    }

    public void setDemageFreeze(Integer demageFreeze) {
        this.demageFreeze = demageFreeze;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
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

    public Date getChangeAt() {
        return changeAt;
    }

    public void setChangeAt(Date changeAt) {
        this.changeAt = changeAt;
    }

    public String getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(String changeBy) {
        this.changeBy = changeBy;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

	@Override
	public String toString() {
		return "BopsInventoryPO [id=" + id + ", vendorId=" + vendorId + ", goodsId=" + goodsId + ", goodsCode="
				+ goodsCode + ", cost=" + cost + ", orderFreeze=" + orderFreeze + ", otherFreeze=" + otherFreeze
				+ ", normalQuantity=" + normalQuantity + ", demageQuantity=" + demageQuantity + ", demageFreeze="
				+ demageFreeze + ", purchasePrice=" + purchasePrice + ", createdAt=" + createdAt + ", createdBy="
				+ createdBy + ", changeAt=" + changeAt + ", changeBy=" + changeBy + ", warehouseId=" + warehouseId
				+ "]";
	}
    
}
package com.need.domain.po.bops.trade;

import java.util.Date;

public class BopsExchangeItem {
    private Long id;

    private Long bopsExchangeId;

    private Long goodsCode;

    private String goodsName;

    private Integer quantity;

    private Long actualQuantity;

    private Long bopsOrderItemId;

    private Long price;

    private Long normalQuantity;

    private Long goodsbreakQuantity;

    private Long goodslossQuantity;

    private Long damagepackQuantity;

    private Long damagewarehouseQuantity;

    private Date createAt;

    private String createBy;

    private Date changeAt;

    private String changeBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBopsExchangeId() {
        return bopsExchangeId;
    }

    public void setBopsExchangeId(Long bopsExchangeId) {
        this.bopsExchangeId = bopsExchangeId;
    }

    public Long getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(Long goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

   

    public Long getBopsOrderItemId() {
        return bopsOrderItemId;
    }

    public void setBopsOrderItemId(Long bopsOrderItemId) {
        this.bopsOrderItemId = bopsOrderItemId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

   

    public Long getActualQuantity() {
		return actualQuantity;
	}

	public void setActualQuantity(Long actualQuantity) {
		this.actualQuantity = actualQuantity;
	}

	public Long getNormalQuantity() {
		return normalQuantity;
	}

	public void setNormalQuantity(Long normalQuantity) {
		this.normalQuantity = normalQuantity;
	}

	public Long getGoodsbreakQuantity() {
		return goodsbreakQuantity;
	}

	public void setGoodsbreakQuantity(Long goodsbreakQuantity) {
		this.goodsbreakQuantity = goodsbreakQuantity;
	}

	public Long getGoodslossQuantity() {
		return goodslossQuantity;
	}

	public void setGoodslossQuantity(Long goodslossQuantity) {
		this.goodslossQuantity = goodslossQuantity;
	}

	public Long getDamagepackQuantity() {
		return damagepackQuantity;
	}

	public void setDamagepackQuantity(Long damagepackQuantity) {
		this.damagepackQuantity = damagepackQuantity;
	}

	public Long getDamagewarehouseQuantity() {
		return damagewarehouseQuantity;
	}

	public void setDamagewarehouseQuantity(Long damagewarehouseQuantity) {
		this.damagewarehouseQuantity = damagewarehouseQuantity;
	}

	public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
}
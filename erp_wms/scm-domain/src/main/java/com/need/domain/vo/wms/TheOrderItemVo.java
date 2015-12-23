package com.need.domain.vo.wms;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-16
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
public class TheOrderItemVo implements Serializable {
    private Long id;
    private Long skuId;
    private Long qty;
    private Long price;
    private String name;
    private Date createdAt;
    private Long pkgSelectId;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Long getPkgSelectId() {
		return pkgSelectId;
	}

	public void setPkgSelectId(Long pkgSelectId) {
		this.pkgSelectId = pkgSelectId;
	}
}

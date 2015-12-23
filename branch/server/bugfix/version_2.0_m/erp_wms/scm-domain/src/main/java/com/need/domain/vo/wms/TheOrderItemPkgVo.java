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
public class TheOrderItemPkgVo implements Serializable {
   
	private Long id	;
    private Long orderId;
    private Long orderItemId	;
    private Long skuId	;
    private Long pkgSelectId	;
    private Long pkgSelectItemId	;
    private Long nowPrice	;
    private Long quantity	;
    private Date createdAt	;
    private String createdBy	;
    private String skuName;
    private Long pkgType;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Long getPkgSelectId() {
		return pkgSelectId;
	}
	public void setPkgSelectId(Long pkgSelectId) {
		this.pkgSelectId = pkgSelectId;
	}
	public Long getPkgSelectItemId() {
		return pkgSelectItemId;
	}
	public void setPkgSelectItemId(Long pkgSelectItemId) {
		this.pkgSelectItemId = pkgSelectItemId;
	}
	public Long getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(Long nowPrice) {
		this.nowPrice = nowPrice;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
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
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}		
	public Long getPkgType() {
		return pkgType;
	}
	public void setPkgType(Long pkgType) {
		this.pkgType = pkgType;
	}	
	
}

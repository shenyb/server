package com.need.integration.common.birdex.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable {

	/**
	 * <p>
	 * </p>
	 * 
	 * @author LV 2015年10月23日 上午11:42:59
	 * @ClassName Item
	 * @Description http://openapi.birdex.cn/help/Api/POST/order/create
	 * @version V1.0
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: LV 2015年10月23日
	 * @modify by reason:{方法名}:{原因}
	 */
	private static final long serialVersionUID = 1L;

	private String itemName;
	private String itemNo;
	private String itemCategoryName;
	private BigDecimal itemUnitPrice;
	private String itemUnitPriceUnit;
	private Integer itemQuantity;
	private String itemImage;
	private String itemUrl;
	private String itemRemarks;
	private String itemModel;
	private String itemBrand;
	private String trackingNo;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemCategoryName() {
		return itemCategoryName;
	}

	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}

	public BigDecimal getItemUnitPrice() {
		return itemUnitPrice;
	}

	public void setItemUnitPrice(BigDecimal itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}

	public String getItemUnitPriceUnit() {
		return itemUnitPriceUnit;
	}

	public void setItemUnitPriceUnit(String itemUnitPriceUnit) {
		this.itemUnitPriceUnit = itemUnitPriceUnit;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public String getItemUrl() {
		return itemUrl;
	}

	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}

	public String getItemRemarks() {
		return itemRemarks;
	}

	public void setItemRemarks(String itemRemarks) {
		this.itemRemarks = itemRemarks;
	}

	public String getItemModel() {
		return itemModel;
	}

	public void setItemModel(String itemModel) {
		this.itemModel = itemModel;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

}

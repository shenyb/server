package com.need.domain.vo.goods;

import java.util.Date;

public class BopsGoodsItemsVO {
	private Integer goodsItemsId;

    private String goodsGroupId; 

    private String goodsId;

    private String goodsCode;

    private Integer goodsCount;

    private Integer createId;

    private Integer updateId;

    private Date createTime;

    private Date updateTime;
    
    private Integer nowStoreCount;//库存
    
    private String goodsStatus;//上下架状态
    
    private Double discountPrice;//need价 
    
    private Double onsalePrice;
    
    private String goodsParams;
    
    private Double purchasePrice;
    
    private String weight;
    
    private String goodsName;
    
    private Integer storeCount;
    
	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getOnsalePrice() {
		return onsalePrice;
	}

	public void setOnsalePrice(Double onsalePrice) {
		this.onsalePrice = onsalePrice;
	}

	public Integer getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(Integer storeCount) {
		this.storeCount = storeCount;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getGoodsParams() {
		return goodsParams;
	}

	public void setGoodsParams(String goodsParams) {
		this.goodsParams = goodsParams;
	}

	public Integer getNowStoreCount() {
		return nowStoreCount;
	}

	public void setNowStoreCount(Integer nowStoreCount) {
		this.nowStoreCount = nowStoreCount;
	}

	public Integer getGoodsItemsId() {
		return goodsItemsId;
	}

	public void setGoodsItemsId(Integer goodsItemsId) {
		this.goodsItemsId = goodsItemsId;
	}

	public String getGoodsGroupId() {
		return goodsGroupId;
	}

	public void setGoodsGroupId(String goodsGroupId) {
		this.goodsGroupId = goodsGroupId;
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

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
    
}

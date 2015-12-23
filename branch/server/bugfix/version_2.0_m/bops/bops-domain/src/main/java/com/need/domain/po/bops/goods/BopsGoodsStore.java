package com.need.domain.po.bops.goods;

import java.io.Serializable;
import java.util.Date;

public class BopsGoodsStore implements Serializable{
	
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 5348729532223071184L;
	
	private Integer storeId;

	private String goodsId;

    private Date createTime;
    
    private Integer nowStoreCount;
    
    private Integer authorId;
    
    private Integer historyStore;
    
    private Integer defectiveStore;
    
    private String storeType;
    
    private String storeNo;
    
    private Integer warehouseId;
    
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getNowStoreCount() {
		return nowStoreCount;
	}

	public void setNowStoreCount(Integer nowStoreCount) {
		this.nowStoreCount = nowStoreCount;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getHistoryStore() {
		return historyStore;
	}

	public void setHistoryStore(Integer historyStore) {
		this.historyStore = historyStore;
	}

	public Integer getDefectiveStore() {
		return defectiveStore;
	}

	public void setDefectiveStore(Integer defectiveStore) {
		this.defectiveStore = defectiveStore;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	@Override
	public String toString() {
		return "BopsGoodsStore [storeId=" + storeId + ", goodsId=" + goodsId + ", createTime=" + createTime
				+ ", nowStoreCount=" + nowStoreCount + ", authorId=" + authorId + ", historyStore=" + historyStore
				+ ", defectiveStore=" + defectiveStore + ", storeType=" + storeType + ", storeNo=" + storeNo
				+ ", warehouseId=" + warehouseId + "]";
	}

}


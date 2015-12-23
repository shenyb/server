package com.need.domain.vo.wms;

import java.io.Serializable;

public class StoreChangeVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -6403115889170274076L;

	private String goodsId;//商品ID
	private Integer defectiveStore; //残品数
	private Integer nowStoreCount;//正品数
	private String storeType;//类型 StoreTypeEnum
	private String storeNo; //类型 订单号或采购单号
	private Integer warehouseId;//仓库ID
	private Integer authorId;//操作人ID
	private Integer historyStore;//历史库存，不需要传
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getDefectiveStore() {
		return defectiveStore;
	}
	public void setDefectiveStore(Integer defectiveStore) {
		this.defectiveStore = defectiveStore;
	}
	public Integer getNowStoreCount() {
		return nowStoreCount;
	}
	public void setNowStoreCount(Integer nowStoreCount) {
		this.nowStoreCount = nowStoreCount;
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
	@Override
	public String toString() {
		return "StoreChangeVO [goodsId=" + goodsId + ", defectiveStore=" + defectiveStore + ", nowStoreCount="
				+ nowStoreCount + ", storeType=" + storeType + ", storeNo=" + storeNo + ", warehouseId=" + warehouseId
				+ ", authorId=" + authorId + ", historyStore=" + historyStore + "]";
	}
	
}

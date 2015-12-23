package com.need.domain.vo.goods;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.need.domain.pub.Page;

public class GoodsStorePageVO extends Page implements Serializable {

	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -2037869619825888093L;

	private String goodsId;

	private String goodsCode;

	private String goodsName;

	private String topPicKeys;

	private Integer allowSellCount;

	private Integer soldCount;

	private Integer allowBuyCount;

	@NotNull(message="库存不能为空")
	private Integer nowStoreCount;

	private Integer onWayCount;
	
	private int authorId;
	
	private int historyStore;
	
	private Integer tradeOccupyCount;

	private Integer lockedCount;

	private String auditStatus;
	
	private String storeStatus;
	
	private String memo;// 审核原因
	
	private String publisherId;
	
	private Date createTime;
	
	private String goodsBarcode;
	
	private String haiguanCount;// 海关计量单位

	private String guojianCount;// 国检计量单位
	
	private String warehouseType;//仓库类型
	
	private String storeType;
	
	private Integer defectiveStore;
	
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
	public String getGoodsName() {
		return goodsName;
	}
	
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getTopPicKeys() {
		return topPicKeys;
	}
	public void setTopPicKeys(String topPicKeys) {
		this.topPicKeys = topPicKeys;
	}
	public Integer getAllowSellCount() {
		return allowSellCount;
	}
	public void setAllowSellCount(Integer allowSellCount) {
		this.allowSellCount = allowSellCount;
	}
	public Integer getSoldCount() {
		return soldCount;
	}
	public void setSoldCount(Integer soldCount) {
		this.soldCount = soldCount;
	}
	public Integer getAllowBuyCount() {
		return allowBuyCount;
	}
	public void setAllowBuyCount(Integer allowBuyCount) {
		this.allowBuyCount = allowBuyCount;
	}
	public Integer getNowStoreCount() {
		return nowStoreCount;
	}
	public void setNowStoreCount(Integer nowStoreCount) {
		this.nowStoreCount = nowStoreCount;
	}
	public Integer getOnWayCount() {
		return onWayCount;
	}
	public void setOnWayCount(Integer onWayCount) {
		this.onWayCount = onWayCount;
	}
	public Integer getTradeOccupyCount() {
		return tradeOccupyCount;
	}
	public void setTradeOccupyCount(Integer tradeOccupyCount) {
		this.tradeOccupyCount = tradeOccupyCount;
	}
	public Integer getLockedCount() {
		return lockedCount;
	}
	public void setLockedCount(Integer lockedCount) {
		this.lockedCount = lockedCount;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStoreStatus() {
		return storeStatus;
	}
	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}
	public String getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getGoodsBarcode() {
		return goodsBarcode;
	}
	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}
	public String getHaiguanCount() {
		return haiguanCount;
	}
	public void setHaiguanCount(String haiguanCount) {
		this.haiguanCount = haiguanCount;
	}
	public String getGuojianCount() {
		return guojianCount;
	}
	public void setGuojianCount(String guojianCount) {
		this.guojianCount = guojianCount;
	}
	public int getHistoryStore() {
		return historyStore;
	}
	public void setHistoryStore(int historyStore) {
		this.historyStore = historyStore;
	}
	
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	public Integer getDefectiveStore() {
		return defectiveStore;
	}
	public void setDefectiveStore(Integer defectiveStore) {
		this.defectiveStore = defectiveStore;
	}
	@Override
	public String toString() {
		return "GoodsStorePageVO [goodsId=" + goodsId + ", goodsCode=" + goodsCode + ", goodsName=" + goodsName
				+ ", topPicKeys=" + topPicKeys + ", allowSellCount=" + allowSellCount + ", soldCount=" + soldCount
				+ ", allowBuyCount=" + allowBuyCount + ", nowStoreCount=" + nowStoreCount + ", onWayCount=" + onWayCount
				+ ", authorId=" + authorId + ", historyStore=" + historyStore + ", tradeOccupyCount=" + tradeOccupyCount
				+ ", lockedCount=" + lockedCount + ", auditStatus=" + auditStatus + ", storeStatus=" + storeStatus
				+ ", memo=" + memo + ", publisherId=" + publisherId + ", createTime=" + createTime + ", goodsBarcode="
				+ goodsBarcode + ", haiguanCount=" + haiguanCount + ", guojianCount=" + guojianCount
				+ ", warehouseType=" + warehouseType + ", storeType=" + storeType + ", defectiveStore=" + defectiveStore
				+ "]";
	}
	
}

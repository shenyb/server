package com.need.domain.vo.goods;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GoodsStoreResultVO implements Serializable{

	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5452853839462832104L;

	private String goodsId;

	private String goodsCode;

	private String goodsName;
	/**
	 * @author zhangmengbin
	 * 商品类型
	 */
	private String goodsType;

	private String topPicKeys;
	
	private String scenePicKey;

	private Integer allowSellCount;

	private Integer soldCount;

	private Integer allowBuyCount;

	private Integer nowStoreCount;

	private Integer onWayCount;//出库的数量

	private Integer tradeOccupyCount;

	private Integer lockedCount; //前台库锁定数

	private String auditStatus;
	
	private String memo;// 审核原因
	
	private Date createTime;
	
	private double onsalePrice;
	
	private double discountPrice;

	private List<String> topPicList;
	
	private String goodsBarcode;
	
	private int historyStore;
	
	private int authorId;
	
	private String userRealName;
	
	private String userDept;
	
	private String haiguanCount;// 海关计量单位

	private String guojianCount;// 国检计量单位
	
	private String warehouseType;//仓库类型
	
	private Integer allStore;
	
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<String> getTopPicList() {
		return topPicList;
	}

	public void setTopPicList(List<String> topPicList) {
		this.topPicList = topPicList;
	}

	public double getOnsalePrice() {
		return onsalePrice;
	}

	public void setOnsalePrice(double onsalePrice) {
		this.onsalePrice = onsalePrice;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
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

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public String getScenePicKey() {
		return scenePicKey;
	}

	public void setScenePicKey(String scenePicKey) {
		this.scenePicKey = scenePicKey;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	
	public Integer getAllStore() {
		return allStore;
	}

	public void setAllStore(Integer allStore) {
		this.allStore = allStore;
	}

	public Integer getDefectiveStore() {
		return defectiveStore;
	}

	public void setDefectiveStore(Integer defectiveStore) {
		this.defectiveStore = defectiveStore;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	@Override
	public String toString() {
		return "GoodsStoreResultVO [goodsId=" + goodsId + ", goodsCode=" + goodsCode + ", goodsName=" + goodsName
				+ ", topPicKeys=" + topPicKeys + ", scenePicKey=" + scenePicKey + ", allowSellCount=" + allowSellCount
				+ ", soldCount=" + soldCount + ", allowBuyCount=" + allowBuyCount + ", nowStoreCount=" + nowStoreCount
				+ ", onWayCount=" + onWayCount + ", tradeOccupyCount=" + tradeOccupyCount + ", lockedCount="
				+ lockedCount + ", auditStatus=" + auditStatus + ", memo=" + memo + ", createTime=" + createTime
				+ ", onsalePrice=" + onsalePrice + ", discountPrice=" + discountPrice + ", topPicList=" + topPicList
				+ ", goodsBarcode=" + goodsBarcode + ", historyStore=" + historyStore + ", authorId=" + authorId
				+ ", userRealName=" + userRealName + ", userDept=" + userDept + ", haiguanCount=" + haiguanCount
				+ ", guojianCount=" + guojianCount + ", warehouseType=" + warehouseType + ", allStore=" + allStore
				+ ", defectiveStore=" + defectiveStore + "]";
	}


}

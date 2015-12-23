package com.need.domain.vo.goodsgroup;

import java.io.Serializable;
import java.util.Date;

import com.need.domain.pub.Page;

/**
 * <p></p>
 * @author LXD 2015年11月25日 上午10:38:36
 * @ClassName GoodsGroupVO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年11月25日
 * @modify by reason:{方法名}:{原因}
 */
public class GoodsGroupParaVO extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5977771578444830512L;
	private String groupBatch;
	private String goodsId;
	private String goodsName;
	private String scenePicKey;
	private Integer discountPrice;
	private Integer onsalePrice;
	private String warehouseType;
	private String isSoldout;
	private Integer storeCount;
	private String goodsStatus;
	private Date updateTime;
	private String goodsCode;
	private Double goodsPrice;
	
	
	
	
	
	
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGroupBatch() {
		return groupBatch;
	}
	public void setGroupBatch(String groupBatch) {
		this.groupBatch = groupBatch;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getScenePicKey() {
		return scenePicKey;
	}
	public void setScenePicKey(String scenePicKey) {
		this.scenePicKey = scenePicKey;
	}
	public Integer getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Integer getOnsalePrice() {
		return onsalePrice;
	}
	public void setOnsalePrice(Integer onsalePrice) {
		this.onsalePrice = onsalePrice;
	}
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getIsSoldout() {
		return isSoldout;
	}
	public void setIsSoldout(String isSoldout) {
		this.isSoldout = isSoldout;
	}
	public Integer getStoreCount() {
		return storeCount;
	}
	public void setStoreCount(Integer storeCount) {
		this.storeCount = storeCount;
	}
	public String getGoodsStatus() {
		return goodsStatus;
	}
	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "GoodsGroupParaVO [goodsId=" + goodsId + ", goodsName=" + goodsName + ", scenePicKey=" + scenePicKey
				+ ", discountPrice=" + discountPrice + ", onsalePrice=" + onsalePrice + ", warehouseType="
				+ warehouseType + ", isSoldout=" + isSoldout + ", storeCount=" + storeCount + ", goodsStatus="
				+ goodsStatus + ", updateTime=" + updateTime + "]";
	}
	
	


	
	
}

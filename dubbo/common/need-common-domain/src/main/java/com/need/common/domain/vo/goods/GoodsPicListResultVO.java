package com.need.common.domain.vo.goods;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class GoodsPicListResultVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1257851087264220252L;

	private String goodsId;

    private String goodsName;

    private String goodsStatus;

    private Integer sceneId;

    private String brief;

    private String[] topPicKeys;

    private Integer discountPrice;

    private Integer onsalePrice;

    private Integer storeCount;

    private Date createTime;

    private Date updateTime;

    private String goodsCode;

    private String oppseReason;
    
    private String scenePicKey;

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

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String[] getTopPicKeys() {
		return topPicKeys;
	}

	public void setTopPicKeys(String[] topPicKeys) {
		this.topPicKeys = topPicKeys;
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

	public Integer getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(Integer storeCount) {
		this.storeCount = storeCount;
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

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getOppseReason() {
		return oppseReason;
	}

	public void setOppseReason(String oppseReason) {
		this.oppseReason = oppseReason;
	}

	public String getScenePicKey() {
		return scenePicKey;
	}

	public void setScenePicKey(String scenePicKey) {
		this.scenePicKey = scenePicKey;
	}

	@Override
	public String toString() {
		return "GoodsPicListResultVO [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsStatus=" + goodsStatus
				+ ", sceneId=" + sceneId + ", brief=" + brief + ", topPicKeys=" + Arrays.toString(topPicKeys)
				+ ", discountPrice=" + discountPrice + ", onsalePrice=" + onsalePrice + ", storeCount=" + storeCount
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", goodsCode=" + goodsCode
				+ ", oppseReason=" + oppseReason + ", scenePicKey=" + scenePicKey + "]";
	}
    
}

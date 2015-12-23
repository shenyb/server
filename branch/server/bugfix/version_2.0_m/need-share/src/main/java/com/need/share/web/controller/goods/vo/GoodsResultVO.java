package com.need.share.web.controller.goods.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class GoodsResultVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -3695431182806611515L;

	private String goodsId;

    private String goodsName;

    private String goodsStatus;

    private String brief;

    private Integer discountPrice;

    private Integer onsalePrice;
    
    private String scenePicKey;
    
    private String discountPriceStr;
    
    private String onsalePriceStr;

    private String 	topPicKeys;//商品图
    
    private String[] detailPicKeys;//详情大图
    
    private String[] goodsTopPics;
    
    private Date updateTime;
    
    
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

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
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

	public String getScenePicKey() {
		return scenePicKey;
	}

	public void setScenePicKey(String scenePicKey) {
		this.scenePicKey = scenePicKey;
	}

	
	
	public String getDiscountPriceStr() {
		return discountPriceStr;
	}

	public void setDiscountPriceStr(String discountPriceStr) {
		this.discountPriceStr = discountPriceStr;
	}

	public String getOnsalePriceStr() {
		return onsalePriceStr;
	}

	public void setOnsalePriceStr(String onsalePriceStr) {
		this.onsalePriceStr = onsalePriceStr;
	}

	
	public String getTopPicKeys() {
		return topPicKeys;
	}

	public void setTopPicKeys(String topPicKeys) {
		this.topPicKeys = topPicKeys;
	}

	

	public String[] getDetailPicKeys() {
		return detailPicKeys;
	}

	public void setDetailPicKeys(String[] detailPicKeys) {
		this.detailPicKeys = detailPicKeys;
	}

	
	
	

	@Override
	public String toString() {
		return "GoodsResultVO [goodsId=" + goodsId + ", goodsName=" + goodsName
				+ ", goodsStatus=" + goodsStatus + ", brief=" + brief
				+ ", discountPrice=" + discountPrice + ", onsalePrice="
				+ onsalePrice + ", scenePicKey=" + scenePicKey
				+ ", discountPriceStr=" + discountPriceStr + ", onsalePriceStr="
				+ onsalePriceStr + ", topPicKeys=" + topPicKeys
				+ ", detailPicKeys=" + Arrays.toString(detailPicKeys)
				+ ", goodsTopPics=" + Arrays.toString(goodsTopPics)
				+ ", updateTime=" + updateTime + "]";
	}

	public String[] getGoodsTopPics() {
		return goodsTopPics;
	}

	public void setGoodsTopPics(String[] goodsTopPics) {
		this.goodsTopPics = goodsTopPics;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
    
    
    
}

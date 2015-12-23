package com.need.domain.po.bops.purchase;

import java.io.Serializable;

public class BopsPurchaseInfoPO implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 5518991092262058707L;

	private Integer purchaseInfoId;

    private Integer purchasePrice;

    private Integer purchaseCount;

    private String goodsCode;
    
    private String goodsId;

    private String goodsBarcode;

    private String goodsName;

    private Integer havestInfoCount;

    private Integer havestInfoPrice;

    private Integer havestInfoDisability;

    private Integer havestInfoNormal;

    private Integer purchaseId;

    public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getPurchaseInfoId() {
        return purchaseInfoId;
    }

    public void setPurchaseInfoId(Integer purchaseInfoId) {
        this.purchaseInfoId = purchaseInfoId;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsBarcode() {
        return goodsBarcode;
    }

    public void setGoodsBarcode(String goodsBarcode) {
        this.goodsBarcode = goodsBarcode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getHavestInfoCount() {
        return havestInfoCount;
    }

    public void setHavestInfoCount(Integer havestInfoCount) {
        this.havestInfoCount = havestInfoCount;
    }

    public Integer getHavestInfoPrice() {
        return havestInfoPrice;
    }

    public void setHavestInfoPrice(Integer havestInfoPrice) {
        this.havestInfoPrice = havestInfoPrice;
    }

    public Integer getHavestInfoDisability() {
    	if(havestInfoDisability==null){
    		 return 0;
    	}else{
    		 return havestInfoDisability;
    	}
       
    }

    public void setHavestInfoDisability(Integer havestInfoDisability) {
        this.havestInfoDisability = havestInfoDisability;
    }

    public Integer getHavestInfoNormal() {
    	if(havestInfoNormal==null){
   		 return 0;
   	}else{
   		 return havestInfoNormal;
   	}
    }

    public void setHavestInfoNormal(Integer havestInfoNormal) {
        this.havestInfoNormal = havestInfoNormal;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }
}
package com.need.domain.po.api.ops;

import java.io.Serializable;
import java.util.Date;

public class OpsSelectedPO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -9143204174484329291L;

	private String selectionId;

    private String goodsId;

    private String goodsName;

    private String goodsProfilePicKey;

    private Integer goodsPrice;

    private Integer goodsSort;

    private Date createTime;

    private String goodsStatus;

    public String getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(String selectionId) {
        this.selectionId = selectionId;
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

    public String getGoodsProfilePicKey() {
        return goodsProfilePicKey;
    }

    public void setGoodsProfilePicKey(String goodsProfilePicKey) {
        this.goodsProfilePicKey = goodsProfilePicKey;
    }

    public Integer getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Integer goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsSort() {
        return goodsSort;
    }

    public void setGoodsSort(Integer goodsSort) {
        this.goodsSort = goodsSort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

	@Override
	public String toString() {
		return "OpsSelectedPO [selectionId=" + selectionId + ", goodsId=" + goodsId + ", goodsName=" + goodsName
				+ ", goodsProfilePicKey=" + goodsProfilePicKey + ", goodsPrice=" + goodsPrice + ", goodsSort="
				+ goodsSort + ", createTime=" + createTime + ", goodsStatus=" + goodsStatus + "]";
	}
    
}
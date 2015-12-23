package com.need.domain.po.api.ops;

import java.io.Serializable;
import java.util.Date;

public class OpsHotGooodsPO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5347929900280846517L;

	private String popularityId;

    private String goodsId;

    private String goodsName;

    private String goodsProfilePicKey;

    private Integer goodsSort;

    private Date createTime;

    private String goodsStatus;

    public String getPopularityId() {
        return popularityId;
    }

    public void setPopularityId(String popularityId) {
        this.popularityId = popularityId;
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
		return "OpsHotGooodsPO [popularityId=" + popularityId + ", goodsId=" + goodsId + ", goodsName=" + goodsName
				+ ", goodsProfilePicKey=" + goodsProfilePicKey + ", goodsSort=" + goodsSort + ", createTime="
				+ createTime + ", goodsStatus=" + goodsStatus + "]";
	}
    
}
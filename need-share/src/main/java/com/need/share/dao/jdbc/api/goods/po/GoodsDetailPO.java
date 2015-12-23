package com.need.share.dao.jdbc.api.goods.po;

import java.io.Serializable;
import java.util.Date;

public class GoodsDetailPO implements Serializable{
	
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -6591163816108547861L;

	private String goodsId;

    private String goodsDesc;

    private String detailPicKeys;

    private String goodsParams;

    private Date createTime;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getDetailPicKeys() {
        return detailPicKeys;
    }

    public void setDetailPicKeys(String detailPicKeys) {
        this.detailPicKeys = detailPicKeys;
    }

    public String getGoodsParams() {
        return goodsParams;
    }

    public void setGoodsParams(String goodsParams) {
        this.goodsParams = goodsParams;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
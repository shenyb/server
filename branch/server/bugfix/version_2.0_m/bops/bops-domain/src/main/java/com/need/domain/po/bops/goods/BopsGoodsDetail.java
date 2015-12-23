package com.need.domain.po.bops.goods;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <p></p>
 * @author xiehao 2015年8月6日 下午5:16:31
 * @ClassName BopsGoodsDetail 商品详细信息
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月6日
 * @modify by reason:{方法名}:{原因}
 */
public class BopsGoodsDetail implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -4725577574587950077L;

	private String goodsId;

    private String goodsDesc;

    private String detailPicKeys;

    private String goodsParams;

    private Date createTime;

    private Date updateTime;

    private Integer publisherId;

    private Integer auditorId;

    private Integer purchasePrice;//进货价
	
	private String purchaseManager;//采购经理
	
	private String purchaseLeader;//采购主管
	
	private String shortName;//短名称

	public Integer getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Integer purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getPurchaseManager() {
		return purchaseManager;
	}

	public void setPurchaseManager(String purchaseManager) {
		this.purchaseManager = purchaseManager;
	}

	public String getPurchaseLeader() {
		return purchaseLeader;
	}

	public void setPurchaseLeader(String purchaseLeader) {
		this.purchaseLeader = purchaseLeader;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

	@Override
	public String toString() {
		return "BopsGoodsDetail [goodsId=" + goodsId + ", goodsDesc=" + goodsDesc + ", detailPicKeys=" + detailPicKeys
				+ ", goodsParams=" + goodsParams + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", publisherId=" + publisherId + ", auditorId=" + auditorId + "]";
	}
    
}
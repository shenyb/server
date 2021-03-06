package com.need.domain.po.bops.xinhuan;

import java.io.Serializable;
import java.util.Date;

public class BopsOpsXinhuanGoods implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1974115477234423750L;

	private String id;

    private String goodsId;

    private String opsId;

    private Date createTime;

    private Date updateTime;
    
    private Integer goodsScore;
    
    
    

    public Integer getGoodsScore() {
		return goodsScore;
	}

	public void setGoodsScore(Integer goodsScore) {
		this.goodsScore = goodsScore;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getOpsId() {
		return opsId;
	}

	public void setOpsId(String opsId) {
		this.opsId = opsId;
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

	@Override
	public String toString() {
		return "BopsOpsXinhuanGoods [id=" + id + ", goodsId=" + goodsId + ", opsId=" + opsId + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
    
}
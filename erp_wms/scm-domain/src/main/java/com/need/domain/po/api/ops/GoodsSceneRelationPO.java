package com.need.domain.po.api.ops;

import java.io.Serializable;
import java.util.Date;

public class GoodsSceneRelationPO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4654170162722879557L;

	private Integer id;

    private Integer scene;
    
    private String goodsId;

    private String scenePicKey;

    private String memo;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScene() {
		return scene;
	}

	public void setScene(Integer scene) {
		this.scene = scene;
	}

	public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getScenePicKey() {
        return scenePicKey;
    }

    public void setScenePicKey(String scenePicKey) {
        this.scenePicKey = scenePicKey;
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

	@Override
	public String toString() {
		return "GoodsSceneRelationPO [id=" + id + ", scene=" + scene + ", goodsId=" + goodsId + ", scenePicKey="
				+ scenePicKey + ", memo=" + memo + ", createTime=" + createTime + "]";
	}
    
}
package com.need.domain.po.bops.goods;

import java.io.Serializable;
import java.util.Date;

public class BopsGoodsScene implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -971308525024182948L;

	private Integer id;

    private Integer sceneId;
    
    private String reason;

    private String goodsId;

    private String scenePicKey;


    private Date createTime;

    private Date updateTime;

    private Integer publisherId;

    private Integer auditorId;
    private String auditStatus;
    private String memo;
   


	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}



	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


	public Integer getSceneId() {
		return sceneId;

	}


	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


	@Override
	public String toString() {
		return "BopsGoodsScene [id=" + id + ", sceneId=" + sceneId + ", reason=" + reason + ", goodsId=" + goodsId
				+ ", scenePicKey=" + scenePicKey + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", publisherId=" + publisherId + ", auditorId=" + auditorId + ", auditStatus=" + auditStatus
				+ ", memo=" + memo + "]";
	}
	

    
	
}
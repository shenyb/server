package com.need.domain.po.bops.basedata;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class BopsScene implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;

	private Integer sceneId;
    @NotBlank(message="场景名称不能为空")
	@Length(min=1,max=10,message="场景名称支持10个字以内")
    private String sceneName;

    private Integer publisherId;

    private Integer auditorId;

    private Date createTime;

    private Date updateTime;
    
    private String sceneDesc;

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }
   
    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
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

	public String getSceneDesc() {
		return sceneDesc;
	}

	public void setSceneDesc(String sceneDesc) {
		this.sceneDesc = sceneDesc;
	}

	@Override
	public String toString() {
		return "BopsScene [sceneId=" + sceneId + ", sceneName=" + sceneName + ", publisherId=" + publisherId
				+ ", auditorId=" + auditorId + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", sceneDesc=" + sceneDesc + "]";
	}
    
}
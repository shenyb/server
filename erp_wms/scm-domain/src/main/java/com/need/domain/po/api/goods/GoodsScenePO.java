package com.need.domain.po.api.goods;

import java.io.Serializable;
import java.util.Date;

public class GoodsScenePO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -6761226237053935314L;

	private Integer sceneId;

    private String sceneName;

    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "GoodsScenePO [sceneId=" + sceneId + ", sceneName=" + sceneName + ", createTime=" + createTime + "]";
	}
    
}
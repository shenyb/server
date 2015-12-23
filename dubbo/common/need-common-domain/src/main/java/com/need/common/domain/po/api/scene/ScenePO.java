package com.need.common.domain.po.api.scene;

import java.io.Serializable;
import java.util.Date;

public class ScenePO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -8902358763926581370L;
	private Integer  sceneId;
	private String  sceneName;
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
		return "ScenePO [sceneId=" + sceneId + ", sceneName=" + sceneName + ", createTime=" + createTime + "]";
	}

	
}

package com.need.domain.po.bops.basedata;

import java.io.Serializable;

public class GoodsSceneResultPO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 2737351679675972599L;

	private Integer id;

    private Integer sceneId;

    private String goodsId;

    private String scenePicKey;

    private String auditStatus;
    private String memo;
    private String goodsName;
    private String topPicKeys;
    private String goodsCode;
    private String sceneName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSceneId() {
		return sceneId;
	}
	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getTopPicKeys() {
		return topPicKeys;
	}
	public void setTopPicKeys(String topPicKeys) {
		this.topPicKeys = topPicKeys;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getSceneName() {
		return sceneName;
	}
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
	@Override
	public String toString() {
		return "GoodsSceneResultPO [id=" + id + ", sceneId=" + sceneId + ", goodsId=" + goodsId + ", scenePicKey="
				+ scenePicKey + ", auditStatus=" + auditStatus + ", memo=" + memo + ", goodsName=" + goodsName
				+ ", topPicKeys=" + topPicKeys + ", goodsCode=" + goodsCode + ", sceneName=" + sceneName + "]";
	}

}

package com.need.domain.vo.xinhuan;

import java.io.Serializable;

public class OpsXinhuanGoodsResultVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2132565248229291609L;

	private String id;

    private String goodsId;

    private String opsId;
    
    private String goodsName;
    
    private String goodsCode;
    
    private String scenePicKey;
    
    private String warehouseType;//仓库类型
    
    private String goodsScore;
    
    


	public String getGoodsScore() {
		return goodsScore;
	}

	public void setGoodsScore(String goodsScore) {
		this.goodsScore = goodsScore;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpsId() {
		return opsId;
	}

	public void setOpsId(String opsId) {
		this.opsId = opsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getScenePicKey() {
		return scenePicKey;
	}

	public void setScenePicKey(String scenePicKey) {
		this.scenePicKey = scenePicKey;
	}
}
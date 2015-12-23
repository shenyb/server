package com.need.domain.vo.xinhuan;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class XinhuanGoodsParamPageVO extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -9178321846306240559L;
	
	private String opsId;
	
	private String goodsCode; //商品编码
	
	private String goodsName;//商品名称
	
	private String warehouseType;//仓库类型 
	private Integer goodsScore;
	
	
	
	
	



	public Integer getGoodsScore() {
		return goodsScore;
	}

	public void setGoodsScore(Integer goodsScore) {
		this.goodsScore = goodsScore;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getOpsId() {
		return opsId;
	}

	public void setOpsId(String opsId) {
		this.opsId = opsId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	
}

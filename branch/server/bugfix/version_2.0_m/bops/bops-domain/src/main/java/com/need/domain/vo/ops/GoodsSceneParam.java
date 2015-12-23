package com.need.domain.vo.ops;

import java.io.Serializable;
import java.util.Arrays;

public class GoodsSceneParam implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1708558775993518261L;

	private String[] goodsIds;
	
	private String[] senceIds;
	
	private String[] scenePicKeys;

	private Integer publisherId;//创建者id
	
	private Integer auditorId;//审核者id
	
	private String auditorStatus;//审核状态
	
	private String memo;//审核评价
	
	public String[] getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(String[] goodsIds) {
		this.goodsIds = goodsIds;
	}



	public String[] getSenceIds() {
		return senceIds;
	}

	public void setSenceIds(String[] senceIds) {
		this.senceIds = senceIds;
	}


	public String[] getScenePicKeys() {
		return scenePicKeys;
	}

	public void setScenePicKeys(String[] scenePicKeys) {
		this.scenePicKeys = scenePicKeys;
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

	public String getAuditorStatus() {
		return auditorStatus;
	}

	public void setAuditorStatus(String auditorStatus) {
		this.auditorStatus = auditorStatus;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "GoodsSceneParam [goodsIds=" + Arrays.toString(goodsIds) + ", senceIds=" + Arrays.toString(senceIds)
				+ ", scenePicKeys=" + Arrays.toString(scenePicKeys) + ", publisherId=" + publisherId + ", auditorId="
				+ auditorId + ", auditorStatus=" + auditorStatus + ", memo=" + memo + "]";
	}
	
	 
}

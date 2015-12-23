package com.need.common.domain.po.api.trade;

import java.io.Serializable;
import java.util.List;

public class TradeLogisticsVO implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 7951959052124168939L;

	private Integer logisticsId;

	private String logisticsName;

	private Long logisticsValue;

	private String logisticsType;

	private Integer logisticsTypeValue;

	private Integer parentLogisticsId;

	private Integer cityCode;

	private String logisticsLetter;

	private List<TradeLogisticsVO> firstList;//一级行政区
	

	public Integer getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public Long getLogisticsValue() {
		return logisticsValue;
	}

	public void setLogisticsValue(Long logisticsValue) {
		this.logisticsValue = logisticsValue;
	}

	public String getLogisticsType() {
		return logisticsType;
	}

	public void setLogisticsType(String logisticsType) {
		this.logisticsType = logisticsType;
	}

	public Integer getLogisticsTypeValue() {
		return logisticsTypeValue;
	}

	public void setLogisticsTypeValue(Integer logisticsTypeValue) {
		this.logisticsTypeValue = logisticsTypeValue;
	}

	public Integer getParentLogisticsId() {
		return parentLogisticsId;
	}

	public void setParentLogisticsId(Integer parentLogisticsId) {
		this.parentLogisticsId = parentLogisticsId;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public String getLogisticsLetter() {
		return logisticsLetter;
	}

	public void setLogisticsLetter(String logisticsLetter) {
		this.logisticsLetter = logisticsLetter;
	}

	public List<TradeLogisticsVO> getFirstList() {
		return firstList;
	}

	public void setFirstList(List<TradeLogisticsVO> firstList) {
		this.firstList = firstList;
	}



	
	
	
	
	
}

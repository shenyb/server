package com.need.common.domain.po.api.trade;

import java.io.Serializable;

public class TradeLogisticsPO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -873250729007962999L;

	private Integer logisticsId;

	private String logisticsName;

	private Long logisticsValue;

	private String logisticsType;

	private String logisticsTypeDesc;

	private Integer logisticsTypeValue;

	private Integer parentLogisticsId;

	private Integer cityCode;

	private Integer logisticsSort;

	private String cityArea;

	private Integer cityAreaValue;

	private String logisticsLetter;

	private Integer recordState;

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

	public String getLogisticsTypeDesc() {
		return logisticsTypeDesc;
	}

	public void setLogisticsTypeDesc(String logisticsTypeDesc) {
		this.logisticsTypeDesc = logisticsTypeDesc;
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

	public Integer getLogisticsSort() {
		return logisticsSort;
	}

	public void setLogisticsSort(Integer logisticsSort) {
		this.logisticsSort = logisticsSort;
	}

	public String getCityArea() {
		return cityArea;
	}

	public void setCityArea(String cityArea) {
		this.cityArea = cityArea;
	}

	public Integer getCityAreaValue() {
		return cityAreaValue;
	}

	public void setCityAreaValue(Integer cityAreaValue) {
		this.cityAreaValue = cityAreaValue;
	}

	public String getLogisticsLetter() {
		return logisticsLetter;
	}

	public void setLogisticsLetter(String logisticsLetter) {
		this.logisticsLetter = logisticsLetter;
	}

	public Integer getRecordState() {
		return recordState;
	}

	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	@Override
	public String toString() {
		return "TradeLogisticsPO [logisticsId=" + logisticsId + ", logisticsName=" + logisticsName + ", logisticsValue="
				+ logisticsValue + ", logisticsType=" + logisticsType + ", logisticsTypeDesc=" + logisticsTypeDesc
				+ ", logisticsTypeValue=" + logisticsTypeValue + ", parentLogisticsId=" + parentLogisticsId
				+ ", cityCode=" + cityCode + ", logisticsSort=" + logisticsSort + ", cityArea=" + cityArea
				+ ", cityAreaValue=" + cityAreaValue + ", logisticsLetter=" + logisticsLetter + ", recordState="
				+ recordState + "]";
	}

}
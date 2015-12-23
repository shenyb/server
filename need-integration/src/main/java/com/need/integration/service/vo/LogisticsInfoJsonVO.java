package com.need.integration.service.vo;

public class LogisticsInfoJsonVO {
	private String logisticsServiceId;
	private String logisticsNums;

	public String getLogisticsServiceId() {
		return logisticsServiceId;
	}

	public void setLogisticsServiceId(String logisticsServiceId) {
		this.logisticsServiceId = logisticsServiceId;
	}

	public String getLogisticsNums() {
		return logisticsNums;
	}

	public void setLogisticsNums(String logisticsNums) {
		this.logisticsNums = logisticsNums;
	}

	public LogisticsInfoJsonVO() {
		super();
	}

	public LogisticsInfoJsonVO(String logisticsServiceId, String logisticsNums) {
		super();
		this.logisticsServiceId = logisticsServiceId;
		this.logisticsNums = logisticsNums;
	}
}
package com.need.integration.common.birdex.bean.tracking;

import java.io.Serializable;

public class TrackingOrderVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 2103797505751848708L;

	private String appKey;
	private String eventTime;
	private String occurTime;
	private String logisticsId;
	private String trackingCode;
	private String trackingDesc;
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getOccurTime() {
		return occurTime;
	}
	public void setOccurTime(String occurTime) {
		this.occurTime = occurTime;
	}
	public String getLogisticsId() {
		return logisticsId;
	}
	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}
	public String getTrackingCode() {
		return trackingCode;
	}
	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}
	public String getTrackingDesc() {
		return trackingDesc;
	}
	public void setTrackingDesc(String trackingDesc) {
		this.trackingDesc = trackingDesc;
	}
	@Override
	public String toString() {
		return "TrackingOrderVO [appKey=" + appKey + ", eventTime=" + eventTime + ", occurTime=" + occurTime
				+ ", logisticsId=" + logisticsId + ", trackingCode=" + trackingCode + ", trackingDesc=" + trackingDesc
				+ "]";
	}
	
}

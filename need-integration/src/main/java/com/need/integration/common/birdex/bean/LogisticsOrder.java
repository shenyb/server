package com.need.integration.common.birdex.bean;

import java.io.Serializable;

public class LogisticsOrder implements Serializable {
	/**
	 * <p>
	 * </p>
	 * 
	 * @author LV 2015年10月23日 上午11:42:59
	 * @ClassName LogisticsOrder
	 * @Description http://openapi.birdex.cn/help/Api/POST/order/create
	 * @version V1.0
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: LV 2015年10月23日
	 * @modify by reason:{方法名}:{原因}
	 */
	private static final long serialVersionUID = 1L;

	private String logisticsId;
	private String expressCode;
	private String expressNo;
	private String warehouseCode;
	private String trackingNos;
	private String routeId;
	
	public String getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getTrackingNos() {
		return trackingNos;
	}

	public void setTrackingNos(String trackingNos) {
		this.trackingNos = trackingNos;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

}

package com.need.common.domain.vo.trade;


public class CreateCombinationTradeParamVO extends CreateTradeBaseParamVO {
	private String batchNo;
	private TradeCartVO[] records;
	private String warehouseType;
	private String couponNo;
	private String certificationChannel;
	
	
	public String getCertificationChannel() {
		return certificationChannel;
	}
	public void setCertificationChannel(String certificationChannel) {
		this.certificationChannel = certificationChannel;
	}
	public String getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	public TradeCartVO[] getRecords() {
		return records;
	}
	public void setRecords(TradeCartVO[] records) {
		this.records = records;
	}
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
}

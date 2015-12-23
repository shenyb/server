package com.need.integration.common.haimeiSdk.domain.base;

/**
 * 库存批次
 * @author Administrator
 *
 */
public class InventoryLot {
	
	private String lotNo;
	
	private Long lotQtyOnLocation;
	
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public Long getLotQtyOnLocation() {
		return lotQtyOnLocation;
	}
	public void setLotQtyOnLocation(Long lotQtyOnLocation) {
		this.lotQtyOnLocation = lotQtyOnLocation;
	}
	
	
}

package com.need.integration.common.haimeiSdk.domain.base;

import java.util.Date;
import java.util.List;

public class CheckInventory {
	private Long stockId ; 
	
	private Long ownerId ; 
	
	private String checkInventoryNo ; 
	
	private String checkInventoryType ; 
	
	private Date endTime ; 
	
	private Date startTime ;
	
	private List<CheckInventoryDet> inventory_check_dets ;

	public List<CheckInventoryDet> getInventory_check_dets() {
		return inventory_check_dets;
	}

	public void setInventory_check_dets(List<CheckInventoryDet> inventoryCheckDets) {
		inventory_check_dets = inventoryCheckDets;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getCheckInventoryNo() {
		return checkInventoryNo;
	}

	public void setCheckInventoryNo(String checkInventoryNo) {
		this.checkInventoryNo = checkInventoryNo;
	}

	public String getCheckInventoryType() {
		return checkInventoryType;
	}

	public void setCheckInventoryType(String checkInventoryType) {
		this.checkInventoryType = checkInventoryType;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	} 
	
}

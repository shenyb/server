package com.need.integration.common.haimeiSdk.domain.base;

import java.util.Date;
import java.util.List;

public class StockProcess {
	private String stockProcessNo ;
	
	private Long ownerId ; 
	
	private Long stockId ; 
	
	private Date createDate ; 
	
	private String stockProcessStatus ; 

	private List<ProcessMaterial> materialLists ; 
	
	private List<ProcessFinished> finishedLists ;

	public String getStockProcessNo() {
		return stockProcessNo;
	}

	public void setStockProcessNo(String stockProcessNo) {
		this.stockProcessNo = stockProcessNo;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStockProcessStatus() {
		return stockProcessStatus;
	}

	public void setStockProcessStatus(String stockProcessStatus) {
		this.stockProcessStatus = stockProcessStatus;
	}

	public List<ProcessMaterial> getMaterialLists() {
		return materialLists;
	}

	public void setMaterialLists(List<ProcessMaterial> materialLists) {
		this.materialLists = materialLists;
	}

	public List<ProcessFinished> getFinishedLists() {
		return finishedLists;
	}

	public void setFinishedLists(List<ProcessFinished> finishedLists) {
		this.finishedLists = finishedLists;
	} 
	
}

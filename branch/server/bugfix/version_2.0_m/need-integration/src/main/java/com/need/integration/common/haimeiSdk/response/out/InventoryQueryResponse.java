package com.need.integration.common.haimeiSdk.response.out;

import java.util.Date;
import java.util.List;

import com.need.integration.common.haimeiSdk.domain.base.Inventory;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class InventoryQueryResponse  extends AbstractResponse<InventoryQueryResponse>{

	private Long totalCount ; 
	
	private Long pageNo ; 
	
	private Long pageSize ; 
	
	private Long pageNum ; 
	
	private Long stockId ; 
	
	private Long ownerId ; 
	
	private Date startUpdateDate ;
	
	private Date endUpdateDate ; 
	
	List<Inventory> skus ;

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getPageNo() {
		return pageNo;
	}

	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getPageNum() {
		return pageNum;
	}

	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
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

	public List<Inventory> getSkus() {
		return skus;
	}

	public void setSkus(List<Inventory> skus) {
		this.skus = skus;
	}

	public Date getStartUpdateDate() {
		return startUpdateDate;
	}

	public void setStartUpdateDate(Date startUpdateDate) {
		this.startUpdateDate = startUpdateDate;
	}

	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}
}

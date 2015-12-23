package com.need.integration.common.haimeiSdk.response.out;

import java.util.List;

import com.need.integration.common.haimeiSdk.domain.base.CheckInventory;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class InventoryCheckResponse extends AbstractResponse<InventoryCheckResponse>{
	private Long pageSize ;
	
	private Long pageNo ; 
	
	private Long pageNum ; 
	
	private Long totalCount ; 
	
	private List<CheckInventory> inventory_checks ;

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getPageNo() {
		return pageNo;
	}

	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}

	public Long getPageNum() {
		return pageNum;
	}

	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public List<CheckInventory> getInventory_checks() {
		return inventory_checks;
	}

	public void setInventory_checks(List<CheckInventory> inventoryChecks) {
		inventory_checks = inventoryChecks;
	}
	
	
}

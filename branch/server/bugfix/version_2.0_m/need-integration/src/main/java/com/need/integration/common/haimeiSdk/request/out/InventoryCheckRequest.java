package com.need.integration.common.haimeiSdk.request.out;

import java.util.Date;
import java.util.List;

import com.need.integration.common.haimeiSdk.domain.base.CheckInventoryDet;
import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.out.InventoryCheckResponse;

public class InventoryCheckRequest extends AbstractRequest implements IscsRequest {

	private Long stockId ; 
	
	private Long ownerId ; 
	
	private Date beginDate ; 
	
	private Date endDate ; 
	
	private Long type ; 
	
	private String productCode ; 
	
	private Long pageNo ; 
	
	private Long pageSize ; 
	
	private List<CheckInventoryDet>  inventory_check_dets ; 
	
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

	public Date getbeginDate() {
		return beginDate;
	}

	public void setbeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
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

	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "inventoryCheck";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return InventoryCheckResponse.class;
	}

}

package com.need.integration.common.haimeiSdk.request.out;

import java.util.Date;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.out.InventoryQueryResponse;

public class InventoryQueryRequest extends AbstractRequest implements IscsRequest {

	private Long stockId;

	private Long ownerId;

	private Date startUpdateDate;

	private Date endUpdateDate;

	private String productCode;
	private String productCodeList;

	private Long returnLotNo;

	private Long pageNo;

	private Long pageSize;

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

	public Long getReturnLotNo() {
		return returnLotNo;
	}

	public void setReturnLotNo(Long returnLotNo) {
		this.returnLotNo = returnLotNo;
	}

	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "inventoryQuery";
	}

	public String getProductCodeList() {
		return productCodeList;
	}

	public void setProductCodeList(String productCodeList) {
		this.productCodeList = productCodeList;
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return InventoryQueryResponse.class;
	}

}

package com.need.integration.common.haimeiSdk.request.base;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.PurchaseInStockQueryResponse;
/**
 * 采购单入库数据查询
 * @author 刘胜超
 *
 */
public class PurchaseInStockQueryRequest extends AbstractRequest implements IscsRequest {
	private Long stockId ; 
	
	private Long ownerId ; 
	
	private String purchaseNo ; 
	
	private Long billType ; 
	
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

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	
	public Long getBillType() {
		return billType;
	}

	public void setBillType(Long billType) {
		this.billType = billType;
	}

	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "purchaseInStockQuery";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return PurchaseInStockQueryResponse.class;
	}

}

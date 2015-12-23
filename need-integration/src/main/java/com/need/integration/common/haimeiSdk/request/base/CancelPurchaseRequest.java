package com.need.integration.common.haimeiSdk.request.base;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.CancelPurchaseResponse;
/**
 * 采购单取消
 * 状态为（0,10,20）可以取消，状态为30（正在收货） ，40（已收货）不能取消 
 * @author 刘胜超
 *
 */
public class CancelPurchaseRequest extends AbstractRequest implements IscsRequest  {
	
	private Long stockId ; 
	
	private Long ownerId ; 
	
	private String purchaseNo ; 
	
	private String remark ; 
	
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "cancelPurchase";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return CancelPurchaseResponse.class;
	}

}

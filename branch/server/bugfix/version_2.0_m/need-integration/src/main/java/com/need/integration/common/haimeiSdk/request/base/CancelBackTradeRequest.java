package com.need.integration.common.haimeiSdk.request.base;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.CancelBackTradeResponse;
/**
 * 销售退货单取消
 * @author 刘胜超
 *
 */
public class CancelBackTradeRequest extends AbstractRequest implements IscsRequest {
	private Long stockId ; 
	
	private Long shopId ; 
	
	private String orderReturnNo ; 
	
	private String iscsOrderReturnNo ; 
	
	private String remark ; 

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getOrderReturnNo() {
		return orderReturnNo;
	}

	public void setOrderReturnNo(String orderReturnNo) {
		this.orderReturnNo = orderReturnNo;
	}

	public String getIscsOrderReturnNo() {
		return iscsOrderReturnNo;
	}

	public void setIscsOrderReturnNo(String iscsOrderReturnNo) {
		this.iscsOrderReturnNo = iscsOrderReturnNo;
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
		return "cancelBackTrade";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return CancelBackTradeResponse.class;
	}

}

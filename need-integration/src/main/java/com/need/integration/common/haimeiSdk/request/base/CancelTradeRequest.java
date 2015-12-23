package com.need.integration.common.haimeiSdk.request.base;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.CancelTradeResponse;

/**
 * 订单取消
 * @author 刘胜超
 *
 */
public class CancelTradeRequest extends AbstractRequest implements IscsRequest  {

	private Long stockId ; 
	
	private Long shopId ; 
	
	private String orderNo ;
	
	private String remark ; 
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "cancelTrade";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return CancelTradeResponse.class;
	}

}

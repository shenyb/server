package com.need.integration.common.haimeiSdk.request.base;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.BackTradeInStockQueryResponse;

/**
 * 销售退货单入库数据查询
 * 外部查询网仓系统退换货单入库情况，不区分良品不良品
 * @author 刘胜超
 *
 */
public class BackTradeInStockQueryRequest extends AbstractRequest implements IscsRequest  {

	private Long stockId ; 
	
	private Long shopId ; 
	
	private String orderReturnNo ; 
	
	private String iscsOrderReturnNo ; 
	
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

	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "backTradeInStockQuery";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return BackTradeInStockQueryResponse.class;
	}

}

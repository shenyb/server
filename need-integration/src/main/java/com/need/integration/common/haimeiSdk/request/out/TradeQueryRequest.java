package com.need.integration.common.haimeiSdk.request.out;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.out.TradeQueryResponse;

/**
 * 订单查询
 * 
 * @author 徐纯
 *
 *  2015-6-24 下午03:42:05
 */
public class TradeQueryRequest extends AbstractRequest implements IscsRequest {
	private String stockId;
	private String shopId;
	private String orderNo;
	private String timeType;
	private String beginCreateDate;
	private String endCreateDate;
	private String pageNo;
	private String pageSize;
	
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 *  1平台创建时间(默认)
	 *	2付款时间
	 *	3发货时间
	 *	4系统创建时间
	 *	5订单更新
	 * @param timeType
	 */
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public void setBeginCreateDate(String beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public void setEndCreateDate(String endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getStockId() {
		return stockId;
	}

	public String getShopId() {
		return shopId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getTimeType() {
		return timeType;
	}

	public String getBeginCreateDate() {
		return beginCreateDate;
	}

	public String getEndCreateDate() {
		return endCreateDate;
	}

	public String getPageNo() {
		return pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	@Override
	public String getApiMethod() {
		return "tradeQuery";
	}

	@Override
	public Class getResponseClass() {
		return TradeQueryResponse.class;
	}
}

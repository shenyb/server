package com.need.integration.common.haimeiSdk.request.base;

import java.util.Date;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.TradeDeliverQueryResponse;
/**
 * 订单发货状态查询
 * 
 * @author 刘胜超
 *
 */
public class TradeDeliverQueryRequest  extends AbstractRequest implements IscsRequest {
	private Long stockId ; 
	
	private Long shopId ; 

	private String orderNo ; 
	
	private String timeType ; 
	
	private Date beginDate ; 
	
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

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	private Date endDate ; 
	
	private Long pageNo ; 
	
	private Long pageSize ; 
	
	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "tradeDeliverQuery";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return TradeDeliverQueryResponse.class;
	}

}

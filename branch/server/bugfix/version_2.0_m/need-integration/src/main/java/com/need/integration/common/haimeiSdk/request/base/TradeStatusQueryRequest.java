package com.need.integration.common.haimeiSdk.request.base;

import java.util.Date;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.TradeStatusQueryResponse;
/**
 * 订单状态查询
 * @author 刘胜超
 *
 */
public class TradeStatusQueryRequest extends AbstractRequest implements IscsRequest  {
	
	private Long stockId ;
	
	private Long shopId ;
	
	private String orderNo ; 
	
	private String timeType ;
	/** timeType
	 *  1订单最后更新时间(默认)
	 *  2付款时间
	 *  3发货时间
	 *  4系统创建时间
	 *  5 平台创建时间
	 */
	
	private Long status ; 
	
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

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
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
		return "tradeStatusQuery";
	}

	@Override
	public Class getResponseClass() {
		return TradeStatusQueryResponse.class;
	}

}

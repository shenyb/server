package com.need.integration.common.haimeiSdk.request.base;

import java.util.Date;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.QueryProductsResponse;

/**
 * 商品查询
 * @author 刘胜超
 *
 */
public class QueryProductsRequest  extends AbstractRequest implements IscsRequest {
	
	private Long ownerId ; 
	private Date beginDate ;
	private Date endDate ;
	private Long pageNo ; 
	private Long pageSize ;

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
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

	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "queryProducts";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return QueryProductsResponse.class;
	}

}

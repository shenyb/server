package com.need.integration.common.haimeiSdk.response.base;

import java.util.List;

import com.need.integration.common.haimeiSdk.domain.base.Product;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class QueryProductsResponse extends AbstractResponse<QueryProductsResponse>  {
	private Long pageSize ;
	private Long pageNum ;
	private Long pageNo ;
	private Long totalCount ;
	List<Product> queryProducts ;

	
	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getPageNum() {
		return pageNum;
	}

	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}

	public Long getPageNo() {
		return pageNo;
	}

	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public List<Product> getQueryProducts() {
		return queryProducts;
	}

	public void setQueryProducts(List<Product> queryProducts) {
		this.queryProducts = queryProducts;
	}
}

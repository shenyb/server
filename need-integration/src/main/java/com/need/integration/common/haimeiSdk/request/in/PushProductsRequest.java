package com.need.integration.common.haimeiSdk.request.in;

import java.util.List;

import com.need.integration.common.haimeiSdk.domain.base.Product;
import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.in.PushProductsResponse;

/**
 * 商品导入
 * @author 徐纯
 *
 *  2015-6-24 下午03:44:44
 */
public class PushProductsRequest extends AbstractRequest implements IscsRequest {
	Long count;
	List<Product> products;
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Long getCount() {
		return this.products == null ? 0L :new Long(this.products.size());
	}

	@Override
	public String getApiMethod() {
		return "pushProducts";
	}

	@Override
	public Class getResponseClass() {
		return PushProductsResponse.class;
	}
}

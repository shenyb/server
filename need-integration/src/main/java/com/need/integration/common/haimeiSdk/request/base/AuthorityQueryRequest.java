package com.need.integration.common.haimeiSdk.request.base;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.AuthorityQueryResponse;


/**
 * 权限查询
 * 根据app查询所拥有的仓库，货主，店铺权限
 * @author 徐纯
 *
 *  2015-6-24 下午01:15:18
 */
public class AuthorityQueryRequest extends AbstractRequest implements IscsRequest {
	@Override
	public String getApiMethod() {
		return "authorityQuery";
	}

	@Override
	public Class getResponseClass() {
		return AuthorityQueryResponse.class;
	}

}

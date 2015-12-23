package com.need.integration.common.haimeiSdk.request.base;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.PrReturnHeaderStatusQueryResponse;
/**
 * 退货单状态查询
 * @author 刘胜超
 *
 */
public class PrReturnHeaderStatusQueryRequest extends AbstractRequest implements IscsRequest {
	private String platformReturnId ;
	
	private Long stockId ; 
	
	private Long ownerId ; 
	
	
	public String getPlatformReturnId() {
		return platformReturnId;
	}

	public void setPlatformReturnId(String platformReturnId) {
		this.platformReturnId = platformReturnId;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "prReturnHeaderStatusQuery";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return PrReturnHeaderStatusQueryResponse.class;
	}

}

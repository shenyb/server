package com.need.integration.common.haimeiSdk.request.base;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.ExpressTrackResponse;
/**
 * 快递物流跟踪 查询
 * @author 刘胜超
 *
 */
public class ExpressTrackRequest extends AbstractRequest implements IscsRequest {

	private String outSid ; 
	
	private String stockId ;
	
	private String shopId ; 
	
	public String getOutSid() {
		return outSid;
	}

	public void setOutSid(String outSid) {
		this.outSid = outSid;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "expressTrack";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return ExpressTrackResponse.class;
	}

}

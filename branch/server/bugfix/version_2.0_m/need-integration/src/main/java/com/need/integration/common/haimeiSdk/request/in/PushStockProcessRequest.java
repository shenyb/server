package com.need.integration.common.haimeiSdk.request.in;

import java.util.List;

import com.need.integration.common.haimeiSdk.domain.base.ProcessFinished;
import com.need.integration.common.haimeiSdk.domain.base.ProcessMaterial;
import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.in.PushStockProcessResponse;

public class PushStockProcessRequest extends AbstractRequest implements IscsRequest {
	
	
	private String tids ; 
	
	private Long stockId ;
	
	private Long ownerId ; 
	
	private String remark ; 
	
	private List<ProcessMaterial> materialLists ;
	
	private List<ProcessFinished> finishedLists ;

	public String getTids() {
		return tids;
	}

	public void setTids(String tids) {
		this.tids = tids;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ProcessMaterial> getMaterialLists() {
		return materialLists;
	}

	public void setMaterialLists(List<ProcessMaterial> materialLists) {
		this.materialLists = materialLists;
	}

	public List<ProcessFinished> getFinishedLists() {
		return finishedLists;
	}

	public void setFinishedLists(List<ProcessFinished> finishedLists) {
		this.finishedLists = finishedLists;
	} 
	
	@Override
	public String getApiMethod() {
		// TODO Auto-generated method stub
		return "createStockProcess";
	}

	@Override
	public Class getResponseClass() {
		// TODO Auto-generated method stub
		return PushStockProcessResponse.class;
	}

}

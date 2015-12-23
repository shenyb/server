package com.need.integration.common.haimeiSdk.request.base;

import com.need.integration.common.haimeiSdk.request.AbstractRequest;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.base.PushSupplierResponse;
/**
 * 采购单入库数据查询
 * @author 刘胜超
 *
 */
public class PushSupplierRequest extends AbstractRequest implements IscsRequest {
	private Long stockId;
	private Long ownerId;
	private String buName;
	private String fullName;
	private String businessMan;
	private Long isProcess;
	private Long isRelatedPresale;
	private String businessRange;
	private String companyIndex;
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

	public String getBuName() {
		return buName;
	}

	public void setBuName(String buName) {
		this.buName = buName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBusinessMan() {
		return businessMan;
	}

	public void setBusinessMan(String businessMan) {
		this.businessMan = businessMan;
	}

	public Long getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(Long isProcess) {
		this.isProcess = isProcess;
	}

	public Long getIsRelatedPresale() {
		return isRelatedPresale;
	}

	public void setIsRelatedPresale(Long isRelatedPresale) {
		this.isRelatedPresale = isRelatedPresale;
	}

	public String getBusinessRange() {
		return businessRange;
	}

	public void setBusinessRange(String businessRange) {
		this.businessRange = businessRange;
	}

	public String getCompanyIndex() {
		return companyIndex;
	}

	public void setCompanyIndex(String companyIndex) {
		this.companyIndex = companyIndex;
	}

	@Override
	public String getApiMethod() {
		return "pushSupplier";
	}

	@Override
	public Class getResponseClass() {
		return PushSupplierResponse.class;
	}

}

package com.need.integration.common.haimeiSdk.response.base;

import java.util.List;

import com.need.integration.common.haimeiSdk.domain.base.Unit;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public class AuthorityQueryResponse extends AbstractResponse<AuthorityQueryResponse> {
	List<Unit> units;

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}
}

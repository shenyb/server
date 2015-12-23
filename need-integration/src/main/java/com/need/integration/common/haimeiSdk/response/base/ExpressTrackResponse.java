package com.need.integration.common.haimeiSdk.response.base;

import java.util.List;

import com.need.integration.common.haimeiSdk.domain.base.ShipmentTrack;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;
/**
 * 快递物流跟踪查询
 * @author 刘胜超
 *
 */
public class ExpressTrackResponse extends AbstractResponse<List<ShipmentTrack>> {
	private String outSid ; 
	
	private List<ShipmentTrack> content ;

	public String getOutSid() {
		return outSid;
	}

	public void setOutSid(String outSid) {
		this.outSid = outSid;
	}

	public List<ShipmentTrack> getContent() {
		return content;
	}

	public void setContent(List<ShipmentTrack> content) {
		this.content = content;
	} 
	
}

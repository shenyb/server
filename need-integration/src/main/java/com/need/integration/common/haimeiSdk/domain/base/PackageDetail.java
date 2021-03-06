package com.need.integration.common.haimeiSdk.domain.base;

import java.util.Date;
import java.util.List;

public class PackageDetail {
	private String transporterId ; 
	
	private String outSid ; 
	
	private Long weight ; 
	
	private Date shipDate ; 
	
	public String getTransporterId() {
		return transporterId;
	}

	public void setTransporterId(String transporterId) {
		this.transporterId = transporterId;
	}

	public String getOutSid() {
		return outSid;
	}

	public void setOutSid(String outSid) {
		this.outSid = outSid;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

 

	private List<Skus> skus ;

	public List<Skus> getSkus() {
		return skus;
	}

	public void setSkus(List<Skus> skus) {
		this.skus = skus;
	} 
}

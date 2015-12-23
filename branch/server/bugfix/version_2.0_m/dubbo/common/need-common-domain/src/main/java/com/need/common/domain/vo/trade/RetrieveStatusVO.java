package com.need.common.domain.vo.trade;

import java.io.Serializable;
import java.util.Date;

public class RetrieveStatusVO implements Serializable {

	private static final long serialVersionUID = 5315369074248979352L;
	private Date createTime;
	private String state;
	private String logisticsNo;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLogisticsNo() {
		return logisticsNo;
	}
	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}
	

}

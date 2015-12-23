package com.need.domain.vo.trade;

import java.io.Serializable;

import com.need.domain.pub.Page;

public class OrderExportParamVO extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 5544701047986545778L;
	private String tradeTimeStart;
	private String tradeTimeEnd;
	private String position;
	private String retrieveStatus;//清关状态
	private String exportCount;
	public String getTradeTimeStart() {
		return tradeTimeStart;
	}
	public void setTradeTimeStart(String tradeTimeStart) {
		this.tradeTimeStart = tradeTimeStart;
	}
	public String getTradeTimeEnd() {
		return tradeTimeEnd;
	}
	public void setTradeTimeEnd(String tradeTimeEnd) {
		this.tradeTimeEnd = tradeTimeEnd;
	}
	public String getRetrieveStatus() {
		return retrieveStatus;
	}
	public void setRetrieveStatus(String retrieveStatus) {
		this.retrieveStatus = retrieveStatus;
	}
	public String getExportCount() {
		return exportCount;
	}
	public void setExportCount(String exportCount) {
		this.exportCount = exportCount;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "OrderExportParamVO [tradeTimeStart=" + tradeTimeStart + ", tradeTimeEnd=" + tradeTimeEnd + ", position="
				+ position + ", retrieveStatus=" + retrieveStatus + ", exportCount=" + exportCount + "]";
	}
	
}

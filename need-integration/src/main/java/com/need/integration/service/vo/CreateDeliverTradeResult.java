package com.need.integration.service.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年10月27日 上午9:45:35
 * @ClassName CreateDeliverTradeParamVO
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年10月27日
 * @modify by reason:{方法名}:{原因}
 */
public class CreateDeliverTradeResult {
	private String totalCount;
	private String successCount;
	private String failedCount;
	private List<OrderResult> failedList=new ArrayList<OrderResult>();
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(String successCount) {
		this.successCount = successCount;
	}
	public String getFailedCount() {
		return failedCount;
	}
	public void setFailedCount(String failedCount) {
		this.failedCount = failedCount;
	}
	public List<OrderResult> getFailedList() {
		return failedList;
	}
	public void setFailedList(List<OrderResult> failedList) {
		this.failedList = failedList;
	}
	
}
class OrderResult{
	String orderNo;
	String reason;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}

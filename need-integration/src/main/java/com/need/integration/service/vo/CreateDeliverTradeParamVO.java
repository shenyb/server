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
public class CreateDeliverTradeParamVO {
	private String count;
	private List<CreateDeliverTradeVO> trades = new ArrayList<CreateDeliverTradeVO>();
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public List<CreateDeliverTradeVO> getTrades() {
		return trades;
	}
	public void setTrades(List<CreateDeliverTradeVO> trades) {
		this.trades = trades;
	}
	
}

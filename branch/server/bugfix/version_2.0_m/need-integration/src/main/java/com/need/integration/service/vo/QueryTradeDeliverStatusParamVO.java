package com.need.integration.service.vo;

/**
 * 
 * <p></p>
 * @author shenyb 2015年10月27日 下午2:21:16
 * @ClassName QueryTradeDeliverStatusParamVO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年10月27日
 * @modify by reason:{方法名}:{原因}
 */
public class QueryTradeDeliverStatusParamVO {
	private String stockId;
	private String shopId;
	private String orderNo;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
}

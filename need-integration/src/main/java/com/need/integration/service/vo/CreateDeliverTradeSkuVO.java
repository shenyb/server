package com.need.integration.service.vo;

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
public class CreateDeliverTradeSkuVO {
	private String productCode;
	private String qty;
	private String ownerId;
	private String shopId;
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
}

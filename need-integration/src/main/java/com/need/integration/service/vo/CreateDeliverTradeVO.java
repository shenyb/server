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
public class CreateDeliverTradeVO {
	private String orderNo;
	private String stockId;
	private String transporterFlag;
	private String shopId;
	private String orderCreateTime;
	private String orderPayTime;
	private String buyerNick;
	private String receiverCountry;
	private String receiverProvince;
	private String receiverCity;
	private String receiverCounty;
	private String receiverAddress;
	private String receiverName;
	private String receiverMobile;
	private String needInvoice;
	private String needCard;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getTransporterFlag() {
		return transporterFlag;
	}

	public void setTransporterFlag(String transporterFlag) {
		this.transporterFlag = transporterFlag;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getOrderPayTime() {
		return orderPayTime;
	}

	public void setOrderPayTime(String orderPayTime) {
		this.orderPayTime = orderPayTime;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public String getReceiverCountry() {
		return receiverCountry;
	}

	public void setReceiverCountry(String receiverCountry) {
		this.receiverCountry = receiverCountry;
	}

	public String getReceiverProvince() {
		return receiverProvince;
	}

	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverCounty() {
		return receiverCounty;
	}

	public void setReceiverCounty(String receiverCounty) {
		this.receiverCounty = receiverCounty;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getNeedInvoice() {
		return needInvoice;
	}

	public void setNeedInvoice(String needInvoice) {
		this.needInvoice = needInvoice;
	}

	public String getNeedCard() {
		return needCard;
	}

	public void setNeedCard(String needCard) {
		this.needCard = needCard;
	}

	private List<CreateDeliverTradeSkuVO> skus = new ArrayList<CreateDeliverTradeSkuVO>();

	public List<CreateDeliverTradeSkuVO> getSkus() {
		return skus;
	}

	public void setSkus(List<CreateDeliverTradeSkuVO> skus) {
		this.skus = skus;
	}
}

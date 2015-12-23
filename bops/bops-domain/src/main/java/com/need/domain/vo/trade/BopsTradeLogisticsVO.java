package com.need.domain.vo.trade;

import java.util.Date;
import java.util.List;

public class BopsTradeLogisticsVO {
	private String tradeNo;

	private String addressId;

	private String userId;

	private Integer logisticsId;

	private String address;

	private Date createTime;

	private String goodsSnNo;

	private String logisticsInfo;
	/**
	 * 商品编码和物料号
	 */
	private List<GoodsSnNoVO> goodsSnNoList;
	/**
	 * 物流信息
	 */
	private List<LogisticsInfoJsonVO> logisticsList; 


	public void setLogisticsList(List<LogisticsInfoJsonVO> logisticsList) {
		this.logisticsList = logisticsList;
	}

	public List<GoodsSnNoVO> getGoodsSnNoList() {
		return goodsSnNoList;
	}

	public void setGoodsSnNoList(List<GoodsSnNoVO> goodsSnNoList) {
		this.goodsSnNoList = goodsSnNoList;
	}

	public List<LogisticsInfoJsonVO> getLogisticsList() {
		return logisticsList;
	}


	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getGoodsSnNo() {
		return goodsSnNo;
	}

	public void setGoodsSnNo(String goodsSnNo) {
		this.goodsSnNo = goodsSnNo;
	}

	public String getLogisticsInfo() {
		return logisticsInfo;
	}

	public void setLogisticsInfo(String logisticsInfo) {
		this.logisticsInfo = logisticsInfo;
	}

	@Override
	public String toString() {
		return "BopsTradeLogisticsVO [tradeNo=" + tradeNo + ", addressId=" + addressId + ", userId=" + userId
				+ ", logisticsId=" + logisticsId + ", address=" + address + ", createTime=" + createTime
				+ ", goodsSnNo=" + goodsSnNo + ", logisticsInfo=" + logisticsInfo + ", goodsSnNoList=" + goodsSnNoList
				+ ", logisticsList=" + logisticsList + "]";
	}
	
}
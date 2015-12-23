package com.need.common.domain.vo.trade;

import java.io.Serializable;
import java.util.Arrays;

/**
 * <p></p>
 * @author Rylan 2015年12月2日 下午9:15:56
 * @ClassName ConfigTradeVO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年12月2日
 * @modify by reason:{方法名}:{原因}
 */
public class ConfigTradeVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 3009181848771815114L;
	
	
	private TradeCartVO[] tradeCartVOArray;
	private String userId;
	private String addressId;
	//private String couponNo;	
	//private String useBalance;
	private String warehouseType;
	
    private String shareUserToken;
	
    private String sut;//用户token
    
	private String goodsId;
	
	private int goodsCount;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public TradeCartVO[] getTradeCartVOArray() {
		return tradeCartVOArray;
	}
	public void setTradeCartVOArray(TradeCartVO... tradeCartVOArray) {
		this.tradeCartVOArray = tradeCartVOArray;
	}
	public String getShareUserToken() {
		return shareUserToken;
	}
	public void setShareUserToken(String shareUserToken) {
		this.shareUserToken = shareUserToken;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	
	
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	
	
	public String getSut() {
		return sut;
	}
	public void setSut(String sut) {
		this.sut = sut;
	}
	@Override
	public String toString() {
		return "ConfigTradeVO [tradeCartVOArray="
				+ Arrays.toString(tradeCartVOArray) + ", userId=" + userId
				+ ", addressId=" + addressId + ", warehouseType="
				+ warehouseType + ", shareUserToken=" + shareUserToken
				+ ", goodsId=" + goodsId + ", goodsCount=" + goodsCount + "]";
	}
	
	
	
	
		
}



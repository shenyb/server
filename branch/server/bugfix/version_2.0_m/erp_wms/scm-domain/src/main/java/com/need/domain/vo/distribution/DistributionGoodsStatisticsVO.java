package com.need.domain.vo.distribution;

import java.io.Serializable;
import java.util.Date;

public class DistributionGoodsStatisticsVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1736658836233105090L;
	private String id;
	private String goodsCode;
	private String goodsName;
	private String amount;
	private String commission;
	private String tradeCount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(String tradeCount) {
		this.tradeCount = tradeCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "DistributionEditStatisticsVO [id=" + id + ", goodsCode=" + goodsCode + ", goodsName=" + goodsName
				+ ", amount=" + amount + ", commission=" + commission + ", tradeCount=" + tradeCount + "]";
	}

	
	
}

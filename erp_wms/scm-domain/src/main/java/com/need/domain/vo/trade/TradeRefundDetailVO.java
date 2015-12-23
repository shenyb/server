package com.need.domain.vo.trade;

import java.util.Date;
import java.util.List;

public class TradeRefundDetailVO {
	private String tradeNo;

	private List<TradeRefundGoodsVO> goodsList;
	
	private int goodsTotalCount;

	private Float totalFee;

	private String nickName;

	private String mobile;

	private String tradeStatus;

	private String payChannel;

	private Date tradeTime;
	private Date payTime;
	private Date sentTime;
	
	

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public List<TradeRefundGoodsVO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<TradeRefundGoodsVO> goodsList) {
		this.goodsList = goodsList;
	}

	public Float getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Float totalFee) {
		this.totalFee = totalFee;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public static class TradeRefundGoodsVO {
		private String goodsName;
		private Integer goodsCount;
		private String goodsCode;
		
		

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

		public Integer getGoodsCount() {
			return goodsCount;
		}

		public void setGoodsCount(Integer goodsCount) {
			this.goodsCount = goodsCount;
		}

	}

	public int getGoodsTotalCount() {
		return goodsTotalCount;
	}

	public void setGoodsTotalCount(int goodsTotalCount) {
		this.goodsTotalCount = goodsTotalCount;
	}
}

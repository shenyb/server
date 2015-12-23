package com.need.domain.vo.trade;

import java.util.Date;
import java.util.List;

import com.need.domain.vo.trade.TradeBaseDetailVO.TradeBaseDetailGoodsVO;

public class TradeBaseSentVO {
	private String tradeNo;

	private List<TradeBaseDetailGoodsVO> goodsList;

	private Float totalFee;

	private String nickName;

	private String mobile;

	private String tradeStatus;

	private String payChannel;

	private String address;

	private Date tradeTime;
	
	private Date payTime;
	
	private Date sentTime;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public List<TradeBaseDetailGoodsVO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<TradeBaseDetailGoodsVO> goodsList) {
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

	
}

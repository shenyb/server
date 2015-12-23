package com.need.common.domain.vo.trade;

import com.need.common.domain.po.api.coupon.CouponUserPO;
import com.need.trade.enums.TradeStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TradeListVO implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -4502251080213353584L;
	
	private String tradeNo;
	private String userTradeNo;
	private Long tradeTime;
	private int totalPrice;
	private TradeStatus tradeStatus;
	
	private List<OrderBaseVO> orderList;
	
	
	private CouponUserPO coupon; //Addy liyongran 20150920 订单列表返回优惠券信息
	
	public String getUserTradeNo() {
		return userTradeNo;
	}

	public void setUserTradeNo(String userTradeNo) {
		this.userTradeNo = userTradeNo;
	}

	public TradeListVO() {
		/** TODO Auto-generated constructor stub */
		orderList=new ArrayList<OrderBaseVO>();
	}
	
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public Long getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Long tradeTime) {
		this.tradeTime = tradeTime;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public TradeStatus getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public List<OrderBaseVO> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<OrderBaseVO> orderList) {
		this.orderList = orderList;
	}
	
	

	public CouponUserPO getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponUserPO coupon) {
		this.coupon = coupon;
	}

	@Override
	public String toString() {
		return "TradeListVO [tradeNo=" + tradeNo + ", tradeTime=" + tradeTime + ", totalPrice=" + totalPrice
				+ ", tradeStatus=" + tradeStatus + ", orderList=" + orderList + "]";
	}
	
}

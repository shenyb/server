package com.need.common.domain.vo.trade;

import com.need.common.domain.enums.PayChannelEnum;

import java.io.Serializable;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年8月6日 上午11:31:55
 * @ClassName AliPayNotifyVO
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月6日
 * @modify by reason:{方法名}:{原因}
 */
public class PayNotifyVO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	// 订单号
	String tradeNo;

	// 支付宝交易号

	private String outTradeNo;

	// 交易状态
	private String tradeStatus;

	// 总交易价格
	private Integer totalFee;

	// 通知类型
	private String notifyType;

	// 买家支付宝账户
	private String buyerId;

	// 商品单价
	private String price;

	// 交易创建时间
	private String gmtCreate;

	// 交易付款时间
	String gmtPayment;
	//支付渠道
	PayChannelEnum payChannel;
	//存所有json信息
	private String memo;

	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public PayChannelEnum getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(PayChannelEnum payChannel) {
		this.payChannel = payChannel;
	}

	public String getGmtPayment() {
		return gmtPayment;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public void setGmtPayment(String gmtPayment) {
		this.gmtPayment = gmtPayment;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	@Override
	public String toString() {
		return "AliPayNotifyVO [tradeNo=" + tradeNo + ", outTradeNo=" + outTradeNo + ", tradeStatus=" + tradeStatus
				+ ", totalFee=" + totalFee + ", notifyType=" + notifyType + ", buyerId=" + buyerId + ", price=" + price
				+ ", gmtCreate=" + gmtCreate + ", gmtPayment=" + gmtPayment + "]";
	}

}
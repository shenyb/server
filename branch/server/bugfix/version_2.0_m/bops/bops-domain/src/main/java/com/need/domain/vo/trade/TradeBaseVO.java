package com.need.domain.vo.trade;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * <p></p>
 * @author shenyb 2015年8月15日 下午12:53:06
 * @ClassName TradeBaseVO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月15日
 * @modify by reason:{方法名}:{原因}
 */
public class TradeBaseVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 9148977098647181847L;

	private String tradeNo;
	
	private String userTradeNo;
	
	private Float totalFee;
	
	private String nickName;
	
	private String mobile;
	
	private String tradeStatus;
	
	private String payChannel;
	
	private Date tradeTime;//取下单时间create_time
	
	private String buyerId;
	
	private String receiver;//实际收货人姓名
	
	private String certificationCard;//收货人身份证号
	
	private String telephone;//收货人联系电话
	
	private String address;//收货人收货地址
	
	private double yunfei = 0;//运费
	
	private double youhuiquan;//优惠券
	
	private String warehouseType;//订单仓库
	
	private String retrieveStatus;
	
	private String trackingStatus;//订单流转信息
	
	private String trackingCode;
	
	
	public String getTrackingCode() {
		return trackingCode;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}

	public String getTrackingStatus() {
		return trackingStatus;
	}

	public void setTrackingStatus(String trackingStatus) {
		this.trackingStatus = trackingStatus;
	}

	public String getRetrieveStatus() {
		return retrieveStatus;
	}

	public void setRetrieveStatus(String retrieveStatus) {
		this.retrieveStatus = retrieveStatus;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
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

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getCertificationCard() {
		return certificationCard;
	}

	public void setCertificationCard(String certificationCard) {
		this.certificationCard = certificationCard;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getYunfei() {
		return yunfei;
	}

	public void setYunfei(double yunfei) {
		this.yunfei = yunfei;
	}

	public double getYouhuiquan() {
		return youhuiquan;
	}

	public void setYouhuiquan(double youhuiquan) {
		this.youhuiquan = youhuiquan;
	}

	public String getUserTradeNo() {
		return userTradeNo;
	}

	public void setUserTradeNo(String userTradeNo) {
		this.userTradeNo = userTradeNo;
	}

	@Override
	public String toString() {
		return "TradeBaseVO [tradeNo=" + tradeNo + ", userTradeNo=" + userTradeNo + ", totalFee=" + totalFee
				+ ", nickName=" + nickName + ", mobile=" + mobile + ", tradeStatus=" + tradeStatus + ", payChannel="
				+ payChannel + ", tradeTime=" + tradeTime + ", buyerId=" + buyerId + ", receiver=" + receiver
				+ ", certificationCard=" + certificationCard + ", telephone=" + telephone + ", address=" + address
				+ ", yunfei=" + yunfei + ", youhuiquan=" + youhuiquan + ", warehouseType=" + warehouseType
				+ ", retrieveStatus=" + retrieveStatus + ", trackingStatus=" + trackingStatus + ", trackingCode="
				+ trackingCode + "]";
	}

}

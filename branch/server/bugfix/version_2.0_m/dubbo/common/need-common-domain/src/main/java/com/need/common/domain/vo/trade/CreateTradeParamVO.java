package com.need.common.domain.vo.trade;

import java.util.Arrays;

public class CreateTradeParamVO extends CreateTradeBaseParamVO {
	private TradeCartVO[] records;
	private String couponNo;
	private String certificationChannel;
	
	private String useBalance;
	
	private String shareUserId;
	
	
	
	
	public TradeCartVO[] getRecords() {
		return records;
	}
	public void setRecords(TradeCartVO[] records) {
		this.records = records;
	}
	public String getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}
	public String getCertificationChannel() {
		return certificationChannel;
	}
	public void setCertificationChannel(String certificationChannel) {
		this.certificationChannel = certificationChannel;
	}
	public String getUseBalance() {
		return useBalance;
	}
	public void setUseBalance(String useBalance) {
		this.useBalance = useBalance;
	}
	public String getShareUserId() {
		return shareUserId;
	}
	public void setShareUserId(String shareUserId) {
		this.shareUserId = shareUserId;
	}
	@Override
	public String toString() {
		return "CreateTradeParamVO [records=" + Arrays.toString(records)
				+ ", couponNo=" + couponNo + ", certificationChannel="
				+ certificationChannel + ", useBalance=" + useBalance
				+ ", shareUserId=" + shareUserId + "]";
	}
	
	
	
	
	
}

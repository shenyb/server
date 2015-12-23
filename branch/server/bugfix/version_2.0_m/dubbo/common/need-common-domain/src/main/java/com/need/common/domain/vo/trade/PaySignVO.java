package com.need.common.domain.vo.trade;

import java.io.Serializable;
/**
 * <p>签名参数VO</p>
 * @author Rylan 2015年12月6日 上午11:25:27
 * @ClassName PaySignVO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年12月6日
 * @modify by reason:{方法名}:{原因}
 */
public class PaySignVO implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7126976523115477794L;

	private String userId;
	
	private String tradeNo;

	private String openId;
	
	private String returnUrl;
	
	
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	
	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	@Override
	public String toString() {
		return "PaySignVO [userId=" + userId + ", tradeNo=" + tradeNo + "]";
	}
	
	
	
	
}

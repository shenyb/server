package com.need.common.core.pub;

import org.springframework.stereotype.Component;

/**
 * <p>读取短信配置文件</p>
 * @author Rylan 2015年9月15日 上午12:42:01
 * @ClassName SMSProConfig
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月15日
 * @modify by reason:{方法名}:{原因}
 */
@Component
public class SMSProConfig {
		
	private  String registSMSContent;
	private  String generalSMSContent;
    private String returnCouponSMSContent;
    private String exchangeCouponSMSContent;
    
	
	public void setRegistSMSContent(String registSMSContent) {
		this.registSMSContent = registSMSContent;
	}

	public void setGeneralSMSContent(String generalSMSContent) {
		this.generalSMSContent = generalSMSContent;
	}

	public void setReturnCouponSMSContent(String returnCouponSMSContent) {
		this.returnCouponSMSContent = returnCouponSMSContent;
	}

	public void setExchangeCouponSMSContent(String exchangeCouponSMSContent) {
		this.exchangeCouponSMSContent = exchangeCouponSMSContent;
	}

	public String getRegistSMSContent() {
		return registSMSContent;
	}

	public String getGeneralSMSContent() {
		return generalSMSContent;
	}

	public String getReturnCouponSMSContent() {
		return returnCouponSMSContent;
	}

	public String getExchangeCouponSMSContent() {
		return exchangeCouponSMSContent;
	}

	@Override
	public String toString() {
		return "SMSProConfig [registSMSContent=" + registSMSContent
				+ ", generalSMSContent=" + generalSMSContent
				+ ", returnCouponSMSContent=" + returnCouponSMSContent
				+ ", exchangeCouponSMSContent=" + exchangeCouponSMSContent
				+ "]";
	}

}

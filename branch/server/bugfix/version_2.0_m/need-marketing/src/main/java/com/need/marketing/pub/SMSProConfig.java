package com.need.marketing.pub;

import org.springframework.beans.factory.annotation.Value;
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
		
	@Value("${registSMSContent}")
	private  String registSMSContent;
	@Value("${generalSMSContent}")
	private  String generalSMSContent;
	@Value("${returnCouponSMSContent}")
    private String returnCouponSMSContent;
	@Value("${exchangeCouponSMSContent}")
    private String exchangeCouponSMSContent;
	@Value("${receiveCouponSMSContent}")
	private String receiveCouponSMSContent;
	@Value("${cheapSuccessSMSContent}")
	private String cheapSuccessSMSContent;
	
	
    
	
	public String getCheapSuccessSMSContent() {
		return cheapSuccessSMSContent;
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
	public String getReceiveCouponSMSContent() {
		return receiveCouponSMSContent;
	}

	@Override
	public String toString() {
		return "SMSProConfig [registSMSContent=" + registSMSContent
				+ ", generalSMSContent=" + generalSMSContent
				+ ", returnCouponSMSContent=" + returnCouponSMSContent
				+ ", exchangeCouponSMSContent=" + exchangeCouponSMSContent
				+ ", receiveCouponSMSContent=" + receiveCouponSMSContent
				+ ", cheapSuccessSMSContent=" + cheapSuccessSMSContent + "]";
	}



	



	
	
	
	
	
}

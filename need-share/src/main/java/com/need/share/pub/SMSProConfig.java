package com.need.share.pub;

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
	@Value("${receiveCouponSMSContent}")
	private String receiveCouponSMSContent;
	public String getRegistSMSContent() {
		return registSMSContent;
	}
	public String getGeneralSMSContent() {
		return generalSMSContent;
	}
	public String getReceiveCouponSMSContent() {
		return receiveCouponSMSContent;
	}
	
	
	
	
	
	
}

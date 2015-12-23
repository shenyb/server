package com.need.marketing.pub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 * @author Rylan 2015年9月10日 上午11:30:46
 * @ClassName ConfigProperties
 * @Description 读取constants.properties属性文件配置信息
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */

@Component
public class ConstantsProConfig {

	@Value("${alipayServiceStatus}")
	private String alipayServiceStatus;//支付宝服务开关
	@Value("${wechatServiceStatus}")
	private String wechatServiceStatus;//微信服务开关
	@Value("${thirdShareServiceStatus}")
	private String thirdShareService;//第三方服务开关
	
	@Value("${certificationUrl}")
	private String certificationUrl;//身份认证地址
	
	@Value("${defaultProfilePicKey}")
	private String defaultProfilePicKey;
	
	@Value("${marketingHost}")
	private String marketingHost;
	
	
	
	
	public String getMarketingHost() {
		return marketingHost;
	}

	@Value("${picDomain}")
	private String picDomain;
	
	
	
	public String getPicDomain() {
		return picDomain;
	}

	public String getCertificationUrl() {
		return certificationUrl;
	}

	public String getAlipayServiceStatus() {
		return alipayServiceStatus;
	}

	public String getWechatServiceStatus() {
		return wechatServiceStatus;
	}

	public String getThirdShareService() {
		return thirdShareService;
	}
	

	public String getDefaultProfilePicKey() {
		return defaultProfilePicKey;
	}

	@Override
	public String toString() {
		return "ConstantsProConfig [alipayServiceStatus=" + alipayServiceStatus
				+ ", wechatServiceStatus=" + wechatServiceStatus
				+ ", thirdShareService=" + thirdShareService
				+ ", certificationUrl=" + certificationUrl
				+ ", defaultProfilePicKey=" + defaultProfilePicKey + "]";
	}	
	
}

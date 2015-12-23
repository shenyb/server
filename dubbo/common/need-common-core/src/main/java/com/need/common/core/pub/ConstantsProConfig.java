package com.need.common.core.pub;

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

	private String alipayServiceStatus;//支付宝服务开关
	private String wechatServiceStatus;//微信服务开关
	private String thirdShareService;//第三方服务开关
	
	private String certificationUrl;//身份认证地址
	
	private String defaultProfilePicKey;
	
	private String cheapListTopPicKey;
	
	private String cheapUrl;
	
	private String couponUrl;
	
	
	
	public void setAlipayServiceStatus(String alipayServiceStatus) {
		this.alipayServiceStatus = alipayServiceStatus;
	}

	public void setWechatServiceStatus(String wechatServiceStatus) {
		this.wechatServiceStatus = wechatServiceStatus;
	}

	public void setThirdShareService(String thirdShareService) {
		this.thirdShareService = thirdShareService;
	}

	public void setCertificationUrl(String certificationUrl) {
		this.certificationUrl = certificationUrl;
	}

	public void setDefaultProfilePicKey(String defaultProfilePicKey) {
		this.defaultProfilePicKey = defaultProfilePicKey;
	}

	public void setCheapListTopPicKey(String cheapListTopPicKey) {
		this.cheapListTopPicKey = cheapListTopPicKey;
	}

	public void setCheapUrl(String cheapUrl) {
		this.cheapUrl = cheapUrl;
	}

	public void setCouponUrl(String couponUrl) {
		this.couponUrl = couponUrl;
	}

	public String getCouponUrl() {
		return couponUrl;
	}

	public String getCheapUrl() {
		return cheapUrl;
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
	public String getCheapListTopPicKey() {
		return cheapListTopPicKey;
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

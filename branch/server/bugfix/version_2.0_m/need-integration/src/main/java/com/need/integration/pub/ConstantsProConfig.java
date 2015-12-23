package com.need.integration.pub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 * </p>
 * 
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

	@Value("${certificateAccount}")
	private String certificateAccount;
	@Value("${certificatePwd}")
	private String certificatePwd;
	@Value("${certificateUrl}")
	private String certificateUrl;
	@Value("${checkBalanceUrl}")
	private String checkBalanceUrl;

	@Value("${pushOrderUrl}")
	private String pushOrderUrl;
	@Value("${queryLogisticsUrl}")
	private String queryLogisticsUrl;
	@Value("${retrieveInfoUrl}")
	private String retrieveInfoUrl;
	@Value("${pic_domain}")
	private String pic_domain;
	@Value("${birdexAppKey}")
	private String birdexAppKey;
	@Value("${birdexSecretKey}")
	private String birdexSecretKey;
	/**
	 * 笨鸟接口地址
	 */
	@Value("${birdex_order_create}")
	private String birdex_order_create;
	public String getPushOrderUrl() {
		return pushOrderUrl;
	}
	public String getBirdex_order_create(){
		return birdex_order_create;
	}
	public String getPic_domain(){
		return pic_domain;
	}
	
	public String getQueryLogisticsUrl() {
		return queryLogisticsUrl;
	}

	public String getRetrieveInfoUrl() {
		return retrieveInfoUrl;
	}

	public String getCertificateAccount() {
		return certificateAccount;
	}

	public String getCertificatePwd() {
		return certificatePwd;
	}

	public String getCertificateUrl() {
		return certificateUrl;
	}

	public String getCheckBalanceUrl() {
		return checkBalanceUrl;
	}

	public String getBirdexAppKey() {
		return birdexAppKey;
	}

	public String getBirdexSecretKey() {
		return birdexSecretKey;
	}

	@Override
	public String toString() {
		return "ConstantsProConfig [certificateAccount=" + certificateAccount + ", certificatePwd=" + certificatePwd
				+ ", certificateUrl=" + certificateUrl + ", checkBalanceUrl=" + checkBalanceUrl + ", pushOrderUrl="
				+ pushOrderUrl + ", queryLogisticsUrl=" + queryLogisticsUrl + ", retrieveInfoUrl=" + retrieveInfoUrl
				+ ", pic_domain=" + pic_domain + ", birdexAppKey=" + birdexAppKey + ", birdexSecretKey="
				+ birdexSecretKey + "]";
	}

}

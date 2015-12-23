package com.need.gateway.pub;

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

@Component("gateway_ConstantsProConfig")
public class ConstantsProConfig {
	
	@Value("${jPush_appKey}")
	private String jPushAppkey;
	
	@Value("${jPush_mastersecret}")
	private String jPushMasterSecret;

	@Value("${qiniu_access_key}")
	private String qiNiuAccessKey;
	@Value("${qiniu_secret_key}")
	private String qiNiuSecretKey;
	@Value("${qiniu_bucket}")
	private String qiNiubucket;
	
	
	public String getjPushAppkey() {
		return jPushAppkey;
	}

	public String getjPushMasterSecret() {
		return jPushMasterSecret;
	}

	public String getQiNiuAccessKey() {
		return qiNiuAccessKey;
	}

	public String getQiNiuSecretKey() {
		return qiNiuSecretKey;
	}

	public String getQiNiubucket() {
		return qiNiubucket;
	}

	@Override
	public String toString() {
		return "ConstantsProConfig [jPushAppkey=" + jPushAppkey
				+ ", jPushMasterSecret=" + jPushMasterSecret
				+ ", qiNiuAccessKey=" + qiNiuAccessKey + ", qiNiuSecretKey="
				+ qiNiuSecretKey + ", qiNiubucket=" + qiNiubucket + "]";
	}
	
	
}

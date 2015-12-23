package com.need.gateway.pub;

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
@Component("gateway_SMSProConfig")
public class SMSProConfig {
	
	//创蓝账号设置
	@Value("${chuanglan_url}")
	private  String chuanLanUrl;
	@Value("${chuanglan_account}")
	private  String chuanLanAccount;
	@Value("${chuanglan_pswd}")
	private  String chuanLanPswd;
	
	//云信账号配置  
	@Value("${yunxin_url}")
    private  String yunXinUrl;
	@Value("${yunxin_userCode}")
	private  String yunXinUserCode;
	@Value("${yunixn_userPass}")
	private  String yunXinUserPass; 
	@Value("${yunxin_sign}")
	private String yunxin_sign;//短信前缀签名

	@Value("${smsOperatorCount}")
	private String smsOperatorCount;//短信供应商个数

	public String getChuanLanUrl() {
		return chuanLanUrl;
	}

	public String getChuanLanAccount() {
		return chuanLanAccount;
	}

	public String getChuanLanPswd() {
		return chuanLanPswd;
	}

	public String getYunXinUrl() {
		return yunXinUrl;
	}

	public String getYunXinUserCode() {
		return yunXinUserCode;
	}

	public String getYunXinUserPass() {
		return yunXinUserPass;
	}

	public String getYunxin_sign() {
		return yunxin_sign;
	}

	public String getSmsOperatorCount() {
		return smsOperatorCount;
	}
	
	
}

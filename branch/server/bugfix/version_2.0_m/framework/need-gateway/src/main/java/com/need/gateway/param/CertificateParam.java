package com.need.gateway.param;

import java.io.Serializable;

public class CertificateParam implements Serializable{
	
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6895266043373780744L;

	private String username; //用户名
	
	private String idCard;//身份证
	
	private  String certificateAccount;//第三方账户
	
	private  String certificatePwd;//密码
	
	private  String certificateUrl;//发送url地址

	public CertificateParam(String username, String idCard,
			String certificateAccount, String certificatePwd,
			String certificateUrl) {
		super();
		this.username = username;
		this.idCard = idCard;
		this.certificateAccount = certificateAccount;
		this.certificatePwd = certificatePwd;
		this.certificateUrl = certificateUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCertificateAccount() {
		return certificateAccount;
	}

	public void setCertificateAccount(String certificateAccount) {
		this.certificateAccount = certificateAccount;
	}

	public String getCertificatePwd() {
		return certificatePwd;
	}

	public void setCertificatePwd(String certificatePwd) {
		this.certificatePwd = certificatePwd;
	}

	public String getCertificateUrl() {
		return certificateUrl;
	}

	public void setCertificateUrl(String certificateUrl) {
		this.certificateUrl = certificateUrl;
	}

	@Override
	public String toString() {
		return "CertificateParam [username=" + username + ", idCard=" + idCard
				+ ", certificateAccount=" + certificateAccount
				+ ", certificatePwd=" + certificatePwd + ", certificateUrl="
				+ certificateUrl + "]";
	}
	
	
	
	
}

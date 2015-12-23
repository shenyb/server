package com.need.gateway.param;

public class SMSParam {


	private String mobiles;//手机号码，多个号码使用","分割.最多可以一次提交50000个手机号码
	private String content;//短信内容
	private boolean needstatus=false;//是否需要状态报告，需要true，不需要false
	
	private String  url;//调用地址
	
	private String account;//账户
	
	private String pwd;//密码
	
	private String smsSign;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMobiles() {
		return mobiles;
	}
	
	public String getSmsSign() {
		return smsSign;
	}
	public void setSmsSign(String smsSign) {
		this.smsSign = smsSign;
	}
	/**
	 * @author Rylan 2015年7月28日 下午3:16:37
	 * @Method: setMobiles 
	 * @Description: TODO 手机号
	 * @param mobiles 
	 * @throws
	 */
	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}
	public String getContent() {
		return content;
	}
	/**
	 * @author Rylan 2015年7月28日 下午3:18:08
	 * @Method: setContent 
	 * @Description: TODO 发送内容
	 * @param content 
	 * @throws
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @author Rylan 2015年7月28日 下午3:17:46
	 * @Method: isNeedstatus 
	 * @Description: TODO 是否需要状态报告
	 * @return 
	 * @throws
	 */
	public boolean isNeedstatus() {
		return needstatus;
	}
	public void setNeedstatus(boolean needstatus) {
		this.needstatus = needstatus;
	}
	@Override
	public String toString() {
		return "SMSParam [mobiles=" + mobiles + ", content=" + content + ", needstatus=" + needstatus + "]";
	}
	
	
	
	
	
}

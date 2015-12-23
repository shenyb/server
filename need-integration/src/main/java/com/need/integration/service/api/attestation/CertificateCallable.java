package com.need.integration.service.api.attestation;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.need.biz.utils.BizCodeUtil;
import com.need.http.HttpClientProxy;
import com.need.integration.common.enums.CertificateProvideEnum;
import com.need.integration.dao.jdbc.api.user.UserCertificationDAO;
import com.need.integration.dao.jdbc.api.user.po.UserCertificationPO;
import com.need.integration.pub.ConstantsProConfig;


@SuppressWarnings("hiding")
public class CertificateCallable<Integer> implements Callable<Integer>{

	private static final Logger logger = Logger.getLogger(CertificateCallable.class);
	
	private String username; //用户名
	
	private String idCard;//身份证
	
	private  String certificateAccount;//第三方账户
	
	private  String certificatePwd;//密码
	
	private  String certificateUrl;//发送url地址
	
	private String userId;//用户id
	
	private String certificationChannel;//渠道
	
	private UserCertificationDAO userCertificationDAO;
	
	
	public CertificateCallable(final String username,final String idCard,final String userId,final String certificationChannel , final  ConstantsProConfig constantsProConfig,UserCertificationDAO userCertificationDAO) {
		super();
		this.username = username;
		this.idCard = idCard;
		this.certificateAccount = constantsProConfig.getCertificateAccount();
		this.certificatePwd = constantsProConfig.getCertificatePwd();
		this.certificateUrl = constantsProConfig.getCertificateUrl();
		this.userId=userId;
		this.certificationChannel=certificationChannel;
		this.userCertificationDAO=userCertificationDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer call() throws Exception {
		/** TODO Auto-generated method stub*/
		//检查是否验证过
		UserCertificationPO userCertification= userCertificationDAO.getByIdCard(idCard);
		logger.info("check idCard  userCertification is : "+userCertification);
		if(userCertification!=null){
			if(userCertification.getRealName().equals(this.username)){
				return (Integer) java.lang.Integer.valueOf(0);
			}else{
				return (Integer) java.lang.Integer.valueOf(-1);
			}
			
		}
		 //美意身份验证
		 Map<String, String> params=new HashMap<String, String>();
		 params.put("corp", certificateAccount);
		 String pwd=certificatePwd;
		 try {
			params.put("password", URLEncoder.encode(pwd,"ISO-8859-1"));
		 } catch (UnsupportedEncodingException e) {
			/** TODO Auto-generated catch block */
			e.printStackTrace();
			return (Integer) java.lang.Integer.valueOf(-1);
		 } 
		 params.put("UserName", username);
		 params.put("IDCard", idCard);
		 
		 String result=HttpClientProxy.sendGetRequest(certificateUrl, params, "UTF-8");
		 logger.info("result :"+result);
		 //得到返回结果
		 int res=java.lang.Integer.valueOf(result.split("&")[0].split("=")[1]);
		 if(res==0){
			//校验成功插入记录
			userCertification=new UserCertificationPO();
			userCertification.setId(BizCodeUtil.generateUserId(userId));
			userCertification.setUserId(userId);
			userCertification.setCertificationChannel(certificationChannel);
			userCertification.setCertificationSource(CertificateProvideEnum.MEIYI.code);
			userCertification.setIdCard(idCard);
			userCertification.setRealName(username);
			userCertificationDAO.insert(userCertification); 
			return (Integer) java.lang.Integer.valueOf(0);
		 }else{
			 return (Integer) java.lang.Integer.valueOf(-1);
		 }
		 
	}
	
}

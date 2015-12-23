package com.need.gateway.sms.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcloud.msg.http.HttpSender;
import com.need.gateway.param.SMSParam;
import com.need.gateway.pub.SMSProConfig;
import com.need.gateway.sms.SMSService;
import com.need.http.HttpClientProxy;
import java.util.HashMap;
import java.util.Map;


@Service
public class SMSServiceImpl implements SMSService{

	private static final Logger logger = Logger.getLogger(SMSServiceImpl.class);

	@Autowired
	private SMSProConfig smsProConfig;
	
	
	
	@Override
	public int sendSMSByChuangLan(String content,String mobiles) throws Exception  {
		// TODO Auto-generated method stub
		String returnString = HttpSender.batchSend(smsProConfig.getChuanLanUrl(), smsProConfig.getChuanLanAccount(), smsProConfig.getChuanLanPswd(), mobiles, content, false, null, null);
		logger.debug("sendSMSByChuangLan result :"+returnString); 	
		return Integer.parseInt(returnString.split(",")[1]) ;
	}


	@Override
	public int sendSMSByYunXin(String content,String mobile) throws Exception {
		/** TODO Auto-generated method stub*/
		    //手机号码个数限制，只能提交一个 
		    if(mobile.split(",").length > 1){
		    	logger.error("sendSMSByYunXin must set 1  mobile.");
		    	return -1;
		    };
		    //配置参数 
		    Map<String, String> params=new HashMap<String, String>();
		    params.put("userCode",smsProConfig.getYunXinUserCode());
		    params.put("userPass", smsProConfig.getYunXinUserPass());
		    params.put("DesNo", mobile);
		    params.put("Msg",content +smsProConfig.getYunxin_sign());
		    params.put("Channel", "0");
		    
		    String str= HttpClientProxy.sendPostRequest(smsProConfig.getYunXinUrl()+"SendMsg", params, "UTF-8");    
            Document doc = DocumentHelper.parseText(str); // 将字符串转为XML
            if (doc == null ) return -1;
            Element rootElt = doc.getRootElement(); // 获取根节点
            if (rootElt == null ) return -1;
            logger.info("root node value is :" + rootElt.getText()); // 拿到根节点的名称
            if (rootElt.getText() == null || "".equals(rootElt.getText())) return -1;           
            if (Long.parseLong(rootElt.getText()) < 0 ) {
            	return -1;
            } 
            return 0;
		
	}

	@Override
	public int sendInternationSMSByYunXin(String content,String mobile) throws Exception {
		/** TODO Auto-generated method stub*/
		 //手机号码个数限制，只能提交一个 
	    if(mobile.split(",").length > 1){
	    	logger.error("sendSMSByYunXin must set 1  mobile.");
	    	return -1;
	    };
	    //配置参数 
	    Map<String, String> params=new HashMap<String, String>();
	    params.put("userCode",smsProConfig.getYunXinUserCode());
	    params.put("userPass", smsProConfig.getYunXinUserPass());
	    params.put("DesNo", mobile);
	    params.put("Msg",content +smsProConfig.getYunxin_sign());
	    params.put("Channel", "999");
	    
	    String str= HttpClientProxy.sendPostRequest(smsProConfig.getYunXinUrl()+"SendMsg", params, "UTF-8");    
        System.out.println(str);
	    Document doc = DocumentHelper.parseText(str); // 将字符串转为XML
        if (doc == null ) return -1;
        Element rootElt = doc.getRootElement(); // 获取根节点
        if (rootElt == null ) return -1;
        logger.info("root node value is :" + rootElt.getText()); // 拿到根节点的名称
        if (rootElt.getText() == null || "".equals(rootElt.getText())) return -1;           
        if (Long.parseLong(rootElt.getText()) < 0 ) {
        	return -1;
        } 
        
        return 0;
	}
		
	@Override
	public boolean checkChuangLanBalance() {
		/** TODO Auto-generated method stub*/
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("account",smsProConfig.getChuanLanAccount());
		params.put("pswd", smsProConfig.getChuanLanPswd());
		String returnString = HttpClientProxy.sendPostRequest(smsProConfig.getChuanLanUrl()+"QueryBalance", params, "UTF-8");	
		logger.info("checkChuangLanBalance result :"+returnString);
		String[] arrayString= returnString.split(",");
		String balanceStr=arrayString[arrayString.length-1];//获取余额
		if(Integer.parseInt(balanceStr) > 0){
			return true;
		}	
		
		return false;
	}


	@Override
	public boolean checkYunXinBalance() {
		/** TODO Auto-generated method stub*/
		Map<String, String> params = new HashMap<String, String>();
		params.put("userCode", smsProConfig.getYunXinUserCode());
		params.put("userPass", smsProConfig.getYunXinUserPass());
		
		String returnString = HttpClientProxy.sendPostRequest(smsProConfig.getYunXinUrl()+"GetBalance", params, "UTF-8");	
		logger.info("checkYunXinBalance result :"+returnString);	
		Document doc=null;
		try {
			doc = DocumentHelper.parseText(returnString);
		} catch (DocumentException e) {
			/** TODO Auto-generated catch block */
			logger.error(e.getMessage());
			
		} // 将字符串转为XML
        if (doc == null ) return false;
        Element rootElt = doc.getRootElement(); // 获取根节点
        if (rootElt == null ) return false;
        logger.info("root node value is :" + rootElt.getText()); // 拿到根节点的名称
        if (rootElt.getText() == null || "".equals(rootElt.getText())) return false;           
        if (Long.parseLong(rootElt.getText()) > 0 ) {
        	 return true;
        } 
		
	    return false;
		
	}
	
	public static void main(String[] args) {
		
		//SMSService sm  sService=new SMSServiceImpl();
		SMSParam p=new SMSParam();
		p.setAccount("Needapp");
		p.setPwd("Daotiankeji123");
		p.setUrl("http://222.73.117.158/msg/");		
		p.setSmsSign("【Need】");
		
		//smsService.checkChuangLanBalance(p);
		//System.out.println(smsService.checkChuangLanBalance(p));
		p.setAccount("DTKJGJ");
		p.setPwd("DTKJGJ2015");
		p.setUrl("http://h.1069106.com:1210/Services/MsgSend.asmx/");
		p.setMobiles("15822060068");
		p.setContent("您的手机注册码为：#（*分钟内有效）。受够了生活枯燥，来 Need 找到真正新欢所要。");
		try {
		//System.out.println(smsService.sendInternationSMSByYunXin(p));
		} catch (Exception e) {
			/** TODO Auto-generated catch block */
			e.printStackTrace();
		}
		
	}


	@Override
	public int distributeSMSService(String content, String mobile) {
		/** TODO Auto-generated method stub*/
		int result = -1;      
        if(StringUtils.isEmpty(mobile)|| StringUtils.isEmpty(content)){
        	logger.info("mobile or content must not be null.");
			return result;
        }
        logger.info("distributeSMSService in..mobile :"+mobile+" content :"+content);
        //短信分发
        String lastString= mobile.charAt(mobile.length()-1)+"";//取到手机号最后一位
        int  remain= Integer.parseInt(lastString)%Integer.parseInt(smsProConfig.getSmsOperatorCount());//余数
	    //  1/3发送创蓝    2/3 发送云信  
		logger.info("remain is :"+remain);
		boolean balanceEnough=false;
		try {
			switch (remain) {
			case 1:
				
				balanceEnough=this.checkChuangLanBalance();
				//余额充足 不充足则使用另一个短信通道，此时不用检查余额信息
				if(balanceEnough){
					logger.info("ChuangLan  balance enogh use sendSMSByChuangLan.");
					result=this.sendSMSByChuangLan(content,mobile);
				}else{
					//云信发送
					logger.info("ChuangLan balance not enogh use sendSMSByYunXin.");					
					result=this.sendSMSByYunXin(content,mobile);
				}
				
				break;
			default:			
				balanceEnough=this.checkYunXinBalance();
				//余额充足 不充足则使用另一个短信通道，此时不用检查余额信息
				if(balanceEnough){
					logger.info("YunXin balance  enough  use sendSMSByYunXin.");
					result=this.sendSMSByYunXin(content,mobile);
				}else{
					logger.info("YunXin balance  not enough  use sendSMSByChuangLans.");					
					result=this.sendSMSByChuangLan(content,mobile);
				}
				break;
			} 
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}		
		logger.info("result is :"+result);
		return result;
	}
	
	
	
}

package com.need.wap.web.controller.weixin;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.common.core.service.weixin.WeixinService;
import com.need.common.domain.pub.Message;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
import com.need.wap.constant.ControllerMappings;
import com.need.wap.constant.RedisKeyConstants;

@Controller
@RequestMapping(value = ControllerMappings.WECHAT)
public class CommenController {
	private static final Logger logger = Logger.getLogger(CommenController.class);
	
	@Autowired
	private WeixinService weixinService;
	/***
	 * 
	 * @author LXD 2015年11月20日 下午5:03:22
	 * @Method: getWeixinTiken 
	 * @Description: TODO 获取微信token
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="/assceToken")
	public Message getWeixinTiken() {
		Message success = Message.success();
		logger.info("getWeixinTiken.....in ");
	    String token= weixinService.getAccessToken();
	    String tokenTime= JedisSentinelClient.getString(RedisKeyConstant.WEIXIN_MP_ACCESS_TOKEN_TIME);
	    success.addData("token", token);
	    success.addData("tokenTime", tokenTime);
		logger.info("getWeixinTiken.....out token="+token);
		logger.info("getWeixinTiken.....out tokenTime="+tokenTime);
		return success;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="/jsapiTicket")
	public Message getWeixinJsTicket() {
		Message success = Message.success();
		logger.info("getWeixinJsTicket.....in ");
	    String token= weixinService.getAccessToken();
	    String jsapiTicket= weixinService.getJsTicket(token);
	    String jsapiTicketTime= JedisSentinelClient.getString(RedisKeyConstant.WEIXIN_MP_JSAPI_TICKET_TIME);
	    success.addData("jsapiTicket", jsapiTicket);
	    success.addData("jsapiTicketTime", jsapiTicketTime);
	    logger.info("getWeixinJsTicket.....out token="+token);
		logger.info("getWeixinJsTicket.....out jsapiTicket="+jsapiTicket);
		logger.info("getWeixinTiken.....out tokenTime="+jsapiTicketTime);
		return success;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="/jsapiAuthentication")
	public Message getJsapiAuthentication(@RequestParam(required = true)String url) {
		Message success = Message.success();
		logger.info("getJsapiAuthentication.....in ");
	    String token= weixinService.getAccessToken();
	    String jsapiTicket= weixinService.getJsTicket(token);
	    try {
			url=java.net.URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			/** TODO Auto-generated catch block */
			e.printStackTrace();
		}
	    Map<String, String> resultMap=weixinService.getJsSignMap(jsapiTicket, url);
	    success.addData("noncestr", resultMap.get("nonceStr"));
	    success.addData("timestamp", resultMap.get("timestamp"));
	    success.addData("jsapi_ticket", resultMap.get("jsapi_ticket"));
	    success.addData("url", url);
	    success.addData("signature", resultMap.get("signature"));
	    success.addData("appId", resultMap.get("appid"));
	    logger.info("getJsapiAuthentication.....out token="+token);
		logger.info("getJsapiAuthentication.....out jsapiTicket="+jsapiTicket);
		logger.info("getJsapiAuthentication.....out resultMap="+resultMap.toString());
		return success;
	}
	
	
	/**
	 * @author Rylan 2015年12月10日 上午10:57:58
	 * @Method: getWeChatOpenId 
	 * @Description: 根据code获取openid
	 * @param code
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="/jsapiGetOpenId/{code}")
	public Message getWeChatOpenId(@PathVariable(value="code") String code) {
		Message success = Message.success();
		logger.info("getWeChatOpenId.....in . code :"+code);
		success =weixinService.getWeiXinOpenId("wxa2769733f861611f", "a147eb89d460b2f08f6d938c12ad7925", code);
		return success;
	}
	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.common.core.service.weixin.impl;

import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.weixin.WeixinService;
import com.need.common.domain.pub.Message;
import com.need.http.HttpClientProxy;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
import com.need.utils.PropertiesUtil;
import com.need.utils.UUIDUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author 庆凯 2015-9-28 2015-9-28 14:11:09
 * @ClassName WeixinServiceImpl
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-28
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class WeixinServiceImpl implements WeixinService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(WeixinServiceImpl.class);
    
    private final String appid = PropertiesUtil.getProperty("/properties/wechat.properties", "wap_app_id");
    private final String secret = PropertiesUtil.getProperty("/properties/wechat.properties", "wap_app_secret");

	@Override
	public JSONObject getWeiXinUserInfo(String code) {
		 String getacc = "https://api.weixin.qq.com/sns/oauth2/access_token";
        try {
            String openid = "";
            String access_token = "";
          /***
           * 获取用户openID
           */
            Map<String, String> map =  new HashMap<String, String>();
            map.put("appid", appid);
            map.put("secret", secret);
            map.put("code", code);
            map.put("grant_type", "authorization_code");

            String accs = HttpClientProxy.sendGetRequest(getacc, map, "utf-8");
            net.sf.json.JSONObject json  = net.sf.json.JSONObject.fromObject(accs); 
            if(StringUtils.isBlank((String) json.get("openid"))){
            	LOGGER.info("getWeiXinUserInfo in WeixinServiceImpl openid is NULL");
                return null;
            }
            openid = (String) json.get("openid");
            /***
             * 获取Token
             */
            access_token = getAccessToken();
            /***
             * 获取用户信息
             */
            String userInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info";
            map.clear();
            map.put("access_token", access_token);
            map.put("openid", openid);
            map.put("lang", "zh_CN");
            String userInfo = HttpClientProxy.sendPostRequest(userInfoUrl, map, "utf-8");
            net.sf.json.JSONObject json3  = net.sf.json.JSONObject.fromObject(userInfo);
            json3.put("openid", openid);
            return json3;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LOGGER.error(e.toString());
            return null;
        }
	}
    /***
     * 
     * @author LXD 2015年11月10日 下午1:32:47
     * @Method: getAccessToken 
     * @Description: TODO 获取全局TOKEN
     * @param map
     * @return
     * @throws UnsupportedEncodingException 
     * @throws
     */
	public String getAccessToken() {
		/**
		 * 先从redis获取token，如果为空，则请求。
		 */
		String access_token = JedisSentinelClient.getString(RedisKeyConstant.WEIXIN_MP_ACCESS_TOKEN);
		if (StringUtils.isBlank(access_token)) {
			/***
			 * 获取全局access_token
			 */
			String token = "https://api.weixin.qq.com/cgi-bin/token";
			Map<String,String> map =new HashMap<String,String>();
			map.clear();
			map.put("grant_type", "client_credential");
			map.put("appid", appid);
			map.put("secret", secret);	 
			try {
				String	access_tokens = HttpClientProxy.sendGetRequest(token, map, "utf-8");
				net.sf.json.JSONObject json2 = net.sf.json.JSONObject.fromObject(access_tokens);
				if (StringUtils.isBlank((String) json2.get("access_token"))) {
					return null;
				}
				access_token = (String) json2.get("access_token");
				/**
				 * 请求成功，存入redis 设置生效时间为90分钟。
				 */
				if (StringUtils.isNotBlank(access_token)) {
                    JedisSentinelClient.setString(RedisKeyConstant.WEIXIN_MP_ACCESS_TOKEN, access_token, RedisKeyConstant.WEIXIN_MP_ACCESS_TOKEN_EXPIRE);// 放在缓存里，失效时间30分钟;
                    JedisSentinelClient.setString(RedisKeyConstant.WEIXIN_MP_ACCESS_TOKEN_TIME, Calendar.getInstance().getTime().getTime()+"", RedisKeyConstant.WEIXIN_MP_ACCESS_TOKEN_EXPIRE);// 放在缓存里，失效时间30分钟;
				}
				return access_token;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				LOGGER.error(e.toString());
				return null;
			}
			
		}
		return access_token;

	}
	/***
	 * 
	 * @author LXD 2015年11月10日 下午3:12:35
	 * @Method: getJsTicket 
	 * @Description: TODO 获取JsTicket
	 * @param token
	 * @return 
	 * @see com.need.marketing.service.weixin.WeixinService#getJsTicket(java.lang.String)	/***
	 * 
	 * @author LXD 2015年11月10日 下午3:12:35
	 * @Method: getJsTicket 
	 * @Description: TODO 获取JsTicket
	 * @param token
	 * @return 
	 * @see com.need.marketing.service.weixin.WeixinService#getJsTicket(java.lang.String)
	 */
	
    public  String getJsTicket(String token){
    	/***
    	 * 从redis获取
    	 */
    	String jsapiTicket = JedisSentinelClient.getString(RedisKeyConstant.JSAPI_TICKET);
    	if(StringUtils.isBlank(jsapiTicket)){
    	try {
           String jsticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
            Map<String,String> map =new HashMap<String,String>();
            map.put("access_token", token);
            map.put("type", "jsapi");
            String jsapiString= HttpClientProxy.sendGetRequest(jsticketUrl,map,"utf-8");
            net.sf.json.JSONObject jsapiJson = net.sf.json.JSONObject.fromObject(jsapiString);
            jsapiTicket=jsapiJson.getString("ticket");
            /**
             * 请求成功，存入redis 设置生效时间为90分钟。
             */
			if (StringUtils.isNotBlank(jsapiTicket)) {
				JedisSentinelClient.setString(RedisKeyConstant.JSAPI_TICKET, jsapiTicket, RedisKeyConstant.WEIXIN_MP_ACCESS_TOKEN_EXPIRE);// 放在缓存里，失效时间90分钟;
				JedisSentinelClient.setString(RedisKeyConstant.WEIXIN_MP_JSAPI_TICKET_TIME, Calendar.getInstance().getTime().getTime()+"", RedisKeyConstant.WEIXIN_MP_ACCESS_TOKEN_EXPIRE);// 放在缓存里，失效时间30分钟;
			}
           
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
            return null;
        }
        return jsapiTicket;
    	}
    	return jsapiTicket;
    }
    
    /***
     * 
     * @author LXD 2015年11月10日 下午3:16:07
     * @Method: getJsSignMap 
     * @Description: TODO 获取jsapi相关参数map 
     * @param jsapi_ticket
     * @param url
     * @return 
     * @see com.need.marketing.service.weixin.WeixinService#getJsSignMap(java.lang.String, java.lang.String)
     */
    public  Map<String, String> getJsSignMap(String jsapi_ticket, String url) {
    	String signature = "";
    	Map<String, String> map = new HashMap<String, String>();

        String nonce_str = UUIDUtils.getUUID();
        LOGGER.debug("nonce_str:"+nonce_str);
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        LOGGER.debug("timestamp:"+timestamp);
        String s = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(s.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
            LOGGER.debug("signature:"+signature);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        map.put("url", url);
        map.put("jsapi_ticket", jsapi_ticket);
        map.put("nonceStr", nonce_str);
        map.put("timestamp", timestamp);
        map.put("signature", signature);
        map.put("appid", appid);

        return map;
    }
    
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    
    /**
     * @author Rylan 2015年12月10日 上午10:17:26
     * @Method: getWeiXinUserInfo 
     * @Description: 获取微信用户信息公共方法
     * @param appId
     * @param secret
     * @param code
     * @return 
     * @throws
     */
    public JSONObject getWeiXinUserInfo(String appId,String secret,String code) {
		 String getacc = "https://api.weixin.qq.com/sns/oauth2/access_token";
       try {
           String openid = "";
           String access_token = "";
         /***
          * 获取用户openID
          */
           Map<String, String> map =  new HashMap<String, String>();
           map.put("appid", appId);
           map.put("secret", secret);
           map.put("code", code);
           map.put("grant_type", "authorization_code");

           String accs = HttpClientProxy.sendGetRequest(getacc, map, "utf-8");
           net.sf.json.JSONObject json  = net.sf.json.JSONObject.fromObject(accs); 
           if(StringUtils.isBlank((String) json.get("openid"))){
           	LOGGER.info("getWeiXinUserInfo in WeixinServiceImpl openid is NULL");
               return null;
           }
           openid = (String) json.get("openid");
           /***
            * 获取Token
            */
           access_token = getAccessToken();
           /***
            * 获取用户信息
            */
           String userInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info";
           map.clear();
           map.put("access_token", access_token);
           map.put("openid", openid);
           map.put("lang", "zh_CN");
           String userInfo = HttpClientProxy.sendPostRequest(userInfoUrl, map, "utf-8");
           net.sf.json.JSONObject json3  = net.sf.json.JSONObject.fromObject(userInfo);
           json3.put("openid", openid);
           return json3;
       } catch (UnsupportedEncodingException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
           LOGGER.error(e.toString());
           return null;
       }
	}
    
    /**
     * @author Rylan 2015年12月10日 上午10:19:12
     * @Method: getWeiXinOpenId 
     * @Description: 获取用户openid公共方法
     * @param appId
     * @param secret
     * @param code
     * @return 
     * @throws
     */
    public Message getWeiXinOpenId(String appId,String secret,String code) {
    	  Message message =Message.success();  
    	 String getacc = "https://api.weixin.qq.com/sns/oauth2/access_token";
         try {
          String openid = "";
          String access_token = "";
         /***
          * 获取用户openID
          */
          Map<String, String> map =  new HashMap<String, String>();
          map.put("appid", appId);
          map.put("secret", secret);
          map.put("code", code);
          map.put("grant_type", "authorization_code");

          String accs = HttpClientProxy.sendGetRequest(getacc, map, "utf-8");
          net.sf.json.JSONObject json  = net.sf.json.JSONObject.fromObject(accs); 
          if(StringUtils.isBlank((String) json.get("openid"))){
          	LOGGER.info("getWeiXinUserInfo in WeixinServiceImpl openid is NULL");
              return null;
          }
          openid = (String) json.get("openid");
          message.addData("openId", openid);
          return message;
      } catch (UnsupportedEncodingException e) {
          // TODO Auto-generated catch block
          LOGGER.error(e.toString());
          return Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);
      }
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.share.service.weixin.impl;


import com.need.http.HttpClientProxy;
import com.need.share.service.weixin.WeixinService;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

	@Override
	public JSONObject getWeiXinUserInfo(String code) {
		// TODO Auto-generated method stub
		 String getacc = "https://api.weixin.qq.com/sns/oauth2/access_token";
        try {
            String openid = "";
            String access_token = "";
            //获取用户openid
            Map<String, String> map =  new HashMap<String, String>();
            map.clear();
            map.put("appid", "wxa2769733f861611f");
            map.put("secret", "a147eb89d460b2f08f6d938c12ad7925");
            map.put("code", code);
            map.put("grant_type", "authorization_code");

            String accs = HttpClientProxy.sendGetRequest(getacc, map, "utf-8");
            net.sf.json.JSONObject json  = net.sf.json.JSONObject.fromObject(accs); 
            if(StringUtils.isBlank((String) json.get("openid"))){
                return null;
            }
            openid = (String) json.get("openid");
            //获取全局access_token
            String token = "https://api.weixin.qq.com/cgi-bin/token";
            map.clear();
            map.put("grant_type", "client_credential");
            map.put("appid", "wxa2769733f861611f");
            map.put("secret", "a147eb89d460b2f08f6d938c12ad7925");

            String access_tokens = HttpClientProxy.sendGetRequest(token, map, "utf-8");
            net.sf.json.JSONObject json2  = net.sf.json.JSONObject.fromObject(access_tokens); 
            if(StringUtils.isBlank((String) json2.get("access_token"))){
                return null;
            }
            access_token=(String) json2.get("access_token");

            //获取用户信息
            String userInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info";
            map.clear();
            map.put("access_token", access_token);
            map.put("openid", openid);
            map.put("lang", "zh_CN");

            String userInfo = HttpClientProxy.sendPostRequest(userInfoUrl, map, "utf-8");
            net.sf.json.JSONObject json3  = net.sf.json.JSONObject.fromObject(userInfo);
            return json3;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
	}

}

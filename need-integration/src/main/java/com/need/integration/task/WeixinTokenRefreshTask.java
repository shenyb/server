/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.integration.task;

import com.need.http.HttpClientProxy;
import com.need.integration.constant.RedisKeyConstants;
import com.need.integration.util.StringUtil;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.JedisSentinelClient;
import com.need.utils.PropertiesUtil;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author 庆凯
 */
@Service
public class WeixinTokenRefreshTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeixinTokenRefreshTask.class);
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    @Scheduled(cron = "${weixinRefreshTime}")
//    @Scheduled(cron = "0 * * * * ?")
    private void refreshWeixinToken() {
        /**
         * *
         * 获取全局access_token
         */

        String appid = PropertiesUtil.getProperty("/properties/wechat.properties", "app_id");
        String secret = PropertiesUtil.getProperty("/properties/wechat.properties", "app_secret");
        Map<String, String> map = new HashMap<String, String>();
        map.put("grant_type", "client_credential");
        map.put("appid", appid);
        map.put("secret", secret);
        LOGGER.info("get weixin access token at {} which params is {}", Calendar.getInstance().getTime(), map);
        try {
            String access_tokens = HttpClientProxy.sendGetRequest(ACCESS_TOKEN_URL, map, "utf-8");
            JSONObject json2 = JSONObject.fromObject(access_tokens);
            String access_token = json2.getString("access_token");
            /**
             * 请求成功，存入redis 设置生效时间为4000秒。
             */
            if (StringUtil.isBlank(access_token)) {
                LOGGER.error("access_token not get which appid is {} and secret is {}", appid, secret);
            }
            JedisSentinelClient.setString(RedisKeyConstants.WEIXIN_MP_ACCESS_TOKEN, access_token, RedisKeyConstants.WEIXIN_MP_ACCESS_TOKEN_EXPIRE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
        }
    }
}

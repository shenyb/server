/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.common.core.service.weixin;

import com.need.common.domain.pub.Message;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * 
 * @author 庆凯 2015-9-28 2015-9-28 14:10:25
 * @ClassName WeixinService
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-28
 * @modify by reason:{方法名}:{原因}
 */
public interface WeixinService {
   
    //获取微信用户信息并插入数据库
    public JSONObject getWeiXinUserInfo(String code);
    //获取微信Token
    public String getAccessToken();
    //获取jsapi
    public  String getJsTicket(String token);
    
    //获取jsapi相关参数map 
    public  Map<String, String> getJsSignMap(String jsapi_ticket, String url);
    
    
    /**
     * @author Rylan 2015年12月10日 上午10:29:02
     * @Method: getWeiXinUserInfo 
     * @Description: 获取微信用户信息公共方法
     * @param appId
     * @param secret
     * @param code
     * @return 
     * @throws
     */
    public JSONObject getWeiXinUserInfo(String appId,String secret,String code);
    
    /**
     * @author Rylan 2015年12月10日 上午10:26:11
     * @Method: getWeiXinOpenId 
     * @Description: 获取用户openid公共方法
     * @param appId
     * @param secret
     * @param code
     * @return 
     * @throws
     */
    public Message getWeiXinOpenId(String appId,String secret,String code);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.share.service.weixin;

import net.sf.json.JSONObject;

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
}

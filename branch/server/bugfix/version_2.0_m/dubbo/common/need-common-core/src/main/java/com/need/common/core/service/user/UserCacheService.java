/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.common.core.service.user;

import com.need.common.domain.po.api.user.UserBasePO;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * 过期时间较短，减轻数据库查询压力，ApplicationConstant.USER_EXPIRES
 * @author YAN
 */
public interface UserCacheService {
    
    public void add(UserBasePO user);
    
    public Map<String, String> getById(String userId);

    public String getIdByPhone(String phone);

    public Map<String, String> getByPhone(String phone);
    
    public void removeById(String userId);
    
    public void updateById(String userId, String property, String value);
    
    public boolean existPhone(String phone);
    
    public JSONObject getSimpleJsonById(String userId);
    
    //redis缓存初始化
    public void init();
}

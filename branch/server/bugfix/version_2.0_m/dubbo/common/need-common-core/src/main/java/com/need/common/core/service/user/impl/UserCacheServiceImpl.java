/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.common.core.service.user.impl;

import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.user.UserBaseDAO;
import com.need.common.core.service.user.UserCacheService;
import com.need.common.core.utils.BeanUtil;
import com.need.common.core.utils.MapConverter;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
import com.need.utils.StringUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 * @author YAN
 */
@Service
public class UserCacheServiceImpl implements UserCacheService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCacheServiceImpl.class);
    
    @Autowired
    private UserBaseDAO userBaseDAO;

    @Override
    public void add(UserBasePO user) {
        String userId = user.getUserId();
        String key = RedisKeyConstant.USER_OF_ID.concat(userId);
        JedisSentinelClient.hmset(key, MapConverter.convert(BeanUtil.toMap(user)));
        JedisSentinelClient.expire(key, Constants.USER_EXPIRES);
        JedisSentinelClient.hset(RedisKeyConstant.MOBILE_OF_REGISTED, user.getMobile(), userId);
    }

    @Override
    public void removeById(String userId) {
        JedisSentinelClient.del(RedisKeyConstant.USER_OF_ID.concat(userId));
    }

    @Override
    public Map<String, String> getByPhone(String phone) {
        String userId = getIdByPhone(phone);
        if(userId != null) {
            return getById(userId);
        }
        return null;
    }

    @Override
    public String getIdByPhone(String phone) {
        String userId = JedisSentinelClient.hget(RedisKeyConstant.MOBILE_OF_REGISTED, phone);
        String result = null;
        if(!StringUtil.isBlank(userId)) {
            result = userId;
        }
        return result;
    }

    @Override
    public JSONObject getSimpleJsonById(String userId) {
        Map<String, String> userMap = getById(userId);
        if(userMap == null || userMap.isEmpty()) {
            return null;
        }
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public Map<String, String> getById(String userId) {
        if(StringUtil.isBlank(userId)) {
            return null;
        }
        String key = RedisKeyConstant.USER_OF_ID.concat("" + userId);
        Map<String, String> cache = JedisSentinelClient.hgetAll(key);
        if(cache == null || cache.isEmpty()) {
            UserBasePO user = userBaseDAO.getUserInfo(userId);
            if(user == null) {
                LOGGER.warn("user not exist which userId is {}" + userId);
                return null;
            }
            add(user);
            return MapConverter.convert(BeanUtil.toMap(user));
        }
        JedisSentinelClient.expire(key, Constants.USER_EXPIRES);
        return cache;
    }

    @Override
    public void updateById(String userId, String property, String value) {
        String key = RedisKeyConstant.USER_OF_ID.concat(userId);
        if(property.equals("mobile")) {
            Map<String, String> userMap = JedisSentinelClient.hgetAll(key);
            String oldMobile = userMap.get("mobile");
            JedisSentinelClient.hsetIfKeyExist(key, property, value);
            JedisSentinelClient.hdel(RedisKeyConstant.MOBILE_OF_REGISTED, oldMobile);
            JedisSentinelClient.hset(RedisKeyConstant.MOBILE_OF_REGISTED, value, userId);
        } else {
            JedisSentinelClient.hsetIfKeyExist(key, property, value);
        }
    }

    @Override
    public boolean existPhone(String phone) {
        return JedisSentinelClient.hexists(RedisKeyConstant.MOBILE_OF_REGISTED, phone);
    }

    @Override
    public void init() {
        long time = System.currentTimeMillis();
        LOGGER.info("-+-+-+-+-+-+-+-+-+-+-+-+-+-+user redis cache init start-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        JedisSentinelClient.del(RedisKeyConstant.MOBILE_OF_REGISTED);
        for(Map<String, Object> userMap : userBaseDAO.queryAllMobiles()) {
            String userId = userMap.get("user_id").toString();
            JedisSentinelClient.hset(RedisKeyConstant.MOBILE_OF_REGISTED, userMap.get("mobile").toString(), userId);
            if(userMap.get("user_sut")!=null){
                JedisSentinelClient.setString(RedisKeyConstant.REPLACE_USER_ID.concat(userMap.get("user_sut").toString()), userId);
            }
        }
        long cost = System.currentTimeMillis() - time;
        LOGGER.info("-+-+-+-+-+-+-+-+-+-+-+-+-+-+user redis cache init end cost : {}-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+", cost);
        if(cost > 120000) {
            LOGGER.error("-+-+-+-+-+-+-+-+-+-+-+-+-+-+user redis cache init cost too much-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        }
    }
    
}

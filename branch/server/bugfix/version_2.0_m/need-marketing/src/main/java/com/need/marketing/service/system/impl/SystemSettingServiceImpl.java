/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.marketing.service.system.impl;

import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
import com.need.marketing.dao.jdbc.api.system.SystemSettingDAO;
import com.need.marketing.dao.jdbc.api.system.po.SystemSettingPO;
import com.need.marketing.service.system.SystemSettingService;
import com.need.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YAN
 */
@Service
public class SystemSettingServiceImpl implements SystemSettingService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemSettingServiceImpl.class);
    
    @Autowired
    private SystemSettingDAO systemSettingDAO;

    @Override
    public String getSystemSettingByName(String name) {
        String value = JedisSentinelClient.hget(RedisKeyConstant.SYSTEM_SETTINGS, name);
        if(StringUtil.isBlank(value)) {
            SystemSettingPO systemSettingPO = systemSettingDAO.selectByPrimaryKey(name);
            if(systemSettingPO == null) {
                LOGGER.error("systemSetting not found which name is {}", name);
                return null;
            }
            value = systemSettingPO.getSystemSettingValue();
            JedisSentinelClient.hset(RedisKeyConstant.SYSTEM_SETTINGS, name, value);
        }
        return value;
    }
    
}

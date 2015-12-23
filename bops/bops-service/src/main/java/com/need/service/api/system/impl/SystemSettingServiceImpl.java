package com.need.service.api.system.impl;

import com.need.dao.api.system.SystemSettingDAO;
import com.need.domain.po.api.system.SystemSettingPO;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
import com.need.service.api.system.SystemSettingService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author YAN 2015-12-12 15:56:35
 * @ClassName SystemSettingServiceImpl
 * @Description TODO
 * @version V2.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: YAN 2015-12-12
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class SystemSettingServiceImpl implements SystemSettingService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemSettingServiceImpl.class);
    
    @Autowired
    private SystemSettingDAO systemSettingDAO;

    @Override
    public void update(String name, String value) {
        SystemSettingPO systemSetting = systemSettingDAO.selectByPrimaryKey(name);
        Date time = Calendar.getInstance().getTime();
        if(systemSetting == null) {
            systemSetting = new SystemSettingPO();
            systemSetting.setCreateTime(time);
            systemSetting.setSystemSettingName(name);
        }
        systemSetting.setSystemSettingValue(value);
        systemSetting.setUpdateTime(time);
        systemSettingDAO.updateByPrimaryKey(systemSetting);
        JedisSentinelClient.hset(RedisKeyConstant.SYSTEM_SETTINGS, name, value);
    }

    @Override
    public void refresh() {
        long time = System.currentTimeMillis();
        LOGGER.info("-+-+-+-+-+-+-+-+-+-+-+-+-+-+systemSetting redis cache init start-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        JedisSentinelClient.del(RedisKeyConstant.SYSTEM_SETTINGS);
        for(SystemSettingPO systemSettingPO : systemSettingDAO.queryAll()) {
            JedisSentinelClient.hset(RedisKeyConstant.SYSTEM_SETTINGS, systemSettingPO.getSystemSettingName(), systemSettingPO.getSystemSettingValue());
        }
        long cost = System.currentTimeMillis() - time;
        LOGGER.info("-+-+-+-+-+-+-+-+-+-+-+-+-+-+systemSetting redis cache init end cost : {}-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+", cost);
        if(cost > 120000) {
            LOGGER.error("-+-+-+-+-+-+-+-+-+-+-+-+-+-+systemSetting redis cache init cost too much-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        }
    }

    @Override
    public List<SystemSettingPO> queryAll() {
        return systemSettingDAO.queryAll();
    }

}
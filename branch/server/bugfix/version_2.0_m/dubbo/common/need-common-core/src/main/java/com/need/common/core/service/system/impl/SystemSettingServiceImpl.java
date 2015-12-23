/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.system.impl;

import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.system.SystemSettingDAO;
import com.need.common.core.service.system.SystemSettingService;
import com.need.common.domain.enums.SystemSettingEnum;
import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.po.api.system.SystemSettingPO;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
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

    @Override
    public int getTransportLimit(WarehouseTypeEnum warehouseType) {
        int result = 0;
        switch(warehouseType) {
            case SELF_WAREHOUSE:
                String selfLimit = getSystemSettingByName(SystemSettingEnum.TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE_LIMIT.code);
                if(!StringUtil.isBlank(selfLimit)) {
                    result = Integer.parseInt(selfLimit);
                } else {
                    result = Constants.TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE_LIMIT;
                }
                break;
            case TAX_WAREHOUSE:
                String taxLimit = getSystemSettingByName(SystemSettingEnum.TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE_LIMIT.code);
                if(!StringUtil.isBlank(taxLimit)) {
                    result = Integer.parseInt(taxLimit);
                } else {
                    result = Constants.TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE_LIMIT;
                }
                break;
            case OVERSEA_WAREHOUSE:
                String overseaLimit = getSystemSettingByName(SystemSettingEnum.TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE_LIMIT.code);
                if(!StringUtil.isBlank(overseaLimit)) {
                    result = Integer.parseInt(overseaLimit);
                } else {
                    result = Constants.TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE_LIMIT;
                }
                break;
        }
        return result;
    }

    @Override
    public int getTransportFee(WarehouseTypeEnum warehouseType, int sumPrice) {
        int result = 0;
        if(getTransportLimit(warehouseType) <= sumPrice) {
            return result;
        }
        switch(warehouseType) {
            case SELF_WAREHOUSE:
                String selfFee = getSystemSettingByName(SystemSettingEnum.TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE.code);
                if(!StringUtil.isBlank(selfFee)) {
                    result = Integer.parseInt(selfFee);
                } else {
                    result = Constants.TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE;
                }
                break;
            case TAX_WAREHOUSE:
                String taxFee = getSystemSettingByName(SystemSettingEnum.TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE.code);
                if(!StringUtil.isBlank(taxFee)) {
                    result = Integer.parseInt(taxFee);
                } else {
                    result = Constants.TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE;
                }
                break;
            case OVERSEA_WAREHOUSE:
                String overseaFee = getSystemSettingByName(SystemSettingEnum.TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE.code);
                if(!StringUtil.isBlank(overseaFee)) {
                    result = Integer.parseInt(overseaFee);
                } else {
                    result = Constants.TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE;
                }
                break;
        }
        return result;
    }

    @Override
    public void init() {
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
    
}

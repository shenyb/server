package com.need.common.core.dao.jdbc.device;

import com.need.common.domain.po.api.device.DeviceVersionPO;


public interface DeviceVersionDAO {
    /**
     * 
     * @author peiboli 2015年7月26日 上午11:52:18
     * @Method: getVersion 
     * @Description: 获取最新版本的版本号和下载地址
     * @param osVersion
     * @return 
     * @throws
     * mapper_api
     */
    DeviceVersionPO getVersion(String deviceChannel);
}
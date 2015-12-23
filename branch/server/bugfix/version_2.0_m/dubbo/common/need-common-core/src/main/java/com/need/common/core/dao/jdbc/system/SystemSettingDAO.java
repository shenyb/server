package com.need.common.core.dao.jdbc.system;

import com.need.common.domain.po.api.system.SystemSettingPO;

import java.util.List;

public interface SystemSettingDAO {
    int deleteByPrimaryKey(String systemSettingName);

    int insert(SystemSettingPO record);

    int insertSelective(SystemSettingPO record);

    SystemSettingPO selectByPrimaryKey(String systemSettingName);

    int updateByPrimaryKeySelective(SystemSettingPO record);

    int updateByPrimaryKey(SystemSettingPO record);
    
    List<SystemSettingPO> queryAll();
}
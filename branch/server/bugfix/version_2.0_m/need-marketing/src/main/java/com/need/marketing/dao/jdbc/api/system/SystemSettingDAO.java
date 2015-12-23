package com.need.marketing.dao.jdbc.api.system;

import com.need.marketing.dao.jdbc.api.system.po.SystemSettingPO;
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
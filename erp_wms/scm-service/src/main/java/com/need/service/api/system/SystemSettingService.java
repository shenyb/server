package com.need.service.api.system;

import java.util.List;

import com.need.domain.po.api.system.SystemSettingPO;

/**
 * 
 * @author YAN 2015-12-12 15:56:18
 * @ClassName SystemSettingService
 * @Description TODO
 * @version V2.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: YAN 2015-12-12
 * @modify by reason:{方法名}:{原因}
 */
public interface SystemSettingService {
    
    void update(String name, String value);
    
    void refresh();
    
    List<SystemSettingPO> queryAll();

}
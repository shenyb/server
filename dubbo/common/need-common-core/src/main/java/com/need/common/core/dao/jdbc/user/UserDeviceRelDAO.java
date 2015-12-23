package com.need.common.core.dao.jdbc.user;

import com.need.common.domain.po.api.user.UserDeviceRel;

/**
 * <p></p>
 * @author Rylan 2015年10月29日 上午10:59:57
 * @ClassName UserDeviceRelDAO
 * @Description 用户设备关联
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年10月29日
 * @modify by reason:{方法名}:{原因}
 */
public interface UserDeviceRelDAO {
	
    int deleteById(Long relId);

    int insert(UserDeviceRel record);

    UserDeviceRel selectById(Long relId);
    
    UserDeviceRel selectByUserId(String userId);

    int updateById(UserDeviceRel record);
    
    int updateByUserId(UserDeviceRel record);
    
    int updateDeviceByUserId(UserDeviceRel record);
}
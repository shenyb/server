package com.need.common.core.dao.jdbc.device;

import com.need.common.domain.po.api.device.DeviceBasePO;

public interface DeviceBaseDAO {

	/**
	 * 
	 * @author xiehao 2015年7月27日 上午9:29:28
	 * @Method: insert
	 * @Description: TODO 客户端激活设备时插入一条新的数据
	 * @param deviceBase
	 * @return
	 * @throws mapper_api
	 */
	int insertNewDevice(DeviceBasePO device);

	DeviceBasePO getByDeviceId(String deviceId);

	/**
	 * @author Rylan 2015年8月9日 下午6:29:22 @Method: selectByMac @Description:
	 * TODO @param mac @return @throws
	 */
	DeviceBasePO selectByMac(String mac);

}
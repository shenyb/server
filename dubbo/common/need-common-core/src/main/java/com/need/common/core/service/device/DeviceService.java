package com.need.common.core.service.device;

import com.need.common.domain.po.api.device.DeviceBasePO;
import com.need.common.domain.po.api.device.DeviceVersionPO;

/**
 * 
 * <p>s
 * </p>
 * @author peiboli 2015年7月26日 下午3:02:26
 * @ClassName DeviceService
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年7月26日
 * @modify by reason:{方法名}:{原因}
 */
public interface DeviceService {
	
	/**
	 * 
	 * @author peiboli 2015年7月26日 下午3:02:51 @Method: getVersion @Description: 获取最新的版本号及下载地址 @param osVersion @return @throws
	 */
	DeviceVersionPO getVersion(String deviceChannel);
	
	/**
	 * 
	 * @author xiehao 2015年7月27日 上午11:07:47 @Method: registerDevice @Description: TODO 注册新设备时插入新数据 @param device @return 设备ID @throws
	 */
	String registerDevice(DeviceBasePO device);
	
	/**
	 * @author shenyb 2015年7月31日 上午11:27:08 @Method: getByDeviceId @Description: TODO @param deviceId @return @throws
	 */
	DeviceBasePO getByDeviceId(String device);
	
	
	/**
	 * @author Rylan 2015年8月9日 下午6:31:11
	 * @Method: getDeviceBaseByMac 
	 * @Description: TODO
	 * @param mac
	 * @return 
	 * @throws
	 */
	DeviceBasePO getDeviceBaseByMac(String mac);
}

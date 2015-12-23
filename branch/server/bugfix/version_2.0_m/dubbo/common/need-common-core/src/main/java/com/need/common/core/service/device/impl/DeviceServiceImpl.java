package com.need.common.core.service.device.impl;

import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.dao.jdbc.device.DeviceBaseDAO;
import com.need.common.core.dao.jdbc.device.DeviceVersionDAO;
import com.need.common.core.service.device.DeviceService;
import com.need.common.domain.po.api.device.DeviceBasePO;
import com.need.common.domain.po.api.device.DeviceVersionPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceVersionDAO deviceVersiondao;
	
	@Autowired
	private DeviceBaseDAO deviceBaseDAO;
    
	/**
	 * 
	 * @author peiboli 2015年7月26日 下午3:07:42
	 * @Method: getVersion 
	 * @Description: 获取最新的版本和下载地址
	 * @param osVersion
	 * @return 
	 * @see com.need.api.service.device.DeviceService#getVersion(java.lang.String)
	 */
	@Override
	public DeviceVersionPO getVersion(String osVersion) {
		// TODO Auto-generated method stub
		DeviceVersionPO dv = deviceVersiondao.getVersion(osVersion);
		return dv;
	}

	@Override
	@Transactional
	public String registerDevice(DeviceBasePO device) {
		// TODO Auto-generated method stub	
		DeviceBasePO deviceBase =deviceBaseDAO.selectByMac(device.getMac());//检查该设备是否注册过
		if(deviceBase == null){
			device.setDeviceId(BizCodeUtil.generateDeviceId(device.getMac()));	//调用工具类生成deviceId
			int isSuccess = deviceBaseDAO.insertNewDevice(device);
			if(isSuccess!=0){
				return device.getDeviceId();
			}
			else{
				return Boolean.FALSE.toString().toUpperCase();
			}
		}
		return deviceBase.getDeviceId();
	}
	
	@Override
	public DeviceBasePO getByDeviceId(String device) {
		return deviceBaseDAO.getByDeviceId(device);
	}

	@Override
	public DeviceBasePO getDeviceBaseByMac(String mac) {
		// TODO Auto-generated method stub
		return deviceBaseDAO.selectByMac(mac);
	}

	

}

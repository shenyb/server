package com.need.api.web.controller.device;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.device.DeviceService;
import com.need.common.domain.po.api.device.DeviceBasePO;
import com.need.common.domain.pub.Message;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p></p>
 * @author xiehao 2015年7月27日 下午1:41:09
 * @ClassName DeviceRegisterController
 * @Description 测试分支 设备注册Controller
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年7月27日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.DEVICE_REGISTER)
public class DeviceRegisterController {

	private static final Logger logger=Logger.getLogger(DeviceRegisterController.class);
	
	@Autowired
	private DeviceService deviceService;
	
	@ResponseBody
	@RequestMapping(params="apiVersion=1.0",method= RequestMethod.POST)
	public Message deviceRegister(DeviceBasePO deviceBase){
		logger.info("deviceRegister in.. deviceBase:" + deviceBase);
		String deviceId = deviceService.registerDevice(deviceBase);
		if(!Boolean.FALSE.toString().toUpperCase().equals(deviceId)){
			Message message = Message.success();
			message.addData("deviceId", deviceId);
			return message;
		}
		else{
			return Message.error(BizReturnCode.SYSTEM_DEVICE_FAIL);
		}
	}
	

	@ResponseBody
	@RequestMapping(params="apiVersion=1.1",method= RequestMethod.POST)
	public Message deviceRegister_V1_1(DeviceBasePO deviceBase){
		logger.info("deviceRegister in.. deviceBase:" + deviceBase.toString());
		String deviceId = deviceService.registerDevice(deviceBase);
		if(!Boolean.FALSE.toString().toUpperCase().equals(deviceId)){
			Message message = Message.success();
			message.addData("deviceId", deviceId);
			return message;
		}
		else{
			return Message.error(BizReturnCode.SYSTEM_DEVICE_FAIL);
		}
	}
	/**
	 * 
	 * @author peiboli 2015年10月15日 下午2:53:55
	 * @Method: deviceRegister_V1_1 
	 * @Description: TODO添加了渠道号检验是否为空
	 * @param deviceBase
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params="apiVersion=1.2",method= RequestMethod.POST)
	public Message deviceRegister_V1_2(DeviceBasePO deviceBase){
		logger.info("deviceRegister in.. deviceBase:" + deviceBase.toString());
		
			if(StringUtil.isBlank(deviceBase.getChannelId())){
				return Message.error(BizReturnCode.CHANNEL_ID_NOT_NULL);
			};
		String deviceId = deviceService.registerDevice(deviceBase);
		if(!Boolean.FALSE.toString().toUpperCase().equals(deviceId)){
			Message message = Message.success();
			message.addData("deviceId", deviceId);
			return message;
		}
		else{
			return Message.error(BizReturnCode.SYSTEM_DEVICE_FAIL);
		}
	}
	
}

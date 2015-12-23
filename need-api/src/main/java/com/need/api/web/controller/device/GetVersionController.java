package com.need.api.web.controller.device;


import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.device.DeviceService;
import com.need.common.domain.po.api.device.DeviceVersionPO;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p></p>
 * @author peiboli 2015年7月26日 下午1:46:18
 * @ClassName getVersionController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年7月26日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value=ControllerMappings.DEVICE_GET_VERSION)
public class GetVersionController {

	private static final Logger logger=Logger.getLogger(GetVersionController.class);
	
	@Autowired
	private DeviceService deviceService;
	
  
   @ResponseBody
   @RequestMapping(params="apiVersion=1.0",method=RequestMethod.POST)
   public Message getVersionInfo(@RequestParam(required=true) String deviceChannel) {
      Message success=Message.success();
      logger.info("getVersion in ...");
      DeviceVersionPO dv= deviceService.getVersion(deviceChannel);
      success.addData("versionNo", dv.getVersionNo());
      success.addData("downloadUrl", dv.getDownloadUrl());
      return success;
   }
  
}

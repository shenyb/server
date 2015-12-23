package com.need.api.web.controller.device;

import com.need.api.constant.ControllerMappings;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p></p>
 * @author xiehao 2015年7月27日 下午2:51:16
 * @ClassName PicAdapterController
 * @Description TODO 图片自适应接口(待定咋做)
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年7月27日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.DEVICE_PIC_ADAPTER)
public class PicAdapterController {

	private static final Logger logger = Logger.getLogger(PicAdapterController.class);
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message picAdapter(
			@RequestParam(required = true)String userId, 
			@RequestParam(required = true)String networkStatusCode){
		logger.info("in device/picAdapter userId: " + userId);
		
		Message message = Message.success();
		return message;
	}
}

package com.need.api.web.controller.pub;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.pub.ConstantsProConfig;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>提供app端控制参数</p>
 * @author Rylan 2015年9月22日 下午5:19:50
 * @ClassName GetServiceStatusController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月22日
 * @modify by reason:{方法名}:{原因}
 */

@Controller
@RequestMapping(ControllerMappings.PUBLIC_SERVICE_STATUS)
public class GetServiceStatusController {
	
	private static final Logger logger = Logger.getLogger(GetServiceStatusController.class);
	@Autowired
	private ConstantsProConfig constantsProConfig;
	
	@ResponseBody
	@RequestMapping(params="apiVersion=1.0", method = RequestMethod.GET)
	public Message getServiceStatus() {		
		logger.info("getServiceStatus  in..");
		Message success=Message.success();
		success.addData("alipayServiceStatus", constantsProConfig.getAlipayServiceStatus());
		success.addData("wechatServiceStatus", constantsProConfig.getWechatServiceStatus());
		success.addData("thirdShareServiceStatus", constantsProConfig.getThirdShareService());
		return success;
	}
	
	
	
}

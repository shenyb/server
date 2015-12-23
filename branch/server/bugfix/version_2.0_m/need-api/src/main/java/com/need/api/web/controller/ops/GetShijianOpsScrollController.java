package com.need.api.web.controller.ops;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.ops.OpsPositionService;
import com.need.common.domain.pub.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = ControllerMappings.SHIJIAN_SCROLL_OPS)
public class GetShijianOpsScrollController {
	@Autowired
	private  OpsPositionService opsPositionService;
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.GET)
	public Message getShijianScrollOps_v1_1(){
		 Message success= opsPositionService.queryShijianScrollOps_v1_1();
		  return success;
		
		
	}
	
}

package com.need.api.web.controller.ops;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.ops.OpsPositionService;
import com.need.common.domain.pub.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = ControllerMappings.KOL_BANNER_OPS)
public class GetkolBannerController {
	@Autowired
	private  OpsPositionService opsPositionService;
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.2", method = RequestMethod.GET)
	public Message getKolBannerOps_v1_2(@RequestParam(required = true)int kolCategoryId){
		 Message success= opsPositionService.queryKolOps_v1_2(kolCategoryId);
		  return success;
		  
		  
	}
	
}

package com.need.customer.web.controller.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.customer.constant.ControllerMappings;
import com.need.customer.pub.ConstantsProConfig;
import com.need.domain.pub.Message;

@Controller
@RequestMapping(ControllerMappings.PIC_DOMAIN)
public class GetPicDomain {
	
	@Autowired
	private  ConstantsProConfig constantsProConfig;
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message getOPicDomian(){
		String picDomain= constantsProConfig.getPic_domain();
		Message message=Message.success();
	    message.addData("picDomian", picDomain);
		return message;
	}

}

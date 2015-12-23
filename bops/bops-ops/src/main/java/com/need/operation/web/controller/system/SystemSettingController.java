package com.need.operation.web.controller.system;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.domain.po.api.system.SystemSettingPO;
import com.need.domain.pub.Message;
import com.need.domain.vo.auth.BopsAuthVO;
import com.need.domain.vo.system.SystemSettingParamsVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.operation.web.controller.auth.AuthManagerController;
import com.need.service.api.system.SystemSettingService;

@Controller
@RequestMapping(ControllerMappings.SYSTEM_SETTING)
public class SystemSettingController {
	private static final Logger logger = Logger.getLogger(SystemSettingController.class);
	
	@Autowired
	private SystemSettingService systemSettingService;
	
	@RequestMapping(method = RequestMethod.GET,value="/page")
	public String getSystemSettingPage(SystemSettingParamsVO systemSettingParamsVO,Model model) {
		    logger.info("getSystemSettingPage in..SystemSettingController systemSettingParamsVO:" + systemSettingParamsVO.toString());
		    List<SystemSettingPO> list= systemSettingService.queryAll();
			model.addAttribute("list", list);
		return ViewMappings.SYSTEM_SETTING_LIST;
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Message updateSetting(SystemSettingParamsVO systemSettingParamsVO) {
		logger.info("in SystemSettingController   updateSetting systemSettingParamsVO: " + systemSettingParamsVO.toString());
		Message message = Message.success();
		systemSettingService.update(systemSettingParamsVO.getSystemSettingName(), systemSettingParamsVO.getSystemSettingValue());
		return message;
	}
	
}

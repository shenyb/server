package com.need.operation.web.controller.ops;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.domain.pub.Message;
import com.need.domain.vo.dic.BopsDicVO;
import com.need.operation.constant.ControllerMappings;
import com.need.service.bops.dic.BopsDicService;
@Controller
@RequestMapping(value = ControllerMappings.OPS_MANAGER)
public class OpsManageController {

	private static final Logger logger = Logger.getLogger(OpsManageController.class);
	
	@Autowired
	private BopsDicService bopsDicService;
	/**
	 * 
	 * @author LXD 2015年8月7日 下午3:04:53
	 * @Method: getOpsId 
	 * @Description: TODO  
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{codeType}")
	public Message getOpsId(@PathVariable String codeType){
		logger.info("in OpsManageController getOpsId codeType: " + codeType);
		
		Message success=Message.success();
		List<BopsDicVO> opsIdlist = bopsDicService.getDicByDicType(codeType);
		if("ops".equals(codeType)){
		success.addData("opsNumberlist", opsIdlist);
		}
		else if("ops_type".equals(codeType)){
		success.addData("opsTypelist", opsIdlist);
		}
		logger.info("out OpsManageController getOpsId codeType: " + codeType);
		return success;
	}
}

package com.need.api.web.controller.ops;


import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.ops.OpsPositionService;
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
 * @author LXD 2015年8月8日 下午3:09:08
 * @ClassName GetHomeOps
 * @Description TODO  获取首页运营位
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.HOME_OPS)
public class GetHomeOpsPositionController {
	private static final Logger logger = Logger.getLogger(GetHomeOpsPositionController.class);
	
	
	
	
	@Autowired
	private  OpsPositionService opsPositionService;
	/***
	 * 
	 * @author LXD 2015年9月16日 下午3:09:06
	 * @Method: getHomeOps 
	 * @Description: TODO 首页运营位
	 * @param userId 
	 * @return 
	 * @throws
	 */
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getHomeOps(@RequestParam(required = false)String userId){
		 logger.info(String.format("get home ops: userId: %s", userId) );
		 Message success= opsPositionService.queryHomeOps();
		  return success;
		
		
	}
	
	/**
	 * 
	 * @author LXD 2015年9月16日 下午3:09:21
	 * @Method: getHomeOps_v1_1 
	 * @Description: TODO 1.1 首页运营位
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.GET)
	public Message getHomeOps_v1_1(){
		 Message success= opsPositionService.queryHomeOps_v1_1();
		  return success;
		
		
	}
	
}

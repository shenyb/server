package com.need.api.web.controller.distribution;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.distribution.UserCommissionService;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 
 * <p></p>
 * @author LXD 2015年12月5日 下午1:24:15
 * @ClassName GetUserIncomeController
 * @Description TODO 获取用户收益列表
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年12月5日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.GET_INCOME_LIST)
public class GetUserIncomeController {

	private static final Logger logger = Logger.getLogger(GetUserIncomeController.class);
	
	@Autowired
	private UserCommissionService userCommissionService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.GET)
	public Message getUserIncomeList(@RequestParam(required = true) int pageNum,@RequestParam(required = true) int pageSize,
			@RequestParam(required = true) String userId) {
		Message message= userCommissionService.getIncomeByUserId(userId,pageNum,pageSize);
		return message;
	}

}

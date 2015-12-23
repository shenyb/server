package com.need.api.web.controller.distribution;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.distribution.UserCommissionAccountService;
import com.need.common.domain.pub.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 
 * <p></p>
 * @author shenyb 2015年12月5日 下午5:56:34
 * @ClassName GetAvailableIncomeController
 * @Description 获取可用余额
 * @version V2.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年12月5日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.GET_AVAILABLE_INCOME)
public class GetAvailableIncomeController {
	@Autowired
	private UserCommissionAccountService userCommissionAccountService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.GET)
	public Message getUserIncomeList(@RequestParam(required = true) String userId,
			@RequestParam(required = true) int totalPrice, @RequestParam(required = false) String warehouseType) {
		Message message = Message.success();
		int balance = userCommissionAccountService.getAvailableBalance(userId, totalPrice);
		message.addData("availableBalance",balance);
		return message;
	}

}

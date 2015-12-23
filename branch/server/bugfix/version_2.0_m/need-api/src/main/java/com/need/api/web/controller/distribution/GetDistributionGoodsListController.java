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

/**
 * 
 * <p></p>
 * @author shenyb 2015年11月26日 下午8:23:39
 * @ClassName GetDistributionGoodsListController
 * @Description 工资条中获取分销的商品列表
 * @version V2.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年11月26日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.GET_DISTRIBUTION_GOODSLIST)
public class GetDistributionGoodsListController {

	private static final Logger logger = Logger.getLogger(GetDistributionGoodsListController.class);
	
	@Autowired
	private UserCommissionService userCommissionService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.GET)
	public Message goodsDetailById(@RequestParam(required = true) int pageNum,@RequestParam(required = true) int pageSize,
			@RequestParam(required = true) String userId) {
		Message message= userCommissionService.getGoodsByuserId(userId,pageNum,pageSize);
		return message;
	}

}

package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.need.NeedKolFansService;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author xiehao 2015年8月28日 上午11:11:12
 * @ClassName GetExternInfoController
 * @Description TODO 统计个人中心的一些数据
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月28日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.GET_EXTERN_INFO)
public class GetExternInfoController {
	
	private static final Logger logger = Logger.getLogger(GetExternInfoController.class);
	
	@Autowired
	private NeedKolFansService needKolFansService;
	
	@Autowired
	private TradeBaseService tradeBaseService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, params = "apiVersion=1.0")
	public Message countExternInfo(String userId){
		logger.info("in GetExternInfoController countExternInfo userId: " + userId);
		
		Message message = Message.success();
		message.addData("userFans", needKolFansService.countKolFans(userId));
		message.addData("userFocus", needKolFansService.countUserConcern(userId));
		message.addData("userOrders", tradeBaseService.countTrade(userId));
		
		return message;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, params = "apiVersion=1.1")
	public Message countExternInfo_v1_1(String userId){
		logger.info("in GetExternInfoController countExternInfo userId: " + userId);
		
		Message message = Message.success();
		message = needKolFansService.countUerExternInfo(userId);
		return message;
	}

}

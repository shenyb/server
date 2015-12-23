//package com.need.api.web.controller.trade;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.need.api.constant.ControllerMappings;
//import com.need.api.pub.Message;
//
///**
// * 
// * <p>
// * </p>
// * 
// * @author shenyb 2015年8月5日 下午10:38:18
// * @ClassName RefundController
// * @Description 退款
// * @version V1.0
// * @modificationHistory=========================逻辑或功能性重大变更记录
// * @modify by user: shenyb 2015年8月5日
// * @modify by reason:{方法名}:{原因}
// */
//@Deprecated
//@Controller
//@RequestMapping(value = ControllerMappings.REFUND)
//public class RefundController {
////	@Autowired
////	private TradeManager tradeManager;
//	private static final Logger logger = Logger.getLogger(RefundController.class);
//	/**
//	 * @throws Exception
//	 * @author shenyb 2015年8月5日 下午10:19:13 @Method: refundNotify @return @throws
//	 */
//
//	@ResponseBody
//	@RequestMapping(params = "apiVersion=1.0",method = RequestMethod.POST)
//	public Message refund(@RequestParam(required = true) String tradeNo,@RequestParam(required = true) Float money) throws Exception {
//		Message success =  Message.success();
//		//先在这测试，之后挪到到bops
//		//int result = tradeManager.refund(tradeNo,money);
//		success.addData("result", "test");
//		return success;
//	}
//}

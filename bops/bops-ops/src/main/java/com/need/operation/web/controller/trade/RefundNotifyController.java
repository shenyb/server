//package com.need.bops.web.controller.trade;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.need.bops.common.alipay.util.AlipayNotify;
//import com.need.bops.constant.ControllerMappings;
//import com.need.bops.web.controller.vo.trade.RefundNotifyVO;
///**
// * 
// * <p></p>
// * @author shenyb 2015年8月5日 下午10:38:18
// * @ClassName RefundNotifyController
// * @Description 给支付宝服务器用的通知页
// * @version V1.0   
// * @modificationHistory=========================逻辑或功能性重大变更记录
// * @modify by user: shenyb 2015年8月5日
// * @modify by reason:{方法名}:{原因}
// */
//@Controller
//@RequestMapping(value = ControllerMappings.REFUND_NOTIFY)
//public class RefundNotifyController {
//
//	Logger logger=Logger.getLogger(this.getClass());
//	/**
//	 * @throws Exception 
//	 * @author shenyb 2015年8月5日 下午10:19:13
//	 * @Method: refundNotify 
//	 * @return 
//	 * @throws
//	 */
//	@SuppressWarnings("unused")
//	//private  tradeManager;
//	@ResponseBody
//	@RequestMapping(method = RequestMethod.POST)
//	public void refundNotify(HttpServletRequest request,HttpServletResponse response
//			) throws Exception{
//		logger.info("refund notify............................start...............................");
//		//TODO request获取数据，退款成功修改订单状态
//		Map<String,String> params = new HashMap<String,String>();
//		Map<?, ?> requestParams = request.getParameterMap();
//		for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
//			String name = (String) iter.next();
//			String[] values = (String[]) requestParams.get(name);
//			String valueStr = "";
//			for (int i = 0; i < values.length; i++) {
//				valueStr = (i == values.length - 1) ? valueStr + values[i]
//						: valueStr + values[i] + ",";
//			}
//			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
//			params.put(name, valueStr);
//		}
//		
//		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
//		//批次号
//		//TODO shenyb 查看文档找信息
//		String batch_no = new String(request.getParameter("batch_no").getBytes("ISO-8859-1"),"UTF-8");
//
//		//批量退款数据中转账成功的笔数
//
//		String success_num = new String(request.getParameter("success_num").getBytes("ISO-8859-1"),"UTF-8");
//
//		//批量退款数据中的详细信息
//		String result_details = new String(request.getParameter("result_details").getBytes("ISO-8859-1"),"UTF-8");
//		RefundNotifyVO vo = new RefundNotifyVO();
//		logger.info("退款verfy before@@#@#@#@#@#@#@#@#@#@#@#@.....................................................result_details:"+result_details);
//		if(AlipayNotify.verify(params)){//验证成功
//			//TODO 修改订单状态，并插入流水表
//			/**
//			 * 1 bops交易退款表 add
//			 * 2 api交易退款表 add
//			 * 3 api 交易主表修改状态 update
//			 * 4 api交易支付流水表 add
//			 */
//		//	tradeManager.refundSuccess(batch_no,vo);
//			logger.info("退款成功");
//		}else{//验证失败
//			logger.info("退款失败");
//		}
//	}
//}

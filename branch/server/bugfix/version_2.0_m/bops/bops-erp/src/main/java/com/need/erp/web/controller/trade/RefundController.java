//package com.need.bops.web.controller.trade;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.need.bops.common.alipay.sign.MD5;
//import com.need.bops.common.alipay.util.AlipayCore;
//import com.need.bops.common.alipay.util.AlipaySubmit;
//import com.need.bops.constant.ControllerMappings;
//import com.need.bops.pub.Message;
//import com.need.framework.utils.PropertiesUtil;
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
//@Controller
//@RequestMapping(value = ControllerMappings.REFUND)
//public class RefundController {
//	Logger logger = Logger.getLogger(this.getClass());
//	/**
//	 * @throws Exception
//	 * @author shenyb 2015年8月5日 下午10:19:13 @Method: refundNotify @return @throws
//	 */
//
//	@ResponseBody
//	@RequestMapping(method = RequestMethod.POST)
//	public Message refund(@RequestParam(required = true) String tradeNo) throws Exception {
//		Message success = new Message();
//		//////////////////////////////////// 请求参数//////////////////////////////////////
//		// 服务器异步通知页面路径
//		String notifyUrl = PropertiesUtil.getProperty("/properties/alipay.properties", "refund_notify_url");
//		// 需http://格式的完整路径，不允许加?id=123这类自定义参数
//		// 卖家支付宝帐户
//		String sellerEmail = PropertiesUtil.getProperty("/properties/alipay.properties", "seller_email");
//		// 卖家支付宝帐户
//		String partner = PropertiesUtil.getProperty("/properties/alipay.properties", "partner");
//		String inputCharset = PropertiesUtil.getProperty("/properties/alipay.properties", "input_charset");
//		String privateKey = PropertiesUtil.getProperty("/properties/alipay.properties", "private_key");
//		DateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		DateFormat smBatch = new SimpleDateFormat("yyyyMMdd");
//		Map<String, String> sParaTemp = new HashMap<String, String>();
//		sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
//		sParaTemp.put("partner", partner);
//		sParaTemp.put("_input_charset", inputCharset);
//		sParaTemp.put("sign_type", "MD5");
//		sParaTemp.put("notify_url", notifyUrl);
//		String date = smBatch.format(new Date());
//		sParaTemp.put("seller_email", sellerEmail);
//		sParaTemp.put("seller_user_id", partner);
//		sParaTemp.put("refund_date", sm.format(new Date()));
//		sParaTemp.put("batch_no", date + Calendar.getInstance().getTimeInMillis());
//		sParaTemp.put("batch_num", "1");
//		sParaTemp.put("detail_data", tradeNo + "^0.01^协商退款");
//		String content = AlipayCore.createOriginLinkString(sParaTemp);
//		String sign = MD5.sign(content, privateKey, inputCharset);
//		sParaTemp.put("sign", sign);
//		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
//		logger.info("!!!!!!!!!!!!!!!!:" + sHtmlText + ":!!!!!!!!!!!!!!!!!!!!!!");
//		success.addData("result", "test");
//		return success;
//	}
//}

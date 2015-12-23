package com.need.api.web.controller.trade;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.trade.TradeManager;
import com.need.common.core.thirdpartypay.tenpay.util.XMLUtil;
import com.need.utils.HttpUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年8月18日 上午10:26:52
 * @ClassName WeChatPayNotifyController
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月18日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
public class WeChatPayNotifyController {
	@Autowired
	private TradeManager tradeManager;
	private static final Logger logger = Logger.getLogger(WeChatPayNotifyController.class);

	/**
	 * 
	 * @author shenyb 2015年8月12日 下午11:58:29 @Method: payNotify @Description:
	 * @param request
	 * 			@param response @return @throws Exception @throws
	 */
	@ResponseBody
	@RequestMapping(value=ControllerMappings.WECHAT_PAY_NOTIFY,method = RequestMethod.POST)
	public void payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("WeChatPayNotifyController.payNotify.......................start");
		byte[] bytes = IOUtils.toByteArray(request.getInputStream());
		String result = new String(bytes);
		@SuppressWarnings("unchecked")
		Map<String, String> resultMap = XMLUtil.doXMLParse(result);
		String retMsg = tradeManager.dealWechatNotify(resultMap);
		logger.info("WeChatPayNotifyController.payNotify tradeNo:"+ (resultMap != null ? resultMap.get("out_trade_no") : null) +",retMsg:"+retMsg);
		HttpUtils.sendToResponse(response, retMsg);
	}
	
	/**
	 * @author Rylan 2015年12月11日 上午10:42:18
	 * @Method: payNotifyForWap 
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value=ControllerMappings.WECHAT_PAY_NOTIFY_WAP,method = RequestMethod.POST)
	public void payNotifyForWap(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("payNotifyForWap.payNotify.......................start");
		byte[] bytes = IOUtils.toByteArray(request.getInputStream());
		String result = new String(bytes);
		@SuppressWarnings("unchecked")
		Map<String, String> resultMap = XMLUtil.doXMLParse(result);
		logger.debug("payNotify .resultMap is :"+resultMap);
		String retMsg = tradeManager.dealWechatNotifyForWap(resultMap);
		logger.info("WeChatPayNotifyController.payNotify tradeNo:"+resultMap.get("out_trade_no")+",retMsg:"+retMsg);
		HttpUtils.sendToResponse(response, retMsg);
	}
	
}

package com.need.api.web.controller.trade;

import com.need.api.constant.Constants;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.trade.TradeManager;
import com.need.utils.HttpUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 * </p>
 * 
 * @author shenyb 2015年8月5日 下午10:16:36
 * @ClassName PayNotifyController
 * @Description 支付宝回调接口
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月5日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.PAY_NOTIFY)
public class PayNotifyController {
	@Autowired
	private TradeManager tradeManager;
	private static final Logger logger = Logger.getLogger(PayNotifyController.class);

	/**
	 * 
	 * @author shenyb 2015年8月12日 下午11:58:29 @Method: payNotify @Description:
	 * @param request
	 * @param response
	 * 			@return @throws Exception @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public void payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("PayNotifyController.payNotify...............");
		// 获取支付宝POST过来反馈信息
		String result;
		try {
			Map<String, String> params = new HashMap<String, String>();
			Map<?, ?> requestParams = request.getParameterMap();
			for (Iterator<?> iterator = requestParams.keySet().iterator(); iterator.hasNext();) {
				String name = (String) iterator.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				params.put(name, valueStr);
			}
			result = tradeManager.dealAlipayNotify(params);
		} catch (Exception e) {
			/** TODO Auto-generated catch block */
			logger.error(e.getMessage());
			result=Constants.NOTIFY_FAIL;
		}	
		HttpUtils.sendToResponse(response, result);
		
	}
}

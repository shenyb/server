package com.need.api.interceptors;

import com.need.api.constant.Constants;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.device.DeviceService;
import com.need.common.domain.po.api.device.DeviceBasePO;
import com.need.common.domain.pub.Message;
import com.need.utils.HttpUtils;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * </p>
 * 
 * @author shenyb 2015年7月28日 下午3:59:13
 * @ClassName DeviceIdCheckInterceptor
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月28日
 * @modify by reason:{方法名}:{原因}
 */
public class DeviceIdCheckInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(DeviceIdCheckInterceptor.class);
	@Autowired
	private DeviceService deviceService;
	private List<Object> noMappingObject;

	/**
	 * 
	 * @author shenyb 2015年7月31日 上午10:47:18
	 * @Method: postHandle
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 
	 * @author shenyb 2015年7月31日 上午10:47:18
	 * @Method: postHandle
	 * @Description: 设备Id拦截器，如果数据库不存在则报错
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 过滤deviceId
		String deviceId = request.getHeader("deviceId");
		if (StringUtil.isBlank(deviceId)) {
			logger.info("DeviceIdCheckInterceptor end......");
			HttpUtils.responseOutWithJson(response, Message.error(BizReturnCode.SYSTEN_DEVICE_NOT_REGISTER));
			return false;
		}
		DeviceBasePO  deviceBasePO =deviceService.getByDeviceId(deviceId);
		if(deviceBasePO==null){
			logger.info("DeviceIdCheckInterceptor end......");
			HttpUtils.responseOutWithJson(response, Message.error(BizReturnCode.SYSTEN_DEVICE_NOT_REGISTER));
			return false;
		}
		//携带渠道版本
		request.setAttribute(Constants.APP_REQUEST_DEVICE_CHANNEL, deviceBasePO.getDeviceChannel());		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	public List<Object> getNoMappingObject() {
		return noMappingObject;
	}

	public void setNoMappingObject(List<Object> noMappingObject) {
		this.noMappingObject = noMappingObject;
	}

}

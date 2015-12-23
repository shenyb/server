package com.need.web.core.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartResolver;

import com.need.common.domain.pub.Message;
import com.need.utils.HttpUtils;
import com.need.utils.PropertiesUtil;
import com.need.utils.StringUtil;
import com.need.web.core.constant.BizReturnCode;
import com.need.web.core.mapping.ParameterRequestWrapper;





/**
 * <p></p>
 * @author Rylan 2015年6月28日 下午8:55:06
 * @ClassName AuthorizationFilter
 * @Description TODO 进行一些类型转换和添加版本信息
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年6月28日
 * @modify by reason:{方法名}:{原因}
 */
public class BeforeDealFilter extends  HttpServlet implements Filter{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8341704514042406782L;
		
	private static MultipartResolver multipartResolver;	
	private static WebApplicationContext wac;
	
	Logger logger=Logger.getLogger(this.getClass());
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,FilterChain arg2) throws IOException, ServletException {
		logger.info("/ doFilter in ...");
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String url = request.getRequestURI();
		//支付宝异步通知 shenyb edit 20150821
		String notifyUrl = StringUtil.dealUrl(PropertiesUtil.getProperty("/properties/alipay.properties", "notify_url"));
	    //支付宝退款异步通知
		String refundNotify =StringUtil.dealUrl(PropertiesUtil.getProperty("/properties/alipay.properties", "refund_notify_url"));
		//微信异步通知
		String weChatNotify =StringUtil.dealUrl(PropertiesUtil.getProperty("/properties/wechat.properties", "notify_url"));
		if(url.equals(notifyUrl)||url.equals(refundNotify)||url.equals(weChatNotify)){
			arg2.doFilter(request, response);
			return;
		}
		String protocolVer=request.getHeader("apiVersion");	
				
		//如果是multipartRequest, 需要转换类型为 MultipartHttpServletRequest 
		if (multipartResolver.isMultipart(request)) {
            request = HttpUtils.resolveMultipartRequest(multipartResolver, request);
        }
		
		//动态添加版本号
		if(protocolVer==null){
			HttpUtils.responseOutWithJson(response, Message.error(BizReturnCode.SYSTEM_VERSION_NULL_ERR));				
		}else{
			@SuppressWarnings("unchecked")
			Map<String,String[]> paramMap= new HashMap<String, String[]> (request.getParameterMap());	//获取新的Map类型 用于重新封装map参数
			paramMap.put("apiVersion",new String[]{protocolVer} );
			request = new ParameterRequestWrapper(request, paramMap); //重新封装request
			
			arg2.doFilter(request, response);//继续	
			logger.info("/ doFilter out ...");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		/** TODO Auto-generated method stub*/
		wac=WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		multipartResolver=(MultipartResolver) wac.getBean("multipartResolver");
	}

	
	
}

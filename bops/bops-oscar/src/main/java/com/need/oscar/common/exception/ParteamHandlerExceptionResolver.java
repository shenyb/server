package com.need.oscar.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.need.domain.pub.Message;
import com.need.service.common.exception.ServiceException;
import com.need.utils.HttpUtils;



/**
 * <p></p>
 * @author Rylan 2015年8月9日 下午3:17:50
 * @ClassName ParteamHandlerExceptionResolver
 * @Description TODO 系统异常处理类
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月9日
 * @modify by reason:{方法名}:{原因}
 */
public class ParteamHandlerExceptionResolver implements HandlerExceptionResolver {

	private Logger logger=Logger.getLogger(this.getClass());
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
		logger.error(ex);
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw, true));
		String str = sw.toString();
		logger.info("printStackTrace :"+str);//记录异常的堆栈信息到log4j
		if(ex instanceof ServiceException){
			
			return new ModelAndView("/error/500");
		} else {// 如果是普通请求
			request.setAttribute("exceptionMessage", ex.getMessage());
			// 根据不同的异常类型可以返回不同界面
			if (ex instanceof SQLException)
				return new ModelAndView("/error/500");
			else
				return new ModelAndView("/error/500");
		}
	}
}

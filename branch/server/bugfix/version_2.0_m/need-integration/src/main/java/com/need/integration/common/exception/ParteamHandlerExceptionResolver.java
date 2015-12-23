package com.need.integration.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.need.integration.pub.Message;
import com.need.integration.pub.ServiceException;




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
	
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
		logger.error(ex);
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw, true));
		String str = sw.toString();
		logger.debug("printStackTrace :"+str);//记录异常的堆栈信息到log4j
		if(ex instanceof ServiceException){
			int code=((ServiceException)ex).getCode();//错误码
			String content=((ServiceException)ex).getContent();//错误信息
			Map<String ,Object> errorMaps=((ServiceException)ex).getMaps();
			if(content!=null&&!"".equals(content)){
				return new ModelAndView("error");
			}else if(errorMaps!=null&&!errorMaps.isEmpty()){
				return new ModelAndView("error");
			}else{
				Message error=null;
				try {
					error=Message.error(code);
			    } catch (Exception e) {
			    	error=Message.error(code, "XXX");
			    }
				return new ModelAndView("error");
			}
		}else {
			try {
				return new ModelAndView("error");
            } catch (Exception e) {
	            logger.error(e);
            }
			return new ModelAndView("error");
		} 
	}
}

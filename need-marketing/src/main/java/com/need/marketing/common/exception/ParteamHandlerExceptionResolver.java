package com.need.marketing.common.exception;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.need.marketing.constant.BizReturnCode;
import com.need.marketing.pub.Message;
import com.need.marketing.pub.ServiceException;
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

	private static final  Logger logger=Logger.getLogger(ParteamHandlerExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
		//记录错误信息到log4j
				StringBuffer sb = new StringBuffer();
				sb.append("\nController err:" + handler.getClass().getName() + "\npath:"+request.getRequestURL().toString()+"\nparmas:\n");
				Enumeration<?> params = request.getParameterNames();
				while (params.hasMoreElements()) {
					Object param = params.nextElement();
					sb.append(param + ":" + request.getParameter((String) param)+"\n");
				}
				try {
					logger.error("printStackTrace :"+sb.toString(), ex);//记录异常的堆栈信息到log4j
					logger.error("response : "+response.getWriter().toString(), ex);
					//业务异常
					if(ex instanceof ServiceException){
						int code=((ServiceException)ex).getCode();//错误码
						String content=((ServiceException)ex).getContent();//错误信息
						Map<String ,Object> errorMaps=((ServiceException)ex).getMaps();
						if(content!=null&&!"".equals(content)){
							HttpUtils.responseOutWithJson(response, Message.error(code,content));
						}else if(errorMaps!=null&&!errorMaps.isEmpty()){
							HttpUtils.responseOutWithJson(response, Message.error(code,errorMaps));
						}else{
							Message error=null;
							try {
								error=Message.error(code);
					        } catch (Exception e) {
					        	error=Message.error(code, "XXX");
					        }
							HttpUtils.responseOutWithJson(response,error);
						}	
						
					}else {//系统其他报错
					     HttpUtils.responseOutWithJson(response, Message.error(BizReturnCode.SYSTEM_ERR));            		
					}
				} catch (IOException e) {
					/** TODO Auto-generated catch block */
					e.printStackTrace();
				} 
				//返回完整信息,避免resin返回500页面问题
				return new ModelAndView();
			}
}

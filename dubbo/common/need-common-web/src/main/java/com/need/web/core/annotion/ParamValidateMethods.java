package com.need.web.core.annotion;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.need.common.core.pub.ServiceException;
import com.need.common.domain.pub.Message;
import com.need.utils.HttpUtils;
import com.need.utils.StringUtil;
import com.need.web.core.constant.BizReturnCode;


public class ParamValidateMethods {
	
	private static final Logger logger=Logger.getLogger(ParamValidateMethods.class);
	
	/**
	 * @author  2015年7月3日 下午9:44:52
	 * @Method: getAnnotationByMethod
	 * @Description: TODO 根据目标方法和注解类型 得到该目标方法的指定注解
	 * @param method
	 * @param annoClass
	 * @return
	 * @throws
	 */
	public static Annotation getAnnotationByMethod(Method method, Class<?> annoClass) {
		Annotation all[] = method.getAnnotations();
		   for (Annotation annotation : all) {
			if (annotation.annotationType() == annoClass) {
				return annotation;
			}
		}
		return null;
	}
	
	/**
	 * @author  2015年7月3日 下午9:45:04
	 * @Method: getMethodByClassAndName
	 * @Description: TODO 根据类和方法名得到方法
	 * @param c
	 * @param methodName
	 * @return
	 * @throws
	 */
	public static Method getMethodByClassAndName(Class<?> c, String methodName) {
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		return null;
	}
	
	/**
	 * @author  2015年7月3日 下午9:45:50
	 * @Method: getGetterNameByFiledName
	 * @Description: TODO 得到该属性的getter方法名
	 * @param fieldName
	 * @return
	 * @throws
	 */
	public static String getGetterNameByFiledName(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	
	/**
	 * @author  2015年7月3日 下午9:46:32
	 * @Method: getFieldByObjectAndFileName
	 * @Description: TODO 根据对象和属性名得到 属性
	 * @param targetObj
	 * @param fileName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws
	 */
	public static Object getFieldByObjectAndFileName(Object targetObj, String fileName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String tmp[] = fileName.split("\\.");
		Object arg = targetObj;
		for (int i = 0; i < tmp.length; i++) {
			Method methdo = arg.getClass().getMethod(getGetterNameByFiledName(tmp[i]));
			arg = methdo.invoke(arg);
		}
		return arg;
	}
	
	/**
	 * @throws ServiceException 
	 * @author Rylan 2015年7月06日 上午11:57:36
	 * @Method: validateFiled 
	 * @Description: 验证字段
	 * @param valiedatefiles
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws
	 */
	public static boolean validateFiled(ParamValidate[] valiedatefiles, HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, ServiceException ,IOException  {
		Map<String, Object> errorMaps = new HashMap<String, Object>();
		
		boolean flag = true;
		int errorCode=BizReturnCode.SYSTEM_ERR;//错误码值
		Message message=Message.error(errorCode);
		
		for (ParamValidate validateFiled : valiedatefiles) {
			//错误码
			errorCode =validateFiled.code();
			String arg = null;
			if(StringUtils.isNotEmpty(validateFiled.name())){
				arg = request.getParameter(validateFiled.name());//取到参数值				
				if (validateFiled.notNull()) { // 判断参数是否为空
					if (StringUtils.isEmpty(arg)) {
						flag = false;
						break;
					}
					continue;
				} 
				if (validateFiled.maxLen() > 0) { // 判断字符串最大长度
					 if(arg.getBytes("utf-8").length > validateFiled.maxLen()){
						 flag = false;
						 break;
					 }	
					 continue;
				}
				
				if (validateFiled.minLen() > 0) { // 判断字符串最小长度
					 if(arg.getBytes("utf-8").length < validateFiled.minLen()){
						 flag = false;
						 break;
					 }	
					 continue;
				}
				if(!StringUtils.isEmpty(validateFiled.regex())){
					if(!arg.matches(validateFiled.regex())){
						 flag = false;
						 break;
					}
					continue;
				}
				if (validateFiled.maxVal() != -1) { // 判断数值最大值
					if (Integer.parseInt(arg) > validateFiled.maxVal()) {
						flag = false;
						break;	
					}
					continue;
				}
				if (validateFiled.minVal() != -1) { // 判断数值最小值
					if (Integer.parseInt(arg) < validateFiled.minVal()) {
						flag = false;
						break;	
					}
					continue;
				}
				
				
			}			
		}
		logger.debug("flag :"+flag+"errorCode :"+errorCode);
		if (!flag) {
				message=Message.error(errorCode);
				//throw new ServiceException(errorCode);
				HttpUtils.responseOutWithJson(response, message);				
		}
		return flag;
	}
	
}

package com.need.share.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import net.sf.json.JSONObject;

/**
 * <p></p>
 * @author Rylan 2015年6月30日 下午1:09:27
 * @ClassName HttpUtils
 * @Description TODO Http相关处理工具类.
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年6月30日
 * @modify by reason:{方法名}:{原因}
 */
public class HttpUtils  {

    private static final String MULTIPART_REQUEST_NAME = MultipartHttpServletRequest.class.getName() + "_NAME";

    /**
     * 从请求中获取int类型参数.
     *
     * @param request 请求
     * @param name 参数名称
     * @return 如果找不到对应值或者请求参数值不是数字将返回{@link Integer.MIN_VALUE}
     * @see Integer#MIN_VALUE
     */
    public static int getIntParameter(HttpServletRequest request, String name) {
        return getIntParameter(request, name, Integer.MIN_VALUE);
    }

    /**
     * 从请求中获取int类型参数。
     * 
     * @param request 请求
     * @param name 参数名称
     * @param defaultValue 默认值。
     * @return
     */
    public static int getIntParameter(HttpServletRequest request, String name, int defaultValue) {
        Assert.hasText(name);
        String value = request.getParameter(name);
        int intValue = defaultValue;
        try {
            intValue = Integer.parseInt(value.trim());
        } catch (RuntimeException e) {}
        return intValue;
    }

    /**
     * 从请求中获取long类型参数.
     *
     * @param request 请求
     * @param name 参数名称
     * @return 如果找不到对应值或者请求参数值不是数字将返回{@link Long.MIN_VALUE}
     * @see Long#MIN_VALUE
     */
    public static long getLongParameter(HttpServletRequest request, String name) {
        return getLongParameter(request, name, Long.MIN_VALUE);
    }

    /**
     * 从请求中获取long类型参数。
     * 
     * @param request 请求
     * @param name 参数名称
     * @param defaultValue 默认值。
     * @return
     */
    public static long getLongParameter(HttpServletRequest request, String name, long defaultValue) {
        Assert.hasText(name);
        String value = request.getParameter(name);
        long intValue = defaultValue;
        try {
            intValue = Long.parseLong(value.trim());
        } catch (RuntimeException e) {}
        return intValue;
    }

    /**
     * 从请求中获取double类型参数.
     *
     * @param request 请求
     * @param name 参数名称
     * @return 如果找不到对应值或者请求参数值不是数字将返回{@link Double.MIN_VALUE}
     * @see Long#MIN_VALUE
     */
    public static double getDoubleParameter(HttpServletRequest request, String name) {
        return getDoubleParameter(request, name, Double.MIN_VALUE);
    }

    /**
     * 从请求中获取double类型参数。
     * 
     * @param request 请求
     * @param name 参数名称
     * @param defaultValue 默认值。
     * @return
     */
    public static double getDoubleParameter(HttpServletRequest request, String name, double defaultValue) {
        Assert.hasText(name);
        String value = request.getParameter(name);
        double intValue = defaultValue;
        try {
            intValue = Double.parseDouble(value.trim());
        } catch (RuntimeException e) {}
        return intValue;
    }

    /**
     * Gets the string parameter.
     *
     * @param request the request
     * @param name the name
     * @param defaultValue the default value
     * @return the string parameter
     */
    public static String getStringParameter(HttpServletRequest request, String name, String defaultValue) {
        Assert.hasText(name);
        String value = request.getParameter(name);
        if (value == null) {
            value = defaultValue;
        }else{
            value = value.trim();
        }
        return value;
    }

    /**
     * Gets the string parameter.
     *
     * @param request the request
     * @param name the name
     * @return the string parameter,default is empty String "".
     */
    public static String getStringParameter(HttpServletRequest request, String name) {
        return getStringParameter(request, name, StringUtils.EMPTY);
    }

    /**
     * Gets the parameters.
     *
     * @param request the request
     * @return the parameters
     */
//    public static Map<String, String> getParameters(HttpServletRequest request) {
//        Enumeration<String> paraNames = request.getParameterNames();
//        Map<String, String> params = Maps.newHashMap();
//        while (paraNames.hasMoreElements()) {
//            String name = paraNames.nextElement();
//            params.put(name, request.getParameter(name));
//        }
//        return params;
//    }


    /**
     * Resolve multipart request.
     *
     * @param multipartResolver the multipart resolver
     * @param request the request
     * @return the multipart http servlet request
     */
    public static MultipartHttpServletRequest resolveMultipartRequest(MultipartResolver multipartResolver,
            HttpServletRequest request) {
        Assert.notNull(multipartResolver);
        Assert.notNull(request);
        MultipartHttpServletRequest muRequest =
                (MultipartHttpServletRequest) request.getAttribute(MULTIPART_REQUEST_NAME);
        if (muRequest == null && multipartResolver.isMultipart(request)) {
            muRequest = multipartResolver.resolveMultipart(request);
            request.setAttribute(MULTIPART_REQUEST_NAME, muRequest);
        }
        return muRequest;
    }
    
    /**
     * @author Rylan 2015年7月30日 下午5:56:17
     * @Method: getRealIp 
     * @Description: TODO 获取真实IP
     * @param request
     * @return 
     * @throws
     */
    public static String getRealIp(HttpServletRequest request){
        String realIp = null;
        if(StringUtils.isNotBlank(request.getHeader("X-Real-IP"))){
            realIp = request.getHeader("X-Real-IP").trim();
        }else if(StringUtils.isNotBlank(request.getHeader("X-Forwarded-For"))){
            realIp = request.getHeader("X-Forwarded-For").trim();
        }else{
            realIp = request.getRemoteAddr();
        }
        return realIp;
    }
    
    /**
     * @author Rylan 2015年7月30日 下午5:58:04
     * @Method: responseOutWithJson 
     * @Description: TODO 回写json数据
     * @param response
     * @param responseObject
     * @throws IOException 
     * @throws
     */
    public static void  responseOutWithJson(HttpServletResponse response,Object responseObject) throws IOException{
    	
    	response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out=response.getWriter();
        JSONObject responseJSONObject = JSONObject.fromObject(responseObject);      
        out.append(responseJSONObject.toString());  
        out.flush();
        out.close();     
    	
    }
    
    
    
}

package com.need.web.core.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)  
public @interface ParamValidate {

	/**
	 * @author  2015年7月3日 上午10:56:46
	 * @Method: name
	 * @Description: TODO 验证字段名称
	 * @return
	 * @throws
	 */
	String name();

	/**
	 * 
	 * @author  2015年7月3日 上午10:56:10
	 * @Method: regex
	 * @Description: TODO 正则表达式
	 * @return
	 * @throws
	 */
	String regex() default "";

	/**
	 * @author  2015年7月3日 上午10:56:13
	 * @Method: notNull
	 * @Description: TODO 必须填的字段
	 * @return
	 * @throws
	 */
	boolean notNull() default false;

	/**
	 * @author  2015年7月3日 上午10:56:16
	 * @Method: message
	 * @Description: TODO 错误提示信息
	 * @return
	 * @throws
	 */
	String message() default "";

	/**
	 * @author  2015年7月3日 上午10:58:10
	 * @Method: min
	 * @Description: TODO 最短
	 * @return
	 * @throws
	 */
	int minLen() default -1;

	/**
	 * @author  2015年7月3日 上午10:58:35
	 * @Method: max
	 * @Description: TODO 最长
	 * @return
	 * @throws
	 */
	int maxLen() default -1;

    /**
     * @author  2015年7月3日 下午9:39:58
     * @Method: maxVal 
     * @Description: TODO 最大
     * @return 
     * @throws 
     */
    int maxVal() default -1 ;  
      
    /**
     * @author  2015年7月3日 下午9:40:00
     * @Method: minVal 
     * @Description: TODO 最小
     * @return 
     * @throws 
     */
    int minVal() default -1 ; 
	
	/**
	 * @author  2015年7月3日 上午10:59:40
	 * @Method: dateFormat
	 * @Description: TODO 时间格式
	 * @return
	 * @throws
	 */
	String dateFormat() default "";
	
	/**
	 * @author  2015年7月3日 上午10:59:40
	 * @Method: code
	 * @Description: TODO 错误格式
	 * @return
	 * @throws
	 */
	int code() default 1;
}

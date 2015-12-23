package com.need.integration.interceptors;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年11月12日 下午2:59:01
 * @ClassName LogTest
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年11月12日
 * @modify by reason:{方法名}:{原因}
 */

@Aspect
public class TaskLog {
	private static Logger logger = Logger.getLogger(TaskLog.class);
	@Around("execution(* com.need.integration.task.*.*(..))")
	public void around(ProceedingJoinPoint joinpoint) {
		try {
			String methodName = joinpoint.getSignature().getName();
			logger.info(String.format("method:%s start", methodName));
			long start = System.currentTimeMillis();

			joinpoint.proceed();

			long end = System.currentTimeMillis();
			logger.info(String.format("method:%s end spent %s ms", methodName, end - start));
			System.out.println(String.format("method:%s end spent %s ms", methodName, end - start));
		} catch (Throwable e) {
			logger.error("error in class:TaskLog around:" + e.getMessage());
		}
	}

}

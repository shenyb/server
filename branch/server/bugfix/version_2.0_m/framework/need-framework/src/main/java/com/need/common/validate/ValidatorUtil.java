package com.need.common.validate;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年8月25日 下午8:52:40
 * @ClassName ValidatorUtil
 * @Description Hibernate Validate校验工具类
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月25日
 * @modify by reason:{方法名}:{原因}
 */
public class ValidatorUtil {
	private static Validator instance = null;

	public static Validator getInstance() {
		if (instance == null) {
			synchronized (ValidatorUtil.class) {
				if (instance == null) {
					ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
					instance = factory.getValidator();
				}
			}

		}
		return instance;
	}

	private ValidatorUtil() {
	}
}

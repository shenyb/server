/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 庆凯
 */
public class BeanUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);
    
    public static final Map<String, Object> toMap(Object bean) {  
        Map<String, Object> returnMap = new HashMap<String, Object>();  
        BeanInfo beanInfo;  
        try {
            beanInfo = Introspector.getBeanInfo(bean.getClass());
        } catch (IntrospectionException ex) {
            LOGGER.error("bean to map error", ex);
            return returnMap;
        }
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result;
                try {
                    result = readMethod.invoke(bean, new Object[0]);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    LOGGER.error("bean to map error", ex);
                    return returnMap;
                }
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }  
        return returnMap;  
    }  
}

package com.need.common.datasource;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;

import com.need.common.datasource.RoutingDataSourceCommon.DBTypeEnum;


/**
 * 
 * @author david.tan
 *
 */
public class RoutingDataSourceProcessor implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(RoutingDataSourceProcessor.class);
    
    private Map<String, Boolean> readMethodMap = new HashMap<String, Boolean>();
    private boolean forceChoiceReadWhenWrite = false;
    /**
     * 当之前操作是写的时候，是否强制从从库读
     * 默认（false） 当之前操作是写，默认强制从写库读
     * @param forceReadOnWrite
     */
    public void setForceChoiceReadWhenWrite(boolean forceChoiceReadWhenWrite) {
        this.forceChoiceReadWhenWrite = forceChoiceReadWhenWrite;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        
        if(!(bean instanceof NameMatchTransactionAttributeSource)) {
            return bean;
        }
        
        try {
            NameMatchTransactionAttributeSource transactionAttributeSource = (NameMatchTransactionAttributeSource)bean;
            Field nameMapField = ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap");
            nameMapField.setAccessible(true);
            @SuppressWarnings("unchecked")
			Map<String, TransactionAttribute> nameMap = (Map<String, TransactionAttribute>) nameMapField.get(transactionAttributeSource);
            
            for(Entry<String, TransactionAttribute> entry : nameMap.entrySet()) {
            	
                RuleBasedTransactionAttribute attr = (RuleBasedTransactionAttribute)entry.getValue();

                //仅对read-only的处理
                if(!attr.isReadOnly()) {
                    continue;
                }
                
                String methodName = entry.getKey();
                logger.debug(bean.getClass()+"  methoded ..  "+methodName);
                Boolean isForceChoiceRead = Boolean.TRUE;
                if(forceChoiceReadWhenWrite) {
                    //不管之前操作是写，默认强制从读库读 （设置为NOT_SUPPORTED即可）
                    //NOT_SUPPORTED会挂起之前的事务
                    attr.setPropagationBehavior(Propagation.NOT_SUPPORTED.value());
                    isForceChoiceRead = Boolean.TRUE;
                } else {
                    //否则 设置为SUPPORTS（这样可以参与到写事务）
                    attr.setPropagationBehavior(Propagation.SUPPORTS.value());
                }
                logger.info("read/write transaction process  method:{}", methodName);
                readMethodMap.put(methodName, isForceChoiceRead);
            }
            
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        
        return bean;
    }
    
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    
    
    /**
     * 
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    public Object markReadOrWriteDB(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {    	
    	@SuppressWarnings("unused")
		DBTypeEnum dbType;
        if (isChoiceReadDB(proceedingJoinPoint.getSignature().getName())) {
            logger.debug("-------------------------------------proceeding read start ...");
            RoutingDataSourceCommon.markRead();
            dbType = DBTypeEnum.read;
        } else {
            logger.debug("-------------------------------------proceeding write start ...");
            RoutingDataSourceCommon.markWrite();
            dbType = DBTypeEnum.write;
        }
            
        try {
            return proceedingJoinPoint.proceed();           
        } 
        finally {
            RoutingDataSourceCommon.reset();
        }
        
    }
    
    private boolean isChoiceReadDB(String methodName) {
    	
        String bestNameMatch = null;
        for (String mappedName : this.readMethodMap.keySet()) {
            if (isMatch(methodName, mappedName)) {
                bestNameMatch = mappedName;
                break;
            }
        }

        Boolean isForceChoiceRead = readMethodMap.get(bestNameMatch);
        //表示强制选择 读 库
        if(isForceChoiceRead == Boolean.TRUE) {
            return true;
        }
        
        //如果之前选择了写库 现在还选择 写库
        if(RoutingDataSourceCommon.isChoiceWrite()) {
            return false;
        }
        
        //表示应该选择读库
        if(isForceChoiceRead != null) {
            return true;
        }
        //默认选择 写库
        return false;
    }


    protected boolean isMatch(String methodName, String mappedName) {
        return PatternMatchUtils.simpleMatch(mappedName, methodName);
    }

}


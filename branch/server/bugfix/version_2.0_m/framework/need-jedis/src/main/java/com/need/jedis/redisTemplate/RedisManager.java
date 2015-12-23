package com.need.jedis.redisTemplate;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.Assert;

import com.need.jedis.callback.RedisGetCallbackInterface;
import com.need.jedis.callback.RedisMultiGetCallbackInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface RedisManager<V> {
   /**
    * 设置数据到缓存中
    * 
    * @param key
    * @param value
    * @param offset
    */
   public void set(String key, V value, long timeout, TimeUnit unit);

   /**
    * 直接从缓存中获取
    * 
    * @param key
    * @param timeout
    * @param unit
    * @return
    */
   public V get(String key);

   /**
    * 从缓存中获取数据,若数据失效,从接口中获取,并存入缓存中
    * 
    * @param key
    * @param offset
    * @param executor
    * @param parameters
    * @return
    */
   public V get(String key, long timeout, TimeUnit unit, RedisGetCallbackInterface<V> executor,
         Object... parameters) ;

   public List<V> multiGet(List<String> keys, long timeout, TimeUnit unit, RedisMultiGetCallbackInterface<V> executor, List<Object[]> dbKeysList) ;


   /**
    * 删除缓存
    * 
    * @param key
    */
   public void delete(String key) ;

   /**
    * 批量删除缓存
    * 
    * @param keys
    */
   public void delete(Collection<String> keys) ;
   
}

package com.need.jedis.redisTemplate.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.need.jedis.callback.RedisGetCallbackInterface;
import com.need.jedis.callback.RedisMultiGetCallbackInterface;
import com.need.jedis.redisTemplate.RedisManager;
import com.need.jedis.redisTemplate.RedisTimeOutValue;


@Repository("RedisManager")
public class RedisManagerImpl<V> implements RedisManager<V>{

   private static final Logger logger = Logger.getLogger(RedisManagerImpl.class);

   // 执行一个缓存任务的最长有效时间
   private final long EXECUTE_MAX_TIMEMILLIS = 1 * 60 * 1000;

   @Autowired
   private RedisTemplate<String, RedisTimeOutValue<V>> redisTemplate;

   /**
    * 设置数据到缓存中
    * 
    * @param key
    * @param value
    * @param offset
    */
   public void set(String key, V value, long timeout, TimeUnit unit) {
      logger.debug("set to redis");
      /*值为空得时候，不存储*/
      if(value!=null){
    	  // 获取操作对象
    	  ValueOperations<String, RedisTimeOutValue<V>> valueOperations = redisTemplate.opsForValue();
    	  long millis = TimeoutUtils.toMillis(timeout, unit);
    	  long new_timeout = millis + EXECUTE_MAX_TIMEMILLIS;
    	  long expire = System.currentTimeMillis() + millis;
    	  valueOperations.set(key, new RedisTimeOutValue<V>(value, expire), new_timeout,
    			  TimeUnit.MILLISECONDS);
      }
   }

   /**
    * 直接从缓存中获取
    * 
    * @param key
    * @param timeout
    * @param unit
    * @return
    */
   public V get(String key) {
      V result = null;
      // 获取操作对象
      ValueOperations<String, RedisTimeOutValue<V>> valueOperations = redisTemplate.opsForValue();
      logger.debug("get from redis");
      RedisTimeOutValue<V> timeOutValue = valueOperations.get(key);
      if (timeOutValue != null) {
         if (timeOutValue.getExpire() >= System.currentTimeMillis()) {
            logger.debug("redis 缓存数据有效");
            result = timeOutValue.getValue();
         }
      }
      return result;
   }

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
         Object... parameters) {
      V result = null;

      // 获取操作对象
      ValueOperations<String, RedisTimeOutValue<V>> valueOperations = redisTemplate.opsForValue();

      logger.debug("get from redis");
      RedisTimeOutValue<V> timeOutValue = valueOperations.get(key);

      if (timeOutValue == null) {
         logger.debug("redis is null");
         result = executor.getFromDB(parameters);

         set(key, result, timeout, unit);
      } else {
         if (timeOutValue.getExpire() < System.currentTimeMillis()) {
            logger.debug("redis 数据已经过期");
            if (getLock(key)) {
               try {
                  result = executor.getFromDB(parameters);
                  set(key, result, timeout, unit);
               } finally {
                  deleteLock(key);
               }
            } else {
               logger.debug("redis 数据锁定中,直接返回");
               result = timeOutValue.getValue();
            }
         } else {
            logger.debug("redis 缓存数据有效");
            result = timeOutValue.getValue();
         }
      }

      return result;
   }

   public List<V> multiGet(List<String> keys, long timeout, TimeUnit unit,
         RedisMultiGetCallbackInterface<V> executor, List<Object[]> dbKeysList) {
      if (keys == null || keys.isEmpty()) {
         return Collections.emptyList();
      }

      Assert.notNull(dbKeysList);
      Assert.isTrue(keys.size() == dbKeysList.size());

      List<V> result = new ArrayList<V>(keys.size());

      // 获取操作对象
      ValueOperations<String, RedisTimeOutValue<V>> valueOperations = redisTemplate.opsForValue();

      logger.debug("multiGet from redis(" + keys.size() + ")个");
      // 从缓存中获取全部数据
      List<RedisTimeOutValue<V>> listRedisTimeOutValue = valueOperations.multiGet(keys);

      List<Integer> indexList = new ArrayList<Integer>(keys.size());
      List<String> needGetRedisKeys = new ArrayList<String>(keys.size());;
      List<Object[]> needGetDbKeys = new ArrayList<Object[]>(keys.size());

      try {
         long currentTime = System.currentTimeMillis();
         // 遍历数据确定是否已经过期
         for (int i = 0; i < listRedisTimeOutValue.size(); i++) {
            RedisTimeOutValue<V> timeOutValue = listRedisTimeOutValue.get(i);

            if (timeOutValue == null) {
               // 数据为空,需要从数据库中查询
               indexList.add(i);
               needGetRedisKeys.add(keys.get(i));
               needGetDbKeys.add(dbKeysList.get(i));
               result.add(null);
            } else {
               if (timeOutValue.getExpire() < currentTime) {
                  logger.debug("redis 数据已经过期");
                  if (getLock(keys.get(i))) {
                     // 数据没有锁定, 需要从数据库中查询
                     indexList.add(i);
                     needGetRedisKeys.add(keys.get(i));
                     needGetDbKeys.add(dbKeysList.get(i));
                     result.add(null);
                  } else {
                     logger.debug("redis 数据锁定中,直接返回");
                     result.add(timeOutValue.getValue());
                  }
               } else {
                  logger.debug("redis 缓存数据有效");
                  result.add(timeOutValue.getValue());
               }
            }
         }


         if (!needGetDbKeys.isEmpty()) {
            // 从数据库中获取已经过期或者不存在的值
            Map<Object[],V> mapDbValues = executor.getMultiFromDB(needGetDbKeys);
            Assert.notNull(mapDbValues);
            // 把数据库中的查询结果放入结果集中
            for (int i = 0; i < needGetDbKeys.size(); i++) {
            	Object[] objects=needGetDbKeys.get(i); 
            	V value = mapDbValues.get(objects);
            	 if(value!=null){
            		 result.set(indexList.get(i), value);
                     // 把数据库中的值存入redis中
                     set(needGetRedisKeys.get(i), value, timeout, unit);
            	 }
            }
            /*for (int i = 0; i < listDbValues.size(); i++) {
               V value = listDbValues.get(i);
               if (value != null) {
                  // map.put(needGetRedisKeys.get(i), value);
                  result.set(indexList.get(i), value);
                  // 把数据库中的值存入redis中
                  set(needGetRedisKeys.get(i), value, timeout, unit);
               }
            }*/
         }
      } finally {
         // 删除所有的锁
         if (needGetRedisKeys.size() > 0) {
            deleteAllLock(needGetRedisKeys);
         }
      }

      return result;
   }


   /**
    * 删除缓存
    * 
    * @param key
    */
   public void delete(String key) {
      redisTemplate.delete(key);
   }

   /**
    * 批量删除缓存
    * 
    * @param keys
    */
   public void delete(Collection<String> keys) {
      redisTemplate.delete(keys);
   }

   /**
    * 获取数据锁
    * <p>
    * 如果获取成功返回true。
    * 
    * @param key 数据对应的key
    * @return
    */
   private boolean getLock(final String key) {
      logger.debug("redis 获取锁");
      // 获取操作对象
      ValueOperations<String, RedisTimeOutValue<V>> valueOperations = redisTemplate.opsForValue();

      String lock_key = StringUtils.join(new Object[] {key, ".lock"});
      Long lock = valueOperations.increment(lock_key, 1L);

      return lock == 1L;
   }


   /**
    * 删除数据锁
    * 
    * @param key
    */
   private void deleteLock(final String key) {
      logger.debug("redis 删除锁");
      String lock_key = StringUtils.join(new Object[] {key, ".lock"});
      redisTemplate.delete(lock_key);
   }

   private void deleteAllLock(final List<String> listKeys) {
      if (listKeys != null && !listKeys.isEmpty()) {
         logger.debug("redis 批量删除锁(" + listKeys.size() + ")个");
         List<String> listLockKeys = new ArrayList<String>(listKeys.size());
         for (String key : listKeys) {
            listLockKeys.add(StringUtils.join(new Object[] {key, ".lock"}));
         }
         redisTemplate.delete(listLockKeys);
      }
   }
}

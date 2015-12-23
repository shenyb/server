package com.need.jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Tuple;

import com.alibaba.fastjson.JSON;
import com.need.biz.utils.BizCodeUtil;
import com.need.jedis.callback.RedisGetCallbackInterface;
import java.util.HashSet;
import redis.clients.jedis.ZParams;

/**
 * 
 * <p></p>
 * @author david.tan 2015年8月7日 下午5:19:07
 * @ClassName JedisClient
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
public class JedisClient {

    private static JedisClient client;
    private static final int DEFAULT_MAX_SIZE = 20;

    private static ShardedJedisPool shardedJedisPool;
    
    private static final  Logger logger = Logger.getLogger(JedisClient.class);

    
    private  JedisClient() {
        ConfigHandler handler = new ConfigHandler(new JedisShardCase());
        handler.getDefaultHandler();
        shardedJedisPool = handler.getShareHandler().getShardedJedisPool();
      }

    static {
        client = new JedisClient();
    }


    /**
     * 往队列里写缓存对象，默认最大队列20
     * 
     * @param key
     * @param json
     * @return
     * @throws Exception
     */
    public synchronized static void pullObject(String key, Object obj) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            String json = JSON.toJSONString(obj);
            Long length = shardedJedis.llen(key);
            if (length >= DEFAULT_MAX_SIZE) {
                shardedJedis.lpop(key);
            }
            shardedJedis.lpush(key, json);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }
    
 /**
  * 往缓存放对象，有失效时间
  * 
  * @author david.tan 2015年8月7日 下午5:51:01
  * @Method: pullObject 
  * @Description: TODO
  * @param key
  * @param obj
  * @param expireTime 秒单位
  * @throws
  */
    public synchronized static void pullObject(String key, Object obj,int expireTime) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            String json = JSON.toJSONString(obj);
            Long length = shardedJedis.llen(key);
            if (length >= DEFAULT_MAX_SIZE) {
                shardedJedis.lpop(key);
            }
            shardedJedis.lpush(key, json);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }
    
    

    /**
     * @param key
     * @return
     */
    public synchronized static List<String> getAll(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        List<String> result = null;
        try {
            result = shardedJedis.lrange(key, 0, -1);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * @param key
     * @return
     */
    public synchronized static int dropKey(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        int result = 0;
        try {
            Long length = shardedJedis.llen(key);
            shardedJedis.del(key);
            result = length.intValue();
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * @param key
     * @return
     */
    public synchronized static Long increase(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Long result = 0L;
        try {
            result = shardedJedis.incr(key);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 
     * @param key
     * @return
     */
    public synchronized static Long decrease(String key){
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Long result = 0L;
        try {
            result = shardedJedis.decr(key);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 写入单个对象
     * 
     * @param key
     * @return
     */
    public synchronized static String set(String key,Object obj) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        String result = "";
        try {
        	 String json = JSON.toJSONString(obj);
            result = shardedJedis.set(key, json);                                                       
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return result;
    }
    
 
    
    /**
     * 放置单个对象，有失效期
     * 
     * @author david.tan 2015年8月7日 下午5:58:53
     * @Method: set 
     * @Description: TODO
     * @param key
     * @param obj
     * @param expireTime 秒为单位
     * @return 
     * @throws
     */
    public synchronized static String setObject(String key,Object obj,int expireTime) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        String result = "";
        try {
        	String json = JSON.toJSONString(obj);
            result = shardedJedis.set(key, json);     
            shardedJedis.expire(key, expireTime);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * @author Rylan 2015年8月16日 下午7:24:34
     * @Method: set 
     * @Description: TODO 字符串存放
     * @param key
     * @param obj
     * @param expireTime 单位是秒
     * @return 
     * @throws
     */
    public synchronized static String setString(String key,String obj,int expireTime) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        String result = "";
        try {      	
            result = shardedJedis.set(key, obj.toString());     
            shardedJedis.expire(key, expireTime);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * @author Rylan 2015年12月11日 下午9:04:58
     * @Method: setString 
     * @Description: TODO
     * @param key
     * @param obj
     * @return 
     * @throws
     */
    public synchronized static String setString(String key,String obj) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        String result = "";
        try {      	
            result = shardedJedis.set(key, obj.toString());     
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return result;
    }
      
    /**
     * @param key
     * @return
     */
    public synchronized static String getString(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        String result = "";
        try {
            result = shardedJedis.get(key);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * @author Rylan 2015年8月16日 下午7:45:08
     * @Method: getObject 
     * @Description: TODO 返回对象
     * @param key
     * @param c
     * @return 
     * @throws
     */
    public synchronized static <T>T getObject(String key,Class<T> c) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        String result = "";
        try {
            result = shardedJedis.get(key);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        T object=JSON.parseObject(result, c);//转换对象		
        return object;
    }
    
    public  static <T>T getCallbackObject(String key,Class<T> c ,RedisGetCallbackInterface<T> executor, Object... parameters) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        String result = "";
        try {
            result = shardedJedis.get(key);
            T t=null;
            if(result==null){//数据过期或是没有数据时，则查询数据库
            	t=executor.getFromDB(parameters);
            }else{
                   if(getLock(key)){//判断是否锁定
                	   t=executor.getFromDB(parameters);
            		   set(key, t);
            		   deleteLock(key);
            	    }else{
            		 //数据锁定中，有线程去查询数据库操作
            	    	
            	    } 
            	
            }	            	 
            
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        T object=JSON.parseObject(result, c);//转换对象		
        return object;
    }
    
    /**
     * 从表头开始向表尾搜索，移除与 value 相等一个元素
     * @param key
     * @param value
     * @return
     */
    public synchronized static Long removeValue(String key,String value){
    	ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Long result = 0L;
        try {
            result = shardedJedis.lrem(key, 1, value);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return result;
    	
    }
    
    
    /**
     * 从缓存列表中指定位置获取object，超过列表长度时报null
     * 
     * @param key
     * @param index
     * @return
     */
    public synchronized static  String getObFromList(String key,int index){
        String result="";
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            result = shardedJedis.lindex(key, index);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        
        return result;
    }
    
    /**
     * 判断成key是否存在
     * @param key
     * @return
     */
    public synchronized static Boolean isExistKey(String key) {
        boolean isExist = false;
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            isExist = shardedJedis.exists(key);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return isExist;
    }
    
    /**
     * 判断成员是否存在列表中
     * @param key
     * @param member
     * @return
     */
    public synchronized static Boolean isExist(String key, String member) {
        boolean isExist = false;
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            isExist = shardedJedis.sismember(key, member);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return isExist;
    }
    
    /**
     * 删除某key
     *
     * @param key
     */
    public synchronized static void del(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            shardedJedis.del(key);
        } catch (Exception e) {
            logger.error("cache error!", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }

    private static boolean getLock(final String key) {
        logger.debug("redis 获取锁");
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        // 获取操作对象
        String lock_key = key + ".lock";
        Long lock = shardedJedis.incrBy(lock_key, 1L);//数据锁	
        shardedJedisPool.returnResource(shardedJedis);
        return lock == 1L;
    }
    
    private static void deleteLock(final String key) {
        logger.debug("redis 删除锁");
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        String lock_key = StringUtils.join(new Object[] {key, ".lock"});        
        shardedJedis.decr("lock_key");
        shardedJedisPool.returnResource(shardedJedis);
     }

    public  static long zadd(String key, double score, String value) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        long result = shardedJedis.zadd(key, score, value);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    public  static Set<String> zrange(String key, int start, int end) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Set<String> result = shardedJedis.zrange(key, start, end);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    public  static Set<String> zrevrange(String key, int start, int end) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Set<String> result = shardedJedis.zrevrange(key, start, end);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    public  static Set<String> zrangeByScore(String key, double min, double max) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Set<String> result = shardedJedis.zrangeByScore(key, min, max);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    public  static Set<String> zrevrangeByScore(String key, double max, double min) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Set<String> result = shardedJedis.zrevrangeByScore(key, max, min);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    public  static Set<String> zall(String key) {
        return zrange(key, 0, -1);
    }

    public synchronized static Set<String> zrevall(String key) {
        return zrevrange(key, 0, -1);
    }

    public  static long zrem(String key, String value) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        long result = shardedJedis.zrem(key, value);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

//   public synchronized static long unionSet(String key, String... keys) {
//	   ShardedJedis shardedJedis = shardedJedisPool.getResource();
//       ZParams zp = new ZParams();
//       zp.aggregate(ZParams.Aggregate.MAX);
//       long result = shardedJedis.zunionstore(key, zp, keys);
//       shardedJedisPool.returnResource(shardedJedis);
//       return result;
//   }
    public  static long zcount(String key, double min, double max) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        long result = shardedJedis.zcount(key, min, max);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    public  static String zFirst(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Set<String> result = shardedJedis.zrange(key, 0, 0);
        shardedJedisPool.returnResource(shardedJedis);
        if (result.isEmpty()) {
            return "";
        }
        return result.iterator().next();
    }

    public  static Double zFirstScore(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Set<Tuple> result = shardedJedis.zrangeWithScores(key, 0, 0);
        shardedJedisPool.returnResource(shardedJedis);
        if (result.isEmpty()) {
            return -1d;
        }
        return result.iterator().next().getScore();
    }

    public  static String zLast(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Set<String> result = shardedJedis.zrange(key, -1, -1);
        shardedJedisPool.returnResource(shardedJedis);
        if (result.isEmpty()) {
            return "";
        }
        return result.iterator().next();
    }

    public  static Double zLastScore(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Set<Tuple> result = shardedJedis.zrangeWithScores(key, -1, -1);
        shardedJedisPool.returnResource(shardedJedis);
        if (result.isEmpty()) {
            return -1d;
        }
        return result.iterator().next().getScore();
    }

    public  static Double zScore(String key, String value) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Double result = shardedJedis.zscore(key, value);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    public  static Double zIncrby(String key, double score, String value) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Double result = shardedJedis.zincrby(key, score, value);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    public  static Set<Tuple> zAllWithScores(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Set<Tuple> result = shardedJedis.zrangeWithScores(key, 0, -1);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    public  static Set<Tuple> zRevAllWithScores(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Set<Tuple> result = shardedJedis.zrangeWithScores(key, 0, -1);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    public  static Long zRank(String key, String value) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        long result = shardedJedis.zrank(key, value);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    public  static Long zRevRank(String key, String value) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Long result = shardedJedis.zrevrank(key, value);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: zrevrange
     * @Description: 逆序返回指定索引区间的set集合
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set<String> zrevrange(String key, long start, long end) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Set<String> set = shardedJedis.zrevrange(key, start, end);
        shardedJedisPool.returnResource(shardedJedis);
        return set;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: zunionstore
     * @Description: 合并keys对应的set复制到key对应的set中
     * @param key
     * @param keys
     * @return
     */
    public static long zunionstore(String key, String... keys) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        ZParams zp = new ZParams();
        zp.aggregate(ZParams.Aggregate.MAX);
        long result = 0l;
//        long result = shardedJedis.zunionstore(key, zp, keys);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: hset
     * @Description: 设置key对应的hash中field对应的值为value
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static Long hset(String key, String field, String value) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Long result = shardedJedis.hset(key, field, value);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: hmset
     * @Description: 设置key对应的hash
     * @param key
     * @param hash
     * @return
     */
    public static String hmset(String key, Map<String, String> hash) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        String result = shardedJedis.hmset(key, hash);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: hgetAll
     * @Description: 返回key对应的hash
     * @param key
     * @return
     */
    public static Map<String, String> hgetAll(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Map<String, String> result = shardedJedis.hgetAll(key);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: hget
     * @Description: 返回key对应的hash的field对应的value
     * @param key
     * @param field
     * @return
     */
    public static String hget(String key, String field) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        String result = shardedJedis.hget(key, field);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: hdel
     * @Description: 删除key对应的hash的field
     * @param key
     * @param field
     * @return
     */
    public static Long hdel(String key, String field) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Long result = shardedJedis.hdel(key, field);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: hsetnx
     * @Description: 当key对应的hash的field对应的value不存在是设置为value
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static Long hsetnx(String key, String field, String value) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Long result = shardedJedis.hsetnx(key, field, value);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: hexists
     * @Description: key对应的hash是否存在field对应的字段
     * @param key
     * @param field
     * @return
     */
    public static Boolean hexists(String key, String field) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Boolean result = shardedJedis.hexists(key, field);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: hsetIfKeyExist
     * @Description: 当key存在时设置key对应的hash中field对应的值为value
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static Long hsetIfKeyExist(String key, String field, String value) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Long result = 0l;
        if (shardedJedis.exists(key)) {
            result = shardedJedis.hset(key, field, value);
        }
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: expire
     * @Description: 设置key对应的过期时间为expire
     * @param key
     * @param expire
     * @return
     */
    public static Long expire(String key, int expire) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Long result = shardedJedis.expire(key, expire);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: keys
     * @Description: 获取所有符合给定模式 pattern 的 key
     * @param pattern
     * @return
     */
    public static Set<String> keys(String pattern) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
//        Set<String> res = shardedJedis.keys(pattern);
        Set<String> res = new HashSet<String>();
        shardedJedisPool.returnResource(shardedJedis);
        return res;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: ttl
     * @Description: 获取key对应的过期时间
     * @param key
     * @return
     */
    public static Long ttl(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Long res = shardedJedis.ttl(key);
        shardedJedisPool.returnResource(shardedJedis);
        return res;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: llen
     * @Description: 获取key对应的list的长度
     * @param key
     * @return
     */
    public static Long llen(String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Long res = shardedJedis.llen(key);
        shardedJedisPool.returnResource(shardedJedis);
        return res;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: lpush
     * @Description: 插入到key对应的list的表头
     * @param key
     * @param values
     * @return
     */
    public static Long lpush(String key, String... values) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        Long res = shardedJedis.lpush(key, values);
        shardedJedisPool.returnResource(shardedJedis);
        return res;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: lrange
     * @Description: 获取key对应的list的从start到end的数据
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static List<String> lrange(String key, long start, long end) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        List<String> res = shardedJedis.lrange(key, start, end);
        shardedJedisPool.returnResource(shardedJedis);
        return res;
    }

    /**
     * @author 庆凯 2015年11月20日 下午8:12:54
     * @Method: zremrangeByRank
     * @Description: 删除key对应的list的索引从start到end的数据
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static long zremrangeByRank(String key, long start, long end) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        long result = shardedJedis.zremrangeByRank(key, start, end);
        shardedJedisPool.returnResource(shardedJedis);
        return result;
    }
    
   
}

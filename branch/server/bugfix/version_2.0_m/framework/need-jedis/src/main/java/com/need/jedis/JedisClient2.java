package com.need.jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;

import com.alibaba.fastjson.JSON;
import com.need.biz.utils.BizCodeUtil;

/**
 * 
 * <p></p>
 * @author david.tan 2015年8月7日 下午5:19:07
 * @ClassName JedisClient2
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
public class JedisClient2 {

    private static JedisClient2 client;
    private static final int DEFAULT_MAX_SIZE = 20;

    
    private static JedisPool jedisPool;
    
    private static final  Logger logger = Logger.getLogger(JedisClient2.class);

    
    private  JedisClient2() {
        ConfigHandler handler = new ConfigHandler(new JedisCase());
        handler.getJedisCaseHandler();
        jedisPool = handler.getJedisCaseHandler().getJedisPool();
      }

    static {
        client = new JedisClient2();
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
        Jedis  jedis = jedisPool.getResource();
        try {
            String json = JSON.toJSONString(obj);
            Long length = jedis.llen(key);
            if (length >= DEFAULT_MAX_SIZE) {
            	jedis.lpop(key);
            }
            jedis.lpush(key, json);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
        	jedisPool.returnResource(jedis);
        }
    }
    
    
    /**
     * @author Rylan 2015年12月1日 下午9:27:32
     * @Method: pullObject 
     * @Description: TODO
     * @param key
     * @param obj 
     * @throws
     */
    public static void pullObject(String key, Object obj,int expireTime) {
        Jedis  jedis = jedisPool.getResource();
        try {
            String json = JSON.toJSONString(obj);
            Long length = jedis.llen(key);
            if (length >= DEFAULT_MAX_SIZE) {
            	jedis.lpop(key);
            }
            jedis.lpush(key, json);
            jedis.expire(key, expireTime);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    public synchronized static String setObject(String key,Object obj,int expireTime) {
    	Jedis  jedis = jedisPool.getResource();
        String result = "";
        try {
        	String json = JSON.toJSONString(obj);
            result = jedis.set(key, json);     
            jedis.expire(key, expireTime);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
        	jedisPool.returnResource(jedis);
        }
        return result;
    }
    
    public synchronized static String setString(String key,String str,int expireTime) {
    	Jedis  jedis = jedisPool.getResource();
    	String result = "";
        try {
            result = jedis.set(key, str.toString()); 
            jedis.expire(key, expireTime);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
        	jedisPool.returnResource(jedis);
        }
        return result;
    }
    
    /**
     * @param key
     * @return
     */
    public static List<String> getAll(String key) {
        Jedis  jedis = jedisPool.getResource();
        List<String> result = null;
        try {
            result = jedis.lrange(key, 0, -1);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * @param key
     * @return
     */
    public static int dropKey(String key) {
       Jedis  jedis = jedisPool.getResource();
        int result = 0;
        try {
            Long length = jedis.llen(key);
            jedis.del(key);
            result = length.intValue();
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * @param key
     * @return
     */
    public static Long increase(String key) {
       Jedis  jedis = jedisPool.getResource();
        Long result = 0L;
        try {
            result = jedis.incr(key);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * @param key
     * @return
     */
    public static String get(String key) {    	
    	Jedis  jedis = jedisPool.getResource();
        String result = "";
        try {
            result = jedis.get(key);
        } catch (Exception e) {
            logger.error("cache error!",e);
            System.out.println("cache error! "+e);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }
    
    /**
     * 从表头开始向表尾搜索，移除与 value 相等一个元素
     * @param key
     * @param value
     * @return
     */
    public static Long removeValue(String key,String value){
    	Jedis  jedis = jedisPool.getResource();
        Long result = 0L;
        try {
            result = jedis.lrem(key, 1, value);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            jedisPool.returnResource(jedis);
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
    public static  String getObFromList(String key,int index){
        String result="";
       Jedis  jedis = jedisPool.getResource();
      // List<Map<String, String>> slaves = jedis.sentinelSlaves("mymaster");
        try {
            result = jedis.lindex(key, index);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            jedisPool.returnResource(jedis);
        }
        
        return result;
    }
    
    
    /**
     * 判断成员是否存在列表中
     * @param key
     * @param member
     * @return
     */
    public static Boolean isExist(String key, String member) {
        boolean isExist = false;
       Jedis  jedis = jedisPool.getResource();
        try {
            isExist = jedis.sismember(key, member);
        } catch (Exception e) {
            logger.error("cache error!",e);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return isExist;
    }
    
    /**
     * 删除某key，仅供本工程测试用
     * @param key
     */
    public  static void del(String key){
       Jedis  jedis = jedisPool.getResource();
        jedis.del(key);
        jedisPool.returnResource(jedis); 
    }
    
    /**
     * @author Rylan 2015年12月1日 下午9:26:09
     * @Method: getKeys 
     * @Description: TODO
     * @param keys
     * @return 
     * @throws
     */
    public  static List<String> getKeys(String... keys){
        Jedis  jedis = jedisPool.getResource();
        List<String>  results= jedis.mget(keys);
        jedisPool.returnResource(jedis);         
        return results; 
     }
    
    /**
     * @author Rylan 2015年12月1日 下午9:39:13
     * @Method: unionSet 
     * @Description: 并集
     * @param key
     * @param keys
     * @return 
     * @throws
     */
    public long unionSet(String key, String... keys) {
        Jedis jedis = jedisPool.getResource();
        ZParams zp = new ZParams();
        zp.aggregate(ZParams.Aggregate.MAX);
        long result = jedis.zunionstore(key, zp, keys);
        jedisPool.returnResource(jedis);
        return result;
    }
    /**
     * @author Rylan 2015年12月1日 下午9:39:09
     * @Method: interSet 
     * @Description: 交集
     * @param key
     * @param keys
     * @return 
     * @throws
     */
    public long interSet(String key, String... keys) {
        Jedis jedis = jedisPool.getResource();
        ZParams zp = new ZParams();
        zp.aggregate(ZParams.Aggregate.MAX);//取其中最大的record
        long result = jedis.zinterstore(key, zp, keys);
        jedisPool.returnResource(jedis);
        return result;
    }
    
    public  static long zadd(String key, double score, String value) {
    	Jedis jedis = jedisPool.getResource();
        long result = jedis.zadd(key, score, value);
        jedisPool.returnResource(jedis);
        return result;
    }

    public  static Set<String> zrange(String key, int start, int end) {
        Jedis jedis = jedisPool.getResource();
        Set<String> result = jedis.zrange(key, start, end);
        jedisPool.returnResource(jedis);
        return result;
    }

    public  static Set<String> zrevrange(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        Set<String> result = jedis.zrevrange(key, start, end);
        jedisPool.returnResource(jedis);
        return result;
    }

    public  static Set<String> zrangeByScore(String key, double min, double max) {
        Jedis jedis = jedisPool.getResource();
        Set<String> result = jedis.zrangeByScore(key, min, max);
        jedisPool.returnResource(jedis);
        return result;
    }

    public  static Set<String> zrevrangeByScore(String key, double max, double min) {
        Jedis jedis = jedisPool.getResource();
        Set<String> result = jedis.zrevrangeByScore(key, max, min);
        jedisPool.returnResource(jedis);
        return result;
    }

    public  static Set<String> zall(String key) {
        return zrange(key, 0, -1);
    }

    public static Set<String> zrevall(String key) {
        return zrevrange(key, 0, -1);
    }

    public  static long zrem(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.zrem(key, value);
        jedisPool.returnResource(jedis);
        return result;
    }


    public  static long zcount(String key, double min, double max) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.zcount(key, min, max);
        jedisPool.returnResource(jedis);
        return result;
    }

    public  static String zFirst(String key) {
        Jedis jedis = jedisPool.getResource();
        Set<String> result = jedis.zrange(key, 0, 0);
        jedisPool.returnResource(jedis);
        if (result.isEmpty()) {
            return "";
        }
        return result.iterator().next();
    }

    public  static Double zFirstScore(String key) {
        Jedis jedis = jedisPool.getResource();
        Set<Tuple> result = jedis.zrangeWithScores(key, 0, 0);
        jedisPool.returnResource(jedis);
        if (result.isEmpty()) {
            return -1d;
        }
        return result.iterator().next().getScore();
    }

    public  static String zLast(String key) {
        Jedis jedis = jedisPool.getResource();
        Set<String> result = jedis.zrange(key, -1, -1);
        jedisPool.returnResource(jedis);
        if (result.isEmpty()) {
            return "";
        }
        return result.iterator().next();
    }

    public  static Double zLastScore(String key) {
        Jedis jedis = jedisPool.getResource();
        Set<Tuple> result = jedis.zrangeWithScores(key, -1, -1);
        jedisPool.returnResource(jedis);
        if (result.isEmpty()) {
            return -1d;
        }
        return result.iterator().next().getScore();
    }

    public  static Double zScore(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Double result = jedis.zscore(key, value);
        jedisPool.returnResource(jedis);
        return result;
    }

    public  static Double zIncrby(String key, double score, String value) {
        Jedis jedis = jedisPool.getResource();
        Double result = jedis.zincrby(key, score, value);
        jedisPool.returnResource(jedis);
        return result;
    }

    public  static Set<Tuple> zAllWithScores(String key) {
        Jedis jedis = jedisPool.getResource();
        Set<Tuple> result = jedis.zrangeWithScores(key, 0, -1);
        jedisPool.returnResource(jedis);
        return result;
    }

    public  static Set<Tuple> zRevAllWithScores(String key) {
        Jedis jedis = jedisPool.getResource();
        Set<Tuple> result = jedis.zrangeWithScores(key, 0, -1);
        jedisPool.returnResource(jedis);
        return result;
    }

    public  static Long zRank(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.zrank(key, value);
        jedisPool.returnResource(jedis);
        return result;
    }

    public  static Long zRevRank(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.zrevrank(key, value);
        jedisPool.returnResource(jedis);
        return result;
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
        Jedis jedis = jedisPool.getResource();
        ZParams zp = new ZParams();
        zp.aggregate(ZParams.Aggregate.MAX);
        long result = jedis.zunionstore(key, zp, keys);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(key, field, value);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        String result = jedis.hmset(key, hash);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        Map<String, String> result = jedis.hgetAll(key);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        String result = jedis.hget(key, field);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(key, field);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hsetnx(key, field, value);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        Boolean result = jedis.hexists(key, field);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        Long result = 0l;
        if (jedis.exists(key)) {
            result = jedis.hset(key, field, value);
        }
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, expire);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        Set<String> res = jedis.keys(pattern);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        Long res = jedis.ttl(key);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        Long res = jedis.llen(key);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        Long res = jedis.lpush(key, values);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        List<String> res = jedis.lrange(key, start, end);
        jedisPool.returnResource(jedis);
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
        Jedis jedis = jedisPool.getResource();
        long result = jedis.zremrangeByRank(key, start, end);
        jedisPool.returnResource(jedis);
        return result;
    }
 
    
    public static void main(String[] args) {
		
		System.out.println(BizCodeUtil.getUserIdByToken("MjAxNTExMjgwMDM0OWRmMjlhYzMxOGRkZXwyMDE1MTIwNTE2Mjg="));
	}
    
}

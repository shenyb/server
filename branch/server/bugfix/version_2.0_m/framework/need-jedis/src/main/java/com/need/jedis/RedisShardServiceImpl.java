package com.need.jedis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.alibaba.fastjson.JSON;


/**
 * 
 * <p></p>
 * @author david.tan 2015年8月7日 下午5:19:38
 * @ClassName RedisShardServiceImpl
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class RedisShardServiceImpl implements RedisService {
    private static final int DEFAULT_MAX_SIZE = 20;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    /**
     * 添加string到后出先出列表的尾部，如果超过20个会逐出首部
     * 
     * @param key
     * @param json
     * @return
     * @throws Exception
     */
    public void pullObject(String key, Object obj) {
        try (ShardedJedis shardedJedis = shardedJedisPool.getResource()) {
            String json = JSON.toJSONString(obj);
            Long length = shardedJedis.llen(key);
            if (length >= DEFAULT_MAX_SIZE) {
                shardedJedis.lpop(key);
            }
            shardedJedis.lpush(key, json);
        } catch (Exception e) {
            logger.error("cache error!", e);
        }
    }

    /**
     * @param key
     * @return
     */
    public List<String> getAll(String key) {
        List<String> result = null;
        try (ShardedJedis shardedJedis = shardedJedisPool.getResource()) {
            result = shardedJedis.lrange(key, 0, -1);
        } catch (Exception e) {
            logger.error("cache error!", e);
        }
        return result;
    }

    /**
     * @param key
     * @return
     */
    public int dropKey(String key) {
        int result = 0;
        try (ShardedJedis shardedJedis = shardedJedisPool.getResource()) {
            Long length = shardedJedis.llen(key);
            shardedJedis.del(key);
            result = length.intValue();
        } catch (Exception e) {
            logger.error("cache error!", e);
        }
        return result;
    }

    /**
     * @param key
     * @return
     */
    public Long increase(String key) {
        Long result = 0L;
        try(ShardedJedis shardedJedis = shardedJedisPool.getResource()){
            result = shardedJedis.incr(key);
            
        } catch (Exception e) {
            logger.error("cache error!", e);
        }
        return result;
    }

    /**
     * @param key
     * @return
     */
    public Long decrease(String key) {
        Long result = 0L;
        try(ShardedJedis shardedJedis = shardedJedisPool.getResource()){
            result = shardedJedis.decr(key);
        } catch (Exception e) {
            logger.error("cache error!", e);
        }
        return result;
    }

    /**
     * @param key
     * @return
     */
    public String set(String key, String value) {
        String result = "";
        try(ShardedJedis shardedJedis = shardedJedisPool.getResource()){
            result = shardedJedis.set(key, value);
        } catch (Exception e) {
            logger.error("cache error!", e);
        }
        return result;
    }

    /**
     * @param key
     * @return
     */
    public String get(String key) {
        String result = "";
        try(ShardedJedis shardedJedis = shardedJedisPool.getResource()){
            result = shardedJedis.get(key);
        } catch (Exception e) {
            logger.error("cache error!", e);
        }
        return result;
    }

    /**
     * 从表头开始向表尾搜索，移除与 value 相等一个元素
     * 
     * @param key
     * @param value
     * @return
     */
    public Long removeValue(String key, String value) {
        Long result = 0L;
        try(ShardedJedis shardedJedis = shardedJedisPool.getResource()){
            result = shardedJedis.lrem(key, 1, value);
        } catch (Exception e) {
            logger.error("cache error!", e);
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
    public String getObFromList(String key, int index) {
        String result = "";
        try(ShardedJedis shardedJedis = shardedJedisPool.getResource()){
            result = shardedJedis.lindex(key, index);
        } catch (Exception e) {
            logger.error("cache error!", e);
        }
        return result;
    }

    /**
     * 判断成key是否存在
     * 
     * @param key
     * @return
     */
    public Boolean isExistKey(String key) {
        boolean isExist = false;
        try(ShardedJedis shardedJedis = shardedJedisPool.getResource()){
            isExist = shardedJedis.exists(key);
        } catch (Exception e) {
            logger.error("cache error!", e);
        }
        return isExist;
    }

    /**
     * 判断成员是否存在列表中
     * 
     * @param key
     * @param member
     * @return
     */
    public Boolean isExist(String key, String member) {
        boolean isExist = false;
        try(ShardedJedis shardedJedis = shardedJedisPool.getResource()){
            isExist = shardedJedis.sismember(key, member);
        } catch (Exception e) {
            logger.error("cache error!", e);
        }
        return isExist;
    }

    /**
     * 删除某key，仅供本工程测试用
     * 
     * @param key
     */
    public void del(String key) {
        try(ShardedJedis shardedJedis = shardedJedisPool.getResource()){
        shardedJedis.del(key);
        }
    }
}

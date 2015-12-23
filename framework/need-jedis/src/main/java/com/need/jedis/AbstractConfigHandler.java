package com.need.jedis;

import java.util.List;

import redis.clients.jedis.ShardedJedis;

/**
 * 
 * <p></p>
 * @author david.tan 2015年8月7日 下午5:18:47
 * @ClassName AbstractConfigHandler
 * @Description TODO
 * @version V1.0   
 * @param <T>
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
public abstract class AbstractConfigHandler<T> {
    public static final int FEED_LIST_SIZE=20;
    /**
     * 初始化连接池
     */
    protected abstract void initPoolCase() ;
	
	/**
	 * 保存key和value数据到redis
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract String set(String key, String value);
	
	/**
     * 保存key和value数据到redis
     * @param key
     * @param value
     * @return 1 成功， 0 不成功
     */
    public abstract Long setnx(String key, String value);
	
	/**
	 * 获取key在redis中保存的值
	 * @param key
	 * @return
	 */
	public abstract String get(String key);
	
	/**
	 * 批量计数操作
	 * @param keys
	 * @return
	 */
	public abstract boolean batchIncrease(String [] keys);
	
	/**
	 * 将该value值+1
	 * @param key
	 * @return 返回+1后的值
	 */
	public abstract Long increase(String key);
    
	/**
	 * 保存key和value数据，并在seconds秒后失效
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	public abstract String setBySeconds(String key, String value,int seconds);
	
	/**
	 * 保存object对象到对应的key
	 * @param key
	 * @param value
	 * @return 1 成功， 0 不成功
	 * @throws Exception
	 */
	public abstract Long setObj(String key, Object value) throws Exception;
	
	/**
	 * 保存object对象到对应的key，并在seconds秒后失效
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 * @throws Exception
	 */
	public abstract String setBySeconds(String key, Object value, final int seconds) throws Exception;
	
	/**
	 * 根据key获取object对象
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public abstract Object getObj(String key) throws Exception;
	
	/**
	 * 判断key值是否存在
	 * @param key
	 * @return
	 */
	public abstract boolean exists(String key);
	
	/**
	 * 添加值到list尾部
	 * @param key
	 * @param strings
	 * @return 
	 */
	public abstract Long addListTail(String key, String... strings);
	
	/**
	 * 获取list的length
	 * @param key
	 * @return
	 */
	public abstract Long getLengthByList(String key);
	
	/**
	 * 修改list中某下标的值
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 */
	public abstract String editByListIndex(String key,int index,String value);
	 
	/**
	 * 删除list中某下标的值
	 * @param key
	 * @param count
	 * @param value
	 * @return
	 */
	public abstract Long removeByListIndex(String key,int count,String value);
	
	/**
	 * 获取list中start到end区间的值
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public abstract List<String> getListByRange(String key,int start,int end);
	
	/**
	 * 删除list的头部元素，即头部的第一个元素
	 * @param key
	 * @return
	 */
	public abstract int removeListHead(String key);
	
	/**
	 * 删除区间之外的list中数据
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public abstract String removeOutsideIndex(String key,int start,int end);
	
	
	
	/**
     * 添加对象到list尾部
     * @param key
     * @param strings
     * @return
     */
    public abstract Long addObjectListTail(String key, T strings);
    
    /**
     * 获取list中start到end区间的对象
     * @param key
     * @param start
     * @param end
     * @return 
     */
    public abstract List<T> getListObjectsByRange(String key,int start,int end);
    
    public abstract ShardedJedis getShardJedisCase();
}

package com.need.jedis;

import java.util.List;

/**
 * 
 * <p></p>
 * @author david.tan 2015年8月7日 下午5:19:30
 * @ClassName RedisService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
public interface RedisService {

    /**
     * 添加string到后出先出列表的尾部，如果超过20个会逐出首部
     * 
     * @param key
     * @param obj
     * @return
     * @throws Exception
     */
    public void pullObject(String key, Object obj);
    
    /**
     * @param key
     * @return
     */
    public List<String> getAll(String key);
    
    /**
     * @param key
     * @return
     */
    public int dropKey(String key);
    
    /**
     * @param key
     * @return
     */
    public Long increase(String key);
    
    /**
     * @param key
     * @return
     */
    public Long decrease(String key);
    
    /**
     * @param key
     * @return
     */
    public String set(String key,String value);
    
    /**
     * @param key
     * @return
     */
    public String get(String key);
    
    /**
     * 从表头开始向表尾搜索，移除与 value 相等一个元素
     * 
     * @param key
     * @param value
     * @return
     */
    public Long removeValue(String key,String value);
    
    /**
     * 从缓存列表中指定位置获取object，超过列表长度时报null
     * 
     * @param key
     * @param index
     * @return
     */
    public String getObFromList(String key,int index);
    
    /**
     * 判断成key是否存在
     * 
     * @param key
     * @return
     */
    public Boolean isExistKey(String key);
    
    /**
     * 判断成员是否存在列表中
     * 
     * @param key
     * @param member
     * @return
     */
    public Boolean isExist(String key, String member);
    
    /**
     * 删除某key，仅供本工程测试用
     * 
     * @param key
     */
    public void del(String key);
    
}

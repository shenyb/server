package com.need.jedis;

import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
 * <p></p>
 * @author david.tan 2015年8月7日 下午5:18:53
 * @ClassName ConfigHandler
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
public class ConfigHandler {
    
	private JedisShardCase shareHandler;
	private JedisSentinelCase sentinelHandler;
	private JedisCase  jedisCase;
	
	
	public ConfigHandler(JedisShardCase handler) {
		this.shareHandler = handler;
	}
	public ConfigHandler(JedisSentinelCase handler) {
        this.sentinelHandler = handler;
    }
	public ConfigHandler(JedisCase handler) {
        this.jedisCase = handler;
    }
	
	protected JedisShardCase getShareHandler(){
		return this.shareHandler;
	}
	
	protected JedisSentinelCase getSentinelCaseHandler(){
	    return this.sentinelHandler;
	}
	protected JedisCase getJedisCaseHandler(){
		jedisCase.initialPool();
	    return this.jedisCase;
	}
	
	public ConfigHandler getDefaultHandler(){
		//TODO
		getShareHandler().initPoolCase();
		return this;
	}
	public ConfigHandler getSentinelHandler(){
	    getSentinelCaseHandler().initPoolCase();
	    return this;
	}
	
	
	protected ShardedJedisPool getShardedJedisPool(){
        return this.shareHandler.getShardedJedisPool();
    }
	
	protected JedisSentinelPool getJedisSentinelPool(){
	    return this.sentinelHandler.getJedisSentinelPool();
	}
}
 
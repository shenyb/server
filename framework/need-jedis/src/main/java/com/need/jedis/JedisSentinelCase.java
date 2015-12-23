package com.need.jedis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.need.jedis.util.BundleUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
/**
 * 
 * <p></p>
 * @author david.tan 2015年8月7日 下午5:19:14
 * @ClassName JedisSentinelCase
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
public class JedisSentinelCase{
	/**
	 * shard client
	 */
	private Jedis jedis;
	/**
	 * shard pool
	 */
	private JedisSentinelPool jedisSentinelPool;
	private List<String> sentinelsNodes = BundleUtil.getSentinels();
		
	protected void  initPoolCase(){
        initialSentineledPool();
    }
	/**
	 * initial shard pool
	 */
	protected void initialSentineledPool() {
		// pool config
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMinIdle(8);                     //最小空闲连接数, 默认0
		config.setMaxTotal(100);                   //最大连接数, 默认8个
		config.setMaxWaitMillis(10000);           //获取连接时的最大等待毫秒数
		config.setTestOnBorrow(false);            //在获取连接的时候检查有效性, 默认false
		config.setLifo(false);                    //是否启用后进先出, 默认true
		
		
		Set<String> sentinels = new HashSet<String>();
        for(String sentinel: sentinelsNodes){
           sentinels.add(sentinel);         
        }
        // structure pool 默认是2000
        jedisSentinelPool = new JedisSentinelPool(BundleUtil.getMasterName(), sentinels, config,Integer.parseInt(BundleUtil.getConnTimeOut()),BundleUtil.getMasterPwd());
                        
	}
	
	public JedisSentinelPool getJedisSentinelPool(){
	    return this.jedisSentinelPool;
	}
	
	
	
}
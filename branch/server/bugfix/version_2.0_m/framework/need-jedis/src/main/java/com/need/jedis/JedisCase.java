package com.need.jedis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import com.need.common.asynchronous.AsynchronousUtil;
import com.need.common.callback.GetCallbackInterface;
import com.need.jedis.callback.ChechRedisConfigCallback;
import com.need.jedis.config.Node;
import com.need.jedis.util.BundleUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
/**
 * 
 * <p></p>
 * @author david.tan 2015年8月7日 下午5:19:24
 * @ClassName JedisShardCase
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
public class JedisCase extends AbstractConfigHandler<Object>{
	/**
	 * shard client
	 */
	private Jedis jedis;
	/**
	 * shard pool
	 */
	private JedisPool jedisPool;
	
	private List<Node> nodes = BundleUtil.getNodes();
	
	
	protected void  initPoolCase(){
		initialPool();
    }
	/**
	 * initial shard pool
	 */
	protected void initialPool() {
		// pool config
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMinIdle(8);                     //最小空闲连接数, 默认0
		config.setMaxTotal(500);                   //最大连接数, 默认8个
		config.setMaxWaitMillis(5000);           //获取连接时的最大等待毫秒数
		config.setTestOnBorrow(true);            //在获取连接的时候检查有效性, 默认false
		config.setLifo(true);                    //是否启用后进先出, 默认true
		
		
		// jedisPool 
		if(nodes.get(0).getPassword()!=null){
			jedisPool=new JedisPool(config, nodes.get(0).getIp(), Integer.parseInt(nodes.get(0).getPort()),5000,nodes.get(0).getPassword());
		}else{
			jedisPool=new JedisPool(config, nodes.get(0).getIp(), Integer.parseInt(nodes.get(0).getPort()));
		}
		
			
		
		//新建异步线程执行检测各个节点
//		 try {			 
//			AsynchronousUtil.getInstance().runAsynNoReturn(Executors.newCachedThreadPool(),new ChechRedisConfigCallback<String>(),config,shards,shardedJedisPool);
//		} catch (InterruptedException | ExecutionException e) {
//			/** TODO Auto-generated catch block */
//			e.printStackTrace();
//		}
		
	}
	
	public JedisPool getJedisPool(){
	    return this.jedisPool;
	}
		
	/**
     * return the case to pool
     */
	protected void returnPool(){
		jedisPool.returnResource(this.jedis);
    }
	
	@Override
	public ShardedJedis getShardJedisCase() {
		/** TODO Auto-generated method stub*/
		return null;
	}
	
	
    /**
     * @return Status code reply
     */
	public String set(String key, String value){
	    String s = getShardJedisCase().set(key,value);
	    returnPool();
		return s;
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return Integer reply, specifically: 1 if the key was set 0 if the key
     *         was not set
	 */
	public Long setnx(String key, String value){
		Long l = getShardJedisCase().setnx(key,value);
	    returnPool();
		return l;
	}
	
	public String setBySeconds(String key, String value,int seconds){
		String s = getShardJedisCase().setex(key, seconds, value);
	    returnPool();
		return s;
	}

    @Override
    public String get(String key) {
        String s = getShardJedisCase().get(key);
        returnPool();
        return s;
    }
    
    public Long setObj(String key, Object value) throws Exception{
    	byte[] keyBytes = key.getBytes(); 
    	Long l = getShardJedisCase().setnx(keyBytes, Serizable.writeObj(value));
	    returnPool();
		return l;
	}
    
    public String setBySeconds(String key, Object value, final int seconds) throws Exception{
    	byte[] keyBytes = key.getBytes(); 
    	String s = getShardJedisCase().setex(keyBytes,seconds,Serizable.writeObj(value));
	    returnPool();
		return s;
    }
    
    public Object getObj(String key) throws Exception{
    	byte[] keyBytes = key.getBytes();
		byte[] bytes = getShardJedisCase().get(keyBytes);
    	return Serizable.readObj(bytes);
    }
    
    
    public boolean batchIncrease(String [] keys)
    {
      
        return true;
    }
    
    public Long increase(String key)
    {
        Long result = 0L;
            result = getShardJedisCase().incr(key);
             returnPool();
        return result;
    }
    
    public boolean exists(String key){
        boolean result = getShardJedisCase().exists(key);
        returnPool();
        return result;
    }
    
    //list operation
    public Long addObjectListTail(String key, Object object){
        Long result = null;
        byte[] keyBytes = key.getBytes();
        byte[] byteObjects=null;
        try {
            byteObjects = Serizable.writeObj(object);
            result = getShardJedisCase().lpush(keyBytes, byteObjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        returnPool();
        return result;
    }

    
    /**
     * 待修改方法
     * 
     */
    public List<Object> getListObjectsByRange(String key,int start,int end){
        List<Object> objs = new ArrayList<Object>();
        byte[] keyBytes = key.getBytes();
        List<byte[]> result = getShardJedisCase().lrange(keyBytes, start, end);
        try {
            for(byte[] by : result){
                Object oj  = Serizable.readObj(by);
                if(null != oj)
                objs.add(oj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        returnPool();
        return objs;
    }
    
    
    
    public Long getLengthByList(String key){
        Long result = getShardJedisCase().llen(key);
        returnPool();
        return result;
    }
    
    public String editByListIndex(String key,int index,String value){
        String result = getShardJedisCase().lset(key, index, value);
        returnPool();
        return result;
    }
    
    public Long removeByListIndex(String key,int count,String value){
        Long result = getShardJedisCase().lrem(key, count, value);
        returnPool();
        return result;
    }
    
    
    public int removeListHead(String key){
        int result = 0;
        String s = getShardJedisCase().lpop(key);
        if(!s.equals("nil")){
            result = 1;
        }
        returnPool();
        return result;
    }
    
    public String removeOutsideIndex(String key,int start,int end){
        String s = getShardJedisCase().ltrim(key, start, end);
        returnPool();
        return s;
    }
    
    
    static final class Serizable{
        static public byte[] writeObj (Object obj) {
            byte[] bytes = null;      
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(bos);
                oos.writeObject(obj); 
                oos.flush();         
                bytes = bos.toByteArray ();      
                        
            } catch (Exception ex) {
//                ex.printStackTrace();   
            }finally{
                try {
                    if(null != oos){
                        oos.close();
                    }
                    bos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return bytes;    
        }
        
        static public Object readObj (byte[] bytes) {
            Object obj = null;      
            ByteArrayInputStream bis = null;        
            ObjectInputStream ois = null;
            try {        
                bis = new ByteArrayInputStream (bytes); 
                ois = new ObjectInputStream (bis);
                obj = ois.readObject();      
            } catch (Exception ex) {
//                ex.printStackTrace();   
            }finally{
                try {
                    if(null != ois){
                        ois.close();
                    }
                    bis.close(); 
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return obj;    
        }
    }


    @Override
    public Long addListTail(String key, String... strings){
        Long result = getShardJedisCase().lpush(key, strings);
        returnPool();
        return result;
    }

    public List<String> getListByRange(String key,int start,int end){
        List<String> result = getShardJedisCase().lrange(key, start, end);
        returnPool();
        return result;
    }
	
	
}

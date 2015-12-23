package com.need.jedis.callback;



import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.LinkedList;
import java.util.List;

import com.need.common.callback.GetCallbackInterface;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public class ChechRedisConfigCallback<T> implements GetCallbackInterface<T>{

	@Override
	public T executor(Object... parameters) {
		/** TODO Auto-generated method stub*/
		//循环检查各个节点是否存在
		//遍历所有的JedisShardInfo
		System.out.println("executor in..");
	    List<JedisShardInfo> shardJedisList=(List<JedisShardInfo>) parameters[1];
	    ShardedJedisPool pool=(ShardedJedisPool) parameters[2];
	    JedisPoolConfig config= (JedisPoolConfig)parameters[0];
	    while(true){
	    	List<JedisShardInfo> destroyList=null;
	    	for (JedisShardInfo jedisShardInfo : shardJedisList) {
				Socket socket = new Socket();
				SocketAddress address =null;
				try{
					address=new InetSocketAddress(jedisShardInfo.getHost(),jedisShardInfo.getPort());
					socket.connect(address,jedisShardInfo.getSoTimeout());
					boolean socketFlag=socket.isConnected();
					System.out.println("socketFlag :"+socketFlag);
					continue;
				}catch(Exception e){
					destroyList=new LinkedList<JedisShardInfo>();
					destroyList.add(jedisShardInfo);//记录失效的节点
				}
				
			}
	    	System.out.println("destroyList :"+destroyList);
	    	if(destroyList!=null){
	    		shardJedisList.removeAll(destroyList);//集合重构
	    		pool.destroy();//销毁原来的连接池
				pool=new ShardedJedisPool(config,shardJedisList);
	    	}	    	
	    	
			try {
				Thread.sleep(10000);//TODO 暂定10秒
			} catch (InterruptedException e) {
				/** TODO Auto-generated catch block */
				e.printStackTrace();
			}
	  }
		    			    		
	}
	
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket();
			SocketAddress address =null;
		    address=new InetSocketAddress("101.200.182.88",6379);
		    socket.connect(address);
			boolean socketFlag=socket.isConnected();
			System.out.println(socketFlag);
			
		} catch (Exception e) {
			/** TODO Auto-generated catch block */
			e.printStackTrace();
		}
		
	}
	
}

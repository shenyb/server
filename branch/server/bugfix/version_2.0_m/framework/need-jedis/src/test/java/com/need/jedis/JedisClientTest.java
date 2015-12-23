package com.need.jedis;


import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.need.jedis.util.User;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;


/**
 * 
 * 类JedisClientTest.java的实现描述：TODO 类实现描述 
 * @author dell 2014年11月13日 下午3:39:36
 */
public class JedisClientTest {
    
 @Test
 public void test_setObj()throws Exception{
     User user=new User();
    
     String key="user";
     int expireTime=3; //秒为单位
//     for(int i=0;i<10;i++){
         user.setCode("david_");
         user.setName("谭德伟");    
         JedisClient.setObject(key, user,expireTime);
//     }
    
    Thread.currentThread().sleep(2000);
    //String json=JedisClient.get(key);
    
    
    //System.out.println(json);
 }
 
 @Test
 public void test_indecrease(){
 }
 
 
 @Test
 public void testZSetAdd(){
	 
	 for (int i = 0; i < 10; i++) {
		 //System.out.println(JedisClient.zSetAdd("zset", i+10, "我是zSet"+i));
	}
	 
 
 
 }
 
 @Test
 public void testZSetASC(){
	 
	 //System.out.println(JedisClient.zSetByScoreAsc("zset", Long.MAX_VALUE, 2));
	 //System.out.println(JedisClient.zSetCount("zset",16,20));	
	 //System.out.println(JedisClient.zSetScoreByMember("zset", "我是zSet6"));
	 //System.out.println(JedisClient.zSetrange("zset", 10l));
	 //System.out.println(JedisClient.zSetIncrByMember("zset", "我是zSet6",9));
	// System.out.println(JedisClient.zSetrange("zset"));
	 //System.out.println(JedisClient.zSetRangeByScore("zset",12,Long.MAX_VALUE));
	 //System.out.println(JedisClient.zSetRevRangeByScore("zset", 25,12,2));
	 //System.out.println(JedisClient.zSetRankByMember("zset","我是zSet6"));
	 
	 //System.out.println(JedisClient.zSetRemoveMemberByScore("zset", 19, 25));
	 
	 //System.out.println(JedisClient.zSetRangeByScoreWithScores("zset", 10, 25));
	 
	 
	 
 }
 
 
 
		 @Test
		 public void testSentinel(){
			
		 }
		 
 
		 public static void main(String[] args) {
			 
			 System.out.println(JedisClient2.getKeys("MHwyMDE1MTIwMjE0Mzc=","MHwyMDE1MTIwMjE0Mzk="));
 
		 }
}

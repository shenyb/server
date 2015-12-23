package com.need.jedis;

import org.junit.Test;

import com.need.jedis.util.User;

/**
 * 
 * 类JedisClientTest.java的实现描述：TODO 类实现描述 
 * @author dell 2014年11月13日 下午3:39:36
 */
@Deprecated
public class JedisSentinelClientTest {
    
 @Test
 public void test_getObFromList(){
     User user=new User();
    
     JedisSentinelClient client=JedisSentinelClient.getJedisClient();
     String key="user";
     for(int i=0;i<10;i++){
         user.setCode("david_"+i);
         user.setName("谭德伟");    
         client.pullObject(key,  user);
     }
     
    String json=JedisSentinelClient.getObFromList(key, 20);
    System.out.println(json);
 }
 

}

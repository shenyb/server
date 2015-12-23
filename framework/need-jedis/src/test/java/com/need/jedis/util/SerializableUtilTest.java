package com.need.jedis.util;


import org.junit.Test;

import com.alibaba.fastjson.JSON;

/**
 * 
 * 
 * @author dell 2014年11月6日 下午9:32:46
 */
public class SerializableUtilTest {
 
//  @Test
//   public void test_ob(){
//      User user=new User();
//      user.setCode("david");
//      user.setName("谭德伟");
//      
//      String result=SerializableUtil.writeToString(user);
//      System.out.println(result);
//  }
    
  @Test
  public void test_obBytes()throws Exception{
     User user=new User();
     user.setCode("david");
     user.setName("谭德伟");
     
     byte[] result=SerializableUtil.writeObj(user);
     for(byte b:result){
     System.out.println(b);
     }
     
     String result2=JSON.toJSONString(user);
     System.out.println(result2);
 }
    
    
}   


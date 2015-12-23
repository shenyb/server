package com.need.biz.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * <p></p>
 * @author david.tan 2015年8月6日 下午7:03:27
 * @ClassName MD5UtilTest
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月6日
 * @modify by reason:{方法名}:{原因}
 */
public class MD5UtilTest {

   @Test
   public void testMD5(){
	  String orgin="david";
	  String md5Str=MD5Util.MD5Encode(orgin);
	  
	  Assert.assertNotNull(md5Str);
	  System.out.println(md5Str);
   }
	

}



package com.need.test.user;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p></p>
 * @author Rylan 2015年7月25日 下午1:03:33
 * @ClassName TestUserInfo
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
public class TestUserInfo {

	
	private ApplicationContext context;
	
	
	@Before
	public void beforeDeal(){
		context=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
//	
//	
//	@Test
//	public void testGetUserInfo(){
//		UserService  userService=(UserService) context.getBean("userServiceImpl");
//		UserInfoParam userInfoParam=new UserInfoParam();	
//		userInfoParam.setPageSize(3);
//		List<UserBasePO>  userBaseList=userService.getUserBaseList(userInfoParam);
//		  for (UserBasePO userBase : userBaseList) {
//				 System.out.println(userBase);
//			 }
//		
//		
//	}
//	
	
}

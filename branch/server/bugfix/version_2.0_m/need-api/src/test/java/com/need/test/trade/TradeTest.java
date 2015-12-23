package com.need.test.trade;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TradeTest {
	
   private ApplicationContext context;
	
	
	@Before
	public void beforeDeal(){
		context=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	
	
//	@Test
//	public void testLogistics(){
//	 TradeBaseDAO tradeBaseService=(TradeBaseDAO) context.getBean("tradeBaseDAO");
//	 List<TradeBaseResult> baseList= tradeBaseService.getTradeBaseByTradeNo("201508101208826cf02ba9338","201508131107936daffac60cda");
//	 for (TradeBaseResult tradeBaseResult : baseList) {
//		System.out.println(tradeBaseResult);
//	 }
//	}
//	
//	@Test
//	public void testGetTradeTotalPrice(){
//		TradeBaseService  tradeBaseService=(TradeBaseService) context.getBean("tradeBaseServiceImpl");
//		System.out.println(tradeBaseService.getTradeTotalPrice("201508181537b25b0f1eb40e8"));
//		
//	}
	
	
	
	
}

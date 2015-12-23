package com.need.biz.utils;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * <p>
 * </p>
 * 
 * @author david.tan 2015年8月7日 上午11:26:20
 * @ClassName BizCodeUtil
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
public class BizCodeUtilTest {

	@Test
	public void test_getUserId() {
		String loginName = "david";
		String userId = BizCodeUtil.generateUserId(loginName);

		Assert.assertNotNull(userId);
		System.out.println(userId);
	}

	@Test
	public void test_getTradeNo() {
		String loginName = "david";
		String goodsName = "LV包包";

		String userId = BizCodeUtil.generateUserId(loginName);
		String tradeNo = BizCodeUtil.generateTradeNo(goodsName, userId);

		Assert.assertNotNull(tradeNo);
		System.out.println(tradeNo);
	}

	@Test
	public void test_generateUserToken() {
		String loginName = "david";
		String userId = BizCodeUtil.generateUserId(loginName);
		Assert.assertNotNull(userId);
		System.out.println(userId); 

		String userToken = BizCodeUtil.generateUserToken(userId);
		Assert.assertNotNull(userToken);

		System.out.println(userToken);

		String fetchUserId = BizCodeUtil.getUserIdByToken(userToken);
		System.out.println(fetchUserId);

		Assert.assertEquals(userId, fetchUserId);
	}
	
	
	@Test
	public void test_generateCartId(){
		String loginName = "david";
		String userId = BizCodeUtil.generateUserId(loginName);
		
		String goodsName = "LV包包";
		String goodsId=BizCodeUtil.generateGoodsId(goodsName);
		
		String cartId=BizCodeUtil.generateCartId(goodsId, userId);
		Assert.assertNotNull(cartId);

		System.out.println("cartId-------"+cartId);
		
	}
	
	
	@Test
	public void test_generatePopularityId(){
		
		String goodsName = "LV包包";
		String goodsId=BizCodeUtil.generateGoodsId(goodsName);
		
		String popularityId=BizCodeUtil.generatePopularityId(goodsId);
		Assert.assertNotNull(popularityId);

		System.out.println("popularityId-------"+popularityId);
		
	}
	@Test
	public void test_generateSelectionId(){
		String goodsName = "LV包包";
		String goodsId=BizCodeUtil.generateGoodsId(goodsName);
		
		String selectioId=BizCodeUtil.generateSelectionId(goodsId);
		Assert.assertNotNull(selectioId);

		System.out.println("selectioId-------"+selectioId);
		
	}

	@Test
	public void test_generateUserOrderNo(){
		String loginName = "david";
		String userId = BizCodeUtil.generateUserId(loginName);
		String goodsName = "LV包包";
		String goodsId=BizCodeUtil.generateGoodsId(goodsName);
//		System.out.println(userId);
		
		String userOrderNo=BizCodeUtil.generateUserOrderNo(userId,goodsId);
//		Assert.assertNotNull(userOrderNo);
		System.out.println("userOrderNo-------"+userOrderNo);
		
	}
	 
	@Test
	public void test_generateCouponNo(){
	  String mobile="13810462060";
	  String couponTemplateNo="1011001";
	   for(int i=0;i<1000;i++){
		   String couponNo=BizCodeUtil.generateCouponNo(mobile, couponTemplateNo);
		   System.out.println(couponNo);
	   }
		
	}
	
	@Test
	public void test_generateAccountId(){
		String loginName = "david";
		String userId = BizCodeUtil.generateUserId(loginName);
		
		String accountId=BizCodeUtil.generateAccountId(userId);
		Assert.assertNotNull(accountId);
		
	}
	
	
	
}

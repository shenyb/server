/**
 * @ProjectName: need-bops
 * @Copyright: 2011 by Shenzhen Tianyuan DIC Information Technology co.,ltd.
 * @address: http://www.edsport.cn
 * @Description: 本内容仅限于万驰体育文化发展有限公司内部使用，禁止转发.
 * @author shenyb
 * @date: 2015年7月25日 上午2:13:28
 * @Title: UUIDUtils.java
 * @Package com.need.api.web.controller.user
 * @Description: TODO
 */
package com.need.utils;

import java.util.UUID;

/**
 * <p>
 * </p>
 * 
 * @author shenyb 2015年7月25日 上午12:13:28
 * @ClassName UUIDUtils
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
public class UUIDUtils {
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉"-"符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	public static void main(String[] args) {
		System.out.println(UUIDUtils.getUUID());
	}
}

/**
 * @ProjectName: need-integration
 * @Copyright: 2015 by Beijin Weplanter Technology co.,ltd.
 * @address: http://www.weplanter.com
 * @Description: 本内容仅限于稻田(北京)科技有限公司内部使用，禁止转发.
 * @author LV
 * @date: 2015年10月23日 下午6:58:13
 * @Title: Utils.java
 * @Package com.need.integration.common.birdex
 * @Description: TODO
 */
package com.need.integration.common.birdex;



import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


/**
 * <p>
 * </p>
 * 
 * @author LV 2015年10月23日 下午6:58:13
 * @ClassName Utils
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LV 2015年10月23日
 * @modify by reason:{方法名}:{原因}
 */
public class Utils {

	public static String getSignature(String sourceData,String secretKey) throws Exception{
	    byte[] keyBytes = secretKey.getBytes();
	    SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
	    Mac mac = Mac.getInstance("HmacSHA1");
	    mac.init(signingKey);
	    byte[] rawHmac = mac.doFinal(sourceData.getBytes("UTF8"));
	    String result = Base64.encodeBase64URLSafeString(rawHmac);
	    return result;
	}
}

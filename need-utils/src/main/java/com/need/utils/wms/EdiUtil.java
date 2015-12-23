package com.need.utils.wms;

public class EdiUtil {
	/**
	 * MD5加密
	 * @param orgString
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static String md5Encrypt(String orgString) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(orgString.getBytes());
			byte[] b = md.digest();
			return byte2hex(b);
		} catch (java.security.NoSuchAlgorithmException ne) {
			throw new IllegalStateException("System doesn't support your  Algorithm.");
		}
	}
		/**
		 * 将字节数组转换成16进制字符串
		 * @param b
		 * @return
		 */
		private static String byte2hex(byte[] b) // 二行制转字符串

		{
			String hs = "";
			String stmp = "";
			for (int n = 0; n < b.length; n++) {
				stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
				if (stmp.length() == 1)
					hs = hs + "0" + stmp;
				else
					hs = hs + stmp;
			}
			return hs;
		}
}

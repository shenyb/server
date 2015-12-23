package com.need.framework.utils;

import java.io.FileInputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * $Header: /server/api/protocol/com.joyworks.utils.MD5Util .java,lijie Exp $
 * $Version: 1.0 $ $Date: 2014/08/21 $
 * </p>
 */
public class MD5Util {

	public static final String SPLIT_SIGN = "|";

	/**
	 * 用来将字节转换成 16 进制表示的字符
	 */
	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 获取MD5值
	 * 
	 * @param strSource
	 *            需要加密的字符
	 * @return MD5值
	 */
	public static String getMD5(String strSource) {
		String md5Value = null;
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(strSource.getBytes());
			byte tmp[] = md.digest();// MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16
											// 进制需要 32 个字符
			int k = 0;// 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) {// 从第一个字节开始，对 MD5 的每一个字节转换成 16 进制字符的转换
				byte byte0 = tmp[i];// 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,>>>
															// 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf];// 取字节中低 4 位的数字转换
			}
			md5Value = new String(str);// 换后的结果转换为字符串
		} catch (Exception e) {
			System.out.println("MD5 Exception:" + e.getMessage());
		}
		return md5Value;
	}
	
	/**
     * 获取唯一随机数
     * 
     * @param strSource
     *            需要加密的字符
     * @return MD5值
     */
    public static String getRadom() {
        String random = null;
        try {
            random = UUID.randomUUID().toString();
        } catch (Exception e) {
            System.out.println("UUID.randomUUID() Exception:" + e.getMessage());
        }
        return random;
    }

	/**
	 * 通过文件生成
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileMD5(String fileName) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fileName);
			return streamToMD5(inputStream);
		} catch (Exception e) {
			return null;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 生成七牛的key
	 * 
	 * @param bookId
	 * @param chapterId
	 * @param pageId
	 * @return
	 */
	public static String generateKey(String bookId, String chapterId,
			String pageId) {
		if (StringUtils.isBlank(bookId) || StringUtils.isBlank(pageId)
				|| StringUtils.isBlank(chapterId)) {
			return null;
		}
		String encyptCode = bookId + SPLIT_SIGN + chapterId + SPLIT_SIGN
				+ pageId;
		return getMD5(encyptCode);
	}
	
	/**
     * 根据参数每次生成唯一的七牛key
     * 
     * @param bookId
     * @param chapterId
     * @param pageId
     * @return
     */
    public static String generateRandomKey() {
        return getRadom();
    }

	
	public static String generateFileMd5(String filePath){
		if(StringUtils.isBlank(filePath)){
			return null;
		}
		return getFileMD5(filePath);
	}
	
	public static void main(String[] args) {
		System.out.println(generateFileMd5("E:\\scing\\53a0fab2abdfb3564b001cb4\\23\\11.jpg"));
		//System.out.println(generateKey("52fb7c2b4e42f44e9d00f6b8", "1", "1.jpg"));
	}
	/**
	 * 通过流生成
	 * 
	 * @param inputStream
	 * @return
	 */
	public static String streamToMD5(InputStream inputStream) {
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[1024];
			int numRead = 0;
			while ((numRead = inputStream.read(buffer)) > 0) {
				mdTemp.update(buffer, 0, numRead);
			}
			return toHexString(mdTemp.digest());
		}catch (RuntimeException runtimeException){
		    
		    return null;
		}catch (Exception e) {
			return null;
		}
	}

	private static String toHexString(byte[] md) {
		int j = md.length;
		char str[] = new char[j * 2];
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[2 * i] = hexDigits[byte0 >>> 4 & 0xf];
			str[i * 2 + 1] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}
}

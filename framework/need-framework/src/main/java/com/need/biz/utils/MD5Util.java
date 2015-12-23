/**
 * @ProjectName: need-framework
 * @Copyright: 2015 by Beijin Weplanter Technology co.,ltd.
 * @address: http://www.weplanter.com
 * @Description: 本内容仅限于稻田(北京)科技有限公司内部使用，禁止转发.
 * @author david.tan
 * @date: 2015年8月6日 下午5:51:39
 * @Title: Md5Util.java
 * @Package com.need.biz.utils
 * @Description: TODO
 */
package com.need.biz.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import org.apache.commons.lang.StringUtils;

/**
 * <p></p>
 * @author david.tan 2015年8月6日 下午5:51:39
 * @ClassName Md5Util
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月6日
 * @modify by reason:{方法名}:{原因}
 */
public class MD5Util {
    
    private MD5Util() {
    }
 
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
 
    /**
     * 用来将字节转换成 16 进制表示的字符
     */
    private static final char hexDigitChars[] = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    
    /**
     * 转换字节数组为16进制字串
     * 
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));//若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
            //resultSb.append(byteToNumString(b[i]));//使用本函数则返回加密结果的10进制数字字串，即全数字形式
        }
        return resultSb.toString();
    }
 
    private static String byteToNumString(byte b) {
 
        int _b = b;
        if (_b < 0) {
            _b = 256 + _b;
        }
        return String.valueOf(_b);
    }
 
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
 
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }
 
    /**
     * 进行md5字符中的比较
     * @param md5a String
     * @param md5b String
     * @return boolean
     */
    public static boolean compareMD5(String md5a, String md5b) {
        boolean flag = false;
        if (md5a == md5b) {
            return true;
        }
        try {
            java.security.MessageDigest alga = java.security.MessageDigest.getInstance("MD5");
            flag = alga.isEqual(md5a.getBytes(), md5b.getBytes());
        } catch (Exception ex) {
        }
        return flag;
    }
    
    public static String generateFileMd5(String filePath){
        if(StringUtils.isBlank(filePath)){
            return null;
        }
        return getFileMD5(filePath);
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
            str[2 * i] = hexDigitChars[byte0 >>> 4 & 0xf];
            str[i * 2 + 1] = hexDigitChars[byte0 & 0xf];
        }
        return new String(str);
    }
}


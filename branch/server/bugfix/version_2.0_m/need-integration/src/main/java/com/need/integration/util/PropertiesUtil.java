package com.need.integration.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

/**
 * <p></p>
 * @author Rylan 2015年7月25日 下午3:47:59
 * @ClassName PropertiesUtil
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
public class PropertiesUtil {
    
    private static final Map<String, String> PROPERTIES = new HashMap<String, String>();
    
	/**
	 * @author Rylan 2015年6月4日 下午2:33:23
	 * @Method: getProperty 
	 * @Description: TODO
	 * @param proFileName  根据配置文件proFileName和key值获取value
	 * @param key
	 * @return 
	 * @throws
	 */
	 public static String getProperty(String proFileName, String key) {
        String mapKey = proFileName.concat(key);
        if(PROPERTIES.containsKey(mapKey)) {
            return PROPERTIES.get(mapKey);
        }
        Properties props = new Properties();
        try {
            props.load(PropertiesUtil.class.getResourceAsStream(proFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = (String) props.get(key);
        if(StringUtils.isBlank(result)) {
            LoggerFactory.getLogger(PropertiesUtil.class).error("property not exist which key is " + key);
            return result;
        }
        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            PROPERTIES.put(proFileName + entry.getKey(), entry.getValue() + "");
        }
        return result;
    }
	 

	 /**
	  * @author Rylan 2015年6月4日 下午2:33:27
	  * @Method: setProperty 
	  * @Description: TODO   根据配置文件proFileName和key值value值
	  * @param proFileName 
	  * @param key
	  * @param value 
	  * @throws
	  */
	public static void setProperty(String proFileName, String key,String value) {
		Properties props = new Properties();
		OutputStream os = null;
		try {
			String classRootPath = PropertiesUtil.class.getResource("/")
					.toString();
			if ("Windows".indexOf(System.getProperty("os.name")) != -1)
				classRootPath = classRootPath.replace("file:/", "");
			else
				classRootPath = classRootPath.replace("file:", "");

			props.load(PropertiesUtil.class.getResourceAsStream( proFileName));
			os = new FileOutputStream(classRootPath + proFileName);
			props.put(key, value);
			props.store(os, "");
            PROPERTIES.put(proFileName + key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null)
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	} 
}

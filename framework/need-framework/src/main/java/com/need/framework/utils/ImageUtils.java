package com.need.framework.utils;

/**
 * <p>图片分割工具</p>
 * @author Rylan 2015年8月25日 下午4:12:57
 * @ClassName ImageUtils
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月25日
 * @modify by reason:{方法名}:{原因}
 */
public class ImageUtils {
	
	 private static final String splitSign=",";
	
	 /**
	  * @author Rylan 2015年8月25日 下午4:14:43
	  * @Method: imageSpilt 
	  * @Description: 图片分割成数组
	  * @param imageKeys
	  * @return 
	  * @throws
	  */
	 public static String[]  imageSplit(String imageKeys){
		 if(imageKeys==null){
			 return null;
		 }
		 return imageKeys.split(splitSign);
	 }
	
	 /**
	  * @author Rylan 2015年8月25日 下午4:14:48
	  * @Method: firstImages 
	  * @Description: 取第一张图片地址
	  * @param imageKeys
	  * @return 
	  * @throws
	  */
	 public static String  firstImages(String imageKeys){
		 if(imageKeys==null){
			 return null;
		 }
		 return imageKeys.split(splitSign)[0];
	 }
	
}

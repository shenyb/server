package com.need.share.pub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 * @author Rylan 2015年9月10日 上午11:30:46
 * @ClassName ConfigProperties
 * @Description 读取constants.properties属性文件配置信息
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */

@Component
public class ConstantsProConfig {

	
	@Value("${pic_domain}")
	private String picDomain;
	@Value("${save_path}")
	private String savePath;
	@Value("${visit_path}")
	private String visitPath;
	
	public String getPicDomain() {
		return picDomain;
	}
	public String getSavePath() {
		return savePath;
	}
	
	public String getVisitPath() {
		return visitPath;
	}
	
	@Override
	public String toString() {
		return "ConstantsProConfig [picDomain=" + picDomain + ", savePath=" + savePath + ", visitPath=" + visitPath
				+ "]";
	}
	
	
}

package com.need.operation.pub;

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

	@Value("${userName}")
	private String userName;
	
	@Value("${userPwd}")
	private String userPwd;
	
	@Value("${fileUploadPath}")
	private String fileUploadPath;
	
	@Value("${goodsUploadPath}")
	private String goodsUploadPath;
	
	@Value("${sceneUploadPath}")
	private String sceneUploadPath;
	
	@Value("${topicUploadPath}")
	private String topicUploadPath;
	
	@Value("${uploadFileMaxSize}")
	private String uploadFileMaxSize;
	
	@Value("${pic_domain}")
	private String pic_domain;
	
	@Value("${waitPayToTradeClose}")
	private String waitPayToTradeClose;
	
	@Value("${waitReceiveToTradeSuccess}")
	private String waitReceiveToTradeSuccess;

	@Value("${uploadTemplateUrl}")
	private String uploadShareUrl;
	
	@Value("${templateUploadParam}")
	private String templateUploadParam;
	
	@Value("${templateReturnResult}")
	private String   templateReturnResult;
	
	@Value("${cookisDomain}")
	private String cookisDomain;
	
	@Value("${addTopicURL}")
	private String addTopicURL;
	
	@Value("${editTopicURL}")
	private String editTopicURL;
	@Value("${expDate}")
	private String expDate;
	
	@Value("${httpsSupport}")
	private String httpsSupport;

	@Value("${newEditTopic}")
	private String newEditTopic;
	
	
	
	
	public String getNewEditTopic() {
		return newEditTopic;
	}

	public String getExpDate() {
		return expDate;
	}

	public String getAddTopicURL() {
		return addTopicURL;
	}

	public String getEditTopicURL() {
		return editTopicURL;
	}

	public String getCookisDomain() {
		return cookisDomain;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public String getGoodsUploadPath() {
		return goodsUploadPath;
	}

	public String getSceneUploadPath() {
		return sceneUploadPath;
	}

	public String getTopicUploadPath() {
		return topicUploadPath;
	}

	public String getUploadFileMaxSize() {
		return uploadFileMaxSize;
	}

	public String getPic_domain() {
		return pic_domain;
	}

	public String getWaitPayToTradeClose() {
		return waitPayToTradeClose;
	}

	public String getWaitReceiveToTradeSuccess() {
		return waitReceiveToTradeSuccess;
	}

	

	public String getUploadShareUrl() {
		return uploadShareUrl;
	}

	
	
	public String getTemplateUploadParam() {
		return templateUploadParam;
	}

	public String getTemplateReturnResult() {
		return templateReturnResult;
	}
	
	

	public String getHttpsSupport() {
		return httpsSupport;
	}

	@Override
	public String toString() {
		return "ConstantsProConfig [userName=" + userName + ", userPwd=" + userPwd + ", fileUploadPath="
				+ fileUploadPath + ", goodsUploadPath=" + goodsUploadPath + ", sceneUploadPath=" + sceneUploadPath
				+ ", topicUploadPath=" + topicUploadPath + ", uploadFileMaxSize=" + uploadFileMaxSize + ", pic_domain="
				+ pic_domain + ", waitPayToTradeClose=" + waitPayToTradeClose + ", waitReceiveToTradeSuccess="
				+ waitReceiveToTradeSuccess + "]";
	}
	
	
}

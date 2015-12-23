package com.need.gateway.push;


/**
 * <p>极光推送核心组件</p>
 * @author Rylan 2015年10月22日 下午11:40:09
 * @ClassName JPush
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年10月22日
 * @modify by reason:{方法名}:{原因}
 */
public interface JPushService {
	
	/**
	 * @author Rylan 2015年10月23日 下午5:54:40
	 * @Method: sendMessageToRegistrationIds 
	 * @Description: 发送信息给所有registrationIds设备
	 * @param title
	 * @param msgContent
	 * @param registrationID
	 * @return 
	 * @throws
	 */
	public int sendMessageToRegistrationIds(String title, String msgContent, String... registrationIds);
	
	/**
	 * @author Rylan 2015年10月23日 下午5:54:26
	 * @Method: sendMessageToAll 
	 * @Description: 发送信息给所有设备
	 * @param title
	 * @param msgContent
	 * @return 
	 * @throws
	 */
	public int sendMessageToAll(String title, String msgContent);
	
	/**
	 * @author Rylan 2015年10月23日 下午5:54:06
	 * @Method: sendMessageToAndroids 
	 * @Description: 发送信息给所有android设备
	 * @param title
	 * @param msgContent
	 * @return 
	 * @throws
	 */
	public int sendMessageToAndroids(String title, String msgContent);

	/**
	 * @author Rylan 2015年10月23日 下午5:53:34
	 * @Method: sendMessageToIOS 
	 * @Description: 发送信息给所有IOS设备
	 * @param title
	 * @param msgContent
	 * @return 
	 * @throws
	 */
	public int sendMessageToIOS(String title, String msgContent);

	/**
	 * @author Rylan 2015年10月23日 下午5:53:09
	 * @Method: sendMessageWithTagToAll 
	 * @Description: 发送给所有的tag 标记下的设备
	 * @param tag
	 * @param title
	 * @param msgContent
	 * @return 
	 * @throws
	 */
	public int sendMessageWithTagToAll(String title, String msgContent,String... tags);
	
	
	
}

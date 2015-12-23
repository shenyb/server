package com.need.facade.user;



/**
 * <p></p>
 * 
 * @author Rylan 2015年7月25日 上午11:23:24
 * @ClassName UserService
 * @Description TODO 读写分离方式使用
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
public interface UserService {

	/**
	 * @author Rylan 2015年8月26日 下午8:54:13
	 * @Method: sendGeneralValidateCode 
	 * @Description: TODO 发送验证码短信
	 * @param mobile
	 * @return 
	 * @throws
	 */
	int sendGeneralValidateCode(String mobile);
	
}

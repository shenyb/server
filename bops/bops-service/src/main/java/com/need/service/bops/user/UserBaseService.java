package com.need.service.bops.user;

import com.need.domain.po.api.user.UserBase;
import com.need.domain.pub.Message;

/**
 * <p></p>
 * @author Rylan 2015年8月12日 下午9:12:32
 * @ClassName UserBaseService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月12日
 * @modify by reason:{方法名}:{原因}
 */
public interface UserBaseService {

	public UserBase getUserBaseByMobile(String mobile);
	/**
	 * 
	 * @author peiboli 2015年12月3日 下午4:53:42
	 * @Method: changeUserStatus 
	 * @Description: TODO用于对用户的操作，禁止登陆，禁言等
	 * @param userId
	 * @param userStatus
	 * @return 
	 * @throws
	 */
	public Message  changeUserStatus(String userId,String userStatus);
	/**
	 * 
	 * @author peiboli 2015年12月18日 下午12:06:43
	 * @Method: delFeed 
	 * @Description: TODO清feed根据feedId
	 * @param feedId
	 * @return 
	 * @throws
	 */
	public Message delFeed(String feedId);
}

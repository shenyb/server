package com.need.dao.api.user;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.api.user.UserBase;

/**
 * <p></p>
 * @author Rylan 2015年7月25日 上午11:51:28
 * @ClassName UserBaseDAO
 * @Description UserBase数据访问层 ,此处注意一定不能使用select * .mysql 的sql语句要用小写
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
public interface UserBaseDAO {

	int deleteByPrimaryKey(String userId);
	
	int insert(UserBase user);

	int insertSelective(UserBase record);

	UserBase selectByPrimaryKey(@Param("userId") String userId);

	int updateByPrimaryKeySelective(UserBase record);

	int updateByPrimaryKey(UserBase record);

	int selectByNickName( String nickName);

	int selectUserBaseCount();
	
	UserBase selectUserBaseByMobile(String mobile);
    
    UserBase selectUserBaseByNickName(String nickName);

	/**
	 * 
	 * @author peiboli 2015年12月3日 下午4:27:52
	 * @Method: updateDenyLogin 
	 * @Description: TODO用户禁止登陆
	 * @param userId
	 * @return 
	 * @throws
	 */
	int updateUserStatus(@Param("userId") String userId,@Param("userStatus") String userStatus);
}
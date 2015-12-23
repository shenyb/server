package com.need.marketing.dao.jdbc.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.marketing.dao.jdbc.user.po.UserBasePO;





/**
 * <p>
 * </p>
 * 
 * @author Rylan 2015年7月25日 上午11:51:28
 * @ClassName UserBaseDAO
 * @Description UserBase数据访问层 ,此处注意一定不能使用select * .mysql 的sql语句要用小写
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
public interface UserBaseDAO {

	/**
	 * @author Rylan 2015年7月25日 上午11:52:50 @Method:
	 *         selectByPrimaryKey @Description: 根据主键查询单个对象 @param
	 *         userId @return @throws
	 *     mapper
	 */
	UserBasePO selectByPrimaryKey(@Param("userId")String userId);


	/**
	 * @author shenyb 2015年7月25日 上午3:25:09 @Method:
	 *         selectByMobileAndLoginPwd @Description: 根据手机号和登录密码查询 @param
	 *         user @throws
	 * mapper
	 */
	List<UserBasePO> selectByMobile(@Param("mobile")String mobile);


	UserBasePO getuserInfo(Integer cheapOpenId);

}
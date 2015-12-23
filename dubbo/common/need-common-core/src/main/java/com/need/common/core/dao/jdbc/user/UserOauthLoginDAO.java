package com.need.common.core.dao.jdbc.user;

import com.need.common.domain.po.api.user.UserOauthLogin;
import org.apache.ibatis.annotations.Param;

public interface UserOauthLoginDAO {
    int deleteByPrimaryKey(String userId);


    int insertSelective(UserOauthLogin record);

    UserOauthLogin selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserOauthLogin record);

    int updateByPrimaryKey(UserOauthLogin record);
    /**
     * 
     * @author peiboli 2015年11月26日 上午11:29:45
     * @Method: getOauthInfo 
     * @Description: TODO通过手机号获取用户的授权信息。
     * @param mobile
     * @return 
     * @throws
     */
	UserOauthLogin getOauthInfo(String userId);

    /**
     * 
     * @author peiboli 2015年11月26日 下午2:09:51
     * @Method: insertWeixin 
     * @Description: TODO如果该用户没有绑定过任何第三方，就插入
     * @param userId
     * @param snsId
     * @return 
     * @throws
     */
	int insertWeixin(@Param(value="userId") String userId,@Param(value="snsId") String snsId);

	/**
     * 
     * @author peiboli 2015年11月26日 下午2:09:51
     * @Method: insertWeixin 
     * @Description: TODO如果该用户没有绑定过任何第三方，就插入
     * @param userId
     * @param snsId
     * @return 
     * @throws
     */
	int insertWeibo(@Param(value="userId") String userId,@Param(value="snsId") String snsId);

	/**
     * 
     * @author peiboli 2015年11月26日 下午2:09:51
     * @Method: insertWeixin 
     * @Description: TODO如果该用户没有绑定过任何第三方，就插入
     * @param userId
     * @param snsId
     * @return 
     * @throws
     */
	int insertQq(@Param(value="userId") String userId,@Param(value="snsId") String snsId);


	/**
	 * 
	 * @author peiboli 2015年11月26日 下午2:10:45
	 * @Method: updateWeixin 
	 * @Description: TODO如果该用户绑定过第三方，就执行更新操作
	 * @param userId
	 * @param snsId
	 * @return 
	 * @throws
	 */
	int updateWeixin(@Param(value="userId") String userId,@Param(value="snsId") String snsId);

	/**
	 * 
	 * @author peiboli 2015年11月26日 下午2:10:45
	 * @Method: updateWeixin 
	 * @Description: TODO如果该用户绑定过第三方，就执行更新操作
	 * @param userId
	 * @param snsId
	 * @return 
	 * @throws
	 */
	int updateWeibo(@Param(value="userId") String userId,@Param(value="snsId") String snsId);

	/**
	 * 
	 * @author peiboli 2015年11月26日 下午2:10:45
	 * @Method: updateWeixin 
	 * @Description: TODO如果该用户绑定过第三方，就执行更新操作
	 * @param userId
	 * @param snsId
	 * @return 
	 * @throws
	 */
	int updateQq(@Param(value="userId") String userId,@Param(value="snsId") String snsId);

    /**
     * 
     * @author peiboli 2015年11月26日 下午4:08:55
     * @Method: updateUnBoundWeixin 
     * @Description: TODO解除绑定微信
     * @param userId
     * @param snsId
     * @return 
     * @throws
     */
	int updateUnBoundWeixin(String userId);

	/**
	 * 
	 * @author peiboli 2015年11月26日 下午4:09:16
	 * @Method: updateUnBoundWeibo 
	 * @Description: TODO解除绑定微博
	 * @param userId
	 * @param snsId
	 * @return 
	 * @throws
	 */
	int updateUnBoundWeibo(String userId);

	/**
	 * 
	 * @author peiboli 2015年11月26日 下午4:09:34
	 * @Method: updateUnBoundQq 
	 * @Description: TODO解除绑定qq
	 * @param userId
	 * @param snsId
	 * @return 
	 * @throws
	 */
	int updateUnBoundQq(String userId);


	/**
	 * 
	 * @author peiboli 2015年12月12日 下午7:02:24
	 * @Method: getCountByWeiXinId 
	 * @Description: TODO判断该微信id是否绑定过Need账号
	 * @param snsId
	 * @return 
	 * @throws
	 */
	int getCountByWeiXinId(String snsId);
	/**
	 * 
	 * @author peiboli 2015年12月12日 下午7:02:24
	 * @Method: getCountByWeiXinId 
	 * @Description: TODO判断该微博id是否绑定过Need账号
	 * @param snsId
	 * @return 
	 * @throws
	 */

	int getCountByWeiBoId(String snsId);

	/**
	 * 
	 * @author peiboli 2015年12月12日 下午7:02:24
	 * @Method: getCountByWeiXinId 
	 * @Description: TODO判断该qqid是否绑定过Need账号
	 * @param snsId
	 * @return 
	 * @throws
	 */
	int getCountByQQId(String snsId);
}
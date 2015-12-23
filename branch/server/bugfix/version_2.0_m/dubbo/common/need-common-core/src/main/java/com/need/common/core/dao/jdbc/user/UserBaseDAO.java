package com.need.common.core.dao.jdbc.user;

import com.need.common.domain.po.api.kol.KolCategoryPO;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.vo.user.*;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
	 * @author Rylan 2015年7月25日 上午11:51:34 @Method:
	 *         deleteByPrimaryKey @Description: 根据id删除用户信息方法 @param
	 *         userId @return @throws
	 */
	int deleteByPrimaryKey(String userId);

	/**
	 * 
	 * @author shenyb 2015年7月25日 上午4:03:49 @Method:
	 *         insert @Description:插入一条数据 @param user @throws
	 * mapper
	 */
	int insert(UserBasePO user);


	/**
	 * @author Rylan 2015年7月25日 上午11:52:50 @Method:
	 *         selectByPrimaryKey @Description: 根据主键查询单个对象 @param
	 *         userId @return @throws
	 *     mapper
	 */
	UserBasePO selectByPrimaryKey(@Param("userId")String userId);

	/**
	 * @author Rylan 2015年7月25日 上午11:52:56 @Method:
	 *         selectAllUserBase @Description: 分页查询 @return @throws
	 *  mapper
	 */
	List<UserBasePO> selectAllUserBase(UserInfoParam userInfoParam);


	/**
	 * @author shenyb 2015年7月25日 上午3:25:09 @Method:
	 *         selectByMobileAndLoginPwd @Description: 根据手机号和登录密码查询 @param
	 *         user @throws
	 * mapper
	 */
	List<UserBasePO> selectByMobile(@Param("mobile")String mobile);

	/**
	 * @author shenyb 2015年7月25日 上午11:00:09 @Method:
	 *         selectByNickName @Description: 根据昵称查询用户数 @param
	 *         nickName @return @throws
	 * mapper
	 */
	int selectByNickName(@Param("nickName")String nickName);

	/**
	 * @author shenyb 2015年7月25日 上午11:28:30 @Method:
	 *         updateByPrimaryKey @Description: 根据主键更新 @param userId @param
	 *         nickName @throws
	 * mapper
	 */
	void updateByPrimaryKey(UserBasePO user);

	/**
	 * @author Rylan 2015年7月25日 下午12:59:53 @Method:
	 *         selectUserBaseCount @Description: 查询总纪录数 @return @throws
	 *  mapper
	 */
	int selectUserBaseCount();

	/**
	 * 
	 * @author peiboli 2015年7月26日 上午10:59:18 @Method: getUserInfo @Description:获取行家信息
	 * @param userId
	 * 			@return @throws
	 * mapper
	 */
	KolInfoVo getKolInfo(@Param("userId")String userId);

	/**
	 * 
	 * @author peiboli 2015年7月26日 下午4:36:24 @Method: getUserType @Description:
	 *         获取用户类型 @param userId @return @throws
	 * mapper
	 */
	String getUserType(@Param("userId")String userId);

	/**
	 * 
	 * @author peiboli 2015年7月26日 下午4:36:43 @Method: getUserInfo @Description:
	 *         获取用户信息 @param userId @return @throws
	 *  mapper
	 */
	UserBasePO getUserInfo(@Param("userId")String userId);

	/**
	 * 
	 * @author xiehao 2015年7月26日 上午11:41:58 @Method:
	 *         updateLoginPwd @Description: 更新用户的密码 @param userId @param
	 *         newLoginPwd @return @throws
	 * mapper
	 */
	void updateLoginPwd(@Param("mobile")String mobile, @Param("newLoginPwd")String  newLoginPwd);
	/**
	 * @author Rylan 2015年8月9日 下午6:59:53
	 * @Method: updateLoginPwdByUserId 
	 * @Description: TODO 根据用户id修改用户登录密码
	 * @param mobile
	 * @param newLoginPwd 
	 * @throws
	 */
	void updateLoginPwdByUserId(@Param("userId")String userId,@Param("newLoginPwd")String  newLoginPwd);

	/**
	 * 
	 * @author xiehao 2015年7月26日 下午4:58:29 @Method:
	 *         findPwdToUpdateLoginPwd @Description: 用户校验过手机验证码之后更新登录密码 @param
	 *         mobile @param loginPwd @throws
	 * mapper
	 */
	int updatePwdToUpdateLoginPwd(@Param("mobile")String mobile, @Param("loginPwd")String loginPwd);

	/**
	 * 
	 * @author xiehao 2015年7月26日 下午5:30:48 @Method: mobileIsExist @Description:
	 *         查找这个手机号是否存在 @param mobile @return @throws
	 * mapper
	 */
	int mobileIsExist(@Param("mobile")String mobile);

	/**
	 * @author shenyb 2015年7月29日 下午4:24:11
	 * @Method: userIdExists 
	 * @Description: 使用userid查询用户数
	 * @param userId
	 * @return 
	 * @throws 
	 * mapper_api
	 */
	int userIdExists(@Param("userId")String userId);

	void updateNickName(UserBasePO user);

	List<KolInfoVo> selectFocusKolList(String userId);

	List<KolVo> queryKolList(String kolCategoryId);

	List<KolCategoryPO> querycategoryNameList(String userId);

	void updateProfileKey(@Param("userId")String userId, @Param("newProfileKey")String newProfileKey);

	List<FansInfoVO> queryFans(String userId);

	/**
	 * 
	 * @author peiboli 2015年11月25日 上午10:37:26
	 * @Method: getUserInfoByMobile 
	 * @Description: TODO根据手机号获取用户信息
	 * @param mobile
	 * @return 
	 * @throws
	 */
	UserBasePO getUserInfoByMobile(String mobile);

	/**
	 * 
	 * @author peiboli 2015年11月25日 下午6:16:51
	 * @Method: getWeiboLoginInfoBySNSId 
	 * @Description: TODO获取微博的loginInfo
	 * @param snsId
	 * @return 
	 * @throws
	 */
	UserInfoVO getWeiboLoginInfoBySNSId(String snsId);
	/**
	 * 
	 * @author peiboli 2015年11月25日 下午6:16:51
	 * @Method: getQQLoginInfoBySNSId 
	 * @Description: TODO获取微博的loginInfo
	 * @param snsId
	 * @return 
	 * @throws
	 */
	UserInfoVO getQQLoginInfoBySNSId(String snsId);
	/**
	 * 
	 * @author peiboli 2015年11月25日 下午6:16:51
	 * @Method: getWeixinLoginInfoBySNSId 
	 * @Description: TODO获取微博的loginInfo
	 * @param snsId
	 * @return 
	 * @throws
	 */
	UserInfoVO getWeixinLoginInfoBySNSId(String snsId);

	/**
	 * 
	 * @author peiboli 2015年11月26日 下午6:49:46
	 * @Method: queryByPhones 
	 * @Description: TODO根据客户端传过来的手机号，获取用户列表
	 * @param phones
	 * @return 
	 * @throws
	 */
	List<ContactsBookUserInfoVO> queryByPhones(@Param("mobiles") Collection<String> mobiles);

	/**
	 * 
	 * @author 庆凯 2015年11月26日 下午6:49:46
	 * @Method: queryAllMobiles 
	 * @Description: 列出所有用户的id和对应的手机号
	 * @return 
	 */
    public List<Map<String, Object>> queryAllMobiles();

	int updateUserSut(@Param("userId")String userId, @Param("userSut")String userSut);

	UserBasePO selectBysut(String userSut);

	/**
	 * 
	 * @author peiboli 2015年12月12日 下午4:32:54
	 * @Method: deleteUserOpenIdByOpenId 
	 * @Description: TODO清除该openId对应用户的openId
	 * @param openId 
	 * @throws
	 */
	void deleteUserOpenIdByOpenId(String openId);

	/**
	 * 
	 * @author peiboli 2015年12月12日 下午4:33:26
	 * @Method: updateUserOpenId 
	 * @Description: TODO添加openId到现在登录的用户
	 * @param openId 
	 * @throws
	 */
	void updateUserOpenId(@Param("userId")String userId,@Param("openId")String openId);

	/**
	 * 
	 * @author peiboli 2015年12月12日 下午4:50:44
	 * @Method: getWapLoginInfoByOpenId 
	 * @Description: TODO根据Wap站的openID获取用户信息
	 * @param openId
	 * @return 
	 * @throws
	 */
	UserInfoVO getWapLoginInfoByOpenId(String openId);

}
package com.need.common.core.service.user;

import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.*;

import java.util.List;

/**
 * <p>
 * </p>
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

	int insert(UserBasePO user);
	Boolean insert(String nickName,String mobile,String loginPwd,String profilePicKey);

	/**
	 * @author Rylan 2015年10月22日 下午11:30:35
	 * @Method: insert_1_3 
	 * @Description: 用户新增1.3接口方法
	 * @param user
	 * @return 
	 * @throws
	 */
	public Boolean insert_1_3(UserBasePO user) ;
	
	public Boolean insert_2_0(UserBasePO user,String channel) ;
	
	int login(String mobile, String loginPwd);

	Boolean checkNickNameExists(String nickName);

	/**
	 * @author shenyb 2015年7月25日 上午11:25:31 @Method:
	 *         updateNickName @Description: TODO @param userId @param
	 *         nickName @throws
	 */
	void updateNickName(String userId, String nickName);

	/**
	 * @author Rylan 2015年7月25日 下午12:10:42
	 * @Method: getUserBaseList
	 * @Description: TODO
	 * @return @throws
	 */
	public List<UserBasePO> getUserBaseList(UserInfoParam userInfoParam);

	/**
	 * @author Rylan 2015年7月25日 下午12:10:46 @Method: addUserBase @Description:
	 *         TODO @param userInfoVo @return @throws
	 */
	public String addUserBase(UserInfoVO userInfoVo);

	String getUserType(String userId);

	UserBasePO getuserInfo(String userId);

	KolInfoVo getKolInfo(String userId);

    //获取带关注信息的用户信息
    UserFollowVO getuserFollowInfo(String userId, String targetId);

	/**
	 * 
	 * @author xiehao 2015年7月26日 上午11:03:29 @Method: checkLoginPwd @Description:
	 *         TODO 校验用户的密码是否和ID匹配 @return @throws
	 */
	public boolean checkLoginPwd(String userId, String loginPwd);

	/**
	 * 
	 * @author xiehao 2015年7月26日 上午11:29:18 @Method:
	 *         changeLoginPwd @Description: TODO 更新用户的登录密码 @param userId @param
	 *         newLoginPwd @return @throws
	 */
	public void changeLoginPwd(String userId, String newLoginPwd);

	/**
	 * 
	 * @author xiehao 2015年7月26日 下午4:50:23 @Method:
	 *         findLoginPwdToUpdateLoginPwd @Description: TODO
	 *         用户找回密码时更改密码 @param mobile @param loginPwd @throws
	 */
	public int findLoginPwdToUpdateLoginPwd(String mobile, String loginPwd, String validateCode);

	/**
	 * 
	 * @author xiehao 2015年7月29日 下午12:02:34 @Method:
	 *         checkMobileIsExist @Description: TODO 校验用户手机号是否存在且唯一 @param
	 *         mobile @return @throws
	 */
	public int checkMobileIsExist(String mobile);

	/**
	 * 
	 * @author shenyb 2015年7月29日 下午2:50:53 @Method:
	 *         checkMobileExists @Description: @param mobile @return @throws
	 */
	public boolean checkMobileExists(String mobile);

	/**
	 * @author shenyb 2015年7月29日 下午4:23:10 @Method:
	 *         checkUserIdExists @Description: TODO @param
	 *         userId @return @throws
	 */
	boolean checkUserIdExists(String userId);

	/**
	 * 
	 * @author xiehao 2015年7月30日 下午5:05:49 @Method:
	 *         sendValidateCode @Description: TODO 发送注册验证码 @param
	 *         mobile @return @throws
	 */
	int sendRegistValidateCode(String mobile);

	/**
	 * @author shenyb 2015年8月5日 下午3:25:27 @Method:
	 *         getUserByMobileAndLoginPwd @Description: TODO @param
	 *         mobile @param loginPwd @return @throws
	 */

	List<UserBasePO> getUserByMobile(String mobile);

	/**
	 * 
	 * @author peiboli 2015年8月8日 下午7:09:19 @Method: getKolList @Description:
	 *         TODO获取用户关注的行家列表 @param userId @return @throws
	 */
	List<KolInfoVo> getfocusKolList(String userId);

	/**
	 * 
	 * @author peiboli 2015年8月9日 下午3:34:49 @Method:
	 *         getTradeGoodsController @Description: TODO用户获取拥有的商品列表 @param
	 *         userId @return @throws
	 */

	List<KolVo> getKolList(String kolCategoryId);

	String getcategoryNameList(String userId);

	void updateProfileKey(String userId, String newProfileKey);
	
	/**
	 * @author Rylan 2015年8月26日 下午8:54:13
	 * @Method: sendGeneralValidateCode 
	 * @Description: TODO 发送验证码短信
	 * @param mobile
	 * @return 
	 * @throws
	 */
	int sendGeneralValidateCode(String mobile);
	List<FansInfoVO> getfansList(String userId);
	/**
	 * 
	 * @author peiboli 2015年11月25日 上午10:21:28
	 * @Method: loginByCode 
	 * @Description: TODO用户快捷登录返回信息
	 * @param params
	 * @return 
	 * @throws
	 */
	Message insertAndGetUserInfoByCode(LoginVO params);
	/**
	 * 
	 * @author peiboli 2015年11月26日 上午10:31:10
	 * @Method: getUserLoginInfo 
	 * @Description: TODO通过snsId获得用户登录info
	 * @param snsId
	 * @param snsType
	 * @return 
	 * @throws
	 */
	Message getUserLoginInfo(String snsId, String snsType);
	/**
	 * 
	 * @author peiboli 2015年11月26日 上午10:55:27
	 * @Method: boundMobileAndGetLoginInfo 
	 * @Description: TODO用户绑定手机号，并且返回用户登录信息
	 * @param snsId
	 * @param snsType
	 * @param mobile
	 * @param channel
	 * @param validateCode
	 * @return 
	 * @throws
	 */
	Message boundMobileAndGetLoginInfo(String snsId, String snsType, String mobile, String channel,
			String validateCode);
	/**
	 * 
	 * @author peiboli 2015年11月26日 下午4:01:50
	 * @Method: boundOpenAuth 
	 * @Description: TODOapp内绑定第三方账号
	 * @param snsId
	 * @param snsType
	 * @param userId
	 * @return 
	 * @throws
	 */
	Message boundOpenAuth(String snsId, String snsType, String userId);
	/**
	 * 
	 * @author peiboli 2015年11月26日 下午4:01:55
	 * @Method: unBound 
	 * @Description: TODO解除绑定第三方账号
	 * @param snsId
	 * @param snsType
	 * @param userId
	 * @return 
	 * @throws
	 */
	Message unBound(String snsType, String userId);
	/**
	 * 
	 * @author peiboli 2015年11月26日 下午5:07:00
	 * @Method: getBoundList 
	 * @Description: TODO获得用户第三方授权绑定列表
	 * @param userId
	 * @return 
	 * @throws
	 */
	Message getBoundList(String userId);
	/**
	 * 
	 * @author peiboli 2015年11月26日 下午5:33:04
	 * @Method: getcontactsBook 
	 * @Description: TODO获得手机通讯录中的need的用户列表
	 * @param userId
	 * @param mobiles
	 * @return 
	 * @throws
	 */
	Message insertContactsBook(String userId,List<ContactsBookVO> list);
	Message updateUserSut(String userId);
	
	String getUserIdBysut(String sut);
	/**
	 * 
	 * @author peiboli 2015年12月12日 下午3:34:33
	 * @Method: wapInsertAndGetUserInfoByCode 
	 * @Description: TODOwap站登录接口
	 * @param params
	 * @return 
	 * @throws
	 */
	Message wapInsertAndGetUserInfoByCode(LoginVO params);
	Message followSynchronization();
	
	
}

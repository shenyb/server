package com.need.common.core.service.user.impl;

import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.need.NeedKolFansDAO;
import com.need.common.core.dao.jdbc.user.UserBaseDAO;
import com.need.common.core.dao.jdbc.user.UserContactsDAO;
import com.need.common.core.dao.jdbc.user.UserOauthLoginDAO;
import com.need.common.core.dao.jdbc.user.UserRegisterInfoDAO;
import com.need.common.core.pub.ConstantsProConfig;
import com.need.common.core.pub.SMSProConfig;
import com.need.common.core.service.coupon.CouponService;
import com.need.common.core.service.follow.FollowCacheService;
import com.need.common.core.service.follow.FollowService;
import com.need.common.core.service.user.UserCacheService;
import com.need.common.core.service.user.UserService;
import com.need.common.core.utils.ShortUrlUtil;
import com.need.common.domain.enums.RegisterTypeEnum;
import com.need.common.domain.enums.SnsTypeEnum;
import com.need.common.domain.enums.UserStatusEnum;
import com.need.common.domain.enums.UserTypeEnum;
import com.need.common.domain.po.api.kol.KolCategoryPO;
import com.need.common.domain.po.api.need.NeedKolFansPO;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.po.api.user.UserOauthLogin;
import com.need.common.domain.po.api.user.UserRegisterInfoPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.*;
import com.need.gateway.sms.SMSService;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserBaseDAO userDao;

	@Autowired
	private CouponService couponService;

	@Autowired
	private ConcurrentTaskScheduler concurrentTaskScheduler;

	@Autowired
	private SMSService smsService;

	@Autowired
	private SMSProConfig smsProConfig;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	@Autowired
	private UserRegisterInfoDAO userRegisterInfoDAO;
	@Autowired
	private UserOauthLoginDAO userOauthLoginDAO;
	@Autowired
	private FollowCacheService followCacheService;
	@Autowired
	private UserContactsDAO userContactsDAO;

	@Autowired
	private UserCacheService userCacheService;
	@Autowired
	private NeedKolFansDAO needkoldao;
    @Autowired
    private FollowService followService;

	@Transactional
	public Boolean insert(String nickName, String mobile, String loginPwd, String profilePicKey) {
		UserBasePO user = new UserBasePO();
		user.setUserId(BizCodeUtil.generateUserId(nickName));
		user.setNickName(nickName);
		user.setMobile(mobile);
		user.setLoginPwd(loginPwd);
		user.setProfilePicKey(profilePicKey);
		user.setUserStatus(UserStatusEnum.NORMAL.name());
		user.setUserType(UserTypeEnum.COMMON.name());

		int isSuccess = userDao.insert(user);
		if (isSuccess != 0) {
			afterSave(user);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Transactional
	public Boolean insert_1_3(UserBasePO user) {
		user.setUserId(BizCodeUtil.generateUserId(user.getNickName()));
		user.setUserStatus(UserStatusEnum.NORMAL.name());
		user.setUserType(UserTypeEnum.COMMON.name());
		int isSuccess = userDao.insert(user);
		if (isSuccess != 0) {
			afterSave(user);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Transactional
	public Boolean insert_2_0(UserBasePO user, String channel) {
		user.setUserId(BizCodeUtil.generateUserId(user.getNickName()));
		user.setUserStatus(UserStatusEnum.NORMAL.name());
		user.setUserType(UserTypeEnum.COMMON.name());
		int isSuccess = userDao.insert(user);
		if (isSuccess != 0) {
			UserRegisterInfoPO userRegisterInfo = new UserRegisterInfoPO();
			userRegisterInfo.setUserId(user.getUserId());
			userRegisterInfo.setRegisterType(RegisterTypeEnum.NORMAL.code);
			userRegisterInfo.setRegisterChannel(channel);
			userRegisterInfoDAO.insert(userRegisterInfo);
			afterSave(user);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	private void afterSave(final UserBasePO user) {
		// concurrentTaskScheduler.execute(new Runnable() {
		//
		// @Override
		// public void run() {
		couponService.checkRegisterCouponMobile(user.getMobile(), user.getUserId());
		userCacheService.add(user);
		// }
		// });
	}

	/**
	 * 
	 * @author shenyb 2015年8月5日 下午2:59:12
	 * @Method: login
	 * @Description: TODO
	 * @param mobile
	 * @param loginPwd
	 * @return 200 登录成功 USER_MOBILE_NOT_REGISTER 手机号未注册 USER_LOGIN_PWD_WRONG
	 *         密码不正确
	 * @see com.need.api.service.user.UserService#login(java.lang.String,
	 *      java.lang.String)
	 */
	public int login(String mobile, String loginPwd) {
		List<UserBasePO> users = userDao.selectByMobile(mobile);
		if (users == null || users.size() < 1) {
			return Constants.USER_MOBILE_NOT_REGISTER;
		}
		if (!loginPwd.equals(users.get(0).getLoginPwd())) {
			return Constants.USER_LOGIN_PWD_WRONG;
		}
		return Constants.USER_SUCCESS;
	}

	@Override
	public List<UserBasePO> getUserBaseList(UserInfoParam userInfoParam) {
		userInfoParam.setTotalCount(userDao.selectUserBaseCount());// 设置总数
		return userDao.selectAllUserBase(userInfoParam);
	}

	@Override
	public String addUserBase(UserInfoVO userInfoVo) {
		return null;
	}

	/**
	 * @author shenyb 2015年7月25日 上午11:06:07
	 * @Method: checkNickName
	 * @Description: 检验昵称是否存在
	 * @param nickName
	 * @return
	 * @see com.need.api.service.user.UserService#checkNickName(java.lang.String)
	 */
	@Override
	public Boolean checkNickNameExists(String nickName) {
		return userDao.selectByNickName(nickName) > 0;
	}

	/**
	 * @author shenyb 2015年7月25日 上午11:27:19
	 * @Method: updateNickNameByprimaryKey
	 * @Description: 修改昵称
	 * @param userId
	 * @param nickName
	 * @see com.need.api.service.user.UserService#updateNickNameByprimaryKey(java.lang.String,
	 *      java.lang.String)
	 */

	@Override
	@Transactional
	public void updateNickName(String userId, String nickName) {
		UserBasePO user = userDao.selectByPrimaryKey(userId);
		user.setNickName(nickName);
		userDao.updateNickName(user);
		userCacheService.updateById(userId, "nickName", nickName);
	}

	@Override
	public String getUserType(String userId) {
		return userDao.getUserType(userId);
	}

	@Override
	public UserBasePO getuserInfo(String userId) {
		return userDao.getUserInfo(userId);
	}

	@Override
	public KolInfoVo getKolInfo(String userId) {
		return userDao.getKolInfo(userId);
	}

	@Override
	public UserFollowVO getuserFollowInfo(String userId, String targetId) {
		UserBasePO userBasePO = getuserInfo(targetId);
		UserFollowVO userFollowVO = new UserFollowVO();
		BeanUtils.copyProperties(userBasePO, userFollowVO);
		userFollowVO.setFollowCount(followCacheService.getFollowCount(targetId));
		userFollowVO.setFollowedCount(followCacheService.getFollowedCount(targetId));
        Boolean follow = false;
		if (!StringUtil.isBlank(userId)) {
			if (!userId.equals(targetId)) {
				follow = followCacheService.isFollow(userId, targetId);
			}
		}
        userFollowVO.setIsFollow(follow.toString().toUpperCase());
		return userFollowVO;
	}

	/**
	 * 
	 * @author xiehao 2015年7月26日 上午11:12:55
	 * @Method: checkLoginPwd
	 * @Description: TODO 校验用户的ID和
	 * @param userId
	 * @param loginPwd
	 * @return
	 * @see com.need.api.service.user.UserService#checkLoginPwd(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean checkLoginPwd(String userId, String loginPwd) {
		// TODO Auto-generated method stub
		if (!StringUtils.hasText(loginPwd) || !StringUtils.hasText(userId))
			return false;
		UserBasePO user = userDao.selectByPrimaryKey(userId);
		if (user == null) {
			return false;
		} else {
			return user.getLoginPwd().equals(loginPwd);
		}
	}

	/**
	 * 
	 * @author xiehao 2015年7月26日 上午11:31:07
	 * @Method: changeLoginPwd
	 * @Description: TODO 更新用密码
	 * @param userId
	 * @param newLoginPwd
	 * @return
	 * @see com.need.api.service.user.UserService#changeLoginPwd(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	@Transactional
	public void changeLoginPwd(String userId, String newLoginPwd) {
		// TODO Auto-generated method stub
		userDao.updateLoginPwdByUserId(userId, newLoginPwd);
		userCacheService.updateById(userId, "loginPwd", newLoginPwd);
	}

	/**
	 * 
	 * @author xiehao 2015年7月29日 下午2:45:00
	 * @Method: findLoginPwdToUpdateLoginPwd
	 * @Description: TODO 验证手机验证码，并修改用户密码
	 * @param mobile
	 * @param loginPwd
	 * @param validateCode
	 * @return
	 * @see com.need.api.service.user.UserService#findLoginPwdToUpdateLoginPwd(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public int findLoginPwdToUpdateLoginPwd(String mobile, String loginPwd, String validateCode) {

		String redisValidateCode = "";

		redisValidateCode = JedisSentinelClient.getString(mobile);
		if (StringUtil.isBlank(redisValidateCode)) {
			return BizReturnCode.CODE_FAILURE;
		}
		if (redisValidateCode.equals(validateCode)) {
			userDao.updatePwdToUpdateLoginPwd(mobile, loginPwd);
			return Message.SUCCESS; //
		} else {
			return BizReturnCode.CODE_ERROR;
		}

	}

	@Override
	public int checkMobileIsExist(String mobile) {
		// TODO Auto-generated method stub
		int mobileCount = userDao.mobileIsExist(mobile);
		return mobileCount;
	}

	/**
	 * @author shenyb 2015年7月29日 下午2:51:35
	 * @Method: checkMobileExists
	 * @Description: 校验手机号是否存在
	 * @param mobile
	 * @return
	 * @see com.need.api.service.user.UserService#checkMobileExists(java.lang.String)
	 */

	@Override
	public boolean checkMobileExists(String mobile) {
		return userDao.mobileIsExist(mobile) > 0;
	}

	/**
	 * @author shenyb 2015年7月29日 下午4:23:19
	 * @Method: checkUserIdExists
	 * @Description: TODO
	 * @param userId
	 * @return
	 * @see com.need.api.service.user.UserService#checkUserIdExists(java.lang.String)
	 */

	@Override
	public boolean checkUserIdExists(String userId) {
		return userDao.userIdExists(userId) > 0;
	}

	@Override
	public int sendRegistValidateCode(String mobile) {
		// TODO Auto-generated method stub
		String validateCode = null;
		String code = JedisSentinelClient.getString(mobile);// 验证码
		boolean validateCodeFlag = false;// 验证码存在标示
		if (code != null) {
			validateCode = code;
			validateCodeFlag = true;// 标记验证码已存在
		} else {
			validateCode = StringUtil.random(Constants.VALIDATE_CODE_LENGTH); // 生成一个随机验证码
																				// //modify
																				// by
																				// liyongran
																				// 20150902
																				// 修改验证码位数
		}
		String content = smsProConfig.getRegistSMSContent(); // 发送的内容 modify by
																// liyongran
																// 20150915
		content = content.replace("#", validateCode);
		content = content.replace("*", Constants.APP_TIME_OUT_MIN / 60 + "");
		int result = -1;
		try {
			result = smsService.distributeSMSService(content, mobile);// 短信提供者
			if (result == 0 && !validateCodeFlag) {
				JedisSentinelClient.setString(mobile, validateCode, Constants.APP_TIME_OUT_MIN);// 把验证码放在缓存里，失效时间30分钟;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} // 发送手机验证码

		return result;
	}

	/**
	 * @author shenyb 2015年8月5日 下午3:25:43
	 * @Method: getUserByMobileAndLoginPwd
	 * @Description: TODO
	 * @param mobile
	 * @param loginPwd
	 * @return
	 * @see com.need.api.service.user.UserService#getUserByMobileAndLoginPwd(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public List<UserBasePO> getUserByMobile(String mobile) {
		return userDao.selectByMobile(mobile);
	}

	@Override
	public List<KolInfoVo> getfocusKolList(String userId) {
		// TODO Auto-generated method stub
		List<KolInfoVo> focusKolList = userDao.selectFocusKolList(userId);
		return focusKolList;
	}

	@Override
	public List<KolVo> getKolList(String kolCategoryId) {
		// TODO Auto-generated method stub
		List<KolVo> kolList = userDao.queryKolList(kolCategoryId);
		return kolList;
	}

	@Override
	public String getcategoryNameList(String userId) {
		// TODO Auto-generated method stub
		List<KolCategoryPO> kolCategoryPO = userDao.querycategoryNameList(userId);
		StringBuilder sb = null;
		if (kolCategoryPO.get(0).getCategoryName() != null) {
			sb = new StringBuilder(kolCategoryPO.get(0).getCategoryName());
			for (int i = 1; i < (kolCategoryPO.size()); i++) {
				sb.append("," + kolCategoryPO.get(i).getCategoryName());
			}
		}
		return sb.toString();
	}

	@Override
	@Transactional
	public void updateProfileKey(String userId, String newProfileKey) {
		// TODO Auto-generated method stub
		userDao.updateProfileKey(userId, newProfileKey);
		userCacheService.updateById(userId, "profilePicKey", newProfileKey);
	}

	@Override
	public int insert(UserBasePO user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sendGeneralValidateCode(String mobile) {
		/** TODO Auto-generated method stub */
		String validateCode = null;
		String code = JedisSentinelClient.getString(mobile);// 验证码
		boolean validateCodeFlag = false;// 验证码存在标示
		if (code != null) {
			validateCode = code;
			validateCodeFlag = true;// 标记验证码已存在
		} else {
			validateCode = StringUtil.random(Constants.VALIDATE_CODE_LENGTH); // 生成一个随机验证码
																				// //modify
																				// by
																				// liyongran
																				// 20150902
																				// 修改验证码位数
		}
		String content = smsProConfig.getRegistSMSContent(); // 发送的内容
		content = content.replace("#", validateCode);
		content = content.replace("*", Constants.APP_TIME_OUT_MIN / 60 + "");

		int result = -1;
		try {
			result = smsService.distributeSMSService(content, mobile);// 短信提供者
			logger.info("result is :" + result);
			if (result == 0 && !validateCodeFlag) {
				JedisSentinelClient.setString(mobile, validateCode, Constants.APP_TIME_OUT_MIN);// 把验证码放在缓存里，失效时间30分钟;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} // 发送手机验证码

		return result;
	}

	@Override
	public List<FansInfoVO> getfansList(String userId) {
		// TODO Auto-generated method stub
		List<FansInfoVO> list = userDao.queryFans(userId);
		return list;
	}

	/**
	 * 
	 * @author peiboli 2015年11月25日 上午10:22:15
	 * @Method: loginByCode
	 * @Description: TODO通过验证码验证登录
	 * @param params
	 * @return
	 * @see com.need.api.service.user.UserService#loginByCode(com.need.api.web.controller.user.vo.LoginVO)
	 */
	@Override
	@Transactional
	public Message insertAndGetUserInfoByCode(LoginVO params) {
		// TODO Auto-generated method stub
		String mobile = params.getMobile();
		Message message = Message.success();
		// 获取验证码
		String redisValidateCode = JedisSentinelClient.getString(mobile);
		if (StringUtil.isBlank(redisValidateCode)) {
			return Message.error(BizReturnCode.CODE_FAILURE);
		}
		if (params.getValidateCode().equals(redisValidateCode)) {
			// 清除对应的验证码
			JedisSentinelClient.del(mobile);
			// 根据用户手机，查询用户信息
			UserBasePO user = userDao.getUserInfoByMobile(mobile);
			if (user != null) {
				if (UserStatusEnum.FREEZE.code.equals(user.getUserStatus())) {
					return Message.error(BizReturnCode.USER_STATUS_FREEZE);
				}
				UserInfoVO result = new UserInfoVO();
				BeanUtils.copyProperties(user, result);
				String userToken = BizCodeUtil.generateUserToken(user.getUserId());
				result.setUserToken(userToken);
				message.addData("user", result);
				
					JedisSentinelClient.setString(user.getUserId(), userToken,
							Constants.APP_USER_TOKEN_DAYS);
				
				return message;
			} else {
				UserBasePO registuser = new UserBasePO();
				String nickName = BizCodeUtil.generateUserNickName(mobile);
				registuser.setMobile(mobile);
				registuser.setNickName("Need" + nickName);
				registuser.setProfilePicKey(constantsProConfig.getDefaultProfilePicKey());
				registuser.setUserId(BizCodeUtil.generateUserId(nickName));
				registuser.setLoginPwd("");
				registuser.setUserStatus(UserStatusEnum.NORMAL.name());
				registuser.setUserType(UserTypeEnum.COMMON.name());

				int isSuccess = userDao.insert(registuser);
				if (isSuccess != 0) {
					// TODO 用户注册来源
					UserRegisterInfoPO userRegisterInfo = new UserRegisterInfoPO();
					userRegisterInfo.setUserId(registuser.getUserId());
					userRegisterInfo.setRegisterType(params.getType());
					userRegisterInfo.setRegisterChannel(params.getChannel());
					userRegisterInfoDAO.insert(userRegisterInfo);
					afterSave(registuser);
					UserInfoVO result = new UserInfoVO();
					BeanUtils.copyProperties(registuser, result);
					String userToken = BizCodeUtil.generateUserToken(registuser.getUserId());
					result.setUserToken(userToken);
					message.addData("user", result);
					// 设置用户对应的token 默认保存 15天
						JedisSentinelClient.setString(registuser.getUserId(), userToken,
								Constants.APP_USER_TOKEN_DAYS);
					
					return message;
				} else {

					return Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);
				}

			}

		} else {
			return Message.error(BizReturnCode.CODE_ERROR);
		}
	}
	/**
	 * 
	 * @author peiboli 2015年12月12日 下午3:35:34
	 * @Method: wapInsertAndGetUserInfoByCode 
	 * @Description: TODOwap快捷登录
	 * @param params
	 * @return 
	 * @see com.need.common.core.service.user.UserService#wapInsertAndGetUserInfoByCode(com.need.common.domain.vo.user.LoginVO)
	 */
	@Override
	@Transactional
	public Message wapInsertAndGetUserInfoByCode(LoginVO params) {
		// TODO Auto-generated method stub
		String openId= params.getOpenId();
		String mobile = params.getMobile();
		Message message = Message.success();
		// 获取验证码
		String redisValidateCode = JedisSentinelClient.getString(mobile);
		if (StringUtil.isBlank(redisValidateCode)) {
			return Message.error(BizReturnCode.CODE_FAILURE);
		}
		if (params.getValidateCode().equals(redisValidateCode)) {
			// 清除对应的验证码
			JedisSentinelClient.del(mobile);
			// 根据用户手机，查询用户信息
			UserBasePO user = userDao.getUserInfoByMobile(mobile);
			if (user != null) {
				if (UserStatusEnum.FREEZE.code.equals(user.getUserStatus())) {
					return Message.error(BizReturnCode.USER_STATUS_FREEZE);
				}
				UserInfoVO result = new UserInfoVO();
				BeanUtils.copyProperties(user, result);
				String userToken = BizCodeUtil.generateUserToken(user.getUserId());
				result.setUserToken(userToken);
				message.addData("user", result);
					JedisSentinelClient.setString(RedisKeyConstant.WAP_TOKEN_OF_USER.concat(user.getUserId()+"_"+params.getUserAreaId()), userToken,
							Constants.APP_USER_TOKEN_DAYS);
//				if(!StringUtil.isBlank(openId)){
//					userDao.deleteUserOpenIdByOpenId(openId);
//					userDao.updateUserOpenId(user.getUserId(),openId);
//				}
				return message;
			} else {
				UserBasePO registuser = new UserBasePO();
				String nickName = BizCodeUtil.generateUserNickName(mobile);
				registuser.setMobile(mobile);
				registuser.setNickName("Need" + nickName);
				registuser.setProfilePicKey(constantsProConfig.getDefaultProfilePicKey());
				registuser.setUserId(BizCodeUtil.generateUserId(nickName));
				registuser.setLoginPwd("");
				registuser.setUserStatus(UserStatusEnum.NORMAL.name());
				registuser.setUserType(UserTypeEnum.COMMON.name());

				int isSuccess = userDao.insert(registuser);
				if (isSuccess != 0) {
					// TODO 用户注册来源
					UserRegisterInfoPO userRegisterInfo = new UserRegisterInfoPO();
					userRegisterInfo.setUserId(registuser.getUserId());
					userRegisterInfo.setRegisterType(params.getType());
					userRegisterInfo.setRegisterChannel(params.getChannel());
					userRegisterInfoDAO.insert(userRegisterInfo);
					afterSave(registuser);
					UserInfoVO result = new UserInfoVO();
					BeanUtils.copyProperties(registuser, result);
					String userToken = BizCodeUtil.generateUserToken(registuser.getUserId());
					result.setUserToken(userToken);
					message.addData("user", result);
					// 设置用户对应的token 默认保存 15天
					JedisSentinelClient.setString(RedisKeyConstant.WAP_TOKEN_OF_USER.concat(registuser.getUserId()+"_"+params.getUserAreaId()), userToken,
							Constants.APP_USER_TOKEN_DAYS);
//					if(!StringUtil.isBlank(openId)){
//						userDao.deleteUserOpenIdByOpenId(openId);
//						userDao.updateUserOpenId(registuser.getUserId(),openId);
//					}
					return message;
				} else {

					return Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);
				}

			}

		} else {
			return Message.error(BizReturnCode.CODE_ERROR);
		}
	}
	/**
	 * 
	 * @author peiboli 2015年11月25日 下午5:30:06
	 * @Method: getUserLoginInfo
	 * @Description: TODO根据snsID获得用户登录的信息
	 * @param snsId
	 * @param snsType
	 * @return
	 * @see com.need.api.service.user.UserService#getUserLoginInfo(java.lang.String,
	 *      java.lang.String)
	 */
	@SuppressWarnings("unused")
	@Override
	public Message getUserLoginInfo(String snsId, String snsType) {
		UserInfoVO loginInfo;
		switch (snsType) {
		case "WEIBO":
			loginInfo = userDao.getWeiboLoginInfoBySNSId(snsId);
			break;
		case "QQ":
			loginInfo = userDao.getQQLoginInfoBySNSId(snsId);
			break;
		case "WEIXIN":
			loginInfo = userDao.getWeixinLoginInfoBySNSId(snsId);
			break;

		default:
			return Message.error(BizReturnCode.SNSTYPE_NOT_EXIST);
		}
		if (loginInfo == null) {
			return Message.error(BizReturnCode.NEED_BOUND_MOBILE);
		}
		if (UserStatusEnum.FREEZE.code.equals(loginInfo.getUserStatus())) {
			return Message.error(BizReturnCode.USER_STATUS_FREEZE);
		}
		String userToken = BizCodeUtil.generateUserToken(loginInfo.getUserId());
		loginInfo.setUserToken(userToken);
		Message message = Message.success();
		message.addData("user", loginInfo);
		JedisSentinelClient.setString(loginInfo.getUserId(), userToken, Constants.APP_USER_TOKEN_DAYS);
		return message;

	}

	/**
	 * 
	 * @author peiboli 2015年11月26日 上午10:56:10
	 * @Method: boundMobileAndGetLoginInfo
	 * @Description: TODO用户绑定手机号，并且返回用户登录信息
	 * @param snsId
	 * @param snsType
	 * @param mobile
	 * @param channel
	 * @param validateCode
	 * @return
	 * @see com.need.api.service.user.UserService#boundMobileAndGetLoginInfo(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Message boundMobileAndGetLoginInfo(String snsId, String snsType, String mobile, String channel,
			String validateCode) {
		// TODO Auto-generated method stub
		Message message = Message.success();
		// 获取验证码
		String redisValidateCode = JedisSentinelClient.getString(mobile);
		if (StringUtil.isBlank(redisValidateCode)) {
			return Message.error(BizReturnCode.CODE_FAILURE);
		}
		if (!validateCode.equals(redisValidateCode)) {
			return Message.error(BizReturnCode.CODE_ERROR);
		}
		// 清除对应的验证码
		JedisSentinelClient.del(mobile);
		// 根据用户手机，查询用户信息
		UserBasePO user = userDao.getUserInfoByMobile(mobile);
		if (user != null) {
			if (UserStatusEnum.FREEZE.code.equals(user.getUserStatus())) {
				return Message.error(BizReturnCode.USER_STATUS_FREEZE);
			}
			UserOauthLogin userOauthLogin = userOauthLoginDAO.getOauthInfo(user.getUserId());
			if (userOauthLogin == null) {
				if (snsType.equals(SnsTypeEnum.WEIXIN.code)) {
					userOauthLoginDAO.insertWeixin(user.getUserId(), snsId);
				}
				if (snsType.equals(SnsTypeEnum.WEIBO.code)) {
					userOauthLoginDAO.insertWeibo(user.getUserId(), snsId);
				}
				if (snsType.equals(SnsTypeEnum.QQ.code)) {
					userOauthLoginDAO.insertQq(user.getUserId(), snsId);
				}
			} else {
				if (snsType.equals(SnsTypeEnum.WEIXIN.code)) {
					if (StringUtil.isBlank(userOauthLogin.getWeixinPid())) {
						userOauthLoginDAO.updateWeixin(user.getUserId(), snsId);
					} else {
						return Message.error(BizReturnCode.WEIXIN_BOUND_EXIST);
					}
				}
				if (snsType.equals(SnsTypeEnum.WEIBO.code)) {
					if (StringUtil.isBlank(userOauthLogin.getWeiboPid())) {
						userOauthLoginDAO.updateWeibo(user.getUserId(), snsId);
					} else {
						return Message.error(BizReturnCode.WEIBO_BOUND_EXIST);
					}
				}
				if (snsType.equals(SnsTypeEnum.QQ.code)) {
					if (StringUtil.isBlank(userOauthLogin.getQqPid())) {
						userOauthLoginDAO.updateQq(user.getUserId(), snsId);
					} else {
						return Message.error(BizReturnCode.QQ_BOUND_EXIST);
					}
				}
			}
		} else {
			user = new UserBasePO();
			String nickName = BizCodeUtil.generateUserNickName(mobile);
			user.setMobile(mobile);
			user.setNickName("Need" + nickName);
			user.setProfilePicKey(constantsProConfig.getDefaultProfilePicKey());
			user.setUserId(BizCodeUtil.generateUserId(nickName));
			user.setLoginPwd("");
			user.setUserStatus(UserStatusEnum.NORMAL.name());
			user.setUserType(UserTypeEnum.COMMON.name());

			int isSuccess = userDao.insert(user);
			if (isSuccess != 0) {
				// TODO 用户注册来源
				UserRegisterInfoPO userRegisterInfo = new UserRegisterInfoPO();
				userRegisterInfo.setUserId(user.getUserId());
				userRegisterInfo.setRegisterType(RegisterTypeEnum.OAUTH.code);
				userRegisterInfo.setRegisterChannel(channel);
				userRegisterInfoDAO.insert(userRegisterInfo);

				if (snsType.equals(SnsTypeEnum.WEIXIN.code)) {
					userOauthLoginDAO.insertWeixin(user.getUserId(), snsId);
				}
				if (snsType.equals(SnsTypeEnum.WEIBO.code)) {
					userOauthLoginDAO.insertWeibo(user.getUserId(), snsId);
				}
				if (snsType.equals(SnsTypeEnum.QQ.code)) {
					userOauthLoginDAO.insertQq(user.getUserId(), snsId);
				}
				afterSave(user);
			} else {

				return Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);
			}

		}
		UserInfoVO result = new UserInfoVO();
		BeanUtils.copyProperties(user, result);
		String userToken = BizCodeUtil.generateUserToken(user.getUserId());
		result.setUserToken(userToken);
		message.addData("user", result);
		JedisSentinelClient.setString(user.getUserId(), userToken, Constants.APP_USER_TOKEN_DAYS);
		return message;

	}

	/**
	 * 
	 * @author peiboli 2015年11月26日 下午3:14:24
	 * @Method: boundOpenAuth
	 * @Description: TODO用户绑定手机号，并且返回用户登录信息
	 * @param snsId
	 * @param snsType
	 * @param userId
	 * @return
	 * @see com.need.api.service.user.UserService#boundOpenAuth(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public Message boundOpenAuth(String snsId, String snsType, String userId) {
		Message message = Message.success();
		UserOauthLogin userOauthLogin = userOauthLoginDAO.getOauthInfo(userId);
		if (userOauthLogin == null) {
			if (snsType.equals(SnsTypeEnum.WEIXIN.code)) {
				userOauthLoginDAO.insertWeixin(userId, snsId);
			}
			if (snsType.equals(SnsTypeEnum.WEIBO.code)) {
				userOauthLoginDAO.insertWeibo(userId, snsId);
			}
			if (snsType.equals(SnsTypeEnum.QQ.code)) {
				userOauthLoginDAO.insertQq(userId, snsId);
			}
		} else {
			if (snsType.equals(SnsTypeEnum.WEIXIN.code)) {
				if (StringUtil.isBlank(userOauthLogin.getWeixinPid())) {
					int isExist=userOauthLoginDAO.getCountByWeiXinId(snsId);
					if(isExist==0){
						userOauthLoginDAO.updateWeixin(userId, snsId);		
					}else{
						return Message.error(BizReturnCode.SNS_IS_EXIST);	
					}
				} else {
					return Message.error(BizReturnCode.WEIXIN_BOUND_EXIST);
				}
			}
			if (snsType.equals(SnsTypeEnum.WEIBO.code)) {
				if (StringUtil.isBlank(userOauthLogin.getWeiboPid())) {
					int isExist=userOauthLoginDAO.getCountByWeiBoId(snsId);
					if(isExist==0){
					userOauthLoginDAO.updateWeibo(userId, snsId);
					}else{
						return Message.error(BizReturnCode.SNS_IS_EXIST);	
					}
				} else {
					return Message.error(BizReturnCode.WEIBO_BOUND_EXIST);
				}
			}
			if (snsType.equals(SnsTypeEnum.QQ.code)) {
				if (StringUtil.isBlank(userOauthLogin.getQqPid())) {
					int isExist=userOauthLoginDAO.getCountByQQId(snsId);
					if(isExist==0){
					userOauthLoginDAO.updateQq(userId, snsId);
					}else{
						return Message.error(BizReturnCode.SNS_IS_EXIST);	
					}
				} else {
					return Message.error(BizReturnCode.QQ_BOUND_EXIST);
				}
			}
		}

		return message;
	}

	/**
	 * 
	 * @author peiboli 2015年11月26日 下午4:02:39
	 * @Method: unBound
	 * @Description: TODO：APP内解除第三方账号的绑定
	 * @param snsId
	 * @param snsType
	 * @param userId
	 * @return
	 * @see com.need.api.service.user.UserService#unBound(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public Message unBound(String snsType, String userId) {
		Message message = Message.success();
		if (snsType.equals(SnsTypeEnum.WEIXIN.code)) {
			userOauthLoginDAO.updateUnBoundWeixin(userId);
		}
		if (snsType.equals(SnsTypeEnum.WEIBO.code)) {
			userOauthLoginDAO.updateUnBoundWeibo(userId);
		}
		if (snsType.equals(SnsTypeEnum.QQ.code)) {
			userOauthLoginDAO.updateUnBoundQq(userId);
		}
		return message;
	}

	/**
	 * 
	 * @author peiboli 2015年11月26日 下午5:08:29
	 * @Method: getBoundList
	 * @Description: TODO获得用户第三方授权绑定列表
	 * @param userId
	 * @return
	 * @see com.need.api.service.user.UserService#getBoundList(java.lang.String)
	 */
	@Override
	public Message getBoundList(String userId) {
		Message message = Message.success();
		UserOauthLogin userOauthLogin = userOauthLoginDAO.getOauthInfo(userId);
		message.addData(SnsTypeEnum.WEIXIN.code, Boolean.FALSE.toString().toUpperCase());
		message.addData(SnsTypeEnum.WEIBO.code, Boolean.FALSE.toString().toUpperCase());
		message.addData(SnsTypeEnum.QQ.code, Boolean.FALSE.toString().toUpperCase());
		if (userOauthLogin != null) {
			if (!StringUtil.isBlank(userOauthLogin.getWeixinPid())) {
				message.addData(SnsTypeEnum.WEIXIN.code, Boolean.TRUE.toString().toUpperCase());
			}
			if (!StringUtil.isBlank(userOauthLogin.getWeiboPid())) {
				message.addData(SnsTypeEnum.WEIBO.code, Boolean.TRUE.toString().toUpperCase());
			}
			if (!StringUtil.isBlank(userOauthLogin.getQqPid())) {
				message.addData(SnsTypeEnum.QQ.code, Boolean.TRUE.toString().toUpperCase());
			}

		}
		return message;
	}

	/**
	 * 
	 * @author peiboli 2015年11月26日 下午5:33:40
	 * @Method: getcontactsBook
	 * @Description: TODO获得手机通讯录中的need的用户列表
	 * @param userId
	 * @param mobiles
	 * @return
	 * @see com.need.api.service.user.UserService#getcontactsBook(java.lang.String,
	 *      java.lang.String)
	 */

	@Override
	@Transactional
	public Message insertContactsBook(String userId, List<ContactsBookVO> list) {
		Message message = Message.success();
		UserBasePO paramsUser = userDao.getUserInfo(userId);
		Map<String, ContactsBookVO> contactsBookMap = new HashMap<String, ContactsBookVO>();
		for (ContactsBookVO contactUser : list) {
			if (isMobileNO(contactUser.getPhone()) && !paramsUser.getMobile().equals(contactUser.getPhone())) {
				contactsBookMap.put(contactUser.getPhone(), contactUser);
				userContactsDAO.insert(userId, contactUser);
			}
		}
		List<ContactsBookUserInfoVO> userList = userDao.queryByPhones(contactsBookMap.keySet());
		for (ContactsBookUserInfoVO user : userList) {
			user.setIsFollow(Boolean.FALSE.toString().toUpperCase());
			if (followCacheService.isFollow(userId, user.getUserId())) {
				user.setIsFollow(Boolean.TRUE.toString().toUpperCase());
			}
			ContactsBookVO contact = contactsBookMap.get(user.getMobile());
			if (contact != null) {
				user.setName(contact.getName());
			}

		}

		message.addData("userList", userList);
		return message;
	}

	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^1+[3|5|7|8]+\\d{9}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	@Override
	public Message updateUserSut(String userId) {
		Message success = Message.success();
		UserBasePO userPO= userDao.selectByPrimaryKey(userId);
        DistributionUserInfo distributionUserInfo=new DistributionUserInfo();
        BeanUtils.copyProperties(userPO, distributionUserInfo);
        if(StringUtils.isEmpty(userPO.getUserSut())){
        //sut为空，插入数据库
        String[] replaceUserId =ShortUrlUtil.shortUrl(userId);
        String sut=replaceUserId[new Random().nextInt(replaceUserId.length-1)];
        userDao.updateUserSut(userId,sut);
        JedisSentinelClient.setString(RedisKeyConstant.REPLACE_USER_ID.concat(sut), userId);
        distributionUserInfo.setSut(sut);
        }else{
        //不为空，从缓存获取	
        String userIdCache= JedisSentinelClient.getString(RedisKeyConstant.REPLACE_USER_ID.concat(userPO.getUserSut()));
        if(StringUtils.isEmpty(userIdCache)){
        	JedisSentinelClient.setString(RedisKeyConstant.REPLACE_USER_ID.concat(userPO.getUserSut()), userId);
         }
        distributionUserInfo.setSut(userPO.getUserSut());
        }
    	success.addData("user", distributionUserInfo);
    	return success;
	}

	@Override
	public String getUserIdBysut(String sut) {
	 String userIdCache= JedisSentinelClient.getString(RedisKeyConstant.REPLACE_USER_ID.concat(sut));
	   if(StringUtils.isEmpty(userIdCache)){
		 UserBasePO userPO= userDao.selectBysut(sut);
	     JedisSentinelClient.setString(RedisKeyConstant.REPLACE_USER_ID.concat(sut), userPO.getUserId());
	     return userPO.getUserId();
	    }    
		return userIdCache;
	}

	@Override
	public Message followSynchronization() {
		// TODO Auto-generated method stub
        List<NeedKolFansPO>  kolList= needkoldao.queryAll();
        for(NeedKolFansPO kol : kolList){
			if(!followCacheService.isFollow(kol.getUserId(),kol.getKolId())){
				followService.addFollow(kol.getUserId().trim(), kol.getKolId().trim());
			}
		}
		return Message.success();
	}
	

}

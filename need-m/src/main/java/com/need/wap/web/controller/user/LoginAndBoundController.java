/**
 * @ProjectName: need-wap
 * @Copyright: 2015 by Beijin Weplanter Technology co.,ltd.
 * @address: http://www.weplanter.com
 * @Description: 本内容仅限于稻田(北京)科技有限公司内部使用，禁止转发.
 * @author peiboli
 * @date: 2015年12月12日 上午10:29:10
 * @Title: LoginAndBoundController.java
 * @Package com.need.wap.web.controller.user
 * @Description: TODO
 */
package com.need.wap.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.user.UserBaseDAO;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.UserInfoVO;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
import com.need.utils.StringUtil;
import com.need.wap.constant.ControllerMappings;
import com.need.wap.web.controller.pub.CheckUserTokenController;

/**
 * <p></p>
 * @author peiboli 2015年12月12日 上午10:29:10
 * @ClassName LoginAndBoundController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年12月12日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.USER)
public class LoginAndBoundController {
	@Autowired
	private UserBaseDAO userDao;
	private static final Logger logger= Logger.getLogger(CheckUserTokenController.class);
	@ResponseBody
	@RequestMapping(value=ControllerMappings.CHECK_TOKEN_AND_OPENID,method = RequestMethod.GET)
	public Message checkUserToken(String openId,HttpServletRequest request){
		Message message= Message.success();
		String userToken = request.getHeader("User-Token");
		String userAreaId= request.getHeader("User-AreaId");
		
		if (StringUtil.isBlank(userToken) && StringUtil.isBlank(openId)) {
			//logger.error("the user userToken is null，return false!"); 
			logger.warn("the user token is null，return false!");
			message.addData("isEffective", Boolean.FALSE.toString().toUpperCase());
			return message;
		}
		if(!StringUtil.isBlank(userToken)){
			String userId=null;
			try {
				userId = BizCodeUtil.getUserIdByToken(userToken);
			} catch (Exception e) {
				/** TODO Auto-generated catch block */
				logger.info("token失效");
//				if(!StringUtil.isBlank(openId)){
//					UserInfoVO loginInfo = userDao.getWapLoginInfoByOpenId(openId);
//					if (loginInfo == null) {
//						message.addData("isEffective", Boolean.FALSE.toString().toUpperCase());	
//						return message;	
//					}
//					if (UserStatusEnum.FREEZE.code.equals(loginInfo.getUserStatus())) {
//						return Message.error(BizReturnCode.USER_STATUS_FREEZE);
//					}
//					String token = BizCodeUtil.generateUserToken(loginInfo.getUserId());
//					loginInfo.setUserToken(token);
//					message.addData("user", loginInfo);
//					JedisSentinelClient.setString(RedisKeyConstant.WAP_TOKEN_OF_USER.concat(loginInfo.getUserId()+"_"+userAreaId), token, Constants.APP_USER_TOKEN_DAYS);
//					message.addData("isEffective", Boolean.TRUE.toString().toUpperCase());
//				}else{
					message.addData("isEffective", Boolean.FALSE.toString().toUpperCase());	
//				}
				
				return message;
	            
			}
			// 从缓存中取得用户信息,并使用最新的token 
					String userTokenRedis = JedisSentinelClient.getString(RedisKeyConstant.WAP_TOKEN_OF_USER.concat(userId+"_"+userAreaId));
					if(userToken.equals(userTokenRedis)){
						UserBasePO userBasePO = userDao.getUserInfo(userId);
						UserInfoVO loginInfo= new UserInfoVO();
						BeanUtils.copyProperties(userBasePO, loginInfo);
						String token=  BizCodeUtil.generateUserToken(loginInfo.getUserId());
						loginInfo.setUserToken(token);
						JedisSentinelClient.setString(RedisKeyConstant.WAP_TOKEN_OF_USER.concat(loginInfo.getUserId()+"_"+userAreaId), token, Constants.APP_USER_TOKEN_DAYS);
						message.addData("user", loginInfo);
						message.addData("isEffective", Boolean.TRUE.toString().toUpperCase());
						return message;
					}else{
						
//						if(!StringUtil.isBlank(openId)){
//							UserInfoVO loginInfo = userDao.getWapLoginInfoByOpenId(openId);
//							if (loginInfo == null) {
//								message.addData("isEffective", Boolean.FALSE.toString().toUpperCase());	
//								return message;	
//							}
//							if(loginInfo!=null){
//								if (UserStatusEnum.FREEZE.code.equals(loginInfo.getUserStatus())) {
//									return Message.error(BizReturnCode.USER_STATUS_FREEZE);
//								}	
//							}
//							
//							String token = BizCodeUtil.generateUserToken(loginInfo.getUserId());
//							loginInfo.setUserToken(token);
//							message.addData("user", loginInfo);
//							JedisSentinelClient.setString(RedisKeyConstant.WAP_TOKEN_OF_USER.concat(loginInfo.getUserId()+"_"+userAreaId), token, Constants.APP_USER_TOKEN_DAYS);
//							message.addData("isEffective", Boolean.TRUE.toString().toUpperCase());
//						}else{
							message.addData("isEffective", Boolean.FALSE.toString().toUpperCase());	
//						}
						
						return message;
						
					}   
		}else{
//			if(!StringUtil.isBlank(openId)){
//				UserInfoVO loginInfo = userDao.getWapLoginInfoByOpenId(openId);
//				if (loginInfo == null) {
//					message.addData("isEffective", Boolean.FALSE.toString().toUpperCase());
//					return message;	
//				}
//				if (UserStatusEnum.FREEZE.code.equals(loginInfo.getUserStatus())) {
//					return Message.error(BizReturnCode.USER_STATUS_FREEZE);
//				}
//				String token = BizCodeUtil.generateUserToken(loginInfo.getUserId());
//				loginInfo.setUserToken(token);
//				message.addData("user", loginInfo);
//				JedisSentinelClient.setString(RedisKeyConstant.WAP_TOKEN_OF_USER.concat(loginInfo.getUserId()+"_"+userAreaId), token, Constants.APP_USER_TOKEN_DAYS);
//				message.addData("isEffective", Boolean.TRUE.toString().toUpperCase());
//			}else{
				message.addData("isEffective", Boolean.FALSE.toString().toUpperCase());	
//			}
			return message;	
		}
		
		
	}

}

package com.need.service.bops.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.need.biz.utils.BizCodeUtil;
import com.need.dao.bops.auth.BopsAuthDAO;
import com.need.dao.bops.role.BopsRoleDAO;
import com.need.dao.bops.user.BopsUserDAO;
import com.need.domain.po.bops.auth.BopsAuth;
import com.need.domain.po.bops.role.BopsRole;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.vo.user.QueryBopsUserVO;
import com.need.framework.utils.PropertiesUtil;
import com.need.jedis.JedisClient;
import com.need.jedis.JedisSentinelClient;
import com.need.service.bops.user.BopsUserService;
import com.need.service.constant.Constants;
import com.need.service.constant.ConstantsTimeOut;
import com.need.utils.MD5Util;

@Service
public class BopsUserServiceImpl implements BopsUserService {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private BopsUserDAO bopsUserDAO;
	@Autowired
	private BopsRoleDAO bopsRoleDAO;
	@Autowired
	private BopsAuthDAO bopsAuthDAO;

	private final static Integer TIME_OUT = ConstantsTimeOut.BOPSUSER_EXPIRE_TIME;

	@Override
	public boolean loginService(String username, String password, StringBuffer bopsUserKey,BopsUser user) {
		// TODO Auto-generated method stub
		// 管理员信息
		String adminName = PropertiesUtil.getProperty(Constants.CONSTANTS_PROPERTIES, "userName");
		String adminPwd = PropertiesUtil.getProperty(Constants.CONSTANTS_PROPERTIES, "userPwd");
		if (!username.equals(adminName)) {// 非管理员处理逻辑
			BopsUser tmpUser = bopsUserDAO.selectByUserName(username);
			logger.info(tmpUser);
			if (tmpUser == null) {
				return false;
			}
			if (tmpUser.getUserPwd().equals(password)) {
				BeanUtils.copyProperties(tmpUser, user);
				bopsUserKey.append(this.saveBopsUserInfoToRedis(user));
				return true;
			}
		} else {// 管理员
			logger.info("needAdmin will login ..");
			if (password.equals(adminPwd)) {
				logger.info("Welcome needAdmin login ..");
				user.setUserName("need超级管理员");
				user.setUserId(0);
				user.setUserEmail("it@weplanter.com");
				user.setUserName("needAdmin");
				user.setUserDept("管理部");
				user.setUserPwd(adminPwd);
				bopsUserKey.append(this.saveBopsUserInfoToRedis(user));
				return true;
			}
		}

		return false;
	}

	@Override
	public BopsUser getBopsUserInfoById(Integer id) {
		// TODO Auto-generated method stub
		return bopsUserDAO.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public BopsUser addBopsUserInfo(BopsUser bopsUser) {
		// TODO Auto-generated method stub
		BopsUser user = getBopsUserByUserName(bopsUser.getUserName());
		BopsUser user2 = new BopsUser();
		user2.setUserId(-1);
		if (user != null) {
			return user2;
		}
        //用户密码md5加密放到服务端
        String userPwd = bopsUser.getUserPwd();
        if(userPwd.length() < 16) {
            bopsUser.setUserPwd(MD5Util.MD5Encode(userPwd));
        }
		bopsUserDAO.insert(bopsUser);
		return bopsUser;
	}

	@Override
	@Transactional
	public String saveBopsUserInfoToRedis(BopsUser bopsUser) {
		// TODO Auto-generated method stub
		String keyName = BizCodeUtil.generateUserToken(bopsUser.getUserId().toString());
		JedisSentinelClient.setObject(keyName, bopsUser, TIME_OUT);
		return keyName;
	}

	@Override
	public BopsUser getBopsUserByUserName(String name) {
		// TODO Auto-generated method stub
		return bopsUserDAO.selectByUserName(name);
	}

	@Override
	@Transactional
	public String saveBopsUserByUserNameToRedis(String userName) {
		// TODO Auto-generated method stub
		BopsUser bopsUser = this.getBopsUserByUserName(userName);
		return this.saveBopsUserInfoToRedis(bopsUser);
	}

	/**
	 * @author shenyb 2015年8月1日 上午10:32:21
	 * @Method: updateToken
	 * @Description: TODO
	 * @param bopsUserByUserName
	 * @see com.need.bops.service.user.BopsUserService#updateToken(com.need.bops.dao.jdbc.user.po.BopsUser)
	 */

	@Override
	@Transactional
	public void updateToken(BopsUser bopsUser) {
		bopsUserDAO.updateUserToken(bopsUser);
	}

	/**
	 * @author shenyb 2015年8月1日 上午11:13:55
	 * @Method: getBopsUserByToken
	 * @Description: TODO
	 * @param token
	 * @return
	 * @see com.need.bops.service.user.BopsUserService#getBopsUserByToken(java.lang.String)
	 */

	@Override
	public BopsUser getBopsUserByToken(String token) {
		return bopsUserDAO.getBopsUserByToken(token);
	}

	/**
	 * @author shenyb 2015年8月1日 下午3:28:30
	 * @Method: getBopsUserAuths
	 * @Description: TODO
	 * @param token
	 * @return
	 * @see com.need.bops.service.user.BopsUserService#getBopsUserAuths(java.lang.String)
	 */

	@Override
	public List<BopsAuth> getBopsUserAuths(BopsUser user) {
		List<BopsAuth> auths = new ArrayList<BopsAuth>();
		Set<String> authIds = new HashSet<String>();
		if (user == null) {
			return auths;
		}
		// 超级管理员，返回所有权限
		if (user.getUserId() == 0) {
			return bopsAuthDAO.getAll();
		}
		BopsRole role = new BopsRole();
		for (String roleId : user.getRoleIds().replaceAll("[\\[\\]\"]", "").split(",")) {
			try {

				role = bopsRoleDAO.selectByPrimaryKey(Integer.valueOf(roleId));
				for (String authId : role.getAuthIds().replaceAll("[\\[\\]\"]", "").split(",")) {
					authIds.add(authId);
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		for (String authId : authIds) {
			auths.add(bopsAuthDAO.selectByPrimaryKey(Integer.valueOf(authId)));
		}
		return auths;
	}

	@Override
	@Transactional
	public void updateBospUserInfo(BopsUser user) {
		// TODO Auto-generated method stub
		bopsUserDAO.updateByPrimaryKey(user);
	}

	@Override
	@Transactional
	public void deleteBospUserById(Integer userId) {
		// TODO Auto-generated method stub
		bopsUserDAO.deleteByPrimaryKey(userId);
	}

	/**
	 * 
	 * @author xiehao 2015年8月4日 下午3:13:22
	 * @Method: getPageOfBopsUser
	 * @Description: TODO 获得用户的分页数据
	 * @param pageBopsUserVO
	 * @return
	 * @see com.need.bops.service.user.BopsUserService#getPageOfBopsUser(com.need.bops.web.controller.vo.user.PageBopsUserVO)
	 */
	@Override
	public List<BopsUser> getPageOfBopsUser(QueryBopsUserVO queryBopsUserVO) {
		// TODO Auto-generated method stub
		if (StringUtils.hasText(queryBopsUserVO.getUserName()) || StringUtils.hasText(queryBopsUserVO.getUserDept())) {
			queryBopsUserVO.setTotal(bopsUserDAO.queryCountBospUser(queryBopsUserVO));
		} else {
			queryBopsUserVO.setTotal(bopsUserDAO.countBospUser());
		}
		return bopsUserDAO.getPageOfBopsUser(queryBopsUserVO);
	}

	@Override
	public List<BopsUser> queryBopsUser(QueryBopsUserVO queryBopsUser) {
		// TODO Auto-generated method stub
		return bopsUserDAO.queryBopsUer(queryBopsUser);
	}

	@Override
	public List<BopsAuth> getBopsAuthsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		// String[] roleIds = user.getRoleIds().split(",");
		BopsUser user = bopsUserDAO.selectByPrimaryKey(userId);
		String roleIds = user.getRoleIds();
		logger.info("roleIds :" + roleIds);
		List<BopsRole> roles = bopsRoleDAO.selectBopsRoleByPrimaryKeys(roleIds);
		if (roles == null) {
			return null;
		}
		StringBuffer authIds = new StringBuffer();
		for (int i = 0; i < roles.size(); i++) {
			authIds.append(roles.get(i).getAuthIds());
			if (i != roles.size() - 1) {
				authIds.append(",");
			}
		}
		logger.info("authIds :" + authIds);
		return bopsAuthDAO.selectBopsAuthsByPrimaryKeys(authIds.toString());
	}

	@Override
	public List<String> getAuthScopesByUserId(Integer userId) {
		// TODO Auto-generated method stub
		BopsUser user = bopsUserDAO.selectByPrimaryKey(userId);
		String roleIds = user.getRoleIds();
		logger.info("roleIds :" + roleIds);
		roleIds = roleIds.replace("[", "").replace("]", "");
		logger.info("roleIds :" + roleIds);
		List<String> authIdList = bopsRoleDAO.selectAuthIdsByPrimaryKeys(roleIds);
		if (authIdList == null) {
			return null;
		}
		StringBuffer authIds = new StringBuffer();
		for (int i = 0; i < authIdList.size(); i++) {
			String authIdstr = authIdList.get(i).replace("[", "").replace("]", "");
			authIds.append(authIdstr);
			if (i != authIdList.size() - 1) {
				authIds.append(",");
			}
		}

		return bopsAuthDAO.selectAuthScopesByPrimaryKeys(authIds.toString());
	}

	@Override
	public List<BopsRole> getBopsRolesByRoleId(String roles) {
		/** TODO Auto-generated method stub */
		// bopsRoleDAO
		return bopsRoleDAO.selectAuthByPrimaryKeys(roles);
	}

	@Override
	public List<String> getAuthScopesByRoleIds(String roleIds) {
		/** TODO Auto-generated method stub */
		roleIds = roleIds.replace("[", "").replace("]", "");
		List<String> authIdList = bopsRoleDAO.selectAuthIdsByPrimaryKeys(roleIds);
		if (authIdList == null) {
			return null;
		}
		StringBuffer authIds = new StringBuffer();
		for (int i = 0; i < authIdList.size(); i++) {
			String auth_Ids = authIdList.get(i).replace("[", "").replace("]", "");
			authIds.append(auth_Ids);
			if (i != authIdList.size() - 1) {
				authIds.append(",");
			}
		}

		return bopsAuthDAO.selectAuthScopesByPrimaryKeys(authIds.toString());
	}

    @Override
    public int updateBospUserPwd(Integer userId, String oldPwd, String newPwd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("oldPwd", MD5Util.MD5Encode(oldPwd));
        param.put("newPwd", MD5Util.MD5Encode(newPwd));
        return bopsUserDAO.updatePwdByPrimaryKey(param);
    }

}
